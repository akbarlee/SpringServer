package com.akbarlee.SpringServer.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDao {

    private UserRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        try {

            return repository.save(user);
        } catch (Exception e) {
            LOGGER.error("Error saving customer: {}", user, e);
            return null;
        }
    }
    public User getCustomerById(int id) {
        Optional<User> optional = repository.findById(id);
        User user = null;
        if(optional.isPresent()) {
            user = optional.get();
        } else {
            throw new IllegalArgumentException("Customer not found for "+ id);
        }
       return user ;
    }
    public List<User> getAllEmployees() {
        try {
            LOGGER.info("Work " + repository.findAll());
        return repository.findAll();
            } catch (Exception e) {

            LOGGER.info("Fail");
            // Handle the exception
            return Collections.emptyList();
        }
    }

    public void deleteById(int id) {
        try {
            LOGGER.info("Deleted " + id);
            repository.deleteById(id);
        } catch (Exception e) {
            // Handle the exception
        }
    }
}
