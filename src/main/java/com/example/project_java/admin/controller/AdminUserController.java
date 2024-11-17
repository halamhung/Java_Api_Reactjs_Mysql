package com.example.project_java.admin.controller;

import com.example.project_java.admin.dto.AdminUserDto;
import com.example.project_java.admin.service.AdminUserService;
import com.example.project_java.common.entity.User;
import com.example.project_java.common.exception.ApiResponse;
import com.example.project_java.common.exception.ImageUploadException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("admin/users")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<ApiResponse> getUsers(){
            List<User> users = adminUserService.getUsers();
            return ResponseEntity.ok(ApiResponse.builder()
                            .status(HttpStatus.OK.value())
                            .message("get all users successfully")
                            .data(users)
                            .timestamp(java.time.LocalDateTime.now())
                            .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String userId){
        User user = adminUserService.getUserById(userId)

                ;
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Get user by id: " + userId + " is Successfully")
                        .data(user)
                        .timestamp(java.time.LocalDateTime.now())
                        .build());
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody AdminUserDto adminUserDto){
        User user = adminUserService.addUser(adminUserDto);
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Add user successfully")
                        .data(user)
                        .timestamp(java.time.LocalDateTime.now())
                        .build());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String userId ,@Valid @RequestBody AdminUserDto adminUserDto){
        User user = adminUserService.updateUser(userId, adminUserDto);
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Update user with id: " + userId + " is successfully")
                        .data(user)
                        .timestamp(java.time.LocalDateTime.now())
                        .build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
          adminUserService.deleteUser(userId);
          return ResponseEntity.ok(ApiResponse.builder()
                          .status(HttpStatus.OK.value())
                          .message("Delete user with id: " + userId + " is success fully")
                          .data(userId)
                          .timestamp(java.time.LocalDateTime.now())
                          .build());
    }

    @PostMapping("/{userId}/image")
    public ResponseEntity<ApiResponse> uploadImage(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        String imageUrl = adminUserService.uploadImage(userId, file);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Upload image user successfully with ID: " + userId)
                .data(imageUrl) // Trả về imageUrl trong trường data
                .timestamp(LocalDateTime.now())
                .build());

    }
}
