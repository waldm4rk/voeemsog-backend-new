package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.repositories.RoomTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<RoomDTO> getRoomsByPage(int page, int limit, String lang){
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("date").descending());
        List<Room> rooms = roomRepository.getAllByOrderByDateDesc(pageable);
        if(rooms.isEmpty()){
            return null;
        }
        return fillWithDto(rooms, lang);
    }

    public long getNumberOfRooms(){
        double pages = (double) roomRepository.count() / 10;
        return (long) Math.ceil(pages);
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
            RoomDTO dto = roomToDTO(room, lang);
            if(dto != null){
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public UUID getIdByCode(String code){
        return roomRepository.getIdByCode(code);
    }


    public RoomDTO roomToDTO(Room room, String lang){
        RoomTranslation roomTranslation = roomTranslationRepository.getRoomTranslationByRoomAndLang(room, lang);
        if(roomTranslation == null) return null;
        RoomDTO dto = new RoomDTO(
                room.getCode(),
                room.getFloor(),
                room.getDangerType(),
                roomTranslation.getName(),
                roomTranslation.getDescription(),
                room.getSize(),
                room.getColor(),
                room.getLightLevel(),
                room.getDate()
        );
        return dto;
    }

    public Room deleteRoom(UUID id){
        return roomRepository.deleteRoomById(id).orElse(null);
    }

}
