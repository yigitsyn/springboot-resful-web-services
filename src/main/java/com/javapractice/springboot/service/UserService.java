package com.javapractice.springboot.service;


import com.javapractice.springboot.entity.User;
import com.javapractice.springboot.exception.UserNotFoundException;
import com.javapractice.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Customer could not be found" + id));

    }
}
