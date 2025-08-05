package com.example.resume_builder.model;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResumeDTO {
    private String name;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
    private String linkedIn;
    private String github;
    private String portfolio;
    private String summary;
    private Long userId;

    private List<Skill> skills;
    private List<Education> education;
    private List<Experience> experience;
    private List<Certificate> certificates;
    private List<Project> projects;
}
