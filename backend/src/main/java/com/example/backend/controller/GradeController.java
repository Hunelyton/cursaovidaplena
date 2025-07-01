package com.example.backend.controller;

import com.example.backend.entity.Grade;
import com.example.backend.entity.Subject;
import com.example.backend.entity.User;
import com.example.backend.repository.GradeRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/subject/{subjectId}")
    public List<Grade> gradesBySubject(@PathVariable Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        return gradeRepository.findBySubject(subject);
    }

    @PutMapping("/{id}")
    public Grade update(@PathVariable Long id, @RequestBody Grade g) {
        Grade grade = gradeRepository.findById(id).orElseThrow();
        grade.setFirstGrade(g.getFirstGrade());
        grade.setSecondGrade(g.getSecondGrade());
        return gradeRepository.save(grade);
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> gradesByStudent(@PathVariable Long studentId) {
        User student = userRepository.findById(studentId).orElseThrow();
        return gradeRepository.findByStudent(student);
    }
}
