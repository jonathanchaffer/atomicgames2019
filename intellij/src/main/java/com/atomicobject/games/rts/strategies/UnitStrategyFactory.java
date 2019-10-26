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
            if (unit.isWorker()) {
                return buildGatherStrategy(map, unit, unitManager);
            } else if (unit.isTank()) {
                return buildAttackStrategy(map, unit, unitManager);
            } else if (unit.isScout()) {
                return buildExploreStrategy(map, unit, unitManager);
            } else {
                return buildExploreStrategy(map, unit, unitManager);
            }
        } else if (unit.isBase()) {
            return buildCreateStrategy(map, unit, unitManager);
        }
        else {
            return buildExploreStrategy(map, unit, unitManager);
        }
    }

    private IUnitStrategy buildExploreStrategy(Map map, Unit unit, UnitManager unitManager) {
        return new ExploreStrategy(map, unit, unitManager);
    }

    private IUnitStrategy buildGatherStrategy(Map map, Unit unit, UnitManager unitManager) {
        return new GatherStrategy(map, unit, unitManager);
    }

    private IUnitStrategy buildAttackStrategy(Map map, Unit unit, UnitManager unitManager) {
        return new AttackStrategy(map, unit, unitManager);
    }

    private IUnitStrategy buildCreateStrategy(Map map, Unit unit, UnitManager unitManager) {
        return new CreateStrategy(map, unit, unitManager);
    }
}
