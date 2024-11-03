package java15.dao.impl;

import java15.config.DatabaseConfig;
import java15.dao.CustomerDao;
import java15.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final Connection connection = DatabaseConfig.getConnection();

    @Override
    public boolean saveTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS customers (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(30) NOT NULL,
                    last_name VARCHAR(30) NOT NULL,
                    age INT CHECK (age > 0),
                    phone_number VARCHAR(20) NOT NULL
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Customer customer) {
        String query = """
                INSERT INTO customers (first_name, last_name, age, phone_number)
                VALUES (?, ?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.setString(4, customer.getPhoneNumber());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Customer> findAll() {
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Customer customer = Customer.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .build();
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Customer.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Long id, Customer newCustomer) {
        String query = """
                UPDATE customers 
                SET first_name = ?,
                    last_name = ?,
                    age = ?
                    phone_number = ?
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newCustomer.getFirstName());
            preparedStatement.setString(2, newCustomer.getLastName());
            preparedStatement.setInt(3, newCustomer.getAge());
            preparedStatement.setString(4, newCustomer.getPhoneNumber());
            preparedStatement.setLong(5, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Customer> sortByAge(String ascOrDesc) {
        List<Customer> customers = new ArrayList<>();
        String direction = ascOrDesc.equalsIgnoreCase("ASC") ? "ASC" : "DESC";
        String query = """
            SELECT * FROM customers ORDER BY age %s
            """.formatted(direction);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = Customer.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .build();
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to sort customers: " + e.getMessage(), e);
        }
        return customers;
    }

    @Override
    public List<Customer> searchByName(String name) {
        String query = "SELECT * FROM customers WHERE first_name ILIKE ?";
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = Customer.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .build();
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }
}
