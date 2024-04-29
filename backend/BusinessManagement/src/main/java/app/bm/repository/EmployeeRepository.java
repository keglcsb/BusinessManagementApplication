package app.bm.repository;

import app.bm.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Long> {
    List<Employee> findAllBy();
    void save(Employee employee);
    Employee findByEmail(String email);
    Employee findById(Long id);
    List<Employee> findAllByGroup_Id(Long id);
    @Transactional
    void deleteEmployeeByEmail(String email);
}
