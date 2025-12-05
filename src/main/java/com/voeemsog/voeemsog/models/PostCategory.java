package com.voeemsog.voeemsog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "post_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCategory {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    @Builder.Default
    private String lang = "en_US";

    @Column(name = "cat_code", nullable = false)
    private Integer catCode;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private Set<Post> posts = new HashSet<>();
}
