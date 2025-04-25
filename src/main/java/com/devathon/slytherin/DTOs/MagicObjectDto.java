package com.devathon.slytherin.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for the creation of a magic object")
public class MagicObjectDto {
    private String name;
    private String short_description;
    private String long_description;
    @JsonProperty("category_id")
    private Integer category_id; 
    @JsonProperty("rarity_id")
    private Integer rarity_id;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
    private String url_image;
    private boolean purchased;
}
