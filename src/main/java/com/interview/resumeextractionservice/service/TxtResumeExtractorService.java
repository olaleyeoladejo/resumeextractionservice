package com.interview.resumeextractionservice.service;


import com.interview.resumeextractionservice.model.ResumeDetails;
import com.interview.resumeextractionservice.parser.ParseApplicantEducation;
import com.interview.resumeextractionservice.parser.ParseApplicantExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class TxtResumeExtractorService implements IResumeExtractorService {

    private ParseApplicantExperience parseApplicantExperience;
    private ParseApplicantEducation parseApplicantEducation;

    @Autowired
    public TxtResumeExtractorService(ParseApplicantExperience parseApplicantExperience, ParseApplicantEducation parseApplicantEducation) {
        this.parseApplicantExperience = parseApplicantExperience;
        this.parseApplicantEducation = parseApplicantEducation;
    }

    @Override
    public ResumeDetails extractDetails(MultipartFile file) {
        ResumeDetails resumeDetails = new ResumeDetails(new ArrayList<>(), new ArrayList<>());

        try {
            Scanner sc = new Scanner(file.getInputStream());
            String text = sc.toString();
            resumeDetails.getEducation().addAll(parseApplicantEducation.findEducations(text));
            resumeDetails.getPastExperiences().addAll(parseApplicantExperience.findWorkExperiences(text));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resumeDetails;
    }

    @Override
    public FileType getFileType() {
        return FileType.TXT;
    }
}
