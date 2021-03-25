package com.nallani.login.registration.model;

import com.nallani.login.registration.util.RegExConstants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Login
{
    @Pattern(regexp = RegExConstants.USER_NAME)
    @Size(min = RegExConstants.USER_NAME_MIN_LENGTH, max = RegExConstants.USER_NAME_MAX_LENGTH)
    private String username;

    @Pattern(regexp = RegExConstants.PASS_WD_FORMAT)
    @Size(min = RegExConstants.PWD_MIN_LENGTH, max = RegExConstants.PWD_MAX_LENGTH)
    private String password;

    public Login() {
        //restricted instantiation
    }

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
}
