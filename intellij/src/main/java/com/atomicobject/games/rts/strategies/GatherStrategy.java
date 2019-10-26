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
    private MapDirections.Direction generalDirection;

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
        var direction = IUnitStrategy.getDirectionForGeneralDirection(generalDirection);
        if (!map.canMove(unit.getLocation(), direction)) {
            generalDirection = MapDirections.turn(generalDirection);
        }
        return AICommand.buildMoveCommand(unit, direction);
    }
}