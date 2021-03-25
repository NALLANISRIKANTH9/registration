package com.nallani.login.registration.model;

import com.nallani.login.registration.util.RegExConstants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateUserRequest {

    @Pattern(regexp = RegExConstants.USER_NAME)
    @Size(min = RegExConstants.USER_NAME_MIN_LENGTH, max = RegExConstants.USER_NAME_MAX_LENGTH)
    private String username;

    @Pattern(regexp = RegExConstants.PASS_WD_FORMAT)
    @Size(min = RegExConstants.PWD_MIN_LENGTH, max = RegExConstants.PWD_MAX_LENGTH)
    private String password;

    @Pattern(regexp = RegExConstants.DATE_OF_BIRTH)
    private String dateOfBirth;

    private FullName fullName;

    private Address address;
    private String email;
    private String phone;

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

    public FullName getFullName() { return fullName; }

    public void setFullName(FullName fullName) { this.fullName = fullName; }

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName=" + fullName +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}