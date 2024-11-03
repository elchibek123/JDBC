package java15.dao;

import java15.models.Job;

import java.util.List;

public interface JobDao {
    boolean createTable();

    boolean save(Job job);

    List<Job> findAll();

    Job findById(Long id);

    String update(Long id, Job job);

    void delete(Long id);

    List<Job> sort(String ascOrDesc);

    Job getJobByEmployeeId(Long employeeId);

    void deleteDescriptionColumn();
}
