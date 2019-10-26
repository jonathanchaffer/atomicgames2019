package com.atomicobject.games.rts.mapping;

import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.updates.Location;

import java.util.*;
import java.util.stream.Collectors;

public class Pathfinder {
    private static class Node {
        private int knownCostToStart;
        private int estimatedCostToEnd;
        private Location location;
        private Node parent;

        Node(Location location) {
            this.location = location;
        }

        int getKnownCostToStart() {
            return knownCostToStart;
        }

        void setKnownCostToStart(int knownCostToStart) {
            this.knownCostToStart = knownCostToStart;
        }

        void setEstimatedCostToEnd(int estimatedCostToEnd) {
            this.estimatedCostToEnd = estimatedCostToEnd;
        }

        Location getLocation() {
            return location;
        }

        Node getParent() {
            return parent;
        }

        void setParent(Node parent) {
            this.parent = parent;
        }

        int getEstimatedTotalPathCost() {
            return knownCostToStart + estimatedCostToEnd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return location.equals(node.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(location);
        }
    }

    static class EstimatedTotalPathCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node node1, Node node2) {
            return node1.getEstimatedTotalPathCost() - node2.getEstimatedTotalPathCost();
        }
    }

    private com.atomicobject.games.rts.state.Map map;
    private List<Node> openNodes;
    private List<Node> closedNodes;
    private List<Location> walkableDirections = new ArrayList<>() {{
        add(new Location(0, -1));
        add(new Location(-1, 0));
        add(new Location(1, 0));
        add(new Location(0, 1));
    }};
    private final EstimatedTotalPathCostComparator estimatedTotalPathCostComparator = new EstimatedTotalPathCostComparator();

    public Pathfinder(Map map) {
        this.map = map;
    }

    public List<Location> findPathToAdjacentTile(Location start, Location destination) {
        return findPath(start, destination, 1);
    }

    public List<Location> findPath(Location start, Location destination, int closeEnough) {
        initializeNodeLists(start, destination);

        while (openNodes.size() > 0) {
            var node = dequeueMostPromisingOpenNode();

            if (reachedDestination(node.getLocation(), destination, closeEnough)) {
                /* We found a path - build a list of locations from the node tree. */
                return buildPathFromLocationToNode(start, node);
            }

            for (Node neighbor : walkableNeighbors(node)) {
                walkNeighbor(node, neighbor, destination);
            }
        }

        /* No path found. */
        return null;
    }

    private boolean reachedDestination(Location location, Location destination, int closeEnough) {
        return map.calculateEstimatedDistance(location, destination) <= closeEnough;
    }

    private List<Location> buildPathFromLocationToNode(Location location, Node node) {
        var path = new ArrayList<Location>();
        var current = node;
        while (current.getLocation() != location) {
            path.add(current.getLocation());
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    private void walkNeighbor(Node parent, Node neighbor, Location destination) {
        if (openNodes.contains(neighbor)) {
            updateOpenNode(parent, neighbor);
        } else {
            addNewOpenNode(parent, neighbor, destination);
        }
    }

    private void updateOpenNode(Node parent, Node neighbor) {
        int costToStart = parent.getKnownCostToStart() + 1;
        if (costToStart < neighbor.getKnownCostToStart()) {
            neighbor.setKnownCostToStart(costToStart);
            neighbor.setParent(parent);
        }
    }

    private void addNewOpenNode(Node parent, Node neighbor, Location destination) {
        neighbor.setKnownCostToStart(parent.getKnownCostToStart() + 1);
        neighbor.setEstimatedCostToEnd(map.calculateEstimatedDistance(neighbor.getLocation(), destination));
        neighbor.setParent(parent);
        openNodes.add(neighbor);
    }

    private void initializeNodeLists(Location origin, Location destination) {
        openNodes = new ArrayList<>();
        closedNodes = new ArrayList<>();

        var start = new Node(origin);
        start.setKnownCostToStart(0);
        start.setEstimatedCostToEnd(map.calculateEstimatedDistance(origin, destination));
        openNodes.add(start);
    }

    private Node dequeueMostPromisingOpenNode() {
        openNodes.sort(estimatedTotalPathCostComparator);
        var node = openNodes.remove(0);
        closedNodes.add(node);
        return node;
    }

    private List<Node> walkableNeighbors(Node node) {
        return walkableDirections
                .stream()
                .map(offset -> walkableNeighborAt(node.getLocation().getX() + offset.getX(), node.getLocation().getY() + offset.getY()))
                .filter(Objects::nonNull)
                .filter(neighbor -> !closedNodes.contains(neighbor))
                .collect(Collectors.toList());
    }

    private Node walkableNeighborAt(int x, int y) {
        var location = new Location(x, y);
        return map.getTile(location).isWalkable() ? new Node(location) : null;
    }
}