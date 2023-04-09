package com.akbarlee.SpringServer.Token;

import com.akbarlee.SpringServer.EmailSender.EmailSenderService;
import com.akbarlee.SpringServer.User.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Calendar;

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
   public void  save( User user , String token) {
         VerificationToken verificationToken = new VerificationToken(token , user);
         // set expiry date
         verificationToken.setExpiryDate(calculateExpiryDate(24 * 60));
         verificationTokenRepository.save(verificationToken);
     }
     private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
         Calendar cal = Calendar.getInstance();
         cal.add(Calendar.MINUTE , expiryTimeInMinutes);
         return new Timestamp(cal.getTime().getTime());
     }
}
