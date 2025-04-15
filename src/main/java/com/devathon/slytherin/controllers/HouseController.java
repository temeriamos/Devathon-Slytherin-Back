package com.devathon.slytherin.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devathon.slytherin.DTOs.HouseDto;
import com.devathon.slytherin.models.HouseModel;
import com.devathon.slytherin.services.HouseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

//@Tag(name = "House", description = "Hogwarts House operations")
//@RestController
//@RequestMapping("house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
 
    @Operation(summary = "Create a new house", description = "Register a new house in DB")
    @ApiResponse(responseCode = "201", description = "House created successfully")
    @PostMapping("/")
    public String createHouse(@RequestBody HouseDto houseDto) {
         HouseModel houseModel =
                 HouseModel.builder()
                         .name(houseDto.getName())
                         .build();
     
         HouseModel savedObject = houseService.store(houseModel);
     
         return savedObject.getName() + " created";
    }

    @Operation(summary = "Obtain list of houses", description = "Return all houses registered")
    @ApiResponse(responseCode = "200", description = "List of houses returned successfully")
    @GetMapping("/")
    public List<HouseModel> getAllHouses() {
        return houseService.get();
    }
    
}