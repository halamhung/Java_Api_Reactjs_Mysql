package com.example.project_java.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User { 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    
    private LocalDate dob;
}
