package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="room_translations")
@Data
public class RoomTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private String lang;
    private String name;
    private String description;
}
