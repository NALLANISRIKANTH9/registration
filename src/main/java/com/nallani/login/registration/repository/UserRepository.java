package com.nallani.login.registration.repository;

import com.nallani.login.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findUserByUserId(String userId);

    User findUserByEmail(String email);

    User findUserByPhone(String phone);

    void deleteUserByUsername(String info);

    void deleteUserByDateOfBirth(String info);

    void deleteUserByUserId(String info);

    void deleteUserByPhone(String info);

    void deleteUserByEmail(String info);

    List<User> findAll();
}