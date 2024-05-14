package app.bm.controller;

import app.bm.dto.*;
import app.bm.model.Issue;
import app.bm.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin"})
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/")
    public ResponseEntity<List<ViewIssue>> getAll(){
        return ResponseEntity.ok( this.issueService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<ViewIssue> getById(@RequestBody GetIssueRequest request){
        return ResponseEntity.ok(this.issueService.getById(request));
    }

    @PostMapping("/employee")
    public ResponseEntity<List<ViewIssue>> getByEmployee(@RequestBody GetUserRequest request){
        return ResponseEntity.ok(this.issueService.getByEmployee(request));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CreateIssueRequest request){
        try {
            issueService.add(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/assign")
    public ResponseEntity<String> assign(@RequestBody AssignOrRemoveIssueRequest request){
        try {
            issueService.assign(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/assignGroup")
    public ResponseEntity<String> assignGroup(@RequestBody AssignGroupRequest request){
        try {
            issueService.assignGroup(request);
            return ResponseEntity.ok("");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/remove")
    public ResponseEntity<String> remove(@RequestBody AssignOrRemoveIssueRequest request){
        try {
            issueService.remove(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/approve")
    public ResponseEntity<String> approve(@RequestBody GetIssueRequest request){
        try {
            issueService.approve(request);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestBody GetIssueRequest request){
        try {
            issueService.changeStatus(request);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
