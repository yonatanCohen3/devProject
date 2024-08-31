package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateJobTests {
        @Test
        void testCreateJob() {
            Job job = new Job();
            job.setJobName("Test Job");
            job.setJobType("Type A");
            job.setStatus("PENDING");

            when(jobRepository.save(any(Job.class))).thenReturn(job);

            Job createdJob = jobService.createJob(job);
            assertEquals("Test Job", createdJob.getJobName());
            assertEquals("Type A", createdJob.getJobType());
            assertEquals("PENDING", createdJob.getStatus());
        }
    }

    @Nested
    class GetJobTests {
        @Test
        void testGetJobById() {
            Job job = new Job();
            job.setJobName("Test Job");
            job.setJobType("Type A");

            when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

            Optional<Job> foundJob = jobService.getJobById(1L);
            assertTrue(foundJob.isPresent());
            assertEquals("Test Job", foundJob.get().getJobName());
        }
    }

    @Nested
    class UpdateJobTests {
        @Test
        void testUpdateJob() {
            Job existingJob = new Job();
            existingJob.setJobName("Old Job");
            existingJob.setJobType("Type A");

            Job updatedJob = new Job();
            updatedJob.setJobName("Updated Job");
            updatedJob.setJobType("Type B");

            when(jobRepository.findById(1L)).thenReturn(Optional.of(existingJob));
            when(jobRepository.save(any(Job.class))).thenReturn(updatedJob);

            Job result = jobService.updateJob(1L, updatedJob);
            assertEquals("Updated Job", result.getJobName());
            assertEquals("Type B", result.getJobType());
        }
    }

    @Nested
    class DeleteJobTests {
        @Test
        void testDeleteJob() {
            when(jobRepository.existsById(1L)).thenReturn(true);
            doNothing().when(jobRepository).deleteById(1L);

            assertDoesNotThrow(() -> jobService.deleteJob(1L));
        }
    }

    @Nested
    class ExceptionTests {
        @Test
        void testDeleteNonExistentJob() {
            when(jobRepository.existsById(1L)).thenReturn(false);

            RuntimeException thrown = assertThrows(RuntimeException.class, () -> jobService.deleteJob(1L));
            assertEquals("Job not found", thrown.getMessage());
        }
    }
}
