package com.example.backend.repository;

import com.example.backend.entity.Warning;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarningRepository extends JpaRepository<Warning, Long> {
    List<Warning> findByStudent(User student);
}
