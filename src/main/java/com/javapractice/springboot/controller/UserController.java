package com.javapractice.springboot.controller;

import com.javapractice.springboot.Dto.UserDto;
import com.javapractice.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - create user, update user, delete user, getUser ,Gel all Users,"
)
@RestController
@RequestMapping("users/api")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Swagger Documentation Rest Api description to identify what they do.
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in db"
    ) // Response desc
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201", description = "HTTP Status 201 Created"
            )
        }
    )
    //build create User REST API
    //http://localhost:8080/users/api Post Req
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        savedUser.setCreationDate(LocalDateTime.now());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    // Swagger Documentation Rest Api description to identify what they do.
    @Operation(
            summary = "Get User REST API",
            description = "Get User REST API is used to get user in db"
    )
    // Response desc
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200", description = "HTTP Status 200 Success."
            )
        }
    )
    //build get user by id RestAPI
    //http://localhost:8080/users/api/6675b2dd-0d7f-44ee-9c14-22ac107863cb
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    // build all users RESTAPI
    //http://localhost:8080/users/api Get Req
    @GetMapping
    public ResponseEntity<List<UserDto>> getUserById() {
       List <UserDto> userList = new ArrayList<>();
       userList = userService.getAllUsers();
       return ResponseEntity.ok(userList);
    }

    // build Update User Rest Api
    //  http://localhost:8080/users/api/6675b2dd-0d7f-44ee-9c14-22ac107863cb Put + header
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @Valid @RequestBody UserDto userDto) {
        // request bodydeki id alanı boş olduğu yani urlden aldığı için burada id setlemesi yapılması  gerekir.
        userDto.setId(id);
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // http://localhost:8080/users/api/6675b2dd-0d7f-44ee-9c14-22ac107863cb Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Deleted user with id " + userId);
    }


}
