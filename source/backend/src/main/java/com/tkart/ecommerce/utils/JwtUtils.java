package com.tkart.ecommerce.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
     private final String jwtSecret = "your_jwt_secret";
     private final long jwtExpirationMs = 86400000;

     public String generateJwtToken(String username) {
          return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
     }
}
