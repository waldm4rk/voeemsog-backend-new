package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.repositories.RoomTranslationRepository;
import com.voeemsog.voeemsog.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTranslationRepository roomTranslationRepository;


    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        return new ResponseEntity<>(roomRepository.save(room), HttpStatusCode.valueOf(201));
    }
    @PostMapping("/room/translation")
    public ResponseEntity<RoomTranslation> createTranslation(@RequestBody RoomTranslation translation){
        return new ResponseEntity<>(roomTranslationRepository.save(translation), HttpStatusCode.valueOf(201));
    }
}
