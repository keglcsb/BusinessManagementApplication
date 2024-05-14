package app.bm.service;

import app.bm.dto.*;
import app.bm.model.Employee;
import app.bm.model.Group;
import app.bm.model.Role;
import app.bm.repository.EmployeeRepository;
import app.bm.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final EmployeeRepository employeeRepository;
    public List<ViewGroup> getAll(){
        List<ViewGroup> result = new ArrayList<>();
        List<Group> groups = groupRepository.findAllBy();
        for (Group group:groups
             ) {

             ViewGroup var = ViewGroup.builder()
                            .id(group.getId())
                            .name(group.getName())
                     .leader(ViewUser.builder()
                             .id(group.getLeader().getId())
                             .salary(group.getLeader().getSalary())
                             .email(group.getLeader().getEmail())
                             .lastName(group.getLeader().getLastName())
                             .firstName(group.getLeader().getFirstName())
                             .role(group.getLeader().getRole().ordinal())
                             .build())
                     .build();
            result.add(var);
        }
        return result;
    }

    public ViewGroup getGroup(GetGroupRequest request){
        Group group = groupRepository.findById(request.getId());
        ViewGroup var = ViewGroup.builder()
                .id(group.getId())
                .name(group.getName())
                .leader(ViewUser.builder()
                        .id(group.getLeader().getId())
                        .salary(group.getLeader().getSalary())
                        .email(group.getLeader().getEmail())
                        .lastName(group.getLeader().getLastName())
                        .firstName(group.getLeader().getFirstName())
                        .role(group.getLeader().getRole().ordinal())
                        .build())
                .build();
        List<ViewUser> members = new ArrayList<>();
        for (Employee e:group.getMembers()
        ) {
            System.out.println(e);
            members.add(ViewUser.builder()
                    .id(e.getId())
                    .salary(e.getSalary())
                    .email(e.getEmail())
                    .lastName(e.getLastName())
                    .firstName(e.getFirstName())
                    .role(e.getRole().ordinal())
                    .build());
        }
        var.setMembers(members);
        return var;
    }

    public void add(AddGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("You are not qualified for this!");
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
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("You are not qualified for this!");
        Group group = groupRepository.findById(request.getGroupId());
        for (Employee e:group.getMembers()) {
            if(e.getId().equals(request.getEmployeeId())) throw new RuntimeException("The employee is already in assigned to this group!");
        }
        Employee e = employeeRepository.findById(request.getEmployeeId());
        group.getMembers().add(e);
        groupRepository.save(group);
        e.setGroup(group);
        employeeRepository.save(e);
    }

    public void remove(AssignOrRemoveGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("You are not qualified for this!");
        Group group = groupRepository.findById(request.getGroupId());
        Employee e = employeeRepository.findById(request.getEmployeeId());
        group.getMembers().remove(e);
        e.setGroup(null);
        groupRepository.save(group);
        employeeRepository.save(e);
    }

    public void changeLeader(AssignOrRemoveGroupRequest request){
        Employee initiator = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("You are not qualified for this!");
        Group group = groupRepository.findById(request.getGroupId());
        System.out.println(group);
        if(group.getLeader().getId().equals(request.getEmployeeId())) throw new RuntimeException("The given user is already the leader of this group!");
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
        if(initiator.getRole() == Role.BASE) throw new RuntimeException("You are not qualified for this!");
        Group group = groupRepository.findById(request.getId());
        for (Employee e:group.getMembers()) {
            e.setGroup(null);
        }
        groupRepository.deleteById(group.getId());
    }
}
