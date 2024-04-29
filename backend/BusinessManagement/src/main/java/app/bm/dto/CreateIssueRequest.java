package app.bm.dto;

import lombok.Data;

@Data
public class CreateIssueRequest {
    String name;
    int value;
    String description;
    Long creatorId;
}
