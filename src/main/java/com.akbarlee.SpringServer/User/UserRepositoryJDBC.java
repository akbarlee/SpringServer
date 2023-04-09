package com.akbarlee.SpringServer.User;

import com.akbarlee.SpringServer.User.User;
import org.springframework.data.repository.CrudRepository;

public interface  UserRepositoryJDBC extends CrudRepository<User, String> {
        User findByEmailIdIgnoreCase(String emailId);
}
