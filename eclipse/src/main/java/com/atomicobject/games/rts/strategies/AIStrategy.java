package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.state.UnitManager;
import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.state.Map;

import java.util.List;
import java.util.stream.Collectors;

public class AIStrategy {
    private UnitManager unitManager;

    public AIStrategy(UnitManager unitManager, Map map) {
        this.unitManager = unitManager;
    }

    public List<AICommand> buildCommandList() {
        return unitManager.getUnits().values()
                .stream()
                .map(u -> u.buildCommand())
                .filter(c -> c != null)
                .collect(Collectors.toList());
    }
}
