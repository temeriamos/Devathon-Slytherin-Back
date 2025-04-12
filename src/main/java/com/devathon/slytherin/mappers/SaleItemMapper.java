package com.devathon.slytherin.mappers;
import com.devathon.slytherin.DTOs.SaleItemDto;
import com.devathon.slytherin.config.MapperConfig;
import com.devathon.slytherin.models.SaleItemModel;

import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SaleItemMapper {
    SaleItemDto toSaleItemDto(SaleItemModel saleItemModel);
}
