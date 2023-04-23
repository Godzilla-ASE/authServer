package com.example.authservice.repository;

import com.example.authservice.entity.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("authRepository")
public interface AuthRepository extends JpaRepository<Authentication, Long> {
    Optional<Authentication> findById(Long id);
}
