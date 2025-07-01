package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> list() {
        return userRepository.findByOccupation("student");
    }

    @PostMapping("/")
    public User create(@RequestBody User student) {
        student.setOccupation("student");
        return userRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
