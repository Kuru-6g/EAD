package com.example.forgot.services;


import com.example.forgot.configs.JWTAuthenticationAdmin;
import com.example.forgot.configs.LoginResponseAdmin;
import com.example.forgot.entities.AdminUser;
import com.example.forgot.repositories.AdminUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTAuthenticationAdmin jwtAuthenticationAdmin;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository , BCryptPasswordEncoder passwordEncoder, JWTAuthenticationAdmin jwtAuthenticationAdmin) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationAdmin = jwtAuthenticationAdmin;
    }

    public LoginResponseAdmin performlogin(String email, String password) {
        //AdminUser adminUser = adminUserRepository.findById(userName).orElse(null);
        AdminUser adminUser = adminUserRepository.findByEmail(email);

        System.out.println(adminUser.getEmail());
        if (adminUser == null) {

            return new LoginResponseAdmin("User not found", false,null);
        } else {

            boolean isPasswordMatched = passwordEncoder.matches(password, adminUser.getPassword());
            if (isPasswordMatched) {
                String token= jwtAuthenticationAdmin.generateToken(email);
                System.out.println(token);
                return new LoginResponseAdmin("Login Successful", true,token);
            } else {
                return new LoginResponseAdmin("Incorrect Password", false,null);
            }
        }
    }
    public boolean checkUsernameExists(String email) {
        AdminUser adminUser = adminUserRepository.findByEmail(email);
        return email != null;
    }


    public String bcryptPassword(String password){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        return encodedPassword;
    }


    public AdminUser saveAdminUser(AdminUser userIdAndPWCreateByAdmin) {
        String encodedPassword=this.bcryptPassword(userIdAndPWCreateByAdmin.getPassword());
        userIdAndPWCreateByAdmin.setPassword(encodedPassword);
        AdminUser userIdAndPWCreateByAdminEntity = new AdminUser();
        BeanUtils.copyProperties(userIdAndPWCreateByAdmin,userIdAndPWCreateByAdminEntity);
        adminUserRepository.save(userIdAndPWCreateByAdminEntity);
        return userIdAndPWCreateByAdmin;

    }


}


