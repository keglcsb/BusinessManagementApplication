package app.bm.controller;

import app.bm.dto.AssignOrRemoveIssueRequest;
import app.bm.dto.CreateIssueRequest;
import app.bm.dto.GetIssueRequest;
import app.bm.model.Issue;
import app.bm.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/")
    public ResponseEntity<List<Issue>> getAll(){
        return ResponseEntity.ok( this.issueService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Issue> getById(@RequestBody GetIssueRequest request){
        return ResponseEntity.ok(this.issueService.getById(request));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CreateIssueRequest request){
        try {
            issueService.add(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
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
}
