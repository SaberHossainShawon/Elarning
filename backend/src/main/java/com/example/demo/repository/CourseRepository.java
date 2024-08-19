package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    boolean existsByCourseName(String courseName);

    @Query("SELECT c FROM Course c WHERE c.courseName LIKE %:search% OR c.courseDuration LIKE %:search% OR c.pricing LIKE %:search%  OR c.traineeName  LIKE %:search%")
    Page<Course> search(@Param("search") String search, Pageable pageable);

}