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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RecordNotFoundException("User Not Found");
        }
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) throws RecordNotFoundException {
        User userFound = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User to update not found."));
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        modelMapper.map(user, userFound);
        userFound.setId(id);
        return userRepository.save(userFound);
    }


    @Override
    public void deleteUser(Long id) throws RecordNotFoundException {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User to delete not found.")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        }
        if (user.getRole() == Roles.USER) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), userAuthority());
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), adminAuthority());
        }
    }

    private List<SimpleGrantedAuthority> adminAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    private List<SimpleGrantedAuthority> userAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}


