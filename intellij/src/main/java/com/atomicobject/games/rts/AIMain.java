package com.atomicobject.games.rts;

import com.atomicobject.games.rts.communication.ServerConnection;

import java.io.IOException;

public class AIMain {
    ServerConnection serverConnection;
    AILoop loop;

    public AIMain(ServerConnection serverConnection, AILoop aiLoop) {
        this.serverConnection = serverConnection;
        this.loop = aiLoop;
    }

    public void run() {
        try {
            serverConnection.acceptConnection();
            loop.runLoop();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverConnection.close();
            } catch (IOException e) {
            }
        }
    }
}
