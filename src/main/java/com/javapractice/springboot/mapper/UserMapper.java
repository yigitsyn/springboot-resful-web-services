package com.javapractice.springboot.mapper;

import com.javapractice.springboot.Dto.UserDto;
import com.javapractice.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")

public interface UserMapper {
    //private final injection
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    // User entity'sini UserDto'ya dönüştürme
    UserDto mapToUserDto(User user);

    // eğer user entity ve dto daki varibale isimleri aynı değilse
    // @Mapping(source = "$entityVariableName",target="$dtoVariableName" -> source = "email",target ="emailAdress")
    User mapToUser(UserDto userDto);
}
