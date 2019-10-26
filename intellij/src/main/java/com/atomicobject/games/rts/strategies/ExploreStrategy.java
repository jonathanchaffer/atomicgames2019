package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.state.UnitManager;

import java.util.Random;

public class ExploreStrategy implements IUnitStrategy {
    private final Map map;
    private final UnitManager unitManager;

    public ExploreStrategy(Map map, Unit unit, UnitManager unitManager) {
        this.map = map;
        this.unitManager = unitManager;
    }

    public AICommand buildCommand(Unit unit) {
        var direction = MapDirections.randomDirection();
        return AICommand.buildMoveCommand(unit, direction);
    }

}