package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.Response;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController implements BaseController<Student,Integer> {
  private final StudentService studentservice;

    @Override
    public ResponseEntity<Response<?>> save(Student student) {
        if(student.hasId()){
            return ResponseEntity.ok(studentservice.merge(student));
        }else{
            return ResponseEntity.ok(studentservice.prisist(student));
        }
       
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        Response<?> response=studentservice.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Student>> findById(Integer id) {
       Response<Student> response=studentservice.findById(id);
       return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<List<Student>>> findAll() {
        Response<List<Student>> student=studentservice.findAll();
        return ResponseEntity.ok(student);
       
    }

    @Override
    public ResponseEntity<Response<Page<Student>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn, String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<Student>> response=studentservice.findAllByPage(pageable,search);
        return ResponseEntity.ok(response);
    }

    
    
}
