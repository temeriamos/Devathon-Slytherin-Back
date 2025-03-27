package com.devathon.slytherin.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devathon.slytherin.DTOs.UserDto;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/")
    public String createUser(@RequestBody UserDto userDto) {

        UserModel userModel =
                UserModel.builder()
                        .name(userDto.getName())
                        .price_galeon(userDto.getPrice_galeon())
                        .price_sickle(userDto.getPrice_sickle())
                        .price_knut(userDto.getPrice_knut())
                        .build();

        UserModel savedObject = userService.store(userModel);

        return "Usuario creado - " + savedObject.getName();

    }

    @GetMapping("/")
    public List<UserModel> getAllUsers() {
        return userService.get();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable Long id) {
        return userService.get(id);
    }
}
