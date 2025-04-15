package com.devathon.slytherin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devathon.slytherin.DTOs.CurrencyDto;
import com.devathon.slytherin.DTOs.HouseBasicDto;
import com.devathon.slytherin.DTOs.SaleItemRequestDto;
import com.devathon.slytherin.DTOs.UserResponseDto;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final CurrencyService currencyService;

    public UserModel store(UserModel userModel) {
        if (userModel.getName() == null || userModel.getName().isEmpty()) {
            throw new IllegalArgumentException("User needs a name");
        }
        if (userModel.getPrice_galeon() == null && userModel.getPrice_sickle() == null && userModel.getPrice_knut() == null) {
            throw new IllegalArgumentException("Price values ​​must be different from null.");
        }

        return userRepository.save(userModel);
    }

    public List<UserResponseDto> get() {
        return userRepository.findAll().stream()
            .map(user -> {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setId(user.getId());
                userResponseDto.setName(user.getName());
                userResponseDto.setPrice_galeon(user.getPrice_galeon());
                userResponseDto.setPrice_sickle(user.getPrice_sickle());
                userResponseDto.setPrice_knut(user.getPrice_knut());
                /*userResponseDto.setHouse(new HouseBasicDto(
                user.getHouseModel().getId(),
                user.getHouseModel().getName()
            ));*/
                return userResponseDto;
            })
            .toList();
    }

    public UserResponseDto get(Long id) {

        // Obtener usuario por is y relacionar con tabla House
        return userRepository
        .findById(id)
        .map(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setName(user.getName());
            userResponseDto.setPrice_galeon(user.getPrice_galeon());
            userResponseDto.setPrice_sickle(user.getPrice_sickle());
            userResponseDto.setPrice_knut(user.getPrice_knut());
            /*userResponseDto.setHouse(new HouseBasicDto(
                user.getHouseModel().getId(),
                user.getHouseModel().getName()
            ));*/
            return userResponseDto;
        })
        .orElseThrow(
            () -> new IllegalArgumentException("User not exist")
        );
    }

    public boolean checkSufficientBalance(UserModel user, List<SaleItemRequestDto> saleItems) {
        // Convertir el saldo del usuario a knuts
        double userBalance = currencyService.convertToKnuts(user.getPrice_galeon(), user.getPrice_sickle(), user.getPrice_knut());
        // Calcular el total de los objetos
        CurrencyDto totalItemsPrice = currencyService.calculateTotalPrice(saleItems);
        double totalPrice = currencyService.convertToKnuts(totalItemsPrice.getGaleons(), totalItemsPrice.getSickles(), totalItemsPrice.getKnuts());

        //Comparar el saldo del usuario con el total de los objetos
        if (userBalance >= totalPrice) {
            return true;
        } else {
            // saldo insuficiente
            return false;
        }
    }

    public void UserPriceUpdate(UserModel user, CurrencyDto totalPrice) {
        //Para evitar valores en negativo, se convierte todo a knuts y se hace la resta
        int userTotalKnuts = currencyService.convertToKnuts(user.getPrice_galeon(), user.getPrice_sickle(), user.getPrice_knut());
        int priceTotalKnuts = currencyService.convertToKnuts(totalPrice.getGaleons(), totalPrice.getSickles(), totalPrice.getKnuts());
        int remainingKnuts = userTotalKnuts - priceTotalKnuts;

        CurrencyDto updatedBalance = currencyService.convertFromKnuts(remainingKnuts);       
        user.setPrice_galeon(updatedBalance.getGaleons());
        user.setPrice_sickle(updatedBalance.getSickles());
        user.setPrice_knut(updatedBalance.getKnuts());
        user = store(user);

        if (user.getId() == null) {
            throw new RuntimeException("Error in DB: User not updated");
        }
    }

}
