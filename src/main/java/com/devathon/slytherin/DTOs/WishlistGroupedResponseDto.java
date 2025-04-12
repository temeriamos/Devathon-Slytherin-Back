package com.devathon.slytherin.DTOs;

import java.util.List;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.models.UserModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "WishlistGroupedResponseDto", description = "Representa la wishlist de un usuario con sus objetos mágicos")
public class WishlistGroupedResponseDto {
    private UserModel user;
    private List<MagicObjectModel> magicObjects;
}
