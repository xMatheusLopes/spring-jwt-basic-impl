package com.matheus.springjwt.controller;

import java.sql.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.matheus.springjwt.domain.User;

@RestController
public class AuthController {

    public static final int TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "463408a1-54c9-4307-bb1c-6cced559f5a7";
    
    @PostMapping(value = "login")
    public ResponseEntity<String> store(@RequestBody User userBody) {
        String token = JWT.create()
                            .withSubject(userBody.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                            .sign(Algorithm.HMAC512(TOKEN_SENHA));
        
        return ResponseEntity.ok().body(token);
    }
}
