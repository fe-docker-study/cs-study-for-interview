package com.mokpo.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokpo.websocket.dto.Message;
import org.junit.jupiter.api.Test;

public class JsonTest {
    @Test
    public void jsonConvertTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "{\n" +
                "  \"messageType\":\"ENTER\",\n" +
                "  \"roomId\":\"UUIDJLHKUD32523\",\n" +
                "  \"sender\":\"user1\",\n" +
                "  \"message\":\"안녕하세요!\"\n" +
                "}";
        System.out.println(objectMapper.readValue(jsonString, Message.class).getMessage());
        System.out.println();
    }
}
