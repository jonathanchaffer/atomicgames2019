package com.atomicobject.games.rts.updates;

import java.util.Objects;

public class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass().equals(this.getClass())) {
            Location other = (Location) obj;
            return other.getX() == getX() && other.getY() == getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location{" + "x: " + x + ", y: " + y + '}';
    }
}
