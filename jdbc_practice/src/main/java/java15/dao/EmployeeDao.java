package java15.dao;

import java15.models.Employee;
import java15.models.Job;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    boolean createTable();

    boolean save(Employee employee);

    List<Employee> findAll();

    Map<Employee, Job> findById(Long employeeId);

    Employee findByEmail(String email);

    List<Employee> findByPosition(String position);

    void update(Long id,Employee employee);

    void cleanTable();

    void dropTable();
}
