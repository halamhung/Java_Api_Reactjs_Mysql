package com.example.project_java.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
public class UserDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dob;
}
