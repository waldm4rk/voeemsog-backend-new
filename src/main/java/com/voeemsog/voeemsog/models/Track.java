package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "track")
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String link;
    private String description;
    private String description_ru;
    private int order;
    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;
}
