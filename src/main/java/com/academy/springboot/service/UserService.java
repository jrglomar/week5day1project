package com.academy.springboot.service;


import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllUsers(Pageable pageable);
    User findUserById(Long id) throws RecordNotFoundException;
}