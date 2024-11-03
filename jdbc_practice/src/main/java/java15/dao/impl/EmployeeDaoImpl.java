package java15.dao.impl;

import java15.config.DatabaseConnection;
import java15.dao.EmployeeDao;
import java15.models.Employee;
import java15.models.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private final Connection connection = DatabaseConnection.getConnection();

    @Override
    public boolean createTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS employees (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(30) NOT NULL,
                    last_name VARCHAR(30) NOT NULL,
                    age INT CHECK (age > 0),
                    email VARCHAR(255) NOT NULL UNIQUE CHECK (email LIKE '%@%'),
                    job_id INT REFERENCES jobs (id)
                );
                """;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Employee employee) {
        String query = """
                INSERT INTO employees (first_name, last_name, age, email, job_id)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public Map<Employee, Job> findById(Long employeeId) {
        return Map.of();
    }

    @Override
    public Employee findByEmail(String email) {
        return null;
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return List.of();
    }

    @Override
    public void update(Long id, Employee employee) {

    }

    @Override
    public void cleanTable() {
        String query = "TRUNCATE TABLE employees CASCADE";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table successfully cleaned");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Failed to clean table");
        }
    }

    @Override
    public void dropTable() {
        String query = "DROP TABLE IF EXISTS employees CASCADE";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table successfully dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Failed to drop table");
        }
    }
}
