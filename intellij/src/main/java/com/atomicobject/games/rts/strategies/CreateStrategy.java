package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.state.UnitManager;
import com.atomicobject.games.rts.updates.Location;

public class CreateStrategy implements IUnitStrategy {
    private final Map map;
    private final UnitManager unitManager;

    public CreateStrategy(Map map, Unit unit, UnitManager unitManager) {
        this.map = map;
        this.unitManager = unitManager;
    }

    public AICommand buildCommand(Unit unit) {
        var resource = unit.getUnitUpdate().getResource();
        if (unitManager.getScoutCount() < 4) {
            return AICommand.buildUnitCommand("scout");
        }
        else if (map.enemyBaseFound() && resource > 250) {
            return AICommand.buildUnitCommand("tank");
        }
        else {
            // basically the same as do nothing
            return AICommand.buildMoveCommand(unit, MapDirections.randomDirection());
        }
    }



}