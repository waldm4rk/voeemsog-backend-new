package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.repositories.RoomTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTranslationRepository roomTranslationRepository;

    public List<RoomDTO> getAllRooms(String lang){
        List<Room> rooms = roomRepository.findAll();
        return fillWithDto(rooms, lang);
    }
    public RoomDTO getRoomDTOById(UUID id, String lang){
        return roomToDTO(roomRepository.getRoomById(id).orElse(null), lang);
    }
    public Optional<Room> getRoomById(UUID id){
        return roomRepository.getRoomById(id);
    }

    public List<RoomDTO> getRoomsByName(String name, String lang){
        List<RoomTranslation> translations = roomTranslationRepository.getRoomTranslationsByNameContainingIgnoreCaseAndLang(name, lang);
        List<RoomDTO> dtos = new LinkedList<>();
        for(RoomTranslation translation : translations){
            dtos.add(roomToDTO(translation.getRoom(), lang));
        }
        return dtos;
    }

    public List<RoomDTO> getRoomsByCode(String code, String lang){
        List<Room> rooms = roomRepository.getRoomsByCodeContainingIgnoreCase(code);
        return fillWithDto(rooms, lang);
    }

    public List<RoomDTO> getRoomsByDanger(String danger, String lang){
        List<Room> rooms = roomRepository.getRoomsByDangerType(danger);
        return fillWithDto(rooms, lang);
    }

    public List<RoomDTO> getRoomsBySize(String size, String lang){
        return fillWithDto(roomRepository.getRoomsBySize(size), lang);
    }

    public List<RoomDTO> fillWithDto(List<Room> rooms, String lang){
        List<RoomDTO> dtos = new LinkedList<>();
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
