package com.mokpo.websocket.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class MsgRoom {
    private String roomId;
    private String roomName;
    private Set<WebSocketSession> sessions = new HashSet<>();


    public static MsgRoom create(String roomName) {
        MsgRoom msgRoom = new MsgRoom();
        msgRoom.roomId = UUID.randomUUID().toString();
        msgRoom.roomName = roomName;

        return msgRoom;
    }

    public void handleActions(WebSocketSession session, Message message, ObjectMapper objectMapper) {

        System.out.println(message.getMessage());
        if(message.getMessageType().equals(Message.MessageType.ENTER)) {
            sessions.add(session);
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }else if(message.getMessageType().equals(Message.MessageType.LEAVE)) {
            sessions.remove(session);
            message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
        }else{
            message.setMessage(message.getMessage() + " : " + message.getMessage());
        }
        sendMessage(message, objectMapper);

    }

    public void sendMessage(Message message, ObjectMapper objectMapper) {
        try{
            TextMessage textMessage = new TextMessage(objectMapper.writeValueAsBytes(message.getMessage()));

            for (WebSocketSession session : sessions) {
                session.sendMessage(textMessage);
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }


    }
}
