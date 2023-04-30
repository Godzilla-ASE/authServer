package com.example.authservice.controller;

import com.example.authservice.entity.Authentication;
import com.example.authservice.exceptions.ResourceNotFoundException;
import com.example.authservice.repository.AuthRepository;

import com.example.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Authentication> getAllUsers(){
        return authRepository.findAll();
    }

    // build create employee REST API
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createToken(@PathVariable long id) {

        // generate random token
        String token = UUID.randomUUID().toString();
        Authentication authentication = new Authentication(id, token);
        authRepository.save(authentication);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getTokenById(@PathVariable long id) {
        Authentication authentication = authRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not login with id:" + id));
        return ResponseEntity.ok(authentication.getToken());
    }
    
    // build delete employee REST API
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        Authentication authentication = authRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not login with id: " + id));

        authRepository.delete(authentication);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
