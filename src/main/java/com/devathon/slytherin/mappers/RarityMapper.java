package com.devathon.slytherin.mappers;
import org.mapstruct.Mapper;
import com.devathon.slytherin.DTOs.RarityDto;
import com.devathon.slytherin.config.MapperConfig;
import com.devathon.slytherin.models.RarityModel;

@Mapper(config = MapperConfig.class)
public interface RarityMapper {
    RarityDto tRarityDto(RarityModel rarityModel);
}
