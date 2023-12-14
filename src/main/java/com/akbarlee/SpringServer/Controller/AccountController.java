package com.akbarlee.SpringServer.Controller;

import com.akbarlee.SpringServer.Customer.User;
import com.akbarlee.SpringServer.Customer.UserRepositoryJDBC;
import com.akbarlee.SpringServer.EmailSender.EmailSenderService;
import com.akbarlee.SpringServer.Token.VerificationToken;
import com.akbarlee.SpringServer.Token.VerificationTokenRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    PasswordEncoder passwordEncoder;

 @Autowired
 private UserRepositoryJDBC userRepositoryJDBC;

 @Autowired
 private VerificationTokenRepository verificationTokenRepository;

 @Autowired
 private EmailSenderService emailSenderService;
    Logger logger =  LoggerFactory.getLogger(getClass());


    @GetMapping(value="/loginP")
    public ModelAndView displayLogin(ModelAndView modelAndView, User user)
    {

        modelAndView.addObject("loginP", user);
        modelAndView.setViewName("loginP");

        return modelAndView;
    }


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        logger.info("Trying to GET request register user  {}", user);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
 public ModelAndView registerUser(ModelAndView modelAndView, User user) {

       User existingUser = userRepositoryJDBC.findByEmail(user.getEmail());
        logger.info("Trying to POST request register user  {}", existingUser);
 if (existingUser != null) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
 } else {

      modelAndView.addObject("user", user);
     user.setPassword(passwordEncoder.encode(user.getPassword()));
            // use a password encoder service
            userRepositoryJDBC.save(user);
            VerificationToken confirmationToken = new VerificationToken(user);
            verificationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("sabuhiakbarli@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
         + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage); // use an async method
            modelAndView.addObject("getEmail", user.getEmail());

            modelAndView.setViewName("successfulRegisteration.html");
        }
 return modelAndView;
    }

 @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
 public ModelAndView confirmUserAccount(ModelAndView modelAndView,
                                        @RequestParam("token") String confirmationToken) {
        VerificationToken token = verificationTokenRepository.findByVerificationToken(confirmationToken);

 if (token != null) {
            User user = userRepositoryJDBC.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepositoryJDBC.save(user);
            modelAndView.setViewName("accountVerified");
 } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

 return modelAndView;
    }


    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
    }
}
