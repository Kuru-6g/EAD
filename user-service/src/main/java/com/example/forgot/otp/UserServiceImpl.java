package com.example.forgot.otp;


import com.example.forgot.entities.AdminUser;
import com.example.forgot.repositories.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private com.example.forgot.services.AdminUserService usr;

    @Override
    public AdminUser findUserByEmail(String email) {
       // System.out.println("hi");
       // return userService.findUserByEmail(email);
        AdminUser adminUser = adminUserRepository.findByEmail(email);
        return adminUser;

    }


    @Override
    public void updatePassword(AdminUser adminUser, String newPassword) {
        // Update the user's password
        String encodedPassword=usr.bcryptPassword(newPassword);
        adminUser.setPassword(encodedPassword);
        adminUserRepository.save(adminUser);
    }
}

