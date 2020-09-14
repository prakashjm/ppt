package com.xyz.mydiary.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.mydiary.model.UserDAO;
import com.xyz.mydiary.model.VerificationToken;

public interface VerificationTokenRepository 
extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);

  VerificationToken findByUser(UserDAO user);
}