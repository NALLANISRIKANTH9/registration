package com.nallani.login.registration.service.impl;

import com.nallani.login.registration.model.User;
import com.nallani.login.registration.repository.UserRepository;
import com.nallani.login.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}