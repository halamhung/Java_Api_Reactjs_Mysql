package com.example.project_java.admin.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.project_java.admin.dto.AdminUserDto;
import com.example.project_java.admin.repository.AdminUserRepository;
import com.example.project_java.admin.service.AdminUserService;
import com.example.project_java.common.entity.Image;
import com.example.project_java.common.entity.User;
import com.example.project_java.common.exception.ImageUploadException;
import com.example.project_java.common.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<User> getUsers() {
        return adminUserRepository.findAll();
    }

    @Override
    public User getUserById(String userId){
        return adminUserRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found with ID:"+ userId));
    };

    @Override
    public User addUser(AdminUserDto adminUserDto) {

        if (adminUserRepository.existsByUsername(adminUserDto.getUsername())) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại.");
        }
        //sử dụng builder pattern để dễ quản lý và rành mạch
        User newUser = User.builder()
                .username(adminUserDto.getUsername())
                .password(adminUserDto.getPassword())
                .firstname(adminUserDto.getFirstname())
                .lastname(adminUserDto.getLastname())
                .dob(adminUserDto.getDob())
                .build();
        //Lưu user
        User saveUser = adminUserRepository.save(newUser);
        return saveUser;
    }

    @Override
    public User updateUser(String userId,AdminUserDto adminUserDto) {
        User existingUser = adminUserRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found with ID: " + userId));
        existingUser.setUsername(existingUser.getUsername());
        existingUser.setPassword(adminUserDto.getPassword());
        existingUser.setFirstname(adminUserDto.getFirstname());
        existingUser.setLastname(adminUserDto.getLastname());
        existingUser.setDob(adminUserDto.getDob());
        return adminUserRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String userId){
        User existingUser = adminUserRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found with ID: " + userId));
        adminUserRepository.delete(existingUser);
    }

    @Override
    public String uploadImage(String userId, MultipartFile file) {
        User existingUser = adminUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Image image = existingUser.getImage();
        if (image == null) {
            image = new Image();
            existingUser.setImage(image);
        }

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            image.setUrl(imageUrl);
            adminUserRepository.save(existingUser);
            return imageUrl;

        } catch (IOException e) {
            throw new ImageUploadException("Upload ảnh thất bại cho người dùng " + userId + e.getMessage());
        }
    }


}
