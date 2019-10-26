package com.atomicobject.games.rts.communication;

import java.util.List;

public class AICommandsMessage {
    public List<AICommand> commands;

    public AICommandsMessage(List<AICommand> commandsToSend) {
        this.commands = commandsToSend;
    }
}
