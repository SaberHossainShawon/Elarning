package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Response;
import com.example.demo.entity.Student;

public interface StudentService extends BaseService<Student, Integer> {

    Response<Page<Student>> findAllByPage(Pageable pageable, String search);

    
} 