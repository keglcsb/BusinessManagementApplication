package app.bm.repository;

import app.bm.model.Issue;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IssueRepository extends Repository<Issue, Long> {

    List<Issue> findAllBy();
    Issue findById(Long id);
    void save(Issue issue);

    @Transactional
    void deleteById(Long id);
}
