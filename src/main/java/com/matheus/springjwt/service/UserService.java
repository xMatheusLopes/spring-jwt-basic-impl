package com.matheus.springjwt.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.matheus.springjwt.domain.User;
import com.matheus.springjwt.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }
    
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User store(User user) {
        return userRepository.save(user);
    }
}
