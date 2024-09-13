package com.javapractice.springboot.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime creationDate;
}
