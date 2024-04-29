package app.bm.repository;

import app.bm.model.Employee;
import app.bm.model.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.Repository;

import java.util.List;
public interface GroupRepository extends Repository<Group, Long> {

    Group findById(Long id);
    List<Group> findAllBy();
    void save(Group group);
    @Transactional
    void deleteById(Long id);
}
