package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.QuizAnswerSheet;

@Repository
public interface QuizAnswerSheetRepository extends JpaRepository<QuizAnswerSheet, Integer> {
    @Query( "SELECT q FROM QuizAnswerSheet q WHERE q.userEmail LIKE %:search%")
    Page<QuizAnswerSheet> search(@Param("search") String search, Pageable pageable);
    

    
    

    
}
