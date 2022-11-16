package com.academy.springboot.service;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.User;
import com.academy.springboot.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllUser(Pageable pageable);
    User findUserById(Long id) throws RecordNotFoundException;

}
