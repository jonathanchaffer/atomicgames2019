package com.atomicobject.games.rts;

import com.atomicobject.games.rts.communication.MessageSerializer;
import com.atomicobject.games.rts.communication.ServerConnection;
import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.GameStateManager;
import com.atomicobject.games.rts.state.UnitManager;
import com.atomicobject.games.rts.strategies.AIStrategy;
import com.atomicobject.games.rts.strategies.UnitStrategyFactory;

public class Main {

    public static int selectPort(String[] args) {
        var port = 9090;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        return port;
    }

    public static void main(String[] args) {
        int port = selectPort(args);
        var serverConnection = new ServerConnection(port, new MessageSerializer());
        var map = new Map();
        var unitManager = new UnitManager(map, new UnitStrategyFactory());
        var stateManager = new GameStateManager(unitManager, map);
        var gameStrategy = new AIStrategy(unitManager, map);
        var loop = new AILoop(serverConnection, stateManager, gameStrategy);
        new AIMain(serverConnection, loop).run();
    }
}
