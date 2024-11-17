package com.example.project_java.admin.service;

import com.example.project_java.admin.dto.AdminUserDto;
import com.example.project_java.common.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminUserService {
    List<User> getUsers();
    User getUserById(String userId);
    User addUser(AdminUserDto adminUserDto);
    User updateUser(String userId,AdminUserDto adminUserDto);
    void deleteUser(String userId);
    String uploadImage(String userId,MultipartFile file);
}
