package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobCreateDTO jobCreateDTO) {
        Job job = new Job();
        job.setJobName(jobCreateDTO.getJobName());
        job.setJobType(jobCreateDTO.getJobType());
        job.setStatus("PENDING"); // Default status
        Job createdJob = jobService.createJob(job);
        logger.info("Created job: {}", createdJob);
        return ResponseEntity.status(201).body(createdJob);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody JobUpdateDTO jobUpdateDTO) {
        try {
            Job job = new Job();
            job.setJobName(jobUpdateDTO.getJobName());
            job.setStatus(jobUpdateDTO.getStatus());
            job.setJobType(jobUpdateDTO.getJobType());
            Job updatedJob = jobService.updateJob(id, job);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status/{status}")
    public List<Job> getJobsByStatus(@PathVariable String status) {
        return jobService.getJobsByStatus(status);
    }

    @GetMapping("/jobType/{jobType}")
    public List<Job> getJobsByJobType(@PathVariable String jobType) {
        return jobService.getJobsByJobType(jobType);
    }

    @GetMapping("/date-range")
    public List<Job> getJobsByDateRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        return jobService.getJobsByDateRange(startDate, endDate);
    }
}
