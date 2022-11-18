package com.academy.springboot.service;


import com.academy.springboot.enums.Roles;
import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.User;
import com.academy.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(Long id) throws RecordNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found."));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) throws RecordNotFoundException {
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User to update not found."));
        Date createdAt = userFound.getCreatedAt();
        modelMapper.map(user, userFound);
        userFound.setId(id);
        userFound.setCreatedAt(createdAt);
        return userRepository.save(userFound);
    }
    @Override
    public void deleteUser(Long id) throws RecordNotFoundException {
        userRepository.delete(userRepository.findById(id).
                orElseThrow(() -> new RecordNotFoundException("User to delete not found.")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user.");
        }
        return new org.springframework.security.core.userdetails.User
                (user.getUserName(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        if (user.getRole().equals(Roles.ADMIN))
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));

    }
}
