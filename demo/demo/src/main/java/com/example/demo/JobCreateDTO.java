package com.example.demo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobCreateDTO {
    private String jobName;
    private String jobType;
}
