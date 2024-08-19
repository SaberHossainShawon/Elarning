package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "quez_exam_paper")
public class QuizExamPaper extends BaseEntity {
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "questions", joinColumns = @JoinColumn(name = "quizexampaper_id"), inverseJoinColumns = @JoinColumn(name = "Quez_id"))
    private Set<Quez> questions;

    @Column(name = "exam_date")
    private Date examdate;

    @Column(name = "total_number")
    private String totalnumber;

    @Column(name = "subject")
    private String subject;
     
    @Column(name = "examtime")
    private String examtime;

    @Column(name = "title")
    private String title;
     
    @Column(name = "examduration")
    private String examduration;

}
