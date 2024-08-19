package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.Response;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController implements BaseController<Course, Integer>{

    private final CourseService courseService;

    @Override
    public ResponseEntity<Response<?>> save(Course course) {
       if(course.hasId()){
         return ResponseEntity.ok(courseService.merge(course));
       }else{
         return ResponseEntity.ok(courseService.prisist(course));
       }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        Response<?> response=courseService.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Course>> findById(Integer id) {
        Response<Course> course=courseService.findById(id);
       
        return ResponseEntity.ok(course);
    }

    @Override
    public ResponseEntity<Response<List<Course>>> findAll() {
        Response<List<Course>> courses=courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @Override
    public ResponseEntity<Response<Page<Course>>> findAllByPage(Integer pageNumber, Integer pageSize, String sortColumn,
        String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<Course>> response = courseService.findAllByPage(pageable, search);
        return ResponseEntity.ok(response);
    }
    
}
