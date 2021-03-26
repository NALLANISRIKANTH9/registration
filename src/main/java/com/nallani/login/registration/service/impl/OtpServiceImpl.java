package com.nallani.login.registration.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpServiceImpl {

    private static final Integer EXPIRE_MINS = 15;
    private LoadingCache<String, String> otpCacheService;

    public OtpServiceImpl() {
        super();
        otpCacheService = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
            public String load(String key) {
                return null;
            }
        });
    }

    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public String generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCacheService.put(key, String.valueOf(otp));
        return String.valueOf(otp);
    }

    //This method is used to return the OPT number against Key->Key values is username
    public String getOtp(String key) {
        try {
            return otpCacheService.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    //clear the OTP cached already
    public void clearOTP(String key) {
        otpCacheService.invalidate(key);
    }
}
