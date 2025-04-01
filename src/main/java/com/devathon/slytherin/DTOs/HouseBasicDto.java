
// com.devathon.slytherin.DTOs.HouseBasicDto.java
package com.devathon.slytherin.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO of the house response")
public class HouseBasicDto {
    private Long id;
    private String name;
}