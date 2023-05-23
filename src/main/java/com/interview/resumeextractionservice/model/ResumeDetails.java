package com.interview.resumeextractionservice.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDetails {
    private String name;
    private String gender;
    private List<String> education;
    private List<String> pastExperiences;
}
