package com.devathon.slytherin.services;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.CurrencyDto;
import com.devathon.slytherin.models.SaleModel;
import com.devathon.slytherin.repositories.SaleRepository;

@Service
@RequiredArgsConstructor
public class SaleService {
    @Autowired
    private final SaleRepository saleRepository;

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
}
