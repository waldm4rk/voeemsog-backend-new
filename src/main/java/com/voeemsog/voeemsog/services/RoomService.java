package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.repositories.RoomTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTranslationRepository roomTranslationRepository;
    @Autowired
    private RoomMapper roomMapper;

    public List<RoomDTO> getAllRooms(String lang){
        List<Room> list = roomRepository.findAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : list) {
            roomDTOS.add(roomToDTO(room, lang));
        }
        return roomDTOS;
    }
    public RoomDTO getRoomById(long id, String lang){
        return roomToDTO(roomRepository.getRoomById(id), lang);
    }

    public RoomDTO roomToDTO(Room room, String lang){
        RoomTranslation roomTranslation = roomTranslationRepository.getRoomTranslationByIdAndLang(room.getId(), lang);
        return roomMapper.toDto(room, roomTranslation);
    }
}
