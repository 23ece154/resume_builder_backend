package com.example.resume_builder.service;

import com.example.resume_builder.model.User;
import com.example.resume_builder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists.");
        }
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found.");
        }

        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password.");
        }

        return user;
    }
}
