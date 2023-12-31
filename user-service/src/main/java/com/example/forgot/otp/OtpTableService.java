package com.example.forgot.otp;


import com.example.forgot.entities.AdminUser;
import com.example.forgot.entities.ForgotPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpTableService {
    @Autowired
    private OtpTableRepository otpTableRepository;

    @Autowired
    private UserService userService;


    public void setOtp(ForgotPasswordRequest forgotPasswordRequest, String otp){
        AdminUser adminUser = userService.findUserByEmail(forgotPasswordRequest.getEmail());

        OtpTable newOtp=new OtpTable();
        newOtp.setOtp(otp);
        newOtp.setUser(adminUser);
        newOtp.setCreatedTimestamp(LocalDateTime.now());

        otpTableRepository.save(newOtp);
    }
    public boolean verifyOtp(Integer uid, String otp){
        Optional<OtpTable> otpPresent=otpTableRepository.verifyOtp(uid, otp);
        return otpPresent.isPresent();
    }
    @Scheduled(fixedDelay = 5000) // Run every 5 Seconds (10 Seconds = 5,000 milliseconds)
    public void deleteOldData() {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(7);
        otpTableRepository.deleteByCreatedTimestampBefore(fiveMinutesAgo);
    }
    public void  delete (Integer uid, String otp){
        otpTableRepository.delete(uid,otp);
    }
    public boolean isPresent(String email){
        AdminUser usr= userService.findUserByEmail(email);
        Optional<OtpTable> otpTableOptional=otpTableRepository.presentData(usr.getId());
        return otpTableOptional.isPresent();
    }
    public  void deleteByUid(Integer uid){
        otpTableRepository.deltebyId(uid);
    }
}
