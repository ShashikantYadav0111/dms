package com.project.dairy_management_system.Service.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private static String SECRETKEY = "";

    public JWTService(){
        try{
            KeyGenerator keygen = KeyGenerator.getInstance("hmacSHA256");
            SECRETKEY = Base64.getEncoder().encodeToString(keygen.generateKey().getEncoded());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+30*60*1000))
                .and()
                .signWith(getKey())
                .compact();
    }


    private static SecretKey getKey(){
        byte[] keyByte = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyByte);
    }



    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }


    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private static Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}


