package com.example.authservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authentication")
public class Authentication {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

}