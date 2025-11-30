package com.voeemsog.voeemsog.dto;

import com.voeemsog.voeemsog.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RoomDTO {
    private String code;
    private int floor;
    private String dangerType;
    private String name;
    private String description;
    private String size;
    private String color;
    private byte lightLevel;
    private LocalDate date;
}
