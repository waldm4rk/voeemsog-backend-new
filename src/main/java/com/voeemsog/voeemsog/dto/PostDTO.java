package com.voeemsog.voeemsog.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private UUID id;
    private String title;
    private String text;
    private LocalDate postDate;
    private Set<PostCategoryDTO> categories;
}
