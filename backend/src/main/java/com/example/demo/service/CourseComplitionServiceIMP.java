package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.CourseComplition;
import com.example.demo.repository.CourseComplitionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseComplitionServiceIMP implements CourseComplitionService{
    private final CourseComplitionRepository courseComplitionRepository;

    @Override
    public Response<?> prisist(CourseComplition courseComplition) {
        if(courseComplition.hasId()){
            courseComplition.setId(null);
        }
        courseComplitionRepository.save(courseComplition);
        return new Response<>(ResponseStatus.SUCCESS, "CourseComplition saved successfully");
    }

    @Override
    public Response<?> merge(CourseComplition courseComplition) {
        if(!courseComplition.hasId()){
            return new Response<>(ResponseStatus.ERROR, "CourseComplition ID is required");
        }
        courseComplitionRepository.save(courseComplition);
        return new Response<>(ResponseStatus.SUCCESS, "CourseComplition updated successfully");
    }

    @Override
    public Response<List<CourseComplition>> findAll() {
        List<CourseComplition> courseComplition = courseComplitionRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, courseComplition);
    }

    @Override
    public Response<String> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Response<CourseComplition> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Response<Page<CourseComplition>> findAllByPage(Pageable pageable, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPage'");
    }
    
}
