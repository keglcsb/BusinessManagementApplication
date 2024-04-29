package app.bm.dto;

import lombok.Data;

@Data
public class AssignOrRemoveIssueRequest {
    Long issueId;
    Long employeeId;
}
