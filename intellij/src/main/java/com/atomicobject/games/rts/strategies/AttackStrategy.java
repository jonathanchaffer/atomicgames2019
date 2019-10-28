package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.mapping.Pathfinder;
import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.state.UnitManager;
import com.atomicobject.games.rts.updates.Location;
import com.atomicobject.games.rts.state.Unit;

public class AttackStrategy implements IUnitStrategy {
    private final Map map;
    private final UnitManager unitManager;
    private MapDirections.Direction generalDirection;

    public AttackStrategy(Map map, Unit unit, UnitManager unitManager) {
        this.map = map;
        this.unitManager = unitManager;
        this.generalDirection = MapDirections.randomDirection();
    }

    public AICommand buildCommand(Unit unit) {
        var direction = IUnitStrategy.getDirectionForGeneralDirection(generalDirection);
        var pathfinder = new Pathfinder(map);
        if (!map.canMove(unit.getLocation(), direction)) {
            generalDirection = MapDirections.turn(generalDirection);
        }
        if (unit.isTank()) {
            var enemies = map.enemyLocationsInRange(unit.getLocation(), 2);
            if (map.enemyBaseFound()) {
                var path = pathfinder.findPath(unit.getLocation(), map.enemyBaseLocation(), 2);
                if (map.locationWithinRange(unit.getLocation(), map.enemyBaseLocation(), 2)) {
                    var base = map.enemyBaseLocation();
                    return AICommand.buildShootCommand(unit, base);
                }
                else {
                    if (path != null) {
                        var destination = path.get(0);
                        direction = MapDirections.cardinalDirection(unit.getLocation(), destination);
                    }
                    if (enemies.size() > 0) {
                        return AICommand.buildShootCommand(unit, enemies.get(0));
                    }
                    else {
                        return AICommand.buildMoveCommand(unit, direction);
                    }
                }
            } else {
                return AICommand.buildMoveCommand(unit, direction);
            }
        }
        else {
            return AICommand.buildMeleeCommand(unit, MapDirections.Direction.EAST);
        }

    }

}
