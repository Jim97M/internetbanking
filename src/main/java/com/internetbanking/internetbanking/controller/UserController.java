package main.java.com.internetbanking.internetbanking.controller;


import lombok.extern.slf4j.Slf4j;
import main.java.com.internetbanking.internetbanking.models.User;
import main.java.com.internetbanking.internetbanking.payload.request.UserRegistrationRequest;
import main.java.com.internetbanking.internetbanking.payload.response.JwtResponse;
import main.java.com.internetbanking.internetbanking.payload.response.LoginRequest;
import main.java.com.internetbanking.internetbanking.payload.response.UserRegistrationResponse;
import main.java.com.internetbanking.internetbanking.security.jwt.JwtUtils;
import main.java.com.internetbanking.internetbanking.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class UserController {
    private final UserService userService;


    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);


    @Autowired
    JwtUtils jwtUtils;



    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @CrossOrigin(origins="*")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isUserPresent(user)) {
            modelAndView.addObject("successMessage", "User already exists!");
        } else {
            userService.saveUser(user);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
//    @PostMapping("/login")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
//
//        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authenticationRequest.getEmail(), authenticationRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        User user=(User)authentication.getPrincipal();
//        String jwtToken= jwtUtils.generateToken(user.getUsername());
//
//        JwtResponse response=new JwtResponse();
//        response.setToken(jwtToken);
//
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) {

        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            JwtResponse jwtResponse = new JwtResponse();

            jwtResponse.setEmail(authenticationRequest.getEmail());
            jwtResponse.setPassword(authenticationRequest.getPassword());


            // Optionally, you can perform additional actions after successful authentication.

            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            // Handle authentication failure, such as invalid credentials.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }

}
