package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
public interface StudentRepostitory extends JpaRepository<Student, Integer> {
    
    @Query("SELECT s FROM Student s WHERE s.name LIKE %:search% OR s.address LIKE %:search% OR s.age LIKE %:search% OR s.phonenumber LIKE %:search%")
    Page<Student> search(@Param("search") String search, Pageable pageable);

}
