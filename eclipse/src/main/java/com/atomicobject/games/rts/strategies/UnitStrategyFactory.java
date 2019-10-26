package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.state.UnitManager;

public class UnitStrategyFactory {

    public void assignStrategy(Map map, Unit unit, UnitManager unitManager) {
        if (unit.getStrategy() == null) {
            unit.setStrategy(buildStrategy(map, unit, unitManager));
        }
    }

    private IUnitStrategy buildStrategy(Map map, Unit unit, UnitManager unitManager) {
        if (unit.isMobile()) {
            return buildExploreStrategy(map, unit, unitManager);
        }
        return null;
    }

    private IUnitStrategy buildExploreStrategy(Map map, Unit unit, UnitManager unitManager) {
        return new ExploreStrategy(map, unit, unitManager);
    }
}
