package com.devathon.slytherin.DTOs;

import com.devathon.slytherin.models.MagicObjectModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "WishlistObjectsResponseDto", description = "Representa un objeto mágico en la wishlist")
public class WishlistObjectsResponseDto {

    private Long idWishlist;
    private MagicObjectModel magicObject;
}
