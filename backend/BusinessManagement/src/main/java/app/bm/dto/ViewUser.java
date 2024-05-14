package app.bm.dto;

import app.bm.model.Issue;
import app.bm.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ViewUser {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private int salary;
    private int role;
}
