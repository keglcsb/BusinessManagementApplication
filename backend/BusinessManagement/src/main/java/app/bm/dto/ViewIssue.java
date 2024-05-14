package app.bm.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class ViewIssue {
    Long id;
    String name;
    Integer status;
    int value;
    String description;
    Long issuedAt;
    Long approvedAt;
    ViewUser creator;
    ViewUser approver;
    Set<ViewUser> workers;
}
