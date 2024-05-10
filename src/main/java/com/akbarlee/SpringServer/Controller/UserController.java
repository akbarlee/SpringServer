package com.akbarlee.SpringServer.Controller;


import com.akbarlee.SpringServer.Auth.AuthenticationService;
import com.akbarlee.SpringServer.Customer.User;
import com.akbarlee.SpringServer.Customer.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller

public class UserController {
    private final AuthenticationService service;
    private static final Logger CTRL_LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserDao userDao;



    public UserController(AuthenticationService service) {
        this.service = service;
    }


    @RequestMapping("/dashboard")
    public String getCustomer(Model model) {
        // Get the list of employees from the database
        List<User> users = userDao.getAllEmployees();
        CTRL_LOGGER.info("List employees "+users);
        // Add the list of employees to the model
        model.addAttribute("employees", users);
          return "dashboardComponents/dashboard.html";
              }



    @GetMapping()
        public String newCustomer(Model model) {
        User customer = new User();
        model.addAttribute("customer",customer);
        return "new_customer.html";
        }
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("user") User user) {
        // save employee to database
        userDao.save(user);
        return "redirect:/";
    }


    @GetMapping("/deleteCustomer/{id}")
  public String deleteEmployee(@PathVariable (value = "id") int id ) {
      userDao.deleteById(id);
      return "redirect:/";
  }

    @PostMapping("/customer/save")
    public ResponseEntity<?> save (@RequestBody User user) {
        try {
            userDao.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/showFormForUpdate/{id}")
 public String showFormforUpdate(@PathVariable (value = "id") int id, Model model) {
        User user = userDao.getCustomerById(id);
        model.addAttribute("user",user);
        return "update_customer";
 }

}
