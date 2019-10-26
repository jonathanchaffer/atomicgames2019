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
    private final String unitName;

    public CreateStrategy(Map map, Unit unit, UnitManager unitManager, String unitName) {
        this.map = map;
        this.unitManager = unitManager;
        this.unitName = unitName;
    }

    public AICommand buildCommand(Unit unit) {
        return AICommand.buildUnitCommand(this.unitName);
    }



}