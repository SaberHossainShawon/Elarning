package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.QuizExamPaper;

@Repository
public interface QuizExamPaperRepository extends JpaRepository<QuizExamPaper, Integer> {
     
    @Query("SELECT q FROM QuizExamPaper q WHERE q.subject LIKE %:search%")
    Page<QuizExamPaper> search(@Param("search") String search, Pageable pageable);
    
}
