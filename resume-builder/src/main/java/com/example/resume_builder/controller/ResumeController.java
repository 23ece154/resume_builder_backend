

package com.example.resume_builder.controller;

import com.example.resume_builder.model.ResumeDTO;
import com.example.resume_builder.model.Resume;
import com.example.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")

public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<Resume> createResume(@RequestBody ResumeDTO resumeDTO) {

        Resume savedResume = resumeService.createResume(resumeDTO);
        return new ResponseEntity<>(savedResume, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable Long id, @RequestBody ResumeDTO resumeDTO) {
        Resume updated = resumeService.updateResume(id, resumeDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/user/{userId}")
    public List<Resume> getUserResumes(@PathVariable Long userId) {
        return resumeService.getAllResumesByUserId(userId);
    }

    @GetMapping("/all")
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }
}
