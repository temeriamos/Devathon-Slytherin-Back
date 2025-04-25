package com.devathon.slytherin.controllers;

import com.devathon.slytherin.DTOs.MagicObjectPaginatorResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;

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

    @Operation(
        summary = "Obtain a list of unsold magic objects and filtered by category", 
        description = "Returns a paginated list of unsold magic objects filtered by category ID. If a category is not specified, returns all magic items.")
    @ApiResponse(responseCode = "200", description = "Lista de objetos mágicos devuelta con éxito")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @GetMapping("/")
    public ResponseEntity<MagicObjectPaginatorResponseDto> getAllMagicObjects(
            @RequestParam(required = false) Long category, // Cambiado a Long para aceptar el ID de la categoría
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(magicObjectService.getFilteredMagicObjects(category, page, size));
    }

    @Operation(summary = "Obtain a single magic object", description = "Return a single magic object by ID")
    @ApiResponse(responseCode = "200", description = "Magic object returned successfully")
    @GetMapping("/{id}")
    public MagicObjectResponseDto getMagicObject(@PathVariable Long id) {
        return magicObjectService.get(id);
    }

    @Operation(
            summary = "Search for Magic Objects by name",
            description = "Returns a paginated list of magic objects whose name contains the provided text."
    )
    @ApiResponse(responseCode = "200", description = "List of magic objects returned successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @GetMapping("/search")
    public ResponseEntity<MagicObjectPaginatorResponseDto> searchMagicObjects(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        MagicObjectPaginatorResponseDto response = magicObjectService.searchByName(query, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Obtain a list of unsold magic objects and filtered by rarity", 
        description = "Returns a list of unsold magic objects filtered by rarity ID. If a rarity is not specified, returns all magic items.")
    @ApiResponse(responseCode = "200", description = "Lista de objetos mágicos devuelta con éxito")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @GetMapping("/rarity")
    public ResponseEntity<List<MagicObjectResponseDto>> getAllMagicObjectsRarity(
            @RequestParam(required = false) Long rarity) {
        return ResponseEntity.ok(magicObjectService.getFilteredMagicObjectsRarity(rarity));
    }
}
