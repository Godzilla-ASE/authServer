package com.example.authservice.controller;

import com.example.authservice.entity.Authentication;
import com.example.authservice.exceptions.ResourceNotFoundException;
import com.example.authservice.repository.AuthRepository;
import com.example.authservice.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthRepository authRepository;

    @MockBean
    private AuthService authService;

    @Test
    public void testGetTokenById() throws Exception {
        // Mock authentication data
        long userId = 1L;
        String token = UUID.randomUUID().toString();
        Authentication authentication = new Authentication(userId, token);

        // Mock authRepository.findById() method
        Mockito.when(authRepository.findById(userId)).thenReturn(Optional.of(authentication));

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(token));
    }

    @Test
    public void testGetTokenById_ThrowsResourceNotFoundException() throws Exception {
        // Mock authentication data
        long userId = 1L;

        // Mock authRepository.findById() method
        Mockito.when(authRepository.findById(userId)).thenReturn(Optional.empty());

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateToken() throws Exception {
        // Mock input data
        long userId = 1L;

        // Mock authRepository.save() method
        Mockito.when(authRepository.save(Mockito.any(Authentication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Mock input data
        long userId = 1L;

        // Mock authRepository.findById() method
        Mockito.when(authRepository.findById(userId)).thenReturn(Optional.of(new Authentication(userId, "")));

        // Perform DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/auth/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteUser_ThrowsResourceNotFoundException() throws Exception {
        // Mock input data
        long userId = 1L;

        // Mock authRepository.findById() method
        Mockito.when(authRepository.findById(userId)).thenReturn(Optional.empty());

        // Perform DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/auth/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
