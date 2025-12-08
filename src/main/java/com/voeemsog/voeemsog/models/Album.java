package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name="album")
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String description_ru;
    @Column(name = "cover_link")
    private String coverLink;
}
