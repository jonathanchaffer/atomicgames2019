package com.atomicobject.games.rts.state;

import com.atomicobject.games.rts.updates.Location;
import com.atomicobject.games.rts.updates.TileUpdate;

public class Tile {
    Location location;
    TileUpdate tileUpdate = new TileUpdate();
    boolean unknown = true;


    public Tile(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public TileUpdate getTileUpdate() {
        return tileUpdate;
    }

    public void setTileUpdate(TileUpdate tileUpdate) {
        if (this.tileUpdate == null || tileUpdate.isVisible()) {
            this.tileUpdate = tileUpdate;
            this.unknown = false;
        } else {
            /*
             * When a tile becomes invisible, the server sends empty fields for everything other than its visibilty and location.
             * We don't want to lose our info about the tile so we'll just toggle the state.
             */
            this.tileUpdate.setVisible(false);
        }
    }

    public boolean isUnknown() {
        return unknown;
    }

    public boolean isVisible() {
        return tileUpdate.isVisible();
    }

    public boolean isWalkable() {
        return !unknown && !tileUpdate.isBlocked();
    }

    public boolean hasResource() {
        return tileUpdate.getResources() != null && tileUpdate.getResources().getValue() > 0;
    }

    public boolean hasEnemyBase() {
        return tileUpdate.getUnits() != null
                && tileUpdate.getUnits().stream().anyMatch(u -> u.isBase());
    }

    public boolean hasEnemies() {
        return tileUpdate.getUnits() != null
                && tileUpdate.getUnits().size() > 0
                && tileUpdate.getUnits().stream().anyMatch(u -> u.isAlive());
    }
}
