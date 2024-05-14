package app.bm.controller;

import app.bm.dto.LoginRequest;
import app.bm.dto.LoginResponse;
import app.bm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    )  {
        return ResponseEntity.ok(LoginResponse.builder().token(authService.login(request)).build());
    }
}
