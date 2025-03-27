package com.devathon.slytherin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devathon.slytherin.DTOs.MagicObjectDto;
import com.devathon.slytherin.models.MagicObjectModel;
import com.devathon.slytherin.services.MagicObjectService;

@RestController
@RequestMapping("magicobject")
@RequiredArgsConstructor
public class MagicObjectController {

    private final MagicObjectService magicObjectService;

    // La anotacion @RequiredArgsConstructor reemplaza esto
//    public MagicObjectController(MagicObjectService magicObjectService) {
//        this.magicObjectService = magicObjectService;
//    }


    @PostMapping("/")
    public String createMagicObject(@RequestBody MagicObjectDto magicObjectDto) {

        /**
         * La anotacion @Builder dentro del Model -> MagicObjectModel , hace que podamos usar el patron Builder que es una mejor forma de crear un nuevo objeto
         */

//        MagicObjectModel magicObjectmodel = new MagicObjectModel(magicObject.getName(), magicObject.getDescription(), magicObject.getPrice(), magicObject.getImage());

        MagicObjectModel magicObjectmodel =
                MagicObjectModel.builder()
                        .name(magicObjectDto.getName())
                        .description(magicObjectDto.getDescription())
                        .price(magicObjectDto.getPrice())
                        .image(magicObjectDto.getImage())
                        .build();

        MagicObjectModel savedObject = magicObjectService.store(magicObjectmodel);

        return "Objeto creado - " + savedObject.getName();
    }
}