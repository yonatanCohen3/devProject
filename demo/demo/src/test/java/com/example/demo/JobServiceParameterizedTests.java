package com.example.demo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

public class JobServiceParameterizedTests {
    @ParameterizedTest
    @ValueSource(strings = {"SUCCESS", "FAILED", "IN_PROGRESS"})
    void testAddJobWithVariousStatuses(String status) {
        Job job = new Job();
        // Add assertions based on your logic
    }
}
