package app.bm.controller;

import app.bm.dto.*;
import app.bm.model.Employee;
import app.bm.service.AuthService;
import app.bm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin"})
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AuthService authService;

    @GetMapping("/")
    public ResponseEntity<List<ViewUser>> getAll(){
        return ResponseEntity.ok(this.employeeService.getAll());
    }

    @GetMapping("/user")
    public ResponseEntity<ViewUser> getCurrentUser(){
        return ResponseEntity.ok(authService.getCurrent());
    }

    @PostMapping("/group")
    public ResponseEntity<List<ViewUser>> getByGroup(@RequestBody GetGroupRequest request){
        return ResponseEntity.ok(employeeService.getByGroup(request));
    }

    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin"})
    @PostMapping("/view")
    public ResponseEntity<ViewUser> getUser(@RequestBody GetUserRequest request){
        return ResponseEntity.ok(this.employeeService.getEmployee(request.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody RegisterRequest request){
         try {
             this.employeeService.add(request);
             return ResponseEntity.ok("");
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
         }
    }

    @PutMapping("/")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            this.employeeService.changePassword(request);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/changeRole")
    public ResponseEntity<String> changeRole(@RequestBody ChangeRoleRequest request){
        try{
            this.employeeService.changeRole(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/changeSalary")
    public ResponseEntity<String> changeSalary(@RequestBody ChangeSalaryRequest request){
        try {
            System.out.println(request);
            this.employeeService.changeSalary(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestBody ViewUser user){
        try{
            this.employeeService.delete(user);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
