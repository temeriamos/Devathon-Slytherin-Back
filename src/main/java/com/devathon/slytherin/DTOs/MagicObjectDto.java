package com.devathon.slytherin.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicObjectDto {
    private String name;
    private String description;
    private Double price;
    private String image;

}
