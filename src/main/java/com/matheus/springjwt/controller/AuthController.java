package com.matheus.springjwt.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

import com.matheus.springjwt.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.springjwt.domain.User;

@RestController
public class AuthController {

    public static final int EXPIRES_TIME = 600_000;

    private final UserService userService;
    private final JwtEncoder jwtEncoder;

    public AuthController(UserService userService, JwtEncoder jwtEncoder) {
        this.userService = userService;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping(value = "login")
    public ResponseEntity<String> login(@RequestBody User userBody) {
        Optional<User> user = this.userService.getByUsername(userBody.getUsername());

        if (user.isEmpty() || !this.userService.isPasswordCorrect(userBody.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("User or password is invalid!");
        }

        var now = Instant.now();
        var expiresIn = 300L;

//        var scopes = user.get().getRoles()
//                .stream()
//                .map(Role::getName)
//                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("spring-jwt-basic-impl")
                .subject(String.valueOf(user.get().getId()))
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
//                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        
        return ResponseEntity.ok().body(jwtValue);
    }
}
