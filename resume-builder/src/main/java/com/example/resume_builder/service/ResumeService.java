package com.example.resume_builder.service;

import com.example.resume_builder.model.ResumeDTO;
import com.example.resume_builder.model.*;
import com.example.resume_builder.repository.ResumeRepository;
import com.example.resume_builder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    public Resume createResume(ResumeDTO dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        Resume resume = new Resume();

        resume.setName(dto.getName());
        resume.setEmail(dto.getEmail());
        resume.setPhone(dto.getPhone());
        resume.setDob(dto.getDob());
        resume.setAddress(dto.getAddress());
        resume.setLinkedIn(dto.getLinkedIn());
        resume.setGithub(dto.getGithub());
        resume.setPortfolio(dto.getPortfolio());
        resume.setSummary(dto.getSummary());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
        resume.setUser(user);

        if (dto.getSkills() != null) {
            dto.getSkills().forEach(skill -> skill.setResume(resume));
            resume.setSkills(dto.getSkills());
        }

        if (dto.getEducation() != null) {
            dto.getEducation().forEach(edu -> edu.setResume(resume));
            resume.setEducation(dto.getEducation());
        }

        if (dto.getExperience() != null) {
            dto.getExperience().forEach(exp -> exp.setResume(resume));
            resume.setExperience(dto.getExperience());
        }

        if (dto.getCertificates() != null) {
            dto.getCertificates().forEach(cert -> cert.setResume(resume));
            resume.setCertificates(dto.getCertificates());
        }

        if (dto.getProjects() != null) {
            dto.getProjects().forEach(proj -> proj.setResume(resume));
            resume.setProjects(dto.getProjects());
        }

        System.out.println("Saving resume for user: " + dto.getUserId());

        return resumeRepository.save(resume);
    }


    public List<Resume> getAllResumesByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    @Transactional
    public Resume updateResume(Long id, ResumeDTO dto) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resume not found with id: " + id));

        resume.setName(dto.getName());
        resume.setEmail(dto.getEmail());
        resume.setPhone(dto.getPhone());
        resume.setDob(dto.getDob());
        resume.setAddress(dto.getAddress());
        resume.setLinkedIn(dto.getLinkedIn());
        resume.setGithub(dto.getGithub());
        resume.setPortfolio(dto.getPortfolio());
        resume.setSummary(dto.getSummary());

        // Safely update each child list

        // Skills
        resume.getSkills().clear();
        if (dto.getSkills() != null) {
            for (Skill skill : dto.getSkills()) {
                skill.setResume(resume);
                resume.getSkills().add(skill);
            }
        }

        // Education
        resume.getEducation().clear();
        if (dto.getEducation() != null) {
            for (Education education : dto.getEducation()) {
                education.setResume(resume);
                resume.getEducation().add(education);
            }
        }

        // Experience
        resume.getExperience().clear();
        if (dto.getExperience() != null) {
            for (Experience exp : dto.getExperience()) {
                exp.setResume(resume);
                resume.getExperience().add(exp);
            }
        }

        // Certificates
        resume.getCertificates().clear();
        if (dto.getCertificates() != null) {
            for (Certificate cert : dto.getCertificates()) {
                cert.setResume(resume);
                resume.getCertificates().add(cert);
            }
        }

        // Projects
        resume.getProjects().clear();
        if (dto.getProjects() != null) {
            for (Project proj : dto.getProjects()) {
                proj.setResume(resume);
                resume.getProjects().add(proj);
            }
        }

        return resumeRepository.save(resume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}


