package com.devathon.slytherin.services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devathon.slytherin.models.RarityModel;
import com.devathon.slytherin.repositories.RarityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RarityService {
    private final RarityRepository rarityRepository;

    public RarityModel store(RarityModel rarityModel) {
        if (rarityModel.getName() == null || rarityModel.getName().isEmpty()) {
            throw new IllegalArgumentException("The name of the rarity cannot be null or empty.");
        }
        return rarityRepository.save(rarityModel);
    }

    public List<RarityModel> get() {
        return rarityRepository.findAll();
    }
}
