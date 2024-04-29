package app.bm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    String token;
}
