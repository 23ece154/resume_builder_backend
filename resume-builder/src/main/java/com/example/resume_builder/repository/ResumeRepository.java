package com.example.resume_builder.repository;

import com.example.resume_builder.model.Resume;
import com.example.resume_builder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUserEmail(String email);

    List<Resume> findByUserId(Long userId);
}