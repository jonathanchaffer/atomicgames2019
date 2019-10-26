package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.mapping.Pathfinder;
import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.state.UnitManager;

import java.util.Random;

public class GatherStrategy implements IUnitStrategy {
    private final Map map;
    private final UnitManager unitManager;
    private final MapDirections.Direction generalDirection;

    public GatherStrategy(Map map, Unit unit, UnitManager unitManager) {
        this.map = map;
        this.unitManager = unitManager;
        this.generalDirection = MapDirections.randomDirection();
    }

    public AICommand buildCommand(Unit unit) {
        var pathfinder = new Pathfinder(map);
        if (unit.isCarryingResource()) {
            var path = pathfinder.findPath(unit.getLocation(), map.homeBaseLocation(), 1);
            if (path != null) {
                var destination = path.get(0);
                var direction = MapDirections.cardinalDirection(unit.getLocation(), destination);
                return AICommand.buildMoveCommand(unit, direction);
            }
        }
        if (map.hasResources()) {
            if (map.isResourceAdjacentTo(unit.getLocation())) {
                return AICommand.buildGatherCommand(unit, map.directionToAdjacentResource(unit.getLocation()));
            }
            var nearestResourceLocaiton = map.resourceLocationsNearest(unit.getLocation()).get(0);
            var path = pathfinder.findPath(unit.getLocation(), nearestResourceLocaiton, 1);
            if (path != null) {
                var destination = path.get(0);
                var direction = MapDirections.cardinalDirection(unit.getLocation(), destination);
                return AICommand.buildMoveCommand(unit, direction);
            }
        }
        // go in the general direction according to odds
        var direction = MapDirections.randomDirection();
        var odds = new int[]{1,1,1,1,2,2,2,3,3,3,4,4};
        var random = new Random();
        var num = odds[random.nextInt(odds.length)];
        if (num == 1) direction = generalDirection; // turn 0
        else if (num == 2) direction = MapDirections.turn(generalDirection); // turn 1
        else if (num == 3) direction = MapDirections.turn(MapDirections.turn(MapDirections.turn(generalDirection))); // turn 3
        else direction = MapDirections.turn(MapDirections.turn(generalDirection)); // turn 2
        return AICommand.buildMoveCommand(unit, direction);
    }
}