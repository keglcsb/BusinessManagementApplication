package app.bm.repository;

import app.bm.model.Issue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends Repository<Issue, Long> {

    List<Issue> findAllBy();
    Issue findById(Long id);
    void save(Issue issue);
    @Query("select i from Issue i  where i in (select e.issues from Employee e where e.id = :id)")
    List<Issue> findIssuesByWorkerId(@Param("id")Long id);
    @Transactional
    void deleteById(Long id);
}
