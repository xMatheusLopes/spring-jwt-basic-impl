package com.matheus.springjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class JwtConfig extends AbstractHttpConfigurer<JwtConfig, HttpSecurity> {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers(HttpMethod.POST,"/login").permitAll()  
         .anyRequest().authenticated()
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .apply(JwtDsl.jwtDsl());
        return http.build();
    }
}