package com.interview.resumeextractionservice.service;


import com.interview.resumeextractionservice.model.ResumeDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeExtractorService implements IResumeExtractorService {

    @Override
    public ResumeDetails extractDetails(MultipartFile file) {
        ResumeDetails resumeDetails = new ResumeDetails("", "", new ArrayList<>(), new ArrayList<>());

        try (InputStreamReader isr = new InputStreamReader(file.getInputStream());
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Education")) {
                    resumeDetails.getEducation().add(line);
                }
                if (line.contains("Experience")) {
                    resumeDetails.getPastExperiences().add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resumeDetails;
    }

    @Override
    public FileType getFileType() {
        return FileType.TEXT;
    }
}
