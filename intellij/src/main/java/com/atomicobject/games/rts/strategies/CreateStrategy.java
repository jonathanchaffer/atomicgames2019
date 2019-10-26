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
        if (unitManager.getWorkerCount() < 9) {
            return AICommand.buildUnitCommand("worker");
        }
        else if (resource > 130 && unitManager.getScoutCount() < 5 && unitManager.getWorkerCount() > 8) {
            return AICommand.buildUnitCommand("scout");
        }
        else if (resource > 500 && unitManager.getTankCount() < 3 && map.hasEnemies()) {
            return AICommand.buildUnitCommand("tank");
        }
        else {
            // basically the same as do nothing
            return AICommand.buildMoveCommand(unit, MapDirections.randomDirection());
        }
    }



}