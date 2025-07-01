package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.Warning;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.WarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warnings")
public class WarningController {

    @Autowired
    private WarningRepository warningRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{studentId}")
    public Warning create(@PathVariable Long studentId, @RequestBody Warning warning) {
        User student = userRepository.findById(studentId).orElseThrow();
        warning.setStudent(student);
        return warningRepository.save(warning);
    }

    @GetMapping("/{studentId}")
    public List<Warning> list(@PathVariable Long studentId) {
        User student = userRepository.findById(studentId).orElseThrow();
        return warningRepository.findByStudent(student);
    }
}
