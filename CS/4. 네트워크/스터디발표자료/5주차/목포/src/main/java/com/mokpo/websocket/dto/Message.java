package com.mokpo.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    public enum MessageType{
        ENTER, CHAT, LEAVE // 입장, 통신, 퇴장
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;

}
