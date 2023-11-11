package com.example.forgot.services;


import com.example.forgot.entities.AdminUser;

public interface EmailService {
    void sendPasswordResetEmail(String to, String otp);
    void sendUserCredentialEmail(AdminUser adminUser, String password);
}
