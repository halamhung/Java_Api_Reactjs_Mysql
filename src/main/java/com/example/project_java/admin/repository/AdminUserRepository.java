package com.example.project_java.admin.repository;

import com.example.project_java.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<User,String> {
    boolean existsByUsername(String username);
}
