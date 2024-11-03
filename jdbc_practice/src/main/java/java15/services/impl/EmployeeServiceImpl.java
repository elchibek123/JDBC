package java15.services.impl;

import java15.dao.EmployeeDao;
import java15.dao.impl.EmployeeDaoImpl;
import java15.models.Employee;
import java15.models.Job;
import java15.services.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public String createEmployeeTable() {
        return employeeDao.createTable() ? "Table successfully saved" : "Failed to save table";
    }

    @Override
    public String createEmployee(Employee employee) {
        return employeeDao.save(employee) ? "Employee successfully saved" : "Failed to save employee";
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return Map.of();
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return List.of();
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        return "";
    }

    @Override
    public void cleanTable() {
        employeeDao.cleanTable();
    }

    @Override
    public void dropTable() {

    }
}
