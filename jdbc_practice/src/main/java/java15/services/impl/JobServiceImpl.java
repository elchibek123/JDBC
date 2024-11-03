package java15.services.impl;

import java15.dao.JobDao;
import java15.dao.impl.JobDaoImpl;
import java15.models.Job;
import java15.services.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    private final JobDao jobDao = new JobDaoImpl();

    @Override
    public String createJobTable() {
        return jobDao.createTable() ? "Table successfully saved" : "Failed to save table";
    }

    @Override
    public String createJob(Job job) {
        return jobDao.save(job) ? "Job successfully saved" : "Failed to save job";
    }

    @Override
    public List<Job> getAllJobs() {
        return jobDao.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobDao.findById(id);
    }

    @Override
    public String updateJob(Long id, Job job) {
        return jobDao.update(id, job);
    }

    @Override
    public void deleteJob(Long id) {
        jobDao.delete(id);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sort(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {

    }
}
