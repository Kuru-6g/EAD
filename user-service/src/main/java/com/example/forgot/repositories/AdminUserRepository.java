package com.example.forgot.repositories;


import com.example.forgot.entities.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,String > {

    Optional<AdminUser> findByUserName(String username);

   // Optional<AdminUser> findByEmail(String email);
    AdminUser findByEmail(String email);




}

