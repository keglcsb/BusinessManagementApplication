package app.bm.service;

import app.bm.dto.LoginRequest;
import app.bm.dto.ViewUser;
import app.bm.model.Employee;
import app.bm.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public String login(LoginRequest request)  {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = employeeRepository.findByEmail(request.getEmail());
        return jwtService.generateToken(user);
    }

    public ViewUser getCurrent(){
        Employee employee = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return ViewUser.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .role(employee.getRole().ordinal())
                .build();
    }
}
