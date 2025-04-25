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
    private String short_description;
    private String long_description;
    private CategoryResponseDto category;
    private RarityResponseDto rarity;
    private Integer price_galeon;
    private Integer price_sickle;
    private Integer price_knut;
    private String url_image;
}