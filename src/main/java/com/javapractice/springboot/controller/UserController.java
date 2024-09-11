package com.javapractice.springboot.controller;

import com.javapractice.springboot.entity.User;
import com.javapractice.springboot.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("users/api")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //build create User REST API
    //http://localhost:8080/users/api Post Req
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id RestAPI
    //http://localhost:8080/users/api/6675b2dd-0d7f-44ee-9c14-22ac107863cb
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    // build all users RESTAPI
    //http://localhost:8080/users/api Get Req
    @GetMapping
    public ResponseEntity<List<User>> getUserById() {
       List <User> userList = new ArrayList<>();
       userList = userService.getAllUsers();
       return ResponseEntity.ok(userList);
    }

    // build Update User Rest Api
    //  http://localhost:8080/users/api/6675b2dd-0d7f-44ee-9c14-22ac107863cb Put + header
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        // request bodydeki id alanı boş olduğu yani urlden aldığı için burada id setlemesi yapılması  gerekir.
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // http://localhost:8080/users/api/6675b2dd-0d7f-44ee-9c14-22ac107863cb Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Deleted user with id " + userId);
    }


}
