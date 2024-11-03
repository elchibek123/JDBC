package java15.services;

import java15.models.Employee;
import java15.models.Job;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    String createEmployeeTable();

    String createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Map<Employee, Job> getEmployeeById(Long employeeId);

    Employee getEmployeeByEmail(String email);

    List<Employee> getEmployeeByPosition(String position);

    String updateEmployee(Long id,Employee employee);

    void cleanTable();

    void dropTable();
}
