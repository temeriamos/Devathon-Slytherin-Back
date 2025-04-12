package com.devathon.slytherin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.CurrencyDto;
import com.devathon.slytherin.DTOs.SaleItemRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    public int convertToKnuts(int galeons, int sickles, int knuts) {
        return (galeons * 493) + (sickles * 29) + knuts;
    }

    public CurrencyDto convertFromKnuts(int totalKnuts) {
        int galeons = totalKnuts / 493;
        int remainderAfterGaleons = totalKnuts % 493;
    
        int sickles = remainderAfterGaleons / 29;
        int knuts = remainderAfterGaleons % 29;
    
        return new CurrencyDto(galeons, sickles, knuts);
    }

    public CurrencyDto calculateTotalPrice(int Totalgaleons, int Totalsickles, int Totalknuts) {
        int totalInKnuts = convertToKnuts(Totalgaleons, Totalsickles, Totalknuts);
        return convertFromKnuts(totalInKnuts);
    }

    public CurrencyDto calculateTotalPrice(List<SaleItemRequestDto> saleItems) {
        int totalGaleons = 0;
        int totalSickles = 0;
        int totalKnuts = 0;

        for (SaleItemRequestDto item : saleItems) {
            totalGaleons += item.getPrice_galeon();
            totalSickles += item.getPrice_sickle();
            totalKnuts += item.getPrice_knut();
        }

        return calculateTotalPrice(totalGaleons, totalSickles, totalKnuts);
    }

}
