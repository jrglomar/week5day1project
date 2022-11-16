package com.academy.springboot.controller;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.User;
import com.academy.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable){
        Page<User> users = userService.findAllUser(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public User getUserById (@PathVariable("id")Long id) throws RecordNotFoundException{
        return userService.findUserById(id);
    }
}
