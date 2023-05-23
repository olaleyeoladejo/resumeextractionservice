package com.interview.resumeextractionservice.parser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParseApplicantEducation {
    private final static Logger LOG = LogManager.getLogger();
    private ParserHelper helper = new ParserHelper();

    public List<String> findEducations(String line) {
        List<String> educationList= new ArrayList<>();
        ParserHelper parser = new ParserHelper();
        int indexOfEducation = parser.getIndexOfThisSection(RegEx.EDUCATION, line);
        if (indexOfEducation != -1) {
            List<Integer> listOfSectionIndexes = parser.getAllSectionIndexes(line);
            LOG.info("SECTION INDEXES: {}", listOfSectionIndexes);
            String texts = line.replaceFirst(RegEx.EDUCATION.toString(), "");
            return getEducationlines(helper.getSectionContent(indexOfEducation, listOfSectionIndexes, texts));
        }
        return educationList;
    }

    public List<String> getEducationlines(String educationSection) {
        List<String> educationList= new ArrayList<>();
        educationSection.lines().forEach( line -> {
            if (line.contains("Universit")) {
                educationList.add(line);
            }
            if (line.contains("Colleg")) {
                educationList.add(line);
            }
        });
        return educationList;
    }

}