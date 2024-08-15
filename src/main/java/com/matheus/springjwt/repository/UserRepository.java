package com.matheus.springjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.matheus.springjwt.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    
}
