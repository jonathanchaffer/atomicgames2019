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

    public AttackStrategy(Map map, Unit unit, UnitManager unitManager) {
        this.map = map;
        this.unitManager = unitManager;
    }

    public AICommand buildCommand(Unit unit) {
        var loco = new Location(5, 3);
        if (unit.isTank()) {
            return AICommand.buildShootCommand(unit, loco);
        }
        else {
            return AICommand.buildMeleeCommand(unit, MapDirections.Direction.EAST);
        }
    }



}
