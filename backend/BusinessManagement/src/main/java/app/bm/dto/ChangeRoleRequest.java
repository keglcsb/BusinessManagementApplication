package app.bm.dto;

import app.bm.model.Role;
import lombok.Data;

@Data
public class ChangeRoleRequest {
    private String email;
    private Role role;
}
