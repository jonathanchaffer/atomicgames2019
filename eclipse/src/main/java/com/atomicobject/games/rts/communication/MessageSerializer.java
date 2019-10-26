package com.atomicobject.games.rts.communication;

import com.atomicobject.games.rts.updates.GameUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

public class MessageSerializer {

    private ObjectMapper objectMapper;

    public MessageSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public GameUpdate parseUpdate(String updateJSON) throws IOException {
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.readValue(updateJSON, GameUpdate.class);
    }

    public String serializeAICommandsMessage(AICommandsMessage message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message) + "\n";
    }
}
