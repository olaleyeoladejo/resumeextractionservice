package com.interview.resumeextractionservice.service;

import com.interview.resumeextractionservice.parser.ParseApplicantEducation;
import com.interview.resumeextractionservice.parser.ParseApplicantExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocResumeExtractorService extends DocxResumeExtractorService {
    @Autowired
    public DocResumeExtractorService(ParseApplicantExperience parseApplicantExperience, ParseApplicantEducation parseApplicantEducation) {
        super(parseApplicantExperience, parseApplicantEducation);
    }

    @Override
    public FileType getFileType() {
        return FileType.DOC;
    }
}
