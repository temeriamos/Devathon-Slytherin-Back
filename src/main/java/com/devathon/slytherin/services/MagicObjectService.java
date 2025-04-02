package com.devathon.slytherin.services;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devathon.slytherin.DTOs.CategoryResponseDto;
import com.devathon.slytherin.DTOs.MagicObjectResponseDto;
import com.devathon.slytherin.DTOs.RarityResponseDto;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.repositories.MagicObjectRepository;

@Service
@RequiredArgsConstructor
public class MagicObjectService {

    private final MagicObjectRepository magicObjectRepository;

    public MagicObjectModel store(MagicObjectModel magicObjectmodel) {
        if (magicObjectmodel.getName() == null || magicObjectmodel.getName().isEmpty()) {
            throw new IllegalArgumentException("The magic object must have a name.");
        }
        if (magicObjectmodel.getShort_description() == null || magicObjectmodel.getShort_description().isEmpty()) {
            throw new IllegalArgumentException("The magic object must have a short description.");
        }
        if (magicObjectmodel.getPrice_galeon() == null && magicObjectmodel.getPrice_sickle() == null && magicObjectmodel.getPrice_knut() == null) {
            throw new IllegalArgumentException("The magic object must have a price.");
        }

        if (Optional.ofNullable(magicObjectmodel.getPrice_galeon()).orElse(0) <= 0 && Optional.ofNullable(magicObjectmodel.getPrice_sickle()).orElse(0) <= 0 && Optional.ofNullable(magicObjectmodel.getPrice_knut()).orElse(0) <= 0 ) {
            throw new IllegalArgumentException("The magic object must have a price greater than 0.");
        }

        if (magicObjectmodel.getUrl_image() == null || magicObjectmodel.getUrl_image().isEmpty()) {
            throw new IllegalArgumentException("The magic object must have an image.");
        }
        return magicObjectRepository.save(magicObjectmodel);
    }

    public List<MagicObjectResponseDto> get() {
        return magicObjectRepository.findAll().stream()
            .map(magicobject -> {
                MagicObjectResponseDto magicobjectResponseDto = new MagicObjectResponseDto();
                magicobjectResponseDto.setId(magicobject.getId());
                magicobjectResponseDto.setName(magicobject.getName());
                magicobjectResponseDto.setShort_description(magicobject.getShort_description());
                magicobjectResponseDto.setLong_description(magicobject.getLong_description());
                magicobjectResponseDto.setCategory(new CategoryResponseDto(
                    magicobject.getCategory().getId(),
                    magicobject.getCategory().getName()
                ));
                magicobjectResponseDto.setRarity(new RarityResponseDto(
                    magicobject.getRarity().getId(),
                    magicobject.getRarity().getName()
                ));
                magicobjectResponseDto.setPrice_galeon(magicobject.getPrice_galeon());
                magicobjectResponseDto.setPrice_sickle(magicobject.getPrice_sickle());
                magicobjectResponseDto.setPrice_knut(magicobject.getPrice_knut());
                magicobjectResponseDto.setUrl_image(magicobject.getUrl_image());
                magicobjectResponseDto.setPurchased(magicobject.isPurchased());
                return magicobjectResponseDto;
            })
            .toList();
    }

    public MagicObjectResponseDto get(Long id) {

        return magicObjectRepository
        .findById(id)
        .map(magicobject -> {
            MagicObjectResponseDto magicobjectResponseDto = new MagicObjectResponseDto();
                magicobjectResponseDto.setId(magicobject.getId());
                magicobjectResponseDto.setName(magicobject.getName());
                magicobjectResponseDto.setShort_description(magicobject.getShort_description());
                magicobjectResponseDto.setLong_description(magicobject.getLong_description());
                magicobjectResponseDto.setCategory(new CategoryResponseDto(
                    magicobject.getCategory().getId(),
                    magicobject.getCategory().getName()
                ));
                magicobjectResponseDto.setRarity(new RarityResponseDto(
                    magicobject.getRarity().getId(),
                    magicobject.getRarity().getName()
                ));
                magicobjectResponseDto.setPrice_galeon(magicobject.getPrice_galeon());
                magicobjectResponseDto.setPrice_sickle(magicobject.getPrice_sickle());
                magicobjectResponseDto.setPrice_knut(magicobject.getPrice_knut());
                magicobjectResponseDto.setUrl_image(magicobject.getUrl_image());
                magicobjectResponseDto.setPurchased(magicobject.isPurchased());
            return magicobjectResponseDto;
        })
        .orElseThrow(
            () -> new IllegalArgumentException("Magic object not exist")
        );
    }
}
