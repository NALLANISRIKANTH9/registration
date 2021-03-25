package com.nallani.login.registration.service;

import com.nallani.login.registration.model.*;
import com.nallani.login.registration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private MyEmailService emailService;

    @Autowired
    public SimpleMailMessage template;

    @Override
    public LoginResult register(CreateUserRequest userRequest) {
        requireNonNull(userRequest, "userRequest must not be null");
        LoginResult toReturn = new LoginResult();

        User userData = new User();
        FullName fullName = new FullName();
        fullName.setFirstName(userRequest.getFullName().getFirstName());
        fullName.setMiddleName(userRequest.getFullName().getMiddleName());
        fullName.setLastName(userRequest.getFullName().getLastName());
        fullName.setSuffix(userRequest.getFullName().getSuffix());

        Address address = new Address();
        address.setLine1(userRequest.getAddress().getLine1());
        address.setLine2(userRequest.getAddress().getLine2());
        address.setCity(userRequest.getAddress().getCity());
        address.setState(userRequest.getAddress().getState());
        address.setPostalCode(userRequest.getAddress().getPostalCode());

        userData.setUserId(UUID.randomUUID());
        userData.setUsername(userRequest.getUsername());
        userData.setPassword(userRequest.getPassword());
        userData.setDateOfBirth(userRequest.getDateOfBirth());
        userData.setFullName(fullName);
        userData.setAddress(address);
        userData.setEmail(userRequest.getEmail());
        userData.setPhone(userRequest.getPhone());

        userRepository.save(userData);
        toReturn.setResultType(true);
        return toReturn;
    }

    @Override
    public LoginResult findUserName(String username) {
        requireNonNull(username, "username must not be null");

        LoginResult toReturn = new LoginResult();
        User user = userRepository.findByUsername(username);
        if (user != null && user.getUsername().equals(username)) {
            toReturn.setResultType(true);
        } else {
            toReturn.setResultType(false);
            log.info("User not found");
            //throw new UsernameNotFoundException(username);
        }
        return toReturn;
    }

    @Override
    public LoginResult updatePassword(Login loginData) {
        requireNonNull(loginData, "loginData must not be null");

        LoginResult toReturn = new LoginResult();
        User user = userRepository.findByUsername(loginData.getUsername());
        if (user != null) {
            user.setPassword(loginData.getPassword());
            userRepository.save(user);
            log.info("Password updated successfully");
            toReturn.setResultType(true);
        } else {
            toReturn.setResultType(false);
            log.info("Password update failed");
        }

        return toReturn;
    }

    @Override
    public LoginResult login(Login loginData, ClientRequestInfo info) {
        requireNonNull(loginData, "loginData must not be null");
        requireNonNull(info, "client request info must not be null");

        LoginResult toReturn = new LoginResult();
        User user = userRepository.findByUsername(loginData.getUsername());
        if (user != null && user.getPassword().equals(loginData.getPassword())) {

            //String token = TokenGenerationService.generateToken(loginData.getUsername());
            //toReturn.setTransferId(token);
            toReturn.setResultType(true);

            log.info("login successful");
        } else {
            toReturn.setResultType(false);
            log.info("login failed");
        }
        return toReturn;
    }

    @Override
    public Otp generateOtp(String userName) {
        String generateOTP = otpService.generateOTP(userName);
        log.info("OTP : " + generateOTP);
        String text = String.format(template.getText(), generateOTP);
        emailService.sendSimpleMessage("nallanisrikanth99@gmail.com", "Your Account: "+generateOTP+" is your verification code for secure access", text);
        Otp otp = new Otp();
        otp.setCode(generateOTP);
        return otp;
    }

    @Override
    public LoginResult validateOtp(Otp otp) {
        LoginResult toReturn = new LoginResult();
        if (otp != null) {
            String serverOtp = otpService.getOtp(otp.getUsername());
            if (serverOtp != null && serverOtp.equals(otp.getCode())) {
                otpService.clearOTP(otp.getUsername());
                log.info("Entered Otp is valid");
                toReturn.setResultType(true);
                return toReturn;
            } else {
                log.info("Entered Otp is invalid");
                toReturn.setResultType(false);
                return toReturn;
            }
        }
        toReturn.setResultType(false);
        return toReturn;
    }

    @Override
    public String validateTransferIdToken(String key, String transferId) {

        String serverToken = TokenGenerationService.getTransferIdToken(key);
        if (serverToken != null && serverToken.equals(transferId)) {
            return "Success";
        }
        return "Failed";
    }
}
