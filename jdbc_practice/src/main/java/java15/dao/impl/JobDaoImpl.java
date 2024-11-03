package java15.dao.impl;

import java15.config.DatabaseConnection;
import java15.dao.JobDao;
import java15.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private final Connection connection = DatabaseConnection.getConnection();

    @Override
    public boolean createTable() {
        String query = """
            CREATE TABLE IF NOT EXISTS jobs (
                id SERIAL PRIMARY KEY,
                position VARCHAR(255) NOT NULL,
                description TEXT,
                experience INT NOT NULL
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
    public boolean save(Job job) {
        String query = """
                INSERT INTO jobs (position, description, experience)
                VALUES (?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getDescription());
            preparedStatement.setInt(3, job.getExperience());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Job> findAll() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jobs.add(new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    @Override
    public Job findById(Long id) {
        String query = "SELECT * FROM jobs WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                return job;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String update(Long id, Job job) {
        String query = """
                UPDATE jobs SET position = ?,
                description = ?,
                experience = ?
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getDescription());
            preparedStatement.setInt(3, job.getExperience());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Job successfully updated";
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM jobs WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Job successfully deleted");
            } else {
                System.out.println("Job with id: " + id + " not found");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Job> sort(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String direction = ascOrDesc.equalsIgnoreCase("ASC") ? "ASC" : "DESC";
        String query = """
            SELECT * FROM jobs ORDER BY experience %s
            """.formatted(direction);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                jobs.add(new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to sort jobs: " + e.getMessage(), e);
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {

    }
}
