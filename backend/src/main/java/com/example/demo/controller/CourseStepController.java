package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.CourseStep;
import com.example.demo.service.CourseStepService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coursestep")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseStepController implements BaseController<CourseStep, Integer> {
    private final CourseStepService courseStepService;

    @Override
    public ResponseEntity<Response<?>> save(CourseStep courseStep) {
        if (courseStep.hasId()) {
            return ResponseEntity.ok(courseStepService.merge(courseStep));
        }else {
            return ResponseEntity.ok(courseStepService.prisist(courseStep));
        }
    }

    @Override
    public ResponseEntity<Response<List<CourseStep>>> findAll() {
        Response<List<CourseStep>> courseStep = courseStepService.findAll();
        return ResponseEntity.ok(courseStep);

    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public ResponseEntity<Response<CourseStep>> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ResponseEntity<Response<Page<CourseStep>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn, String order, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPage'");
    }

}
