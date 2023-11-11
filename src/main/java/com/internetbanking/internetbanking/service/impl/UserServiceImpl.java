package main.java.com.internetbanking.internetbanking.service.impl;

import main.java.com.internetbanking.internetbanking.models.User;
import main.java.com.internetbanking.internetbanking.payload.request.UserRegistrationRequest;
import main.java.com.internetbanking.internetbanking.payload.response.JwtResponse;
import main.java.com.internetbanking.internetbanking.payload.response.LoginRequest;
import main.java.com.internetbanking.internetbanking.payload.response.UserRegistrationResponse;
import main.java.com.internetbanking.internetbanking.repository.UserRepository;
import main.java.com.internetbanking.internetbanking.security.UserDetailsImpl;
import main.java.com.internetbanking.internetbanking.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;

import main.java.com.internetbanking.internetbanking.security.jwt.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;


import java.util.*;
@Service
public class UserServiceImpl implements UserService {


    private final AuthenticationManager authenticationManager;


    @Autowired
    UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) throws Exception {
        return null;
    }

    @Autowired

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public void saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        boolean userExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser!=null){
            userExists = true;
        }
        return userExists;
    }
}
