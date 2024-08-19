package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "quiz")
public class Quez extends BaseEntity {
    @Column(name = "question")
    private String question;

    @ElementCollection
    @CollectionTable(name = "quiz_option", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<String> options=new ArrayList<>();

    @Column(name = "answer")
    private String answer;
}
