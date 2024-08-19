package com.example.demo.repository;

import com.example.demo.entity.Quez;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository <Quez,Integer> {

    boolean existsByQuestion(String question);
    
    @Query("SELECT q FROM Quez q WHERE q.question LIKE %:search%")
    Page<Quez> search(@Param("search") String search, Pageable pageable);
    
} 
