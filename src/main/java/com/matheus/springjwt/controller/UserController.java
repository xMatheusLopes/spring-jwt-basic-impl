package com.matheus.springjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.springjwt.domain.User;
import com.matheus.springjwt.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users")
    public ResponseEntity<Iterable<User>> listAll() {
        Iterable<User> users = this.userService.listAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping(value = "user")
    public ResponseEntity<User> store(@RequestBody User userBody) {
        User user = this.userService.store(userBody);
        return ResponseEntity.ok().body(user);
    }
}
