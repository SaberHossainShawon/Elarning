package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceIMP implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public Response<?> prisist(Course course) {
        if(course.hasId()){
            course.setId(null);
        }
        if(courseRepository.existsByCourseName(course.getCourseName())){
            return new Response<>(ResponseStatus.ERROR, "Course already exists");
        }
        courseRepository.save(course);
        return new Response<>(ResponseStatus.SUCCESS, "Course saved successfully");

    }

    @Override
    public Response<?> merge(Course course) {
       if(!course.hasId()){
           return new Response<>(ResponseStatus.ERROR, "Course ID is required");
       }
       courseRepository.save(course);
       return new Response<>(ResponseStatus.SUCCESS, "Course updated successfully");
    }

    @Override
    public Response<List<Course>> findAll() {
        List<Course> course=courseRepository.findAll();
        Response<List<Course>> response=new Response<>(ResponseStatus.SUCCESS, null,course);
        return response;
    }

    @Override
    public Response<String> deleteById(Integer id) {
        courseRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Course deleted successfully");
    }

    @Override
    public Response<Course> findById(Integer id) {
        Course course= courseRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null,course);
    }

    @Override
    public Response<Page<Course>> findAllByPage(Pageable pageable, String search) {
        Page<Course> course;
        if (search == null || search.equals("*")) {
            course = courseRepository.findAll(pageable);
        } else {
            course = courseRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, course);
    }
    
}
