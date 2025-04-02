package com.devathon.slytherin.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devathon.slytherin.DTOs.CategoryDto;
import com.devathon.slytherin.models.CategoryModel;
import com.devathon.slytherin.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category operations")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Create a new category", description = "Register a new category in DB")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @PostMapping("/")
    public String createCategory(@RequestBody CategoryDto categoryDto) {

        CategoryModel categoryModel =
            CategoryModel.builder()
                        .name(categoryDto.getName())
                        .build();

        CategoryModel savedObject = categoryService.store(categoryModel);

        return savedObject.getName() + " created";
    }

    @Operation(summary = "Obtain a list of categories", description = "Return all categories registered")
    @ApiResponse(responseCode = "200", description = "List of categories returned successfully")
    @GetMapping("/")
    public List<CategoryModel> getAllCategories() {
        return categoryService.get();
    }
    
}
