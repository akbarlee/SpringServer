package com.akbarlee.SpringServer.Cart;

import com.akbarlee.SpringServer.Customer.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);

}
