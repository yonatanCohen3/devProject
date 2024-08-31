package com.example.demo;

import lombok.Data;

@Data
public class createJobDto {
    private String jobName;
    private String description;
    private String status;
    private String jobType;
}
