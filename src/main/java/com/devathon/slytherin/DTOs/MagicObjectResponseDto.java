package com.devathon.slytherin.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for the response of a magic object")
public class MagicObjectResponseDto {
    private long id;
    private String name;
    private String short_description;
    private String long_description;
    private CategoryResponseDto category;
    private RarityResponseDto rarity;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
    private String url_image;
    private boolean purchased;
}
