package com.nallani.login.registration.model;

import javax.persistence.*;

@Entity
@Table(name = "usertable")
public class User {

    @Column(nullable = false)
    private String userId;

    @Id
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String dateOfBirth;

    private FullName fullName;

    private Address address;

    private String email;

    private String phone;

    public User() {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public FullName getFullName() { return fullName; }

    public void setFullName(FullName fullName) { this.fullName = fullName; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

}