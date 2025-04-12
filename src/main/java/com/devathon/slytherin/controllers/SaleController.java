package com.devathon.slytherin.controllers;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devathon.slytherin.DTOs.CurrencyDto;
import com.devathon.slytherin.DTOs.SaleItemResponseDto;
import com.devathon.slytherin.DTOs.SaleRequestDto;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.models.SaleItemModel;
import com.devathon.slytherin.models.SaleModel;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.repositories.MagicObjectRepository;
import com.devathon.slytherin.repositories.UserRepository;
import com.devathon.slytherin.services.CurrencyService;
import com.devathon.slytherin.services.SaleItemService;
import com.devathon.slytherin.services.SaleService;
import com.devathon.slytherin.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("sale")
@RequiredArgsConstructor
@Tag(name = "Sale", description = "Sale operations")
public class SaleController {

    private final SaleService saleService;
    private final SaleItemService saleItemService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final MagicObjectRepository magicObjectRepository;
    private final CurrencyService currencyService;
    
    public void updatePurchaseTrue(Long id) {      
        MagicObjectModel magicObjectModel = magicObjectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Magic object not found"));
        magicObjectModel.setPurchased(true);
        magicObjectRepository.save(magicObjectModel);   
    }

    @Operation(summary = "Create a new sale", description = "Register a new sale in DB")
    @ApiResponse(responseCode = "201", description = "Sale created successfully")
    @PostMapping("/")
    public String createSale(@RequestBody SaleRequestDto saleRequestDto) {

        //Comprobar el saldo del usuario
        UserModel user = userRepository.findById(saleRequestDto.getUser_id())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!userService.checkSufficientBalance(user, saleRequestDto.getItems())) {
            throw new RuntimeException("User does not have enough balance");
        }

        //Crear la venta
        SaleModel saleModel = SaleModel.builder()
                .user_id(saleRequestDto.getUser_id())
                .date(new Date(System.currentTimeMillis()))
                .price_galeon(saleRequestDto.getTotal_galeon())
                .price_sickle(saleRequestDto.getTotal_sickle())
                .price_knut(saleRequestDto.getTotal_knut())
                .build();

        SaleModel saved = saleService.store(saleModel);

        if (saved.getId() == null) {
            throw new RuntimeException("Error in DB: Sale not created");
        }

        //items de la venta
        for (int i = 0; i < saleRequestDto.getItems().size(); i++) {
            SaleItemModel saleItemModel = SaleItemModel.builder()        
                    .sale(saved)          
                    .object_id(saleRequestDto.getItems().get(i).getObject_id())
                    .price_galeon(saleRequestDto.getItems().get(i).getPrice_galeon())
                    .price_sickle(saleRequestDto.getItems().get(i).getPrice_sickle())
                    .price_knut(saleRequestDto.getItems().get(i).getPrice_knut())               
                    .build();
            
            SaleItemModel savedSaleItem = saleItemService.store(saleItemModel);
            if (savedSaleItem.getId() == null) {
                throw new RuntimeException("Error in DB: Sale item not created");
            }
            
            //Actualizar el campo purchades a comprado
            updatePurchaseTrue(saleRequestDto.getItems().get(i).getObject_id());            
        }

        // Actualizar el total de la venta (ya que podría no coincidir con el frontend)
        CurrencyDto totalPrice = currencyService.calculateTotalPrice(saleRequestDto.getItems());
        saleService.updateSaleTotal(saved, totalPrice);

        // Actualizar el saldo del usuario
        userService.UserPriceUpdate(user, totalPrice);        

        return "Sale " + saved.getId() + " created";
    }

    @Operation(summary = "Obtain a list of sales", description = "Return all sales registered")
    @ApiResponse(responseCode = "200", description = "List of sales returned successfully")
    @GetMapping("/")
    public List<SaleModel> getAllSales() {
        return saleService.get();
    }

    @Operation(summary = "Obtain a list of item sales by sale id", description = "Return all item sales registered")
    @ApiResponse(responseCode = "200", description = "List of item sales returned successfully")
    @GetMapping("/items/{id_sale}")
    public ResponseEntity<List<SaleItemResponseDto>> getAllSaleItems(@PathVariable Long id_sale) {
        return ResponseEntity.ok(saleItemService.getItemsbySaleId(id_sale));
    }

}
