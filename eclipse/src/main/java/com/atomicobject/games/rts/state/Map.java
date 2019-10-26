package com.atomicobject.games.rts.state;

import com.atomicobject.games.rts.updates.Location;
import com.atomicobject.games.rts.updates.TileUpdate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Map {

    private static final Location HOME_BASE_LOCATION = new Location(0, 0);
    private int mapWidth;
    private int mapHeight;
    private HashMap<Location, Tile> tiles = new HashMap<>();

    public void setSize(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void updateTiles(List<TileUpdate> tileUpdates) {

        tileUpdates.forEach(t -> {
            getTile(t.getLocation()).setTileUpdate(t);
        });
    }

    public Tile getTile(Location location) {

        if (!tiles.containsKey(location)) {
            tiles.put(location, new Tile(location));
        }
        return tiles.get(location);
    }

    public List<Location> buildNeighborLocationList(int range) {
        var xLocations = IntStream.rangeClosed(-range, range);
        return xLocations
                .mapToObj(x -> buildLocationsForRange(x, range))
                .flatMap(o -> o)
                .filter(l -> l.getX() != 0 || l.getY() != 0)
                .collect(Collectors.toList());
    }

    private Stream<Location> buildLocationsForRange(int x, int range) {
        var yLocations = IntStream.rangeClosed(-range, range);
        return yLocations.mapToObj(y -> new Location(x, y));
    }

    public List<Tile> neighbors(Location target, int range) {
        return buildNeighborLocationList(range)
                .stream()
                .map(l -> getTileAtOffset(target, l))
                .collect(Collectors.toList());
    }

    public List<Tile> neighbors(Location location) {
        return neighbors(location, 1);
    }

    private Tile getTileAtOffset(Location target, Location offset) {
        var location = new Location(target.getX() + offset.getX(), target.getY() + offset.getY());
        return getTile(location);
    }

    public int calculateEstimatedDistance(Location from, Location to) {
        return Math.abs(to.getX() - from.getX()) + Math.abs(to.getY() - from.getY());
    }

    public boolean hasUnknownNeighbors(Location location, int range) {
        return neighbors(location, range).stream().anyMatch(tile -> tile.isUnknown());
    }

    public boolean canMove(Location location, MapDirections.Direction direction) {
        return neighborInDirection(location, direction).isWalkable();
    }

    public MapDirections.Direction directionToAdjacentResource(Location location) {
        var direction = MapDirections.DIRECTIONS
                .stream()
                .filter(d -> neighborInDirection(location, d).hasResource())
                .findFirst();
        return direction.isPresent() ? direction.get() : MapDirections.Direction.NONE;
    }

    private Tile neighborInDirection(Location location, MapDirections.Direction direction) {
        return getTileAtOffset(location, MapDirections.offsetForDirection(direction));
    }

    public boolean isResourceAdjacentTo(Location location) {
        return directionToAdjacentResource(location) != MapDirections.Direction.NONE;
    }

    class NearestTileComparator implements Comparator<Tile> {

        private final Location origin;

        public NearestTileComparator(Location location) {
            this.origin = location;
        }

        @Override
        public int compare(Tile one, Tile two) {
            var distanceOne = calculateEstimatedDistance(origin, one.getLocation());
            var distanceTwo = calculateEstimatedDistance(origin, two.getLocation());
            if (distanceOne < distanceTwo) return -1;
            if (distanceOne == distanceTwo) return 0;
            return 1;
        }
    }

    public List<Location> resourceLocationsNearest(Location location) {
        var comparator = new NearestTileComparator(location);
        return allResourceTiles()
                .stream()
                .sorted(comparator)
                .map(t -> t.getLocation())
                .collect(Collectors.toList());
    }

    public boolean hasResources() {
        return tiles.values().stream().anyMatch(t -> t.hasResource());
    }

    private List<Tile> allResourceTiles() {
        return tiles.values()
                .stream()
                .filter(t -> t.hasResource())
                .collect(Collectors.toList());
    }

    public Location homeBaseLocation() {
        return HOME_BASE_LOCATION;
    }

    public Location enemyBaseLocation() {
        var enemyBase = tiles.values().stream().filter(t -> t.hasEnemyBase()).findFirst();
        return enemyBase.isPresent() ? enemyBase.get().getLocation() : null;
    }

    public boolean hasEnemies() {
        return tiles.values().stream().anyMatch(t -> t.hasEnemies());
    }

    public boolean enemyBaseFound() {
        return enemyBaseLocation() != null;
    }

    public boolean locationWithinRange(Location from, Location to, int range) {
        return Math.abs(to.getX() - from.getX()) <= range
                && Math.abs(to.getY() - from.getY()) <= range;
    }

    public List<Location> enemyLocationsInRange(Location start, int range)
    {
        return neighbors(start, range)
                .stream()
                .filter(t -> t.isVisible())
                .filter(t -> t.hasEnemies())
                .map(t -> t.getLocation())
                .collect(Collectors.toList());
    }

	public int getMapWidth() {
		return mapWidth;
	}
	
	public int getMapHeight() {
		return mapHeight;
	}
}
