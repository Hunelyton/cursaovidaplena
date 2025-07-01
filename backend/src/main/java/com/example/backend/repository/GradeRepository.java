package com.example.backend.repository;

import com.example.backend.entity.Grade;
import com.example.backend.entity.Subject;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findBySubject(Subject subject);
    List<Grade> findByStudent(User student);
}
