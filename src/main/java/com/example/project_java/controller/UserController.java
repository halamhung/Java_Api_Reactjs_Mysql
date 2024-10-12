package com.example.project_java.controller;

import com.example.project_java.dto.UserDto;
import com.example.project_java.entity.User;
import com.example.project_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    User createUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
}
