package com.devathon.slytherin.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devathon.slytherin.DTOs.RarityDto;
import com.devathon.slytherin.models.RarityModel;
import com.devathon.slytherin.services.RarityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("rarity")
@RequiredArgsConstructor
@Tag(name = "Rarity", description = "Rarity operations")
public class RarityController {
    private final RarityService rarityService;

    @Operation(summary = "Create a new rarity", description = "Register a new rerity in DB")
    @ApiResponse(responseCode = "201", description = "Rarity created successfully")
    @PostMapping("/")
    public String createRarity(@RequestBody RarityDto rarityDto) {
        RarityModel rarityModel =
            RarityModel.builder()
                        .name(rarityDto.getName())
                        .build();

        RarityModel savedObject = rarityService.store(rarityModel);

        return savedObject.getName() + " created";
    }
    
    @Operation(summary = "Obtain a list of rarities", description = "Return all rarities registered")
    @ApiResponse(responseCode = "200", description = "List of rarities returned successfully") 
    @GetMapping("/")
    public List<RarityModel> getAllRarities() {
        return rarityService.get();
    }
    
}
