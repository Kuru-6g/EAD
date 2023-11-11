package com.example.forgot.otp;



import com.example.forgot.entities.AdminUser;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "otp")
public class OtpTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="otpid")
    private long otpid;
    @Column(name = "otp")
    private String otp;

    @OneToOne
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private AdminUser adminUser;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public OtpTable(String otp, AdminUser adminUser) {
        this.otp = otp;
        this.adminUser = adminUser;
    }

    public OtpTable() {

    }

    public long getOtpid() {
        return otpid;
    }

    public void setOtpid(long otpid) {
        this.otpid = otpid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public AdminUser getUser() {
        return adminUser;
    }

    public void setUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }
}
