package com.mokpo.websocket.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokpo.websocket.dto.Message;
import com.mokpo.websocket.dto.MsgRoom;
import com.mokpo.websocket.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 소켓 통신은 기본적으로 1 : N의 서버-클라이언트 관계를 맺는다.
 * 따라서 한 서버에 여러 클라이언트가 접속할 수 있으므로 서버는 여러 클라이언트가 발송한 메세지를 받아서 처리해줄 Handler가 필요
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    // RequiredArgsConstructor 어노테이션은 초기화되지 않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자를 생성해준다. 주로 의존성 주입 편의성을 위해 사용되곤한다.

    private final ObjectMapper objectMapper;
    private final MsgService msgService;


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : {}", payload);

        Message msg = objectMapper.readValue(payload, Message.class);

        MsgRoom room = msgService.findById(msg.getRoomId());

        room.handleActions(session, msg, objectMapper);
    }


}
