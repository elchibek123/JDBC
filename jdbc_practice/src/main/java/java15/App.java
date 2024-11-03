package java15;

import java15.models.Employee;
import java15.models.Job;
import java15.services.EmployeeService;
import java15.services.JobService;
import java15.services.impl.EmployeeServiceImpl;
import java15.services.impl.JobServiceImpl;

public class App {
    public static void main( String[] args ) {
        JobService jobService = new JobServiceImpl();

        System.out.println(jobService.createJobTable());
//
//        System.out.println(jobService.createJob(new Job("Java Developer", "This is Java Development position", 5)));
//        System.out.println(jobService.createJob(new Job("Python Developer", "This is Python Development position", 8)));
//
//        jobService.getAllJobs().forEach(System.out::println);
//
//        System.out.println(jobService.getJobById(1L));
//
//        System.out.println(jobService.updateJob(2L, new Job("Ruby Developer", "This is Ruby Development position", 3)));
//
//        jobService.deleteJob(4L);
//
//        jobService.sortByExperience("asc").forEach(System.out::println);

        jobService.dropJobTable();


//        EmployeeService employeeService = new EmployeeServiceImpl();
//
//        System.out.println(employeeService.createEmployeeTable());
//
////        System.out.println(employeeService.createEmployee(new Employee("John", "Doe", 24, "johndoe@gmail.com", 6L)));
//        System.out.println(employeeService.createEmployee(new Employee("Mark", "Smith", 52, "marksmith@gmail.com", 4L)));
    }
}
