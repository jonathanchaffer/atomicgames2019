package com.atomicobject.games.rts.updates;

import java.util.HashMap;

public class GameInfoUpdate {

    private int mapWidth;
    private int mapHeight;
    private int gameDuration;
    private int turnDuration;
    private HashMap<String, UnitInfoUpdate> unitInfo;

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public int getTurnDuration() {
        return turnDuration;
    }

    public void setTurnDuration(int turnDuration) {
        this.turnDuration = turnDuration;
    }

    public HashMap<String, UnitInfoUpdate> getUnitInfo() {
        return unitInfo;
    }

    public void setUnitInfo(HashMap<String, UnitInfoUpdate> unitInfo) {
        this.unitInfo = unitInfo;
    }
}
