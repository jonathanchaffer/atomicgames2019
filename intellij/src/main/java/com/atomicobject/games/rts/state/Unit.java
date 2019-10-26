package com.atomicobject.games.rts.state;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.strategies.IUnitStrategy;
import com.atomicobject.games.rts.updates.Location;
import com.atomicobject.games.rts.updates.UnitUpdate;

import java.util.List;

public class Unit {
    private UnitUpdate unitUpdate;
    private IUnitStrategy strategy;
    private List<Location> path;

    public int getId() {
        return unitUpdate.getId();
    }

    public Location getLocation() {
        return new Location(unitUpdate.getX(), unitUpdate.getY());
    }

    public UnitUpdate getUnitUpdate() {
        return unitUpdate;
    }

    public void setUnitUpdate(UnitUpdate unitUpdate) {
        this.unitUpdate = unitUpdate;
    }

    public IUnitStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IUnitStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean isTank() {
        return unitUpdate.getType().equals(UnitUpdate.TYPE_TANK);
    }

    public boolean isScout() {
        return unitUpdate.getType().equals(UnitUpdate.TYPE_SCOUT);
    }

    public boolean isWorker() {
        return unitUpdate.getType().equals(UnitUpdate.TYPE_WORKER);
    }

    public boolean isBase() {
        return unitUpdate.getType().equals(UnitUpdate.TYPE_BASE);
    }

    public boolean isIdle() {
        return unitUpdate.isIdle();
    }

    public boolean isAlive() {
        return unitUpdate.isAlive();
    }

    public boolean isMobile() {
        return isWorker() || isScout() || isTank();
    }

    public boolean hasStrategy() {
        return strategy != null;
    }

    public boolean isCarryingResource() {
        return unitUpdate.isCarryingResource();
    }

    public int getAvailableResources() {
        return unitUpdate.getResource();
    }

    public boolean canAttack() {
        return unitUpdate.canAttack();
    }

    public AICommand buildCommand() {
        if (isIdle() && hasStrategy()) {
            return strategy.buildCommand(this);
        }
        return null;
    }

    public List<Location> getPath() {
        return path;
    }

    public void setPath(List<Location> path) {
        this.path = path;
    }

    public boolean hasPath() {
        return path != null && path.size() > 0;
    }

    public MapDirections.Direction nextMove() {
        return hasPath()
                ? getNextMoveFromPath()
                : MapDirections.randomDirection();
    }

    public boolean isAdjacentToResource(Map map) {
        return map.isResourceAdjacentTo(getLocation());
    }

    private MapDirections.Direction getNextMoveFromPath() {
        return MapDirections.cardinalDirection(getLocation(), popNextLocationFromPath());
    }

    private Location popNextLocationFromPath() {
        return path.remove(0);
    }
}
