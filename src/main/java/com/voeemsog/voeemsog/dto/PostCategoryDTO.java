package com.voeemsog.voeemsog.dto;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCategoryDTO {
    private UUID id;
    private String title;
    private String lang;
    private Integer catCode;
}
