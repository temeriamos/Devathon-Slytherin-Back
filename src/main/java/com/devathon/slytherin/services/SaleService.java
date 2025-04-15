package com.devathon.slytherin.services;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.CurrencyDto;
import com.devathon.slytherin.DTOs.PurchaseHistoryDto;
import com.devathon.slytherin.models.SaleModel;
import com.devathon.slytherin.models.SaleItemModel;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.repositories.SaleRepository;
import com.devathon.slytherin.repositories.SaleItemRepository;
import com.devathon.slytherin.repositories.MagicObjectRepository;
import com.devathon.slytherin.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final MagicObjectRepository magicObjectRepository;
    private final SaleItemRepository saleItemRepository;

    public SaleModel store(SaleModel saleModel) {
        if (saleModel.getUser() == null || saleModel.getUser().getId() == null) {
            throw new IllegalArgumentException("The sale must have a valid user.");
        }
        if (saleModel.getDate() == null) {
            throw new IllegalArgumentException("The sale must have a date.");
        }
        if (saleModel.getPrice_galeon() == null && saleModel.getPrice_sickle() == null && saleModel.getPrice_knut() == null) {
            throw new IllegalArgumentException("The sale must have a price.");
        }
        return saleRepository.save(saleModel);
    }

    public List<SaleModel> get() {
        return saleRepository.findAll();
    }

    public void updateSaleTotal(SaleModel saleModel, CurrencyDto totalPrice) {
        saleModel.setPrice_galeon(totalPrice.getGaleons());
        saleModel.setPrice_sickle(totalPrice.getSickles());
        saleModel.setPrice_knut(totalPrice.getKnuts());
        saleModel = store(saleModel);
        if (saleModel.getId() == null) {
            throw new RuntimeException("Error in DB: Sale not updated");
        }
    }

    @Transactional(readOnly = true)
    public List<PurchaseHistoryDto> getPurchaseHistoryByUserId(Long userId) {
        UserModel user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        List<SaleModel> sales = saleRepository.findByUserOrderByDateDesc(user);

        return sales.stream().flatMap(sale -> {
            List<SaleItemModel> saleItems = saleItemRepository.findBySale_Id(sale.getId());
            return saleItems.stream().map(saleItem -> PurchaseHistoryDto.builder()
                .date(sale.getDate().toString())
                .objectName(saleItem.getObject().getName()) // Incluye el nombre del objeto
                .priceGaleon(saleItem.getPrice_galeon())
                .priceSickle(saleItem.getPrice_sickle())
                .priceKnut(saleItem.getPrice_knut())
                .build());
        }).collect(Collectors.toList());
    }

    private String getObjectNameFromSale(SaleModel sale) {
        return saleItemRepository.findBySale_Id(sale.getId()).stream()
                .findFirst() // Suponiendo que cada venta tiene al menos un objeto
                .map(saleItem -> saleItem.getObject().getName()) // Accede a la relación `object` y luego al nombre
                .orElse("Unknown Object");
    }
}
