package com.devathon.slytherin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.HouseBasicDto;
import com.devathon.slytherin.DTOs.UserResponseDto;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public UserModel store(UserModel userModel) {
        if (userModel.getName() == null || userModel.getName().isEmpty()) {
            throw new IllegalArgumentException("User needs a name");
        }
        if (userModel.getPrice_galeon() == null && userModel.getPrice_sickle() == null && userModel.getPrice_knut() == null) {
            throw new IllegalArgumentException("Price values ​​must be different from null.");
        }

        return userRepository.save(userModel);
    }

    public List<UserResponseDto> get() {
        return userRepository.findAll().stream()
            .map(user -> {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setId(user.getId());
                userResponseDto.setName(user.getName());
                userResponseDto.setPrice_galeon(user.getPrice_galeon());
                userResponseDto.setPrice_sickle(user.getPrice_sickle());
                userResponseDto.setPrice_knut(user.getPrice_knut());
                userResponseDto.setHouse(new HouseBasicDto(
                user.getHouseModel().getId(),
                user.getHouseModel().getName()
            ));
                return userResponseDto;
            })
            .toList();
    }

    public UserResponseDto get(Long id) {

        // Obtener usuario por is y relacionar con tabla House
        return userRepository
        .findById(id)
        .map(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setName(user.getName());
            userResponseDto.setPrice_galeon(user.getPrice_galeon());
            userResponseDto.setPrice_sickle(user.getPrice_sickle());
            userResponseDto.setPrice_knut(user.getPrice_knut());
            userResponseDto.setHouse(new HouseBasicDto(
                user.getHouseModel().getId(),
                user.getHouseModel().getName()
            ));
            return userResponseDto;
        })
        .orElseThrow(
            () -> new IllegalArgumentException("User not exist")
        );
    }
}
