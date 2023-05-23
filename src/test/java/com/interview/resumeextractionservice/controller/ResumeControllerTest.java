package com.interview.resumeextractionservice.controller;

import com.interview.resumeextractionservice.model.ResumeDetails;
import com.interview.resumeextractionservice.service.ExtractorFactory;
import com.interview.resumeextractionservice.service.PdfResumeExtractorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ResumeController.class)
class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExtractorFactory extractorFactory;
    @MockBean
    private PdfResumeExtractorService pdfResumeExtractorService;
    @Test
    void uploadResume() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.pdf",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        ResumeDetails result = new ResumeDetails("Olaleye", "Male", new ArrayList<>(), new ArrayList<>());
        when(extractorFactory.findExtractor(any())).thenReturn(pdfResumeExtractorService);
        when(pdfResumeExtractorService.extractDetails(any())).thenReturn(result);
        this.mockMvc.perform(multipart("/api/resume").file(file)).andDo(print()).andExpect(status().isOk());
    }
}