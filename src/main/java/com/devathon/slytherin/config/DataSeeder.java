package com.devathon.slytherin.config;

import com.devathon.slytherin.models.HouseModel;
import com.devathon.slytherin.models.UserModel;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.repositories.HouseRepository;
import com.devathon.slytherin.repositories.UserRepository;
import com.devathon.slytherin.repositories.MagicObjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final MagicObjectRepository magicObjectRepository;

    public DataSeeder(HouseRepository houseRepository,
                      UserRepository userRepository,
                      MagicObjectRepository magicObjectRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.magicObjectRepository = magicObjectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (houseRepository.count() == 0) {
            // 1. Crear casas
            HouseModel sanDamian = houseRepository.save(
                    HouseModel.builder()
                            .name("SAN DAMIAN")
                            .build());

            HouseModel slytherin = houseRepository.save(
                    HouseModel.builder()
                            .name("SLYTHERIN")
                            .build());

            // 2. Crear usuarios asociados a casas
            UserModel user1 = userRepository.save(
                    UserModel.builder()
                            .name("Harry Potter")
                            .price_galeon(50)
                            .price_sickle(30)
                            .price_knut(20)
                            .houseModel(sanDamian)
                            .build());

            UserModel user2 = userRepository.save(
                    UserModel.builder()
                            .name("Draco Malfoy")
                            .price_galeon(40)
                            .price_sickle(25)
                            .price_knut(15)
                            .houseModel(slytherin)
                            .build());
                            
            // 3. Crear objetos mágicos (sin relación)
            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("Varita de Saúco")
                            .short_description("La varita más poderosa del mundo mágico.")
                            .price_galeon(1000)
                            .price_sickle(0)
                            .price_knut(0)
                            .url_image("https://example.com/varita.jpg")
                            .build());

            magicObjectRepository.save(
                    MagicObjectModel.builder()
                            .name("Capa de Invisibilidad")
                            .short_description("Permite al usuario volverse invisible.")
                            .price_galeon(500)
                            .price_sickle(200)
                            .price_knut(50)
                            .url_image("https://example.com/capa.jpg")
                            .build());

            System.out.println("🌱 Datos de prueba creados correctamente.");
        }
    }
}
