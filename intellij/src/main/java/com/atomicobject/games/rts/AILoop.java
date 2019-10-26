package com.atomicobject.games.rts;

import com.atomicobject.games.rts.communication.ServerConnection;
import com.atomicobject.games.rts.state.GameStateManager;
import com.atomicobject.games.rts.strategies.AIStrategy;
import com.atomicobject.games.rts.updates.GameUpdate;

import java.io.IOException;

public class AILoop {

    ServerConnection serverConnection;
    GameStateManager stateManager;
    AIStrategy aiStrategy;

    public AILoop(ServerConnection serverConnection, GameStateManager stateManager, AIStrategy aiStrategy) {
        this.serverConnection = serverConnection;
        this.stateManager = stateManager;
        this.aiStrategy = aiStrategy;
    }

    public void runLoop() throws IOException {
        GameUpdate update = null;
        while ((update = serverConnection.readUpdate()) != null)
        {
            stateManager.handleGameUpdate(update);
            serverConnection.sendCommands(aiStrategy.buildCommandList());
        }
    }
}
