package com.akbarlee.SpringServer.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepositoryJDBC extends CrudRepository<User, Long> {
        User findByEmail(String email);
}
