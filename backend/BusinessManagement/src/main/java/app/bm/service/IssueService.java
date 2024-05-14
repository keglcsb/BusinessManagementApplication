package app.bm.service;


import app.bm.dto.*;
import app.bm.model.*;
import app.bm.repository.EmployeeRepository;
import app.bm.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final EmployeeRepository employeeRepository;

    public List<ViewIssue> getAll(){
        List<ViewIssue> result = new ArrayList<>();
        List<Issue> issues = issueRepository.findAllBy();
        for (Issue issue: issues
             ) {
            result.add(ViewIssue.builder()
                            .id(issue.getId())
                            .name(issue.getName())
                            .value(issue.getValue())
                            .status(issue.getStatus().ordinal())
                    .build());
        }
        return result;
    }

    public ViewIssue getById(GetIssueRequest request) {
        Issue issue = this.issueRepository.findById(request.getId());
        ViewUser creator = ViewUser.builder()
                .id(issue.getCreator().getId())
                .email(issue.getCreator().getEmail())
                .firstName(issue.getCreator().getFirstName())
                .lastName(issue.getCreator().getLastName())
                .salary(issue.getCreator().getSalary())
                .role(issue.getCreator().getRole().ordinal())
                .build();
        ViewUser approver = null;
        if(issue.getApprover() != null){
             approver = ViewUser.builder()
                    .id(issue.getApprover().getId())
                    .email(issue.getApprover().getEmail())
                    .firstName(issue.getApprover().getFirstName())
                    .lastName(issue.getApprover().getLastName())
                    .salary(issue.getApprover().getSalary())
                    .role(issue.getApprover().getRole().ordinal())
                    .build();
        }
        Set<ViewUser> workers = new HashSet<>();
        if(!issue.getWorkers().isEmpty()){
            for (Employee e: issue.getWorkers()
            ) {
                workers.add(ViewUser.builder()
                        .id(e.getId())
                        .email(e.getEmail())
                        .firstName(e.getFirstName())
                        .lastName(e.getLastName())
                        .salary(e.getSalary())
                        .role(e.getRole().ordinal())
                        .build());
            }
        }

        return ViewIssue.builder()
                .id(issue.getId())
                .description(issue.getDescription())
                .issuedAt(issue.getIssuedAt().getTime())
                .approvedAt(issue.getApprovedAt() != null ? issue.getApprovedAt().getTime() : null)
                .status(issue.getStatus().ordinal())
                .value(issue.getValue())
                .name(issue.getName())
                .creator(creator)
                .approver(approver)
                .workers(workers)
                .build();
    }

    public List<ViewIssue> getByEmployee(GetUserRequest request) {
//        Employee e = employeeRepository.findById(request.getId());
//        List<Issue> issues = issueRepository.findIssuesByWorkersContaining(e);
        List<Issue> issues = issueRepository.findIssuesByWorkerId(request.getId());
        List<ViewIssue> result = new ArrayList<>();
        for (Issue issue:issues
             ) {
            result.add(ViewIssue.builder()
                    .id(issue.getId())
                    .description(issue.getDescription())
                    .issuedAt(issue.getIssuedAt().getTime())
                    .approvedAt(issue.getApprovedAt() != null ? issue.getApprovedAt().getTime() : null)
                    .status(issue.getStatus().ordinal())
                    .value(issue.getValue())
                    .name(issue.getName())
                    .build());
        }
        result.sort(Comparator.comparing(ViewIssue::getStatus));
        return result;
    }

    public void add(CreateIssueRequest request){
        Employee e = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        issueRepository.save(
                Issue.builder()
                        .Name(request.getName())
                        .Status(IssueStatus.HOLD)
                        .Description(request.getDescription())
                        .Value(request.getValue())
                        .IssuedAt(new Date())
                        .Creator(e)
                        .build()
        );
     }

    public void assign(AssignOrRemoveIssueRequest request) {
        Employee e = employeeRepository.findById(request.getEmployeeId());
        Issue issue = issueRepository.findById(request.getIssueId());

        assign(issue,e);
    }

    public void assign(Issue issue, Employee employee){
        issue.getWorkers().add(employee);
        issueRepository.save(issue);
    }


    public void remove(AssignOrRemoveIssueRequest request) {
        Employee e = employeeRepository.findById(request.getEmployeeId());
        Issue i = issueRepository.findById(request.getIssueId());

        i.getWorkers().remove(e);
        i.toString();
        issueRepository.save(i);
    }

    public void assignGroup(AssignGroupRequest request) {
        Issue i = issueRepository.findById(request.getIssueId());
        List<Employee> employees = employeeRepository.findAllByGroup_Id(request.getGroupId());
        for (Employee e:employees) {
            if(i.getWorkers().contains(e)) {
                System.out.println(e.getFirstName());
                continue;
            }
            assign(i,e);
        }
    }

    public void approve(GetIssueRequest request) {
        Employee e = employeeRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(e.getRole().equals(Role.BASE)){
            throw new RuntimeException("You are not qualified for this!");
        }
        Issue i = issueRepository.findById(request.getId());
        i.setApprover(e);
        i.setApprovedAt(new Date(System.currentTimeMillis()));
        i.setStatus(IssueStatus.OPEN);
        issueRepository.save(i);
    }

    public void changeStatus(GetIssueRequest request){
        Issue i = issueRepository.findById(request.getId());
        if(i.getStatus()==IssueStatus.OPEN) i.setStatus(IssueStatus.CLOSED);
        else i.setStatus(IssueStatus.OPEN);
        issueRepository.save(i);
    }
}
