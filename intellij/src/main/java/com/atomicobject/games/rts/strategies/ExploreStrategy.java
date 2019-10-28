package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.state.UnitManager;
import com.atomicobject.games.rts.state.Tile;

import java.util.Random;

public class ExploreStrategy implements IUnitStrategy {
    private final Map map;
    private final UnitManager unitManager;
    private MapDirections.Direction generalDirection;

    public ExploreStrategy(Map map, Unit unit, UnitManager unitManager) {
        this.map = map;
        this.unitManager = unitManager;
        this.generalDirection = MapDirections.randomDirection();
    }

    public AICommand buildCommand(Unit unit) {
        var direction = IUnitStrategy.getDirectionForGeneralDirection(generalDirection);
        if (!map.canMove(unit.getLocation(), direction)) {
            generalDirection = MapDirections.turn(generalDirection);
        }
        if (!unit.isScout()) {
            return AICommand.buildMoveCommand(unit, direction);
        }
        else {
            if (map.hasUnknownNeighbors(unit.getLocation(), 5)) {
                var tiles = map.buildNeighborLocationList(5);
                for (int i = 0; i < tiles.size(); i++) {
                    if(map.getTile(tiles.get(i)).isUnknown()) {
                        direction = MapDirections.cardinalDirection(unit.getLocation(), tiles.get(i));
                        return AICommand.buildMoveCommand(unit, direction);
                    }
                }
                return AICommand.buildMoveCommand(unit, direction);
            }
            else {
                return AICommand.buildMoveCommand(unit, direction);
            }
        }
    }

}