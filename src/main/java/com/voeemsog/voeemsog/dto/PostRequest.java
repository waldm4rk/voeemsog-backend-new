package com.voeemsog.voeemsog.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private String title;
    private String text;
    private Set<UUID> categories;
}
