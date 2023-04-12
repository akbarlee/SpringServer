package com.akbarlee.SpringServer.Token;

import com.akbarlee.SpringServer.Customer.User;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken , String> {
     VerificationToken findByVerificationToken(String token);
     VerificationToken findByUser(User user);
}
