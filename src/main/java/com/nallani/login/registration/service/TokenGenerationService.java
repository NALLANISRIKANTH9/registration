package com.nallani.login.registration.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class TokenGenerationService {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final Integer EXPIRE_MINS = 15;
    private static LoadingCache<String, String> tokenCacheService;

    public TokenGenerationService() {
        super();
        tokenCacheService = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
            public String load(String key) {
                return null;
            }
        });
    }

    public static String generateTransferIdToken(String key) {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);
        tokenCacheService.put(key, token);
        return token;
    }

    public static String getTransferIdToken(String key) {
        try {
            return tokenCacheService.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static String createJWTToken(){
        String id = "1234567890";
        String issuer = "Nallani";
        String subject = "Hi";
        long ttlMillis = 60;

        return Jwt.createJWT(id, issuer, subject, ttlMillis);
    }
}
