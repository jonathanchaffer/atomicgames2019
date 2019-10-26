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
        return AICommand.buildUnitCommand("scout");
    }



}