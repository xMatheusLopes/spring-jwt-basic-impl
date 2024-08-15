package com.matheus.springjwt.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheus.springjwt.domain.User;
import com.matheus.springjwt.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }
    
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User store(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean isPasswordCorrect(String loginPassword, String userPassword) {
        return passwordEncoder.matches(loginPassword, userPassword);
    }
}
