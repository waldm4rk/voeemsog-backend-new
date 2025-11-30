package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "story_category")
@Data
public class StoryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String lang;
}
