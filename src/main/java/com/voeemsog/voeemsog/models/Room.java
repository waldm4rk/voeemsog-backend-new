package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="rooms")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String code;
    private byte floor;
    @Column(name="danger_type")
    private String dangerType;
    private String size;
    private String color;
    @Column(name="light_level")
    private byte lightLevel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoomTranslation> translations = new ArrayList<>();
}
