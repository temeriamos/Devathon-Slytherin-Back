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
@Schema(description = "DTO for the creation of a user")
public class UserDto {
    //@JsonProperty("house_id")
    //private Integer house_id;    
    private String name;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
}