package com.akbarlee.SpringServer.Auth;

import com.akbarlee.SpringServer.Configuration.JWTService;
import com.akbarlee.SpringServer.User.Role;
import com.akbarlee.SpringServer.User.UserRepository;
import com.akbarlee.SpringServer.User.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
 @Slf4j
@AllArgsConstructor
public class AuthenticationService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        Logger logger = Logger.getLogger(AuthenticationService.class.getName());
        UserDetails user1 = org.springframework.security.core.userdetails.User.withUsername("user1")
                .password(passwordEncoder.encode("user1Pass"))
                .roles("USER")
                .build();
        UserDetails user2 = org.springframework.security.core.userdetails.User.withUsername("user2")
                .password(passwordEncoder.encode("user2Pass"))
                .roles("USER")
                .build();
        UserDetails admin = org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(passwordEncoder.encode("adminPass"))
                .roles("ADMIN")
                .build();
        logger.info(user1 + "Test");
        logger.info(user2 + "Test");
        logger.info(admin + "Test");
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request)  {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow() ;
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }
}