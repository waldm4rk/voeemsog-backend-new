package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
}
