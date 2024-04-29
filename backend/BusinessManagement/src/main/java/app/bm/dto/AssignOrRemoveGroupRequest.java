package app.bm.dto;

import lombok.Data;

@Data
public class AssignOrRemoveGroupRequest {
    Long groupId;
    Long employeeId;
}
