package com.javapractice.springboot.service;


import com.javapractice.springboot.Dto.UserDto;
import com.javapractice.springboot.Dto.UserDtoConverter;
import com.javapractice.springboot.entity.User;
import com.javapractice.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        return UserDtoConverter.mapToUserDto(
                userRepository.save(UserDtoConverter.mapToUser(userDto))
        );
    }

    public UserDto getUserById(String id) {
        return userRepository.findById(id)
                .map(UserDtoConverter::mapToUserDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(UserDtoConverter::mapToUserDto).collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto) {
        // Mevcut kullanıcıyı ID ile bul, bulunamazsa hata fırlat
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.getId()));

        // Kullanıcı bilgilerinin güncellenmesi ve boş değerlere göre update edilmesi
        if (userDto.getFirstName() != null) {
            existingUser.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            existingUser.setLastName(userDto.getLastName());
        }

        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }

        // Güncellenmiş kullanıcıyı veritabanına kaydet
        User updatedUser = userRepository.save(existingUser);

        // Güncellenmiş kullanıcıyı DTO'ya dönüştür ve geri döndür
        return UserDtoConverter.mapToUserDto(updatedUser);
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


}
