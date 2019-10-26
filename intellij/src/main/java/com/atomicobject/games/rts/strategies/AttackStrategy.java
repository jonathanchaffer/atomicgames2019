package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
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
        if(!map.canMove(unit.getLocation(), direction)) {
            generalDirection = MapDirections.turn(generalDirection);
        }
        if (unit.isTank()) {
            if(map.hasEnemies()) {
                var enemies = map.enemyLocationsInRange(unit.getLocation(), 2);
                if (enemies.size() == 0) {
                    return AICommand.buildMoveCommand(unit, MapDirections.Direction.NONE);
                }
                else {
                    return AICommand.buildShootCommand(unit, enemies.get(0));
                }
            }
            else {
                return AICommand.buildMoveCommand(unit, MapDirections.Direction.NONE);
            }
        }
        else {
            return AICommand.buildMeleeCommand(unit, MapDirections.Direction.EAST);
        }
    }



}
