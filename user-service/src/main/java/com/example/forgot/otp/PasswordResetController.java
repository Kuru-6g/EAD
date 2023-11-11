package com.example.forgot.otp;

import com.example.forgot.entities.AdminUser;
import com.example.forgot.entities.ForgotPasswordRequest;
import com.example.forgot.repositories.AdminUserRepository;
import com.example.forgot.services.EmailService;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;
@RequestMapping("/forgotpass")
@RestController
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpTableService otpTableService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ForgotPasswordRequest request) {
        // Find the user by email
        //User user = userService.findUserByEmail(request.getEmail());
       // AdminUser adminUser = adminUserRepository.findByEmail(request.getEmail());
        AdminUser adminUser=adminUserRepository.findByEmail(request.getEmail());

        if (adminUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (otpTableService.isPresent(request.getEmail())){

            otpTableService.deleteByUid(adminUser.getId());
        }
        String otp=generateOTP();
        otpTableService.setOtp(request,otp);

        emailService.sendPasswordResetEmail(adminUser.getEmail(), otp);

        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Otp otp){
            AdminUser usr=userService.findUserByEmail(otp.getEmail());

        if (otpTableService.verifyOtp(usr.getId(),otp.getOtp())){

           return new ResponseEntity(HttpStatus.OK);
        }
        return new  ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/new-pass")
    public ResponseEntity<String> setNewPass(@RequestBody NewPass newPass){
            AdminUser usr=userService.findUserByEmail(newPass.getEmail());
        if (newPass.getNewPass().equals(newPass.getConfPass()) && otpTableService.verifyOtp(usr.getId(),newPass.getOtp())){
          AdminUser adminUser = userService.findUserByEmail(newPass.getEmail());
          userService.updatePassword(adminUser,newPass.getNewPass());
          otpTableService.delete(usr.getId(),newPass.getOtp());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new  ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    private String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10)); // Generate a random number between 0 and 9
        }

        return otp.toString();
    }

}

