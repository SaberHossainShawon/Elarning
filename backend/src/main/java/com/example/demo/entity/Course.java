package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private String image;

    @Column(name = "pricing")
    private String pricing;

    @Column(name = "rating")
    private String rating;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "trainee_name")
    private String traineeName;

    @Column(name = "course_duration")
    private String courseDuration;

    @Column(name = "total_students")
    private String totalStudents;

    

    // Constructors, getters, and setters
}
