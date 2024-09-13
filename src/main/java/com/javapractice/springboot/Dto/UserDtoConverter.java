package com.javapractice.springboot.Dto;

import com.javapractice.springboot.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDtoConverter {

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                LocalDateTime.now()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                LocalDateTime.now()
        );
        return user;
    }
}
