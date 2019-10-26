package com.atomicobject.games.rts.updates;

import java.util.List;

public class GameUpdate {
    private int player;
    private int turn;
    private int time;
    private GameInfoUpdate gameInfo;
    private List<TileUpdate> tileUpdates;
    private List<UnitUpdate> unitUpdates;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public GameInfoUpdate getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfoUpdate gameInfo) {
        this.gameInfo = gameInfo;
    }

    public List<TileUpdate> getTileUpdates() {
        return tileUpdates;
    }

    public void setTileUpdates(List<TileUpdate> tileUpdates) {
        this.tileUpdates = tileUpdates;
    }

    public List<UnitUpdate> getUnitUpdates() {
        return unitUpdates;
    }

    public void setUnitUpdates(List<UnitUpdate> unitUpdates) {
        this.unitUpdates = unitUpdates;
    }
}
