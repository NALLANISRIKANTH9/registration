package com.nallani.login.registration.service;

import com.nallani.login.registration.model.User;

public interface UserService {

    User findByUsername(String username);
}
