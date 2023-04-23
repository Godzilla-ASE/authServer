package com.example.authservice.service;

import com.example.authservice.entity.Authentication;
import com.example.authservice.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.authservice.repository.AuthRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuthService {
    private final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private final AuthRepository authRepository;
    public AuthService(@Qualifier("authRepository") AuthRepository authRepository) {
        this.authRepository = authRepository;
    }



}
