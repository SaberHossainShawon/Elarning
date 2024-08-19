package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.CourseStep;
import com.example.demo.repository.CourseStepRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseStepServiceIMP implements CourseStepService {
    private final CourseStepRepository courseStepRepository;

    @Override
    public Response<?> prisist(CourseStep courseStep) {
        if (courseStep.hasId()) {
            courseStep.setId(null);
        }
        courseStepRepository.save(courseStep);
        return new Response<>(ResponseStatus.SUCCESS, "CourseStep saved successfully");
    }

    @Override
    public Response<?> merge(CourseStep courseStep) {
        if (!courseStep.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "CourseStep ID is required");
        }
        courseStepRepository.save(courseStep);
        return new Response<>(ResponseStatus.SUCCESS, "CourseStep updated successfully");

    }

    @Override
    public Response<List<CourseStep>> findAll() {
        List<CourseStep> courseStep = courseStepRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, courseStep);
    }

    @Override
    public Response<String> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Response<CourseStep> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Response<Page<CourseStep>> findAllByPage(Pageable pageable, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPage'");
    }

}
