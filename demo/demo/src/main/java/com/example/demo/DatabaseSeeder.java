package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedDatabase(JobRepository jobRepository) {
        return args -> {
            jobRepository.save(new Job(null, "Job 1", "PENDING", LocalDateTime.now(), null, "Type A"));
            jobRepository.save(new Job(null, "Job 2", "COMPLETED", LocalDateTime.now(), null, "Type B"));
        };
    }
}
