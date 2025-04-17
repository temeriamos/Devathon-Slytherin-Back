package com.devathon.slytherin.DTOs;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for the creation of a sale and items")
public class SaleRequestDto {
    private String user_id;
    private Integer total_galeon;
    private Integer total_sickle;
    private Integer total_knut;
    private List<SaleItemRequestDto> items;
}
