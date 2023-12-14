package com.akbarlee.SpringServer.Customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepositoryJDBC extends JpaRepository<User, Long> {
        User findByEmail(String email);

        Optional<User> findByEmailAndPassword(String email, String password);
}
