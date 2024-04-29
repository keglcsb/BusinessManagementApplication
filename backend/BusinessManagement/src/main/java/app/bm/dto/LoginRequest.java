package app.bm.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
