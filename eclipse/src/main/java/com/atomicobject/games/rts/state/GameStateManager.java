package com.atomicobject.games.rts.state;

import com.atomicobject.games.rts.updates.GameInfoUpdate;
import com.atomicobject.games.rts.updates.GameUpdate;
import com.atomicobject.games.rts.updates.TileUpdate;
import com.atomicobject.games.rts.updates.UnitUpdate;

import java.util.List;

public class GameStateManager {
    private Map map;
    private UnitManager unitManager;

    public GameStateManager(UnitManager unitManager, Map map) {
        this.map = map;
        this.unitManager = unitManager;
    }

    public void handleGameUpdate(GameUpdate update) {
        if (update.getGameInfo() != null) updateGameInfo(update.getGameInfo());
        if (update.getUnitUpdates() != null) updateUnits(update.getUnitUpdates());
        if (update.getTileUpdates() != null) updateTiles(update.getTileUpdates());

    }

    private void updateGameInfo(GameInfoUpdate gameInfo)
    {
        map.setSize(gameInfo.getMapWidth(), gameInfo.getMapHeight());
        unitManager.updateGameInfo(gameInfo);
    }


    private void updateUnits(List<UnitUpdate> unitUpdates)
    {
        unitManager.updateUnits(unitUpdates);
    }


    private void updateTiles(List<TileUpdate> tileUpdates)
    {
        map.updateTiles(tileUpdates);
    }
}
