package com.devathon.slytherin.services;

import com.devathon.slytherin.DTOs.MagicObjectPaginatorResponseDto;
import com.devathon.slytherin.mappers.MagicObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.devathon.slytherin.DTOs.CategoryResponseDto;
import com.devathon.slytherin.DTOs.MagicObjectResponseDto;
import com.devathon.slytherin.DTOs.RarityResponseDto;
import com.devathon.slytherin.DTOs.SaleItemResponseDto;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.models.SaleItemModel;
import com.devathon.slytherin.repositories.MagicObjectRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MagicObjectService {

    private final MagicObjectRepository magicObjectRepository;
    private final MagicObjectMapper magicObjectMapper;

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

        if (Optional.ofNullable(magicObjectmodel.getPrice_galeon()).orElse(0) <= 0 && Optional.ofNullable(magicObjectmodel.getPrice_sickle()).orElse(0) <= 0 && Optional.ofNullable(magicObjectmodel.getPrice_knut()).orElse(0) <= 0) {
            throw new IllegalArgumentException("The magic object must have a price greater than 0.");
        }

        if (magicObjectmodel.getUrl_image() == null || magicObjectmodel.getUrl_image().isEmpty()) {
            throw new IllegalArgumentException("The magic object must have an image.");
        }
        return magicObjectRepository.save(magicObjectmodel);
    }

    @Transactional(readOnly = true)
    public MagicObjectPaginatorResponseDto get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MagicObjectModel> magicObjectPage = magicObjectRepository.findAll(pageable);
        List<MagicObjectResponseDto> magicObjectDtos = magicObjectPage.getContent()
                .stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());

        return new MagicObjectPaginatorResponseDto(magicObjectDtos, magicObjectPage.getTotalPages(), magicObjectPage.getSize());
    }

    @Transactional(readOnly = true)
    public MagicObjectPaginatorResponseDto getByCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MagicObjectModel> magicObjectPage;

        if (category != null && !category.isEmpty()) {
            magicObjectPage = magicObjectRepository.findByCategoryName(category, pageable);
        } else {
            magicObjectPage = magicObjectRepository.findAll(pageable);
        }

        List<MagicObjectResponseDto> magicObjectDtos = magicObjectPage.getContent()
                .stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());

        return new MagicObjectPaginatorResponseDto(magicObjectDtos, magicObjectPage.getTotalPages(), magicObjectPage.getSize());
    }

  

    @Transactional(readOnly = true)
    public MagicObjectPaginatorResponseDto getUnsoldMagicObjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MagicObjectModel> magicObjectPage = magicObjectRepository.findByPurchased(false, pageable);

        List<MagicObjectResponseDto> magicObjectDtos = magicObjectPage.getContent()
                .stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());

        return new MagicObjectPaginatorResponseDto(magicObjectDtos, magicObjectPage.getTotalPages(), magicObjectPage.getSize());
    }

    @Transactional(readOnly = true)
    public MagicObjectPaginatorResponseDto getByCategoryId(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MagicObjectModel> magicObjectPage;

        if (categoryId != null) {
            magicObjectPage = magicObjectRepository.findByCategory_Id(categoryId, pageable);
        } else {
            magicObjectPage = magicObjectRepository.findAll(pageable);
        }

        List<MagicObjectResponseDto> magicObjectDtos = magicObjectPage.getContent()
                .stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());

        return new MagicObjectPaginatorResponseDto(magicObjectDtos, magicObjectPage.getNumber(), magicObjectPage.getSize());
    }

    @Transactional(readOnly = true)
    public MagicObjectPaginatorResponseDto getFilteredMagicObjects(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MagicObjectModel> magicObjectPage;

        if (categoryId != null) {
            magicObjectPage = magicObjectRepository.findByCategory_IdAndPurchased(categoryId, false, pageable);
        } else {
            magicObjectPage = magicObjectRepository.findAll(pageable);
        }

        List<MagicObjectResponseDto> magicObjectDtos = magicObjectPage.getContent()
                .stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());

        return new MagicObjectPaginatorResponseDto(magicObjectDtos, magicObjectPage.getTotalPages(), magicObjectPage.getSize());
    }

    @Transactional(readOnly = true)
    public List<MagicObjectResponseDto> getFilteredMagicObjectsRarity(Long rarityId) {
        List<MagicObjectModel> magicObjects;

        if (rarityId != null) {
            magicObjects = magicObjectRepository.findByRarity_Id(rarityId);
        } else {
            magicObjects = magicObjectRepository.findAll();
        }

        return magicObjects.stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());
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

    @Transactional(readOnly = true)
    public MagicObjectPaginatorResponseDto searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MagicObjectModel> magicObjectPage = magicObjectRepository.findByNameContainingIgnoreCase(name, pageable);

        List<MagicObjectResponseDto> magicObjectDtos = magicObjectPage.getContent()
                .stream()
                .map(magicObjectMapper::toMagicObjectDto)
                .collect(Collectors.toList());

        return new MagicObjectPaginatorResponseDto(magicObjectDtos, magicObjectPage.getNumber(), magicObjectPage.getSize());
    }
}
