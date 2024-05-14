package app.bm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeStats {
    String firstName;
    String lastName;
    Double value;
}
