package app.bm.service;

import app.bm.dto.AddGroupRequest;
import app.bm.dto.AssignOrRemoveGroupRequest;
import app.bm.dto.GetGroupRequest;
import app.bm.model.Employee;
import app.bm.model.Group;
import app.bm.model.Role;
import app.bm.repository.EmployeeRepository;
import app.bm.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final EmployeeRepository employeeRepository;
    public List<Group> getAll(){
        return groupRepository.findAllBy();
    }

    public Group getGroup(GetGroupRequest request){
        return groupRepository.findById(request.getId());
    }

    public void add(AddGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("Ehhez nincs Önnek jogosultsága");
        Employee e = employeeRepository.findById(request.getLeaderId());
        Group group = Group.builder()
                .Name(request.getName())
                .Leader(e)
                .build();
        e.setGroup(group);
        groupRepository.save(group);
        employeeRepository.save(e);
    }

    public void assign(AssignOrRemoveGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("Ehhez nincs Önnek jogosultsága");
        Group group = groupRepository.findById(request.getGroupId());
        for (Employee e:group.getMembers()) {
            if(e.getId().equals(request.getEmployeeId())) throw new RuntimeException("A felhasználó már tagja a csoportnak!");
        }
        Employee e = employeeRepository.findById(request.getEmployeeId());
        group.getMembers().add(e);
        groupRepository.save(group);
        e.setGroup(group);
        employeeRepository.save(e);
    }

    public void remove(AssignOrRemoveGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("Ehhez nincs Önnek jogosultsága");
        Group group = groupRepository.findById(request.getGroupId());
        Employee e = employeeRepository.findById(request.getEmployeeId());
        group.getMembers().remove(e);
        e.setGroup(null);
        groupRepository.save(group);
        employeeRepository.save(e);
    }

    public void changeLeader(AssignOrRemoveGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("Ehhez nincs Önnek jogosultsága");
        Group group = groupRepository.findById(request.getGroupId());
        if(group.getLeader().getId().equals(request.getEmployeeId())) throw new RuntimeException("A felhasználó már vezetője a csoportnak!");
        for (Employee e:group.getMembers()) {
            if(e.getId().equals(request.getEmployeeId())) {
                group.setLeader(e);
                return;
            }
        }
        Employee e = employeeRepository.findById(request.getEmployeeId());
        group.getMembers().add(e);
        group.setLeader(e);
        e.setGroup(group);
        employeeRepository.save(e);
    }

    public void delete(GetGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("Ehhez nincs Önnek jogosultsága");
        Group group = groupRepository.findById(request.getId());
        for (Employee e:group.getMembers()) {
            e.setGroup(null);
        }
        groupRepository.deleteById(group.getId());
    }
}
