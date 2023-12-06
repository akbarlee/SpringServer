package com.akbarlee.SpringServer.Configuration;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;


import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration  {

    @Autowired
    private DataSource dataSource;


    UserDetailsService userDetailsService;



    private static final Logger CTRL_LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class.getName());
    private final JwtAuthenticationFilter jwtAuthFilter;
      private final AuthenticationProvider authenticationProvider;



    @Bean
   public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .formLogin()
                .loginPage("/loginP")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login.html?error=true")
                .permitAll();


                .httpBasic();
              //  .and().httpBasic();


              //  .loginPage("/login");


              return httpSecurity.build();

    }


    }





