package com.mokpo.websocket.controller;

import com.mokpo.websocket.dto.MsgRoom;
import com.mokpo.websocket.service.MsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
public class MsgController {


    private final MsgService msgService;


    @GetMapping
    public List<MsgRoom> findAllRoom() {
        return msgService.findAllRoom();
    }


    @GetMapping("/rooms/{id}")
    public MsgRoom findById(@PathVariable String id) {
        return msgService.findById(id);
    }

    @PostMapping
    public MsgRoom createRoom(@RequestParam String roomId) {
        MsgRoom msgRoom = msgService.createRoom(roomId);
        System.out.println(msgRoom.getRoomId());
        return msgRoom;
    }


}
