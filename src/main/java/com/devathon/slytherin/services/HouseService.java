package com.devathon.slytherin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.models.HouseModel;
import com.devathon.slytherin.repositories.HouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseModel store(HouseModel houseModel) {
        return houseRepository.save(houseModel);
    }
    public List<HouseModel> get() {
        return houseRepository.findAll();
    }
    //obtener house by id
    public HouseModel getHouseModel(Long id) {
        return houseRepository
        .findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("The house not exist.")
        );
    }
}