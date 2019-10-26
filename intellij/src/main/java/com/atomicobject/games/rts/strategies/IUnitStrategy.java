package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;

import java.util.Random;

public interface IUnitStrategy {
    AICommand buildCommand(Unit unit);

    static MapDirections.Direction getDirectionForGeneralDirection(MapDirections.Direction generalDirection) {
        // go in the general direction according to odds
        var direction = MapDirections.randomDirection();
        var odds = new int[]{1,1,2,3,4};
        var random = new Random();
        var num = odds[random.nextInt(odds.length)];
        if (num == 1) direction = generalDirection; // turn 0
        else if (num == 2) direction = MapDirections.turn(generalDirection); // turn 1
        else if (num == 3) direction = MapDirections.turn(MapDirections.turn(MapDirections.turn(generalDirection))); // turn 3
        else direction = MapDirections.turn(MapDirections.turn(generalDirection)); // turn 2
        return direction;
    }
}
