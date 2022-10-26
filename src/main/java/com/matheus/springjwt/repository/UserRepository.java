package com.matheus.springjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.matheus.springjwt.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    
}
