package app.bm.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issue")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Workers"})
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private IssueStatus Status;
    private int Value;
    private String Description;
    private Date IssuedAt;
    @Nullable
    private Date ApprovedAt;
    @JsonIgnoreProperties(value = "issues")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "Id")
    private Employee Creator;
    @Nullable
    @JsonIgnoreProperties(value = "issues")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id", referencedColumnName = "Id")
    private Employee Approver;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Employee.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "issue_workers",
            joinColumns = @JoinColumn(name = "issueId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId")
    )
    private Set<Employee> Workers;
}
