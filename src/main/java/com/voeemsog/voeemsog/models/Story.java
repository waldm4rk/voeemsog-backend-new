package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="stories")
@Data
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "category")
    private StoryCategory category;
    private LocalDate date = LocalDate.now();
    @Column(name = "reading_time")
    private int readingTime;
    private String lang;
}
