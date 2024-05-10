package com.akbarlee.SpringServer.Cart;

import com.akbarlee.SpringServer.Customer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemDAO {

    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItem> listCartItems (User user) {
      return  cartItemRepository.findByUser(user);
    }
}
