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
@Schema(description = "DTO for the response of a user")
public class UserResponseDto {
    private String id;
    private String name;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
    private String imageBase64;
}
