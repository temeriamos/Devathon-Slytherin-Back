package com.devathon.slytherin.services;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devathon.slytherin.DTOs.SaleItemResponseDto;
import com.devathon.slytherin.models.SaleItemModel;
import com.devathon.slytherin.repositories.SaleItemRepository;

@Service
@RequiredArgsConstructor
public class SaleItemService {
    @Autowired
    private final SaleItemRepository saleItemRepository;

    public SaleItemModel store(SaleItemModel saleItemModel) {
        if (saleItemModel.getSale() == null) {
            throw new IllegalArgumentException("The sale item must have a sale.");
        }
        if (saleItemModel.getSale().getId() == null || saleItemModel.getSale().getId() == 0) {
            throw new IllegalArgumentException("The sale item must have a sale id.");
        }
        if (saleItemModel.getObject_id() == null || saleItemModel.getObject_id() == 0) {
            throw new IllegalArgumentException("The sale item must have a magic object id.");
        }
        if (saleItemModel.getPrice_galeon() == null && saleItemModel.getPrice_sickle() == null && saleItemModel.getPrice_knut() == null) {
            throw new IllegalArgumentException("The sale item must have a price.");
        }

        return saleItemRepository.save(saleItemModel);
    }

    @Transactional(readOnly = true)
    public List <SaleItemResponseDto> getItemsbySaleId(Long id_sale) {
        List<SaleItemModel> saleItems = saleItemRepository.findBySale_Id(id_sale);
        if (saleItems.isEmpty()) {
            return null;
        }
        List<SaleItemResponseDto> saleItemDtos = saleItems.stream()
                .map(saleItem -> new SaleItemResponseDto(
                        saleItem.getId(),
                        saleItem.getSale().getId(),
                        saleItem.getObject_id(),
                        saleItem.getPrice_galeon(),
                        saleItem.getPrice_sickle(),
                        saleItem.getPrice_knut()
                ))
                .collect(Collectors.toList());
        return saleItemDtos;
    }
}
