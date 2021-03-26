package com.nallani.login.registration.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.nallani.login.registration.service.impl.JwtServiceImpl;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
public class TokenGenerationServiceImpl {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final Integer EXPIRE_MINS = 15;
    private static LoadingCache<String, String> tokenCacheService;

    public TokenGenerationServiceImpl() {
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
}
