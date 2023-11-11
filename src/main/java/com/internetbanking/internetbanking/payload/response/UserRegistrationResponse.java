package main.java.com.internetbanking.internetbanking.payload.response;


import lombok.*;
import main.java.com.internetbanking.internetbanking.models.User;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationResponse implements Serializable {
    Long id;

    String email;

    String username;

    String password;

    public UserRegistrationResponse(String consultationRegisteredSuccessfully, Long id, String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static UserRegistrationResponse build(User user) {
        return new UserRegistrationResponse("User Registered Successfully",
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword());
    }
}
