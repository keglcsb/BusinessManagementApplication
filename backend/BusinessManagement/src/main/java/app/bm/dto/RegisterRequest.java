package app.bm.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int salary;
}
