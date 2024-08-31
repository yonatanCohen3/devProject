package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    void testGetAllJobs() throws Exception {
        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testCreateJob() throws Exception {
        JobCreateDTO dto = new JobCreateDTO();
        dto.setJobName("New Job");
        dto.setJobType("Type A");

        mockMvc.perform(post("/jobs")
                        .contentType("application/json")
                        .content("{\"jobName\":\"New Job\",\"jobType\":\"Type A\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.jobName").value("New Job"))
                .andExpect(jsonPath("$.jobType").value("Type A"));
    }

    @Test
    void testUpdateJob() throws Exception {
        JobUpdateDTO dto = new JobUpdateDTO();
        dto.setJobName("Updated Job");
        dto.setStatus("Completed");
        dto.setJobType("Type B");

        mockMvc.perform(put("/jobs/1")
                        .contentType("application/json")
                        .content("{\"jobName\":\"Updated Job\",\"status\":\"Completed\",\"jobType\":\"Type B\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobName").value("Updated Job"))
                .andExpect(jsonPath("$.status").value("Completed"));
    }

    @Test
    void testDeleteJob() throws Exception {
        mockMvc.perform(delete("/jobs/1"))
                .andExpect(status().isNoContent());
    }
}
