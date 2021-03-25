package com.nallani.login.registration.service;

import com.nallani.login.registration.model.CreateUserRequest;
import com.nallani.login.registration.model.Login;
import com.nallani.login.registration.model.LoginResult;
import com.nallani.login.registration.model.Otp;

public interface LoginService {

    LoginResult register(CreateUserRequest userRequest);

    LoginResult findUserName(String loginData);

    LoginResult updatePassword(Login loginData);

    LoginResult login(Login loginData, ClientRequestInfo info);

    Otp generateOtp(String userName);

    LoginResult validateOtp(Otp otp);

    String validateTransferIdToken(String key, String transferId);
}
