package com.voeemsog.voeemsog.controllers;

import com.sun.net.httpserver.HttpsServer;
import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.repositories.RoomTranslationRepository;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTranslationRepository roomTranslationRepository;


    // Null room crashes server
    // FIXED?
    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        if(room == null) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(roomRepository.saveAndFlush(room), HttpStatusCode.valueOf(201));
    }
    @PutMapping("/room")
    public ResponseEntity<Room> upsertRoom(@RequestBody Room room){
        UUID id = roomService.getIdByCode(room.getCode());
        if(id == null) id = UUID.randomUUID();
        room.setId(id);
        roomRepository.saveAndFlush(room);
        return new ResponseEntity<>(room, HttpStatusCode.valueOf(201));
    }

    @PostMapping("/room/translation")
    public ResponseEntity<?> createTranslation(@RequestBody RoomTranslation translation, @RequestParam UUID roomId){
        Room room = roomService.getRoomById(roomId).orElse(null);
        if(room == null){
            return new ResponseEntity<>(false, HttpStatusCode.valueOf(204));
        }
        translation.setRoom(room);
        roomTranslationRepository.saveAndFlush(translation);
        return new ResponseEntity<>(true, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>(false, HttpStatusCode.valueOf(204));
    }
}
