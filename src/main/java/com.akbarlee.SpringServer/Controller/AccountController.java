package com.akbarlee.SpringServer.Controller;

import com.akbarlee.SpringServer.Auth.AuthenticationResponse;
import com.akbarlee.SpringServer.Auth.RegisterRequest;
import com.akbarlee.SpringServer.EmailSender.EmailSenderService;
import com.akbarlee.SpringServer.Token.VerificationToken;
import com.akbarlee.SpringServer.Token.VerificationTokenRepository;
import com.akbarlee.SpringServer.User.Role;
import com.akbarlee.SpringServer.User.User;
import com.akbarlee.SpringServer.User.UserRepositoryJDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
     PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepositoryJDBC userRepositoryJDBC;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User existingUser = userRepositoryJDBC.findByEmailIdIgnoreCase(user.getEmail());
        if(existingUser != null) {
            return new ResponseEntity<>("This email already exists!", HttpStatus.CONFLICT);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // use a password encoder service
            userRepositoryJDBC.save(user);
            VerificationToken confirmationToken = new VerificationToken(user);
            verificationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : " +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage); // use an async method
            return new ResponseEntity<>("Registration successful. Please check your email.", HttpStatus.CREATED);
        }
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        VerificationToken token = verificationTokenRepository.findByVerificationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepositoryJDBC.findByEmailIdIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepositoryJDBC.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
