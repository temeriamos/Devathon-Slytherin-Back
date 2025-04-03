package com.devathon.slytherin.controllers;

import com.devathon.slytherin.DTOs.MagicObjectPaginatorResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devathon.slytherin.DTOs.MagicObjectDto;
import com.devathon.slytherin.DTOs.MagicObjectResponseDto;
import com.devathon.slytherin.models.CategoryModel;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.models.RarityModel;
import com.devathon.slytherin.repositories.CategoryRepository;
import com.devathon.slytherin.repositories.RarityRepository;
import com.devathon.slytherin.services.MagicObjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("magicobject")
@RequiredArgsConstructor
@Tag(name = "Magic Object", description = "Magic object operations")
public class MagicObjectController {

    private final MagicObjectService magicObjectService;
    private final CategoryRepository categoryRepository;
    private final RarityRepository rarityRepository;

    @Operation(summary = "Create a new magic object", description = "Register a new magic object in DB")
    @ApiResponse(responseCode = "201", description = "Magic object created successfully")
    @PostMapping("/")
    public String createMagicObject(@RequestBody MagicObjectDto magicobjectDto) {

        CategoryModel category = categoryRepository.findById(magicobjectDto.getCategory_id().longValue())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        RarityModel rarity = rarityRepository.findById(magicobjectDto.getRarity_id().longValue())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        MagicObjectModel magicObjectModel = MagicObjectModel.builder()
                .name(magicobjectDto.getName())
                .short_description(magicobjectDto.getShort_description())
                .long_description(magicobjectDto.getLong_description())
                .category(category)
                .rarity(rarity)
                .price_galeon(magicobjectDto.getPrice_galeon())
                .price_sickle(magicobjectDto.getPrice_sickle())
                .price_knut(magicobjectDto.getPrice_knut())
                .url_image(magicobjectDto.getUrl_image())
                .purchased(magicobjectDto.isPurchased())
                .build();

        MagicObjectModel saved = magicObjectService.store(magicObjectModel);

        return saved.getName() + " created";
    }

    @Operation(summary = "Obtain a list of magic objects", description = "Return all magic objects registered")
    @ApiResponse(responseCode = "200", description = "List of magic objects returned successfully")
    @GetMapping("/")
    public ResponseEntity<MagicObjectPaginatorResponseDto> getAllMagicObjects(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(magicObjectService.get(page,size));
    }

    @Operation(summary = "Obtain a single magic object", description = "Return a single magic object by ID")
    @ApiResponse(responseCode = "200", description = "Magic object returned successfully")
    @GetMapping("/{id}")
    public MagicObjectResponseDto getMagicObject(@PathVariable Long id) {
        return magicObjectService.get(id);
    }
}