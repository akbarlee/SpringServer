package com.akbarlee.SpringServer.Cart;


import com.akbarlee.SpringServer.Customer.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

  @Autowired
    CartItemDAO cartItemDAO;

  @Autowired
  UserDao userDao;

  @GetMapping("/incart")
  String cart() {
    return "";
  }
}
