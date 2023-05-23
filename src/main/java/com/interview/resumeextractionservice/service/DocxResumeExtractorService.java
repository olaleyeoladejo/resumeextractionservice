package com.interview.resumeextractionservice.service;

import com.interview.resumeextractionservice.model.ResumeDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class DocxResumeExtractorService implements IResumeExtractorService{
    @Override
    public ResumeDetails extractDetails(MultipartFile file) {
        ResumeDetails resumeDetails = new ResumeDetails("", "",new ArrayList<>(), new ArrayList<>());

        try (XWPFDocument doc = new XWPFDocument(file.getInputStream())) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String text = xwpfWordExtractor.getText();

            text.lines().forEach( line -> {
                    if (line.contains("Education")) {
                        resumeDetails.getEducation().add(line);
                    }
                    if (line.contains("Experience")) {
                        resumeDetails.getPastExperiences().add(line);
                    }
                }
            );
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
