package com.akbarlee.SpringServer.Auth;

import com.akbarlee.SpringServer.Configuration.JWTService;
import com.akbarlee.SpringServer.User.Role;
import com.akbarlee.SpringServer.User.UserRepository;
import com.akbarlee.SpringServer.User.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        Logger logger = Logger.getLogger(AuthenticationService.class.getName());
        var user = User.builder()

                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
                repository.save(user);

        // Usere uygun tokeni generate edib jwtToken icinde saxla
        var jwtToken = jwtService.generateToken(user);

        logger.info("Auth Service jwtToken "+ jwtToken);
        // jwtTokeni Response classin icindeki tokene gonder
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
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