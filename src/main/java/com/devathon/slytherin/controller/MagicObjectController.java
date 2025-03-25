package com.devathon.slytherin.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devathon.slytherin.DTO.MagicObject;
import com.devathon.slytherin.model.MagicObjectModel;
import com.devathon.slytherin.services.MagicObjectService;

@RestController
@RequestMapping("magicobject")
public class MagicObjectController {

    private final MagicObjectService magicObjectService;

    public MagicObjectController(MagicObjectService magicObjectService) {
        this.magicObjectService = magicObjectService;
    }

    @PostMapping("/")
    public String createMagicObject (@RequestBody MagicObject magicObject) {
        MagicObjectModel magicObjectmodel = new MagicObjectModel(magicObject.getName(), magicObject.getDescription(), magicObject.getPrice(), magicObject.getImage());   
        MagicObjectModel savedObject = magicObjectService.store(magicObjectmodel);

        return "Objeto creado - " + savedObject.getName();
    }
}