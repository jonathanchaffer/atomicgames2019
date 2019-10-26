package com.atomicobject.games.rts.state;

import com.atomicobject.games.rts.updates.Location;

import java.util.*;

public class MapDirections {

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        NONE
    }

    private final static Random Random = new Random();

    public final static List<Direction> DIRECTIONS = Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

    private final static HashMap<Direction, Direction> TURN_MAP = new HashMap<>() {{
        put(Direction.NORTH, Direction.EAST);
        put(Direction.EAST, Direction.SOUTH);
        put(Direction.SOUTH, Direction.WEST);
        put(Direction.WEST, Direction.NORTH);
    }};

    private final static HashMap<Direction, Location> OFFSET_MAP = new HashMap<>() {{
        put(Direction.NORTH, new Location(0, -1));
        put(Direction.EAST, new Location(1, 0));
        put(Direction.SOUTH, new Location(0, 1));
        put(Direction.WEST, new Location(-1, 0));
        put(Direction.NONE, new Location(0, 0));
    }};

    public static Direction cardinalDirection(Location location, Location destination) {
        if (destination.getY() < location.getY()) return Direction.NORTH;
        if (destination.getY() > location.getY()) return Direction.SOUTH;
        if (destination.getX() > location.getX()) return Direction.EAST;
        return Direction.WEST;
    }

    public static Direction randomDirection() {
        return DIRECTIONS.get(Random.nextInt(4));
    }

    public static Direction turn(Direction direction) {
        return TURN_MAP.get(direction);
    }

    public static Location offsetForDirection(Direction direction) {
        return OFFSET_MAP.get(direction);
    }
}