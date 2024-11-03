package java15.services;

import java15.models.Job;

import java.util.List;

public interface JobService {
    String createJobTable();

    String createJob(Job job);

    List<Job> getAllJobs();

    Job getJobById(Long jobId);

    String updateJob(Long id, Job job);

    void deleteJob(Long id);

    List<Job> sortByExperience(String ascOrDesc);

    Job getJobByEmployeeId(Long employeeId);

    void deleteDescriptionColumn();
}
