package com.atomicobject.games.rts.communication;

import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.updates.Location;

import java.util.HashMap;

public class AICommand {

    public static String MOVE = "MOVE";
    public static String GATHER = "GATHER";
    public static String CREATE = "CREATE";
    public static String SHOOT = "SHOOT";
    public static String MELEE = "MELEE";
    public static String IDENTIFY = "IDENTIFY";

    public static String NORTH = "N";
    public static String EAST = "E";
    public static String SOUTH = "S";
    public static String WEST = "W";

    private static HashMap<MapDirections.Direction, String> DirectionLookup = new HashMap<MapDirections.Direction, String>() {{
        put(MapDirections.Direction.NORTH, NORTH);
        put(MapDirections.Direction.SOUTH, SOUTH);
        put(MapDirections.Direction.EAST, EAST);
        put(MapDirections.Direction.WEST, WEST);
    }};

    public String command;
    public int unit;
    public String dir;
    public String type;
    public int dx;
    public int dy;
    public int target;


    public static AICommand buildMoveCommand(Unit unit, MapDirections.Direction direction) {
        var command = new AICommand();
        command.setCommand(AICommand.MOVE);
        command.setUnit(unit.getId());
        command.setDir(serializeDirection(direction));
        return command;
    }

    public static AICommand buildGatherCommand(Unit unit, MapDirections.Direction direction) {
        var command = new AICommand();
        command.setCommand(AICommand.GATHER);
        command.setUnit(unit.getId());
        command.setDir(serializeDirection(direction));
        return command;
    }

    public static AICommand buildUnitCommand(String type) {
        var command = new AICommand();
        command.setCommand(AICommand.CREATE);
        command.setType(type);
        return command;
    }

    public static AICommand buildShootCommand(Unit unit, Location location) {
        var command = new AICommand();
        command.setCommand(AICommand.SHOOT);
        command.setUnit(unit.getId());
        command.setDx(location.getX() - unit.getLocation().getX());
        command.setDy(location.getY() - unit.getLocation().getY());
        return command;
    }

    public static String serializeDirection(MapDirections.Direction direction) {
        return DirectionLookup.get(direction);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
