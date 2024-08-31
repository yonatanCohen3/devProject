package com.example.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class JobServiceExceptionTests {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteNonExistentJob() {
        // Arrange
        when(jobRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            jobService.deleteJob(999L);
        });
    }

    @Test
    void testUpdateJobWithInvalidData() {
        // Arrange
        Job existingJob = new Job();
        existingJob.setId(1L);
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(existingJob));

        Job invalidJob = new Job();
        invalidJob.setId(1L);
        invalidJob.setStatus(null); // Assuming status can't be null

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            jobService.updateJob(1L, invalidJob);
        });
    }
}
