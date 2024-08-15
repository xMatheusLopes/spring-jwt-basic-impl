//package com.matheus.springjwt.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//public class JwtConfig extends AbstractHttpConfigurer<JwtConfig, HttpSecurity> {
//    final JwtAuthorization jwtAuthenticationFilter;
//
//    public JwtConfig(JwtAuthorization jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/user").permitAll()
//                .anyRequest().authenticated()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}