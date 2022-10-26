package com.matheus.springjwt.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class JwtDsl extends AbstractHttpConfigurer<JwtDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new JwtAuthorization(authenticationManager));
    }

    public static JwtDsl jwtDsl() {
        return new JwtDsl();
    }
}