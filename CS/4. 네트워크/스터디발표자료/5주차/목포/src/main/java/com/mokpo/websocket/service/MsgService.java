package com.mokpo.websocket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokpo.websocket.dto.MsgRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsgService {
    private final ObjectMapper objectMapper;
    private Map<String, MsgRoom> msgRooms;

    @PostConstruct
    private void init() { // was 구동 시 채팅방 map 초기화
        msgRooms = new LinkedHashMap<>();
    }

    public List<MsgRoom> findAllRoom() {
        return new ArrayList<>(msgRooms.values());
    }

    public MsgRoom findById(String roomId) {
        return msgRooms.get(roomId);
    }

    public MsgRoom createRoom(String roomName) {
        MsgRoom msgRoom = MsgRoom.create(roomName);
        msgRooms.put(msgRoom.getRoomId(), msgRoom);
        return msgRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsBytes(message)));
        }catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }


}
