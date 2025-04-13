package com.devathon.slytherin.services;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.CurrencyDto;
import com.devathon.slytherin.DTOs.PurchaseHistoryDto;
import com.devathon.slytherin.models.SaleModel;
import com.devathon.slytherin.repositories.SaleRepository;
import com.devathon.slytherin.repositories.SaleItemRepository;
import com.devathon.slytherin.repositories.MagicObjectRepository;
import com.devathon.slytherin.models.MagicObjectModel;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    @Autowired
    private MagicObjectRepository magicObjectRepository;
    @Autowired
    private SaleItemRepository saleItemRepository;

    public SaleModel store(SaleModel saleModel) {

        if (saleModel.getUser_id() == null || saleModel.getUser_id() == 0) {
            throw new IllegalArgumentException("The sale must have a user id.");
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
        // Actualizar el total de la venta (ya que podría no coincidir con el frontend)
        saleModel.setPrice_galeon(totalPrice.getGaleons());
        saleModel.setPrice_sickle(totalPrice.getSickles()); 
        saleModel.setPrice_knut(totalPrice.getKnuts());
        saleModel = store(saleModel); 
        if (saleModel.getId() == null) {
            throw new RuntimeException("Error in DB: Sale not updated");
        }
    }

    public List<PurchaseHistoryDto> getPurchaseHistoryByUserId(Long userId) {
        List<SaleModel> sales = saleRepository.findByUserIdOrderByDateDesc(userId);

        return sales.stream().map(sale -> PurchaseHistoryDto.builder()
                .date(sale.getDate().toString())
                .objectName(getObjectNameFromSale(sale)) // Método auxiliar para obtener el nombre del objeto
                .priceGaleon(sale.getPrice_galeon())
                .priceSickle(sale.getPrice_sickle())
                .priceKnut(sale.getPrice_knut())
                .build())
                .collect(Collectors.toList());
    }

    private String getObjectNameFromSale(SaleModel sale) {
        // Obtén el nombre del objeto asociado a la venta
        return saleItemRepository.findBySale_Id(sale.getId()).stream()
                .findFirst() // Suponiendo que cada venta tiene al menos un objeto
                .map(saleItem -> magicObjectRepository.findById(saleItem.getObject_id())
                        .map(MagicObjectModel::getName)
                        .orElse("Unknown Object"))
                .orElse("Unknown Object");
    }
}
