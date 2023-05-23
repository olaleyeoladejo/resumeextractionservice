package com.interview.resumeextractionservice.service;

import com.interview.resumeextractionservice.model.ResumeDetails;
import com.interview.resumeextractionservice.parser.ParseApplicantEducation;
import com.interview.resumeextractionservice.parser.ParseApplicantExperience;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class DocxResumeExtractorService implements IResumeExtractorService{

    private ParseApplicantExperience parseApplicantExperience;
    private ParseApplicantEducation parseApplicantEducation;

    @Autowired
    public DocxResumeExtractorService(ParseApplicantExperience parseApplicantExperience, ParseApplicantEducation parseApplicantEducation) {
        this.parseApplicantExperience = parseApplicantExperience;
        this.parseApplicantEducation = parseApplicantEducation;
    }
    @Override
    public ResumeDetails extractDetails(MultipartFile file) {
        ResumeDetails resumeDetails = new ResumeDetails(new ArrayList<>(), new ArrayList<>());

        try (XWPFDocument doc = new XWPFDocument(file.getInputStream())) {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String text = xwpfWordExtractor.getText();
            resumeDetails.getEducation().addAll(parseApplicantEducation.findEducations(text));
            resumeDetails.getPastExperiences().addAll(parseApplicantExperience.findWorkExperiences(text));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resumeDetails;
    }

    @Override
    public FileType getFileType() {
        return FileType.DOCX;
    }
}
