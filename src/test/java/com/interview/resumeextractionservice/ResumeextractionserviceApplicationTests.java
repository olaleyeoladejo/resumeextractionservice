package com.interview.resumeextractionservice;

import com.interview.resumeextractionservice.controller.ResumeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ResumeextractionserviceApplicationTests {

    @Autowired
    private ResumeController resumeController;
    @Test
    void contextLoads() {
        assertThat(resumeController).isNotNull();
    }

}
