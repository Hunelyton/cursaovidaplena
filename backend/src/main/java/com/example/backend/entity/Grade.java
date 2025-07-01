package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double firstGrade;
    private Double secondGrade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getFirstGrade() { return firstGrade; }
    public void setFirstGrade(Double firstGrade) { this.firstGrade = firstGrade; }
    public Double getSecondGrade() { return secondGrade; }
    public void setSecondGrade(Double secondGrade) { this.secondGrade = secondGrade; }
    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}
