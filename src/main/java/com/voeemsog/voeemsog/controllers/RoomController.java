package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<RoomDTO> getAllRooms(@RequestParam String lang){
        lang = lang == null ? "us" : lang;
        return roomService.getAllRooms(lang);
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable long id, @RequestParam String lang){
        lang = lang == null ? "us" : lang;
        return roomService.getRoomById(id, lang);
    }
}
