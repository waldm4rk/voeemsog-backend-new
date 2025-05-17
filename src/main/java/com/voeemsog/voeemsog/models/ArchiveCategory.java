package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="archive_category", uniqueConstraints = @UniqueConstraint(columnNames = {"category_code", "lang"}))
public class ArchiveCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "category_code")
    private int categoryCode;
    private String lang;
    private String name;
    private String description;
}
