package com.akbarlee.SpringServer.Token;

import com.akbarlee.SpringServer.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken , Long> {
     VerificationToken findByVerificationToken(String token);
     VerificationToken findByUser(User user);
}
