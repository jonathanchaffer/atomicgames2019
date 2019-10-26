package com.atomicobject.games.rts.strategies;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.state.Unit;

public interface IUnitStrategy {
    AICommand buildCommand(Unit unit);
}
