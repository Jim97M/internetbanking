package main.java.com.internetbanking.internetbanking.payload.response;

import lombok.*;
import javax.validation.constraints.NotBlank;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Please enter your username")
    private String email;


    @NotBlank(message = "Please enter your password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}