package com.example.demo.entity;



import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "course_step")
public class CourseStep extends BaseEntity {

    // @Column(name = "contant_type")
    // private String contanttype;

    @Column(name = "content", columnDefinition = "text")
    private String content;
    
    @Column(name = "video_link", columnDefinition = "text")
    private String videoLink;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private QuizExamPaper quizExamPapers;

    @Column(name = "step_number")
    private String stepnumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
