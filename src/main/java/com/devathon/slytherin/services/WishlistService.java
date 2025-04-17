package com.devathon.slytherin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.WishlistDto;
import com.devathon.slytherin.DTOs.WishlistGroupedResponseDto;
import com.devathon.slytherin.DTOs.WishlistObjectsResponseDto;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.models.WishlistModel;
import com.devathon.slytherin.repositories.MagicObjectRepository;
import com.devathon.slytherin.repositories.UserRepository;
import com.devathon.slytherin.repositories.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final MagicObjectRepository magicObjectRepository;
    private final UserRepository userRepository;

    public WishlistGroupedResponseDto getWishlistByUser(String userId) {
        List<WishlistModel> wishlists = wishlistRepository.findAll().stream()
            .filter(wishlist -> wishlist.getUserModel().getId().equals(userId))
            .toList();

        if (wishlists.isEmpty()) {
            throw new RuntimeException("Wishlist not found for user " + userId);
        }

        UserModel user = wishlists.get(0).getUserModel();

        List<WishlistObjectsResponseDto> wishlistObject = wishlists.stream()
            .map(wishlist -> {
                MagicObjectModel magicObject = wishlist.getMagicObjectModel();
                return new WishlistObjectsResponseDto(
                    wishlist.getId(),
                    magicObject
                );
            }).toList();

        return WishlistGroupedResponseDto.builder()
            .user(user)
            .wishlistObjects(wishlistObject)
            .build();
    }

    public String deleteWishlist(Long wishlistId) {
        WishlistModel wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        wishlistRepository.delete(wishlist);
        return "Wishlist deleted successfully";
    }

    public String createWishlist(WishlistDto wishlistDto) {

        MagicObjectModel magicObject = magicObjectRepository.findById(wishlistDto.getMagicObjectId())
                .orElseThrow(() -> new RuntimeException("Magic object not found"));

        UserModel user = userRepository.findById(wishlistDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        WishlistModel wishlist = new WishlistModel();
        wishlist.setUserModel(user);
        wishlist.setMagicObjectModel(magicObject);
        wishlistRepository.save(wishlist);
        return "Wishlist created successfully";
    }
}
