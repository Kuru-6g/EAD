package com.example.forgot.controllers;


import com.example.forgot.configs.LoginResponseAdmin;
import com.example.forgot.entities.AdminUser;
import com.example.forgot.repositories.AdminUserRepository;
import com.example.forgot.services.AdminUserService;
import com.example.forgot.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3001","http://localhost:3000"})

public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private EmailService emailService;
    @PostMapping("/signup")
    public ResponseEntity<String> saveAdminUser(@RequestBody AdminUser userIdAndPWCreateByAdmin){
        String password=userIdAndPWCreateByAdmin.getPassword();

      AdminUser usr= adminUserRepository.findByEmail(userIdAndPWCreateByAdmin.getEmail());
      //  AdminUser adminUser= adminUserRepository.findByEmail(userIdAndPWCreateByAdmin.getEmail());

        if (usr!=null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        AdminUser create=adminUserService.saveAdminUser(userIdAndPWCreateByAdmin);
        emailService.sendUserCredentialEmail(userIdAndPWCreateByAdmin,password);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseAdmin> login(@RequestBody AdminUser adminUser)
    {

        String email=adminUser.getEmail();
        String password=adminUser.getPassword();

        LoginResponseAdmin loginResponseAdmin =adminUserService.performlogin(email,password);
        return ResponseEntity.ok(loginResponseAdmin);

    }


}
