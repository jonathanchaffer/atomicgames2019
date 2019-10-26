package com.atomicobject.games.rts.state;

import com.atomicobject.games.rts.strategies.UnitStrategyFactory;
import com.atomicobject.games.rts.updates.GameInfoUpdate;
import com.atomicobject.games.rts.updates.UnitInfoUpdate;
import com.atomicobject.games.rts.updates.UnitUpdate;

import java.util.HashMap;
import java.util.List;

public class UnitManager {
    private Map map;
    private UnitStrategyFactory unitStrategyFactory;
    private HashMap<Integer, Unit> units = new HashMap<>();

    private UnitInfoUpdate tankInfo;
    private UnitInfoUpdate scoutInfo;
    private UnitInfoUpdate workerInfo;

    public UnitManager(Map map, UnitStrategyFactory unitStrategyFactory) {
        this.map = map;
        this.unitStrategyFactory = unitStrategyFactory;
    }

    public void updateGameInfo(GameInfoUpdate gameInfo) {
        tankInfo = gameInfo.getUnitInfo().get(UnitUpdate.TYPE_TANK);
        scoutInfo = gameInfo.getUnitInfo().get(UnitUpdate.TYPE_SCOUT);
        workerInfo = gameInfo.getUnitInfo().get(UnitUpdate.TYPE_WORKER);
    }

    public void updateUnits(List<UnitUpdate> unitUpdates) {
        unitUpdates.forEach((u) -> {
            updateUnit(u);
        });
    }

    public HashMap<Integer, Unit> getUnits() {
        return units;
    }

    private void updateUnit(UnitUpdate u) {
        var unit = getUnitForUpdate(u);
        unit.setUnitUpdate(u);
        updateUnitStrategy(unit);
    }

    private void updateUnitStrategy(Unit unit)
    {
        unitStrategyFactory.assignStrategy(map, unit, this);
    }

    private Unit getUnitForUpdate(UnitUpdate u)
    {
        if (!units.containsKey(u.getIntegerId()))
        {
            units.put(u.getIntegerId(), new Unit());
        }
        return units.get(u.getIntegerId());
    }

    public int getTankCount()
    {
        return (int) units.values()
                .stream()
                .filter(u -> u.isTank())
                .filter(u -> u.isAlive())
                .count();
    }

    public int getWorkerCount()
    {
        return (int) units.values()
                .stream()
                .filter(u -> u.isWorker())
                .filter(u -> u.isAlive())
                .count();
    }

    public int getScoutCount()
    {
        return (int) units.values()
                .stream()
                .filter(u -> u.isScout())
                .filter(u -> u.isAlive())
                .count();
    }

    public int getRange(Unit unit)
    {
        if (unit.isScout())
        {
            return scoutInfo.getRange();
        }
        if (unit.isTank())
        {
            return tankInfo.getRange();
        }
        return workerInfo.getRange();
    }

    public UnitInfoUpdate getTankInfo() {
        return tankInfo;
    }

    public UnitInfoUpdate getScoutInfo() {
        return scoutInfo;
    }

    public UnitInfoUpdate getWorkerInfo() {
        return workerInfo;
    }
}
