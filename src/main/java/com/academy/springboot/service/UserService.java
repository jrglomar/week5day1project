package com.academy.springboot.service;


import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.User;
import com.academy.springboot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User findUserById(Long id) throws RecordNotFoundException;
    
    Page<User> findAllUser(Pageable pageable);


    User saveUser(User user);

    User updateUser(User user, Long id) throws RecordNotFoundException;

    void deleteUser(Long id) throws RecordNotFoundException;
}