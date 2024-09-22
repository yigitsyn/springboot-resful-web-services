package com.javapractice.springboot.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    @NotEmpty(message = "user firstname should not be empyt.")
    private String firstName;
    @NotEmpty(message = "User lastname should not be empyt.")
    private String lastName;
    //boş olmaması ve @sonra tek karakter gelse bile kaydediyor.
    @NotEmpty(message = "user email should not be empyt.")
    @Email(message = "email address should be valid.")
    private String email;
    private LocalDateTime creationDate;
}
