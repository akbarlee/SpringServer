package com.akbarlee.SpringServer.Token;

import com.akbarlee.SpringServer.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken , Long> {
     VerificationToken findByVerificationToken(String token);
     VerificationToken findByUser(User user);
}
