package app.bm.controller;

import app.bm.dto.*;
import app.bm.model.Group;
import app.bm.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin"})
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/")
    public ResponseEntity<List<ViewGroup>> getAll(){
        return ResponseEntity.ok(groupService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<ViewGroup> getGroup(@RequestBody GetGroupRequest request){
        return ResponseEntity.ok(groupService.getGroup(request));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody AddGroupRequest request){
        try {
            groupService.add(request);
            return ResponseEntity.ok("");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/assign")
    public ResponseEntity<String> assign(@RequestBody AssignOrRemoveGroupRequest request){
        try {
            groupService.assign(request);
            return ResponseEntity.ok("");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/remove")
    public ResponseEntity<String> remove(@RequestBody AssignOrRemoveGroupRequest request){
        try {
            groupService.remove(request);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/changeLeader")
    public ResponseEntity<String> changeLeader(@RequestBody AssignOrRemoveGroupRequest request){
        try {
            groupService.changeLeader(request);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestBody GetGroupRequest request){
        try {
            groupService.delete(request);
            return ResponseEntity.ok("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
