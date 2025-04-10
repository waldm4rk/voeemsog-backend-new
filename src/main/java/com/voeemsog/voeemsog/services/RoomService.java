package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
}
