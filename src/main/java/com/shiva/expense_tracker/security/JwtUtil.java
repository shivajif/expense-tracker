package com.shiva.expense_tracker.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;



@Component
public class JwtUtil {



    private final String secret =
            "expenseTrackerSecretKeyExpenseTrackerAuthenticationKey12345";



    private SecretKey getSigningKey(){

        return Keys.hmacShaKeyFor(
                secret.getBytes()
        );

    }




    // Generate JWT Token
    public String generateToken(String email){


        return Jwts.builder()

                .subject(email)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                .signWith(getSigningKey())

                .compact();

    }





    // Extract email from token
    public String extractEmail(String token){


        Claims claims =
                Jwts.parser()

                        .verifyWith(getSigningKey())

                        .build()

                        .parseSignedClaims(token)

                        .getPayload();



        return claims.getSubject();

    }



}