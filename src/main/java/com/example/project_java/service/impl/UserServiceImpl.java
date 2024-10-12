package com.example.project_java.service.impl;

import com.example.project_java.dto.UserDto;
import com.example.project_java.entity.User;
import com.example.project_java.repository.UserRepository;
import com.example.project_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(UserDto userDto) {
        //sử dụng builder pattern để dễ quản lý và rành mạch
        User newUser = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .dob(userDto.getDob())
                .build();
        //Lưu user
        User saveUser = userRepository.save(newUser);
        return saveUser;
    }
}
