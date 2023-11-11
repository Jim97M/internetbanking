package main.java.com.internetbanking.internetbanking.payload.request;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequest implements Serializable {


    private String username;


    private String email;

    private String password;
}
