package com.akbarlee.SpringServer.Token;

import com.akbarlee.SpringServer.Customer.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;


    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByVerificationToken(token);
    }

    @Transactional
    VerificationToken findByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Transactional
    public void save(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        // set expiry date
        verificationToken.setExpiryDate(calculateExpiryDate(24 * 60));
        verificationTokenRepository.save(verificationToken);
    }

    private Timestamp calculateExpiryDate (int expiryTimeInMinutes) {
        LocalDateTime now = LocalDateTime.now ();
        LocalDateTime expiry = now.plusMinutes (expiryTimeInMinutes);
        return Timestamp.valueOf (expiry);
    }
}