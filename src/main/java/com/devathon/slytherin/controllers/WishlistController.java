package com.devathon.slytherin.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devathon.slytherin.DTOs.WishlistDto;
import com.devathon.slytherin.DTOs.WishlistGroupedResponseDto;
import com.devathon.slytherin.services.WishlistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Wishlist", description = "Wishlist operations")
@RestController
@RequestMapping("wishlists")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @Operation(summary = "Obtain wishlist by user", description = "Return all magic objects in the wishlist of a user")
    @GetMapping("/{userId}")
    @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public WishlistGroupedResponseDto getWishlistByUser(@PathVariable Long userId) {
        return wishlistService.getWishlistByUser(userId);
    }

    @Operation(summary = "Insert wishlist by idUser and idObject", description = "Insert a magic object in the wishlist of a user")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "404", description = "User or Magic Object not found")
    public String createWishlist(@RequestBody WishlistDto wishlistDto) {
        return wishlistService.createWishlist(wishlistDto);
    }
    @Operation(summary = "Delete wishlist by idWishlist", description = "Delete a magic object in the wishlist of a user")
    @DeleteMapping("/")
    @ApiResponse(responseCode = "200", description = "Wishlist deleted successfully")
    @ApiResponse(responseCode = "404", description = "User or Magic Object not found")
    public String deleteWishlist(@RequestBody Long wishlistId) {
        return wishlistService.deleteWishlist(wishlistId);
    }
    
}
