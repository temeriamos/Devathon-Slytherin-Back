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
@Schema(description = "DTO for the creation of a sale item")
public class SaleItemDto {
    private Long sale_id;
    private Long object_id;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
}
