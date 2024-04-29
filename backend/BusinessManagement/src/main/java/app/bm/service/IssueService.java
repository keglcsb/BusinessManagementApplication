package app.bm.service;


import app.bm.dto.AssignOrRemoveIssueRequest;
import app.bm.dto.CreateIssueRequest;
import app.bm.dto.GetIssueRequest;
import app.bm.model.Employee;
import app.bm.model.Issue;
import app.bm.model.IssueStatus;
import app.bm.repository.EmployeeRepository;
import app.bm.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final EmployeeRepository employeeRepository;

    public List<Issue> getAll(){
        return issueRepository.findAllBy();
    }

    public Issue getById(GetIssueRequest request) {
        return this.issueRepository.findById(request.getId());
    }

    public void add(CreateIssueRequest request){
        Employee e = employeeRepository.findById(request.getCreatorId());
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

        issue.getWorkers().add(e);
        System.out.println(issue);
        e.getIssues().add(issue);
        System.out.println(e);
        employeeRepository.save(e);
    }
}
