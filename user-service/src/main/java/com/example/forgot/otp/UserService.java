package com.example.forgot.otp;

import com.example.forgot.entities.AdminUser;


public interface UserService {
    AdminUser findUserByEmail(String email);

    void updatePassword(AdminUser adminUser, String newPassword);
}

