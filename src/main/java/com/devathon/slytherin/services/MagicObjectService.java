package com.devathon.slytherin.services;
import org.springframework.stereotype.Service;
import com.devathon.slytherin.model.MagicObjectModel;
import com.devathon.slytherin.repository.MagicObjectRepository;

@Service
public class MagicObjectService {

    private final MagicObjectRepository magicObjectRepository;

    public MagicObjectService(MagicObjectRepository magicObjectRepository) {
        this.magicObjectRepository = magicObjectRepository;
    }

    public MagicObjectModel store(MagicObjectModel magicObjectmodel) {
        if (magicObjectmodel.getName() == null || magicObjectmodel.getName().isEmpty()) {
            throw new IllegalArgumentException("El objeto mágico debe tener un nombre.");
        }
        return magicObjectRepository.save(magicObjectmodel);
    }
}
