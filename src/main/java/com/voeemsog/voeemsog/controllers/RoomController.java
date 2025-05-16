package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable UUID id, @RequestParam String lang){
        RoomDTO room = roomService.getRoomDTOById(id, lang);
        if(room != null) return new ResponseEntity<>(room, HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<RoomDTO>> getRoomsByName(@PathVariable String name, @RequestParam String lang){
        List<RoomDTO> rooms = roomService.getRoomsByName(name, lang);
        if(!rooms.isEmpty()) return new ResponseEntity<>(rooms, HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<List<RoomDTO>> getRoomsByCode(@PathVariable String code, @RequestParam String lang){
        List<RoomDTO> rooms = roomService.getRoomsByCode(code, lang);
        if(!rooms.isEmpty()) return new ResponseEntity<>(rooms, HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    @GetMapping("/danger/{danger}")
    public ResponseEntity<List<RoomDTO>> getRoomsByDanger(@PathVariable String danger, @RequestParam String lang){
        List<RoomDTO> rooms = roomService.getRoomsByDanger(danger, lang);
        if(!rooms.isEmpty()) return new ResponseEntity<>(rooms, HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<RoomDTO>> getRoomsBySize(@PathVariable String size, @RequestParam String lang){
        List<RoomDTO> rooms = roomService.getRoomsBySize(size, lang);
        if(!rooms.isEmpty()) return new ResponseEntity<>(rooms, HttpStatusCode.valueOf(200));
        else return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }
}
