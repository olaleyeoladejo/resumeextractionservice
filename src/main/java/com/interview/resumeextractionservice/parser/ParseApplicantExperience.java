package com.interview.resumeextractionservice.parser;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParseApplicantExperience {

    private ParserHelper helper = new ParserHelper();


    public List<String> findWorkExperiences(String line) {
        List<String> experienceList= new ArrayList<>();
        ParserHelper parser = new ParserHelper();

        int indexOfExperience = parser.getIndexOfThisSection(RegEx.EXPERIENCE, line);
        if (indexOfExperience != -1) {
            List<Integer> listOfSectionIndexes = parser.getAllSectionIndexes(line);
            String texts = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
            return getExperiencelines(helper.getSectionContent(indexOfExperience, listOfSectionIndexes, texts));
        }
        return experienceList;
    }

    public List<String> getExperiencelines(String experienceSection) {
        List<String> months = List.of(" Jan ", " January ", " Feb ", " February ", " Mar ", " March ",
                " Apr ", " April ", " May ", " Jun ", " June ", " July ", " Jul ", " August ", " Aug ",
                " September ", " Sept ", " October ", " Oct ", " November ", " Nov ", " December ", " Dec " );
        List<String> experienceList= new ArrayList<>();
        experienceSection.lines().forEach( line -> {
            for(String month : months) {
                if (line.contains(month)) {
                    experienceList.add(line);
                    break;
                }
            }
        });
        return experienceList;
    }

}
