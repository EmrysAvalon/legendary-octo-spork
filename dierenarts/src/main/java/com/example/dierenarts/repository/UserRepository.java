package com.example.dierenarts.repository;

import com.example.dierenarts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
