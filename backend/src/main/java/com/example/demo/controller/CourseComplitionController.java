package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.CourseComplition;
import com.example.demo.service.CourseComplitionService;

import lombok.RequiredArgsConstructor;

@CrossOrigin( origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/courseComplition")
@RestController

public class CourseComplitionController implements BaseController<CourseComplition,Integer> {
    private final CourseComplitionService courseComplitionService;

    @Override
    public ResponseEntity<Response<?>> save(CourseComplition courseComplition) {
        if( courseComplition.hasId() ){
             return ResponseEntity.ok(courseComplitionService.merge(courseComplition));
        }else{
            return ResponseEntity.ok(courseComplitionService.prisist(courseComplition));
        }   

    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public ResponseEntity<Response<CourseComplition>> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ResponseEntity<Response<List<CourseComplition>>> findAll() {
       Response<List<CourseComplition>> response = courseComplitionService.findAll();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<CourseComplition>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn, String order, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPage'");
    }
    
}
