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
import java.util.UUID;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTranslationRepository roomTranslationRepository;

    public List<RoomDTO> getAllRooms(String lang){
        List<Room> list = roomRepository.findAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : list) {
            roomDTOS.add(roomToDTO(room, lang));
        }
        return roomDTOS;
    }
    public RoomDTO getRoomById(UUID id, String lang){
        return roomToDTO(roomRepository.getRoomById(id), lang);
    }

    public List<RoomDTO> getRoomsByName(String name, String lang){
        List<RoomTranslation> translations = roomTranslationRepository.getRoomTranslationsByNameContainingIgnoreCaseAndLang(name, lang);
        List<RoomDTO> dtos = new ArrayList<>();
        for(RoomTranslation translation : translations){
            dtos.add(roomToDTO(translation.getRoom(), lang));
        }
        return dtos;
    }

    public List<RoomDTO> getRoomsByCode(String code, String lang){
        List<Room> rooms = roomRepository.getRoomsByCodeContainingIgnoreCase(code);
        List<RoomDTO> dtos = new ArrayList<>();
        for(Room room : rooms){
            dtos.add(roomToDTO(room, lang));
        }
        return dtos;
    }
    public RoomDTO roomToDTO(Room room, String lang){
        RoomTranslation roomTranslation = roomTranslationRepository.getRoomTranslationByRoomAndLang(room, lang);
        RoomDTO dto = new RoomDTO(
                room.getCode(),
                room.getFloor(),
                room.getDangerType(),
                roomTranslation.getName(),
                roomTranslation.getDescription(),
                room.getSize(),
                room.getColor(),
                room.getLightLevel()
        );
        return dto;
    }
}
