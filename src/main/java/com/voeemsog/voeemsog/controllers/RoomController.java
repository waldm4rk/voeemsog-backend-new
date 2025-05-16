package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<RoomDTO> getAllRooms(@RequestParam String lang){
        return roomService.getAllRooms(lang);
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable UUID id, @RequestParam String lang){
        return roomService.getRoomById(id, lang);
    }

    @GetMapping("/name/{name}")
    public List<RoomDTO> getRoomsByName(@PathVariable String name, @RequestParam String lang){
        return roomService.getRoomsByName(name, lang);
    }
    @GetMapping("/code/{code}")
    public List<RoomDTO> getRoomsByCode(@PathVariable String code, @RequestParam String lang){
        return roomService.getRoomsByCode(code, lang);
    }

    @GetMapping("/danger/{danger}")
    public List<RoomDTO> getRoomsByDanger(@PathVariable String danger, @RequestParam String lang){
        return roomService.getRoomsByDanger(danger, lang);
    }

    @GetMapping("/size/{size}")
    public List<RoomDTO> getRoomsBySize(@PathVariable String size, @RequestParam String lang){
        return roomService.getRoomsBySize(size, lang);
    }
}
