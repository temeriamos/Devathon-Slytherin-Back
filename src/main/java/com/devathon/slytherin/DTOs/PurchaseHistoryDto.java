package com.devathon.slytherin.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseHistoryDto {
    private String date;
    private String objectName;
    private Integer priceGaleon;
    private Integer priceSickle;
    private Integer priceKnut;
}