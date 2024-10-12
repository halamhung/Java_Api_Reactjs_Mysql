package com.example.project_java.service;

import com.example.project_java.dto.UserDto;
import com.example.project_java.entity.User;
import com.example.project_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {
    User addUser(UserDto userDto);
}
