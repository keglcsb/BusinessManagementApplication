package app.bm.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String password;
}
