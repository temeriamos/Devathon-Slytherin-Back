package com.devathon.slytherin.mappers;

import com.devathon.slytherin.DTOs.MagicObjectDto;
import com.devathon.slytherin.DTOs.MagicObjectResponseDto;
import com.devathon.slytherin.config.MapperConfig;
import com.devathon.slytherin.models.MagicObjectModel;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MagicObjectMapper {

    MagicObjectResponseDto toMagicObjectDto(MagicObjectModel magicObjectModel);

}
