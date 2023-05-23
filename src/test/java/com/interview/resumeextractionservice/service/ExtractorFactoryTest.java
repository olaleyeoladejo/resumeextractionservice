package com.interview.resumeextractionservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExtractorFactoryTest {
    @Autowired
    private ExtractorFactory extractorFactory;
    @Test
    void findPDFExtractor() {
        IResumeExtractorService extractorService = extractorFactory.findExtractor(FileType.PDF);
        assertThat(extractorService).isInstanceOf(PdfResumeExtractorService.class);
    }

    @Test
    void findDOCExtractor() {
        IResumeExtractorService extractorService = extractorFactory.findExtractor(FileType.DOC);
        assertThat(extractorService).isNotNull();
        assertThat(extractorService).isInstanceOf(DocResumeExtractorService.class);
    }

    @Test
    void findDOCXExtractor() {
        IResumeExtractorService extractorService = extractorFactory.findExtractor(FileType.DOCX);
        assertThat(extractorService).isInstanceOf(DocxResumeExtractorService.class);
    }
}