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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping("/")
    public List<Subject> list() {
        return subjectRepository.findAll();
    }

    @PostMapping("/")
    public Subject create(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/insertStudents/{id}")
    public ResponseEntity<?> insertStudents(@PathVariable Long id, @RequestBody StudentsBody body) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        Set<User> students = new HashSet<>(subject.getStudents());
        for (Long idStudent : body.getStudentsIds()) {
            userRepository.findById(idStudent).ifPresent(student -> {
                students.add(student);
                Grade grade = new Grade();
                grade.setStudent(student);
                grade.setSubject(subject);
                grade.setFirstGrade(0.0);
                grade.setSecondGrade(0.0);
                gradeRepository.save(grade);
            });
        }
        subject.setStudents(students);
        subjectRepository.save(subject);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeStudents/{id}")
    public ResponseEntity<?> removeStudents(@PathVariable Long id, @RequestBody StudentsBody body) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        Set<User> students = new HashSet<>(subject.getStudents());
        for (Long idStudent : body.getStudentsIds()) {
            userRepository.findById(idStudent).ifPresent(student -> {
                students.remove(student);
                gradeRepository.findByStudent(student).stream()
                        .filter(g -> g.getSubject().equals(subject))
                        .forEach(gradeRepository::delete);
            });
        }
        subject.setStudents(students);
        subjectRepository.save(subject);
        return ResponseEntity.ok().build();
    }

    static class StudentsBody {
        private List<Long> studentsIds;
        public List<Long> getStudentsIds() { return studentsIds; }
        public void setStudentsIds(List<Long> studentsIds) { this.studentsIds = studentsIds; }
    }
}
