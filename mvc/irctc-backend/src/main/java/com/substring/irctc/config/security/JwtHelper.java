package com.substring.irctc.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {


    private static final long ACCESS_TOKEN_VALIDITY = 30 * 1000; // 5 minutes
    private static final long REFRESH_TOKEN_VALIDITY = 60*60 * 1000; // 30 minutes

    private static final String SECRET = "asohgfasogfasiogfhaosfakshfgoafghasihfgoasghiasfgiowhtioawhtosagbkasbgoasghgiasghasoghaskgbfasoghoasghisa";

    private SecretKey key;


    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    // generate token

    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", "access_token");
        return buildToken(claims, userDetails.getUsername(), ACCESS_TOKEN_VALIDITY);


    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", "refresh_token");
        return buildToken(claims, userDetails.getUsername(), REFRESH_TOKEN_VALIDITY);
    }


    public boolean isRefreshToken(String token) {
        return getTokenType(token).equals("refresh_token");
    }

    public boolean isAccessToken(String token) {
        return getTokenType(token).equals("access_token");
    }


    private String getTokenType(String token) {
        Object tokenType = getClaims(token).get("token_type");
        return tokenType != null ? tokenType.toString() : "";
    }

    private String buildToken(Map<String, Object> claims, String subject, long validity) {
        // to generate token
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }


    // get username from token

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }


    //validate token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    // check token expiration
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }


    // get all claims from token


    private Claims getClaims(String token) {
//        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //refresh token information:


}
