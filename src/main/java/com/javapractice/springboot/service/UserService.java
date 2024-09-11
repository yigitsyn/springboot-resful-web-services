package com.javapractice.springboot.service;


import com.javapractice.springboot.entity.User;
import com.javapractice.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user) {
       User existingUser = userRepository.findById(user.getId()).orElseThrow();
           existingUser.setFirstName(user.getFirstName());
           existingUser.setLastName(user.getLastName());
           existingUser.setEmail(user.getEmail());
           User updatedUser = userRepository.save(existingUser);
           return updatedUser;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


}
