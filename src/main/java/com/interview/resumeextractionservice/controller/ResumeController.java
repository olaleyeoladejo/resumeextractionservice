package com.interview.resumeextractionservice.controller;


import com.interview.resumeextractionservice.model.ResumeDetails;
import com.interview.resumeextractionservice.service.ExtractorFactory;
import com.interview.resumeextractionservice.service.FileType;
import com.interview.resumeextractionservice.service.IResumeExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private ExtractorFactory extractorFactory;

    @Autowired
    public ResumeController(ExtractorFactory extractorFactory) {
        this.extractorFactory = extractorFactory;
    }

    @PostMapping
    public ResponseEntity uploadResume(@RequestBody MultipartFile file) {
        if(file.isEmpty()) return ResponseEntity.badRequest().body("empty request");

        String fileType = StringUtils.getFilenameExtension(file.getOriginalFilename()).toUpperCase();

        if(!Arrays.stream(FileType.values()).anyMatch(t -> t.name().equals(fileType)))
            return ResponseEntity.badRequest().body("file type not supported");

        IResumeExtractorService extractorService = extractorFactory.findExtractor(FileType.valueOf(fileType));
        ResumeDetails extractedDetails = extractorService.extractDetails(file);

        return ResponseEntity.ok(extractedDetails);
    }
}