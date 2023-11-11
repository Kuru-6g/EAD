package com.example.forgot.configs;


import com.example.forgot.entities.AdminUser;
import com.example.forgot.repositories.AdminUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class JWTAuthenticationAdmin {
    @Autowired
    AdminUserRepository adminUserRepository;



    public String generateToken(String email) {
        AdminUser adminUser = adminUserRepository.findByEmail(email);
        byte[] secretKeyBytes= Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String secretKey= Base64.getEncoder().encodeToString(secretKeyBytes);
        String token= Jwts.builder()
                .claim("email",adminUser.getEmail())
                .claim("userRole",adminUser.getUserRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
        return token;

    }

}

