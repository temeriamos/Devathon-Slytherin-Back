package com.devathon.slytherin.services;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.repositories.MagicObjectRepository;

@Service
@RequiredArgsConstructor
public class MagicObjectService {

    private final MagicObjectRepository magicObjectRepository;

//    public MagicObjectService(MagicObjectRepository magicObjectRepository) {
//        this.magicObjectRepository = magicObjectRepository;
//    }

    public MagicObjectModel store(MagicObjectModel magicObjectmodel) {
        if (magicObjectmodel.getName() == null || magicObjectmodel.getName().isEmpty()) {
            throw new IllegalArgumentException("El objeto mágico debe tener un nombre.");
        }
        if (magicObjectmodel.getDescription() == null || magicObjectmodel.getDescription().isEmpty()) {
            throw new IllegalArgumentException("El objeto mágico debe tener una descripción.");
        }
        if (magicObjectmodel.getPrice_galeon() == null && magicObjectmodel.getPrice_sickle() == null && magicObjectmodel.getPrice_knut() == null) {
            throw new IllegalArgumentException("El objeto mágico debe tener un precio.");
        }

        if (Optional.ofNullable(magicObjectmodel.getPrice_galeon()).orElse(0) <= 0 && Optional.ofNullable(magicObjectmodel.getPrice_sickle()).orElse(0) <= 0 && Optional.ofNullable(magicObjectmodel.getPrice_knut()).orElse(0) <= 0 ) {
            throw new IllegalArgumentException("El objeto mágico debe tener un precio mayor que 0.");
        }

        if (magicObjectmodel.getUrl_image() == null || magicObjectmodel.getUrl_image().isEmpty()) {
            throw new IllegalArgumentException("El objeto mágico debe tener una imagen.");
        }
        return magicObjectRepository.save(magicObjectmodel);
    }

    public List<MagicObjectModel> get() {
        return magicObjectRepository.findAll();
    }
}
