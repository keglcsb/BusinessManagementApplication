package app.bm.dto;

import lombok.Data;

@Data
public class ChangeSalaryRequest {
    private String email;
    private int salary;
}
