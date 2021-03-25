package com.nallani.login.registration.rest;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nallani.login.registration.model.CreateUserRequest;
import com.nallani.login.registration.model.Login;
import com.nallani.login.registration.model.LoginResult;
import com.nallani.login.registration.model.Otp;
import com.nallani.login.registration.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.id.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Random;

import static com.nallani.login.registration.util.SessionTokens.*;

@RestController
public class RestResource {

    private static final Logger log = LoggerFactory.getLogger(RestResource.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private OtpService otpService;

    @PostMapping("/registration")
    @ApiOperation(value = "Initiate (create) a user.", response = LoginResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-useragent", value = "user's agent name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-ipaddress", value = "user's IP", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-application-context", value = "application name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-device-token", value = "user's device token", dataType = "string", paramType = "header", required = true)
    })
    public LoginResult registration(@RequestBody CreateUserRequest userRequest,
                                    @Autowired HttpServletRequest servletRequest,
                                    @Autowired HttpServletResponse servletResponse) throws Exception {
        log.info("request is {}", userRequest.toString());
        Random random = new Random();
        random.nextInt();
        //ClientRequestInfo headerInfo = buildClientRequestInfo(servletRequest);
        //headerInfo.getSessionToken().setToken(null);
        servletResponse.addHeader("JWT", Jwt.createJWT(random.toString(), "Sri", "Nallani.com", 60));
        servletResponse.setStatus(HttpServletResponse.SC_CREATED);
        return loginService.register(userRequest);
    }

    @PostMapping("/login")
    @ApiOperation(value = "login given user.", response = LoginResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-useragent", value = "user's agent name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-ipaddress", value = "user's IP", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-application-context", value = "application name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-device-token", value = "user's device token", dataType = "string", paramType = "header", required = true)
    })
    public LoginResult login(@RequestBody Login loginData,
                             @Autowired HttpServletRequest servletRequest,
                             @Autowired HttpServletResponse servletResponse) throws Exception {
        log.info("login request for user {}", loginData.getUsername());
        ClientRequestInfo headerInfo = buildClientRequestInfo(servletRequest);

        Cookie cookie = new Cookie("transfer-id", TokenGenerationService.generateTransferIdToken(loginData.getUsername()));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
        servletResponse.addHeader("login-session-id", headerInfo.getLoginSessionId());
        return loginService.login(loginData, headerInfo);

    }

    @GetMapping(path = "/{userName}")
    @ApiOperation(value = "Find user for given user name.", response = LoginResult.class)
    public LoginResult login(@PathVariable("userName") String userName) {
        log.info(" Find username begin for user name {}", userName);
        return loginService.findUserName(userName);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "Update password for given username.", response = LoginResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-useragent", value = "user's agent name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-ipaddress", value = "user's IP", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-application-context", value = "application name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-device-token", value = "user's device token", dataType = "string", paramType = "header", required = true)
    })
    public LoginResult verifyPassword(@RequestParam(name = "transferIdToken") String transferIdToken,
                                      @RequestBody Login loginData) throws Exception {
        log.info("processing update password request for user {}", loginData.getUsername());
        if (loginService.validateTransferIdToken(loginData.getUsername(), transferIdToken).equals("Success")) {
            return loginService.updatePassword(loginData);
        } else throw new Exception("User unauthorized");

    }

    @GetMapping(path = "/otp/{userName}")
    @ApiOperation(value = "Generate otp for given username.", response = LoginResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-useragent", value = "user's agent name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-ipaddress", value = "user's IP", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-application-context", value = "application name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-device-token", value = "user's device token", dataType = "string", paramType = "header", required = true)
    })
    public Otp generateOtp(@PathVariable("userName") String userName) {
        log.info("processing create otp request for user {}", userName);
       /* String generateOTP = otpService.generateOTP(userName);
        log.info("OTP : "+ generateOTP);
        Otp otp = new Otp();
        otp.setCode(generateOTP);
        return otp;*/
        return loginService.generateOtp(userName);

    }

    @PostMapping("/validateOtp")
    @ApiOperation(value = "Update password for given username.", response = LoginResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-useragent", value = "user's agent name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-ipaddress", value = "user's IP", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-application-context", value = "application name", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "x-device-token", value = "user's device token", dataType = "string", paramType = "header", required = true)
    })
    public LoginResult validateOtp(@RequestBody Otp otp) {
        log.info("processing validate otp request for user");

        return loginService.validateOtp(otp);

    }

    @GetMapping("/health")
    public String healthCheck() {
        log.info("health check method called successfully");
        return "OK";
    }
}