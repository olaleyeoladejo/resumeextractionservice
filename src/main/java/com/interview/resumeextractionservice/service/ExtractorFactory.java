package com.interview.resumeextractionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ExtractorFactory {
    private Map<FileType, IResumeExtractorService> extractors;

    @Autowired
    public ExtractorFactory(Set<IResumeExtractorService> extractorSet) {
        createExtractor(extractorSet);
    }

    public IResumeExtractorService findExtractor(FileType fileType) {
        return extractors.get(fileType);
    }
    private void createExtractor(Set<IResumeExtractorService> extractorSet) {
        extractors = new HashMap<>();
        extractorSet.forEach(
                extractor ->extractors.put(extractor.getFileType(), extractor));
    }
}
