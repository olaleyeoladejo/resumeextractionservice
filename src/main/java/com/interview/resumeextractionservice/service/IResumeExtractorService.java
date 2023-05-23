package com.interview.resumeextractionservice.service;

import com.interview.resumeextractionservice.model.ResumeDetails;
import org.springframework.web.multipart.MultipartFile;

public interface IResumeExtractorService {
    ResumeDetails extractDetails(MultipartFile file);
    FileType getFileType();
}
