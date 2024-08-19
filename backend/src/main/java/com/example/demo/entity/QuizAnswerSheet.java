package com.example.demo.entity;

import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "quiz_answer_sheet")
public class QuizAnswerSheet extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "quizexampaper_id")
    private QuizExamPaper examPaperId;

    @Column(name = "user_email")
    private String userEmail;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "answer", joinColumns = @JoinColumn(name = "quizanswersheet_id"), inverseJoinColumns = @JoinColumn(name = "QuezAnswer_id"))
    private Set<QuizAnswer> answers;

    @Column(name = "Total_achieved_marks")
    private String totalAchevedMarkes;

}
