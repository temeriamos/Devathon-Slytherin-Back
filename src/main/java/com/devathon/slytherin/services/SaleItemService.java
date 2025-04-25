package com.devathon.slytherin.services;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devathon.slytherin.DTOs.SaleItemResponseDto;
import com.devathon.slytherin.models.SaleItemModel;
import com.devathon.slytherin.repositories.SaleItemRepository;

@Service
@RequiredArgsConstructor
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemModel store(SaleItemModel saleItemModel) {
        if (saleItemModel.getSale() == null || saleItemModel.getSale().getId() == null) {
            throw new IllegalArgumentException("The sale item must have a valid sale.");
        }
        if (saleItemModel.getObject() == null || saleItemModel.getObject().getId() == null) {
            throw new IllegalArgumentException("The sale item must have a valid magic object.");
        }
        if (saleItemModel.getPrice_galeon() == null && saleItemModel.getPrice_sickle() == null && saleItemModel.getPrice_knut() == null) {
            throw new IllegalArgumentException("The sale item must have a price.");
        }

        return saleItemRepository.save(saleItemModel);
    }

    @Transactional(readOnly = true)
    public List<SaleItemResponseDto> getItemsbySaleId(Long id_sale) {
        List<SaleItemModel> saleItems = saleItemRepository.findBySale_Id(id_sale);
        if (saleItems.isEmpty()) {
            return List.of(); // Devuelve una lista vacía en lugar de `null`
        }
        return saleItems.stream()
                .map(saleItem -> new SaleItemResponseDto(
                        saleItem.getId(),
                        saleItem.getSale().getId(),
                        saleItem.getObject().getId(), // Accede a la relación `object` y luego al ID
                        saleItem.getPrice_galeon(),
                        saleItem.getPrice_sickle(),
                        saleItem.getPrice_knut()
                ))
                .collect(Collectors.toList());
    }
}
