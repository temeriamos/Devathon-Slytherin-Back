package com.devathon.slytherin.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
}