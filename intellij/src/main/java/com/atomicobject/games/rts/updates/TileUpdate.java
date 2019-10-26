package com.atomicobject.games.rts.updates;

import java.util.List;

public class TileUpdate {
    private boolean visible = false;
    private boolean blocked = true;
    private int x;
    private int y;
    private ResourceUpdate resources;
    private List<UnitUpdate> units;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ResourceUpdate getResources() {
        return resources;
    }

    public void setResources(ResourceUpdate resources) {
        this.resources = resources;
    }

    public List<UnitUpdate> getUnits() {
        return units;
    }

    public void setUnits(List<UnitUpdate> units) {
        this.units = units;
    }

    public Location getLocation() {
        return new Location(x, y);
    }
}
