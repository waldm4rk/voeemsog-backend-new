package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(source="room.code", target="code")
    @Mapping(source="room.floor", target="floor")
    @Mapping(source="room.dangerType", target="dangerType")
    @Mapping(source="room.size", target = "size")
    @Mapping(source="room.color", target = "color")
    @Mapping(source="room.lightLevel", target = "lightLevel")
    @Mapping(source = "translation.name", target="name")
    @Mapping(source="translation.description", target = "description")
    RoomDTO toDto(Room room, RoomTranslation translation);
}
