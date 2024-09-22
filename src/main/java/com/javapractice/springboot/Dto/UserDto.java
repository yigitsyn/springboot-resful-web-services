package com.javapractice.springboot.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(
        description = "UserDto model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    // swagger Document specification for dto
    @Schema(
            description = "user first name"
    )
    private String id;
    @NotEmpty(message = "user firstname should not be empyt.")
    private String firstName;
    @Schema(
            description = "user last name"
    )
    @NotEmpty(message = "User lastname should not be empyt.")
    private String lastName;


    @Schema(
            description = "user email"
    )

    //boş olmaması ve @sonra tek karakter gelse bile kaydediyor.
    @NotEmpty(message = "user email should not be empyt.")
    @Email(message = "email address should be valid.")
    private String email;
    private LocalDateTime creationDate;
}
