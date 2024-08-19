package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.Quez;
import com.example.demo.service.QuizService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequestMapping("/quiz")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class QuizController implements BaseController<Quez,Integer> {
 private final QuizService quizService;

    @Override
    public ResponseEntity<Response<?>> save(Quez quiz) {
       if(quiz.hasId()){
        return ResponseEntity.ok(quizService.merge(quiz));          
       }else{
        return ResponseEntity.ok(quizService.prisist(quiz));
       }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        return ResponseEntity.ok(quizService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<Quez>> findById(Integer id) {
        return ResponseEntity.ok(quizService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Quez>>> findAll() {
        return ResponseEntity.ok(quizService.findAll());
    }

    @Override
    public ResponseEntity<Response<Page<Quez>>> findAllByPage(Integer pageNumber, Integer pageSize, String sortColumn,
            String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<Quez>> response = quizService.findAllByPage(pageable, search);
        return ResponseEntity.ok(response);
        
    }
    
}
