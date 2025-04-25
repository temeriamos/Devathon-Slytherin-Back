package com.devathon.slytherin.mappers;
import org.mapstruct.Mapper;
import com.devathon.slytherin.DTOs.UserDto;
import com.devathon.slytherin.config.MapperConfig;
import com.devathon.slytherin.models.UserModel;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserDto toUserDto(UserModel userModel);

}
