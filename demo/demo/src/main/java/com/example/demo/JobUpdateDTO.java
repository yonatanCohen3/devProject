package com.example.demo;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class JobUpdateDTO {
    private String jobName;
    private String status;
    private String jobType;
}
