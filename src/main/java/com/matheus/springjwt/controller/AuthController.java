package com.matheus.springjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.springjwt.domain.User;

@RestController
public class AuthController {
    @PostMapping(value = "login")
    public ResponseEntity<User> store(@RequestBody User userBody) {
        return ResponseEntity.ok().body(userBody);
    }
}
