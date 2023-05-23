package com.interview.resumeextractionservice.controller;


import com.interview.resumeextractionservice.model.ResumeDetails;
import com.interview.resumeextractionservice.service.ExtractorFactory;
import com.interview.resumeextractionservice.service.FileType;
import com.interview.resumeextractionservice.service.IResumeExtractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private ExtractorFactory extractorFactory;

    public ResumeController(ExtractorFactory extractorFactory) {
        this.extractorFactory = extractorFactory;
    }

    @PostMapping
    public ResponseEntity<ResumeDetails> uploadResume(@RequestBody MultipartFile file) {
        String fileType = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if(fileType==null) return ResponseEntity.badRequest().build();
        IResumeExtractorService extractorService = extractorFactory.findExtractor(FileType.valueOf(fileType.toUpperCase()));
        ResumeDetails extractedDetails = extractorService.extractDetails(file);

        if (extractedDetails != null)
            return ResponseEntity.ok(extractedDetails);
        else
            return ResponseEntity.badRequest().build();
    }
}