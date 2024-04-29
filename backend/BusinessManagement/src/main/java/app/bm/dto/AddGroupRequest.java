package app.bm.dto;

import lombok.Data;

@Data
public class AddGroupRequest {
    String name;
    Long leaderId;
}
