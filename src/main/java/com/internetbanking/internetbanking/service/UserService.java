package main.java.com.internetbanking.internetbanking.service;

import main.java.com.internetbanking.internetbanking.models.User;

import java.util.List;

import main.java.com.internetbanking.internetbanking.payload.request.UserRegistrationRequest;
import main.java.com.internetbanking.internetbanking.payload.response.LoginRequest;
import main.java.com.internetbanking.internetbanking.payload.response.UserRegistrationResponse;
import main.java.com.internetbanking.internetbanking.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) throws Exception;

    public void saveUser(User user);


    boolean isUserPresent(User user);
}
