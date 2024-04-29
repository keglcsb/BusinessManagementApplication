package app.bm.service;

import java.util.ArrayList;
import java.util.List;

import app.bm.dto.*;
import app.bm.model.Employee;
import app.bm.model.Role;
import app.bm.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;


    public List<ViewUser> getAll(){
        List<Employee> employees = employeeRepository.findAllBy();
        List<ViewUser> users = new ArrayList<>();
        for (Employee employee:employees) {
            users.add(ViewUser.builder()
                            .id(employee.getId())
                            .email(employee.getEmail())
                            .firstName(employee.getFirstName())
                            .lastName(employee.getLastName())
                            .salary(employee.getSalary())
                            .issues(employee.getIssues())
                            .role(employee.getRole().ordinal())
                            .build());
        }
        return users;
    }

    public ViewUser getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id);
        return ViewUser
                .builder()
                .id(id)
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .role(employee.getRole().ordinal())
                .issues(employee.getIssues())
                .build();
    }

    public List<ViewUser> getByGroup(GetGroupRequest request){
        List<Employee> employees = employeeRepository.findAllByGroup_Id(request.getId());
        List<ViewUser> users = new ArrayList<>();
        for (Employee employee:employees) {
            users.add(ViewUser.builder()
                    .id(employee.getId())
                    .email(employee.getEmail())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .salary(employee.getSalary())
                    .issues(employee.getIssues())
                    .role(employee.getRole().ordinal())
                    .build());
        }
        return users;
    }

    public void add(RegisterRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() != Role.MODERATOR && initiator.getRole() != Role.OWNER){
            throw new RuntimeException("You are not qualified to add users!");
        }
        try {
            employeeRepository.save(Employee
                    .builder()
                    .email(request.getEmail())
                    .FirstName(request.getFirstName())
                    .LastName(request.getLastName())
                    .Password(passwordEncoder.encode(request.getPassword()))
                    //.Password(request.getPassword())
                    .Salary(request.getSalary())
                    .role(Role.BASE)
                    .build());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void changePassword(ChangePasswordRequest request){
        Employee employee = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(passwordEncoder.matches(request.getOldPassword(), employee.getPassword())){
            employee.setPassword(passwordEncoder.encode(request.getPassword()));
            employeeRepository.save(employee);
        } else {
            throw new RuntimeException("The original passwords do not match!");
        }
    }

    public void changeRole(ChangeRoleRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.OWNER){
            Employee employee = employeeRepository.findByEmail(request.getEmail());
            if(employee.getRole() == Role.OWNER)  throw new RuntimeException("Ownership cannot be revoked!");
            employee.setRole(request.getRole());
            employeeRepository.save(employee);
        } else if (initiator.getRole() == Role.MODERATOR && request.getRole() != Role.OWNER) {
            Employee employee = employeeRepository.findByEmail(request.getEmail());
            if(employee.getRole() == Role.MODERATOR) throw new RuntimeException("Only owners can change the roles of moderators!");
            employee.setRole(request.getRole());
            employeeRepository.save(employee);
        } else {
            throw new RuntimeException("You are not qualified to change roles!");
        }
    }

    public void changeSalary(ChangeSalaryRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.OWNER){
            Employee employee = employeeRepository.findByEmail(request.getEmail());
            employee.setSalary(request.getSalary());
            employeeRepository.save(employee);
        } else{
            throw new RuntimeException("Only owners allowed to change salaries!");
        }
    }

    public void delete(ViewUser user) {
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() != Role.MODERATOR && initiator.getRole() != Role.OWNER){
            throw new RuntimeException("You are not qualified to delete users!");
        }
        if(user.getRole() == Role.OWNER.ordinal()){
            throw new RuntimeException("Owners cannot be deleted!");
        }
        this.employeeRepository.deleteEmployeeByEmail(user.getEmail());
    }


}
