package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        return new ResponseEntity<>(roomRepository.save(room), HttpStatusCode.valueOf(201));
    }
}
