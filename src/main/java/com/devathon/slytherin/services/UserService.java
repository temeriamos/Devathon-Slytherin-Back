package com.devathon.slytherin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public UserModel store(UserModel userModel) {
        if (userModel.getName() == null || userModel.getName().isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener un nombre.");
        }
        if (userModel.getPrice_galeon() == null && userModel.getPrice_sickle() == null && userModel.getPrice_knut() == null) {
            throw new IllegalArgumentException("Los valores de los precios deben ser diferentes de nulo.");
        }

        return userRepository.save(userModel);
    }

    public List<UserModel> get() {
        return userRepository.findAll();
    }

    public UserModel get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El usuario no existe."));
    }
}
