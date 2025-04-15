package com.devathon.slytherin.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devathon.slytherin.DTOs.UserDto;
import com.devathon.slytherin.DTOs.UserResponseDto;
import com.devathon.slytherin.models.HouseModel;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.devathon.slytherin.repositories.HouseRepository;

import lombok.RequiredArgsConstructor;

@Tag(name = "Users", description = "Users operations")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HouseRepository houseRepository;
    
    @Operation(summary = "Create a new user", description = "Register a new user in DB")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping("/")
    public String createUser(@RequestBody UserDto userDto) {

        // Buscar la casa por ID
        //HouseModel house = houseRepository.findById(userDto.getHouse_id().longValue())
        //        .orElseThrow(() -> new RuntimeException("House not found"));

        // Crear el usuario
        UserModel userModel = UserModel.builder()
                .name(userDto.getName())
                .price_galeon(userDto.getPrice_galeon())
                .price_sickle(userDto.getPrice_sickle())
                .price_knut(userDto.getPrice_knut())
                .build();

        UserModel saved = userService.store(userModel);

        return saved.getName() + " created";
    }
    @Operation(summary = "Obtain list of users", description = "Return all users registered")
    @ApiResponse(responseCode = "200", description = "List of users returned successfully") 
    @GetMapping("/")
    public List<UserResponseDto> getAllUsers() {
        return userService.get();
    }

    @Operation(summary = "Obtain a single user", description = "Return a single user by ID")
    @ApiResponse(responseCode = "200", description = "User returned successfully")
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.get(id);
    }
}
