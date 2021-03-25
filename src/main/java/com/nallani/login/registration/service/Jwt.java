package com.nallani.login.registration.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class Jwt {

    // The secret key.
    private static String SECRET_KEY = "secret-key";

    //Sample method to construct a JWT
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        String keyStore = "";

        //create RSA-Signer with the private key
        //PrivateKey privateKey = getPrivateKey(keyStore);

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        Date now = Date.from(Instant.now());

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Setting the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //adding the expiration
        if (ttlMillis >= 0) {
            Date exp = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
            builder.setExpiration(exp);

        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

//    private static PrivateKey getPrivateKey(String keyStore){
//        try {
//            DerInputStream derInputStream = new DerInputStream(Base64.getDecoder().decode(keyStore));
//            DerValue[] seq = derInputStream.getSequence(0);
//            if (seq.length < 9){
//                throw new GeneralSecurityException("could not parse a PKCS1 private key.");
//            }
//            BigInteger modulus = seq[1].getBigInteger();
//            BigInteger publicExp = seq[1].getBigInteger();
//            BigInteger privateExp = seq[1].getBigInteger();
//            BigInteger primeP = seq[1].getBigInteger();
//            BigInteger primeQ = seq[1].getBigInteger();
//            BigInteger primeExpP = seq[1].getBigInteger();
//            BigInteger primeExpQ = seq[1].getBigInteger();
//            BigInteger crtCoef = seq[1].getBigInteger();
//            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus,publicExp,privateExp,primeP,primeQ,primeExpP,primeExpQ,crtCoef);
//            KeyFactory factory = KeyFactory.getInstance("RSA");
//            return factory.generatePrivate(keySpec);
//
//        } catch (IOException | GeneralSecurityException e) {
//            e.getMessage();
//        }
//
//        return null;
//    }
}
