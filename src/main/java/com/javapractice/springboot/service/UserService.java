package com.javapractice.springboot.service;

import com.javapractice.springboot.exception.EmailAlreadyExist;
import com.javapractice.springboot.exception.UserNotFoundException;
import com.javapractice.springboot.Dto.UserDto;
import com.javapractice.springboot.entity.User;
import com.javapractice.springboot.mapper.UserMapper;
import com.javapractice.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto userDto) {
        //modelmapper usage

        //return modelMapper.map(userRepository.save(modelMapper.map(userDto,User.class)),UserDto.class);
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExist(userDto.getEmail()+" is already used" );
        }
        //mapStruct usage
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
    }

    public UserDto getUserById(String id) {
        //model mapper usage
        //return userRepository.findById(id).map(user -> modelMapper.map(user,UserDto.class)).orElseThrow();
        //mapStruct usage
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user","id",id)
                )
        );
    }

    public List<UserDto> getAllUsers(){
        //model mapper usage
        //return userRepository.findAll().stream().map(user->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        //mapStruct usage
        return userRepository.findAll().stream().map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
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
        return  //modelMapper.map(updatedUser,UserDto.class);
                userMapper.mapToUserDto(updatedUser);
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


}
