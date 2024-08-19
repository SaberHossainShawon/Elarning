package com.example.demo.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.QuizExamPaper;
import com.example.demo.service.QuizExamPaperService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequestMapping("/exampaper")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class QuizExamPaperController implements BaseController<QuizExamPaper,Integer>  {

    private final QuizExamPaperService quizExamPaperService;

    @Override
    public ResponseEntity<Response<?>> save(QuizExamPaper quizExamPaper) {
        if(quizExamPaper.hasId()){
            return ResponseEntity.ok(quizExamPaperService.merge(quizExamPaper));
          }else{
            return ResponseEntity.ok(quizExamPaperService.prisist(quizExamPaper));
          }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        return ResponseEntity.ok(quizExamPaperService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<QuizExamPaper>> findById(Integer id) {
        return ResponseEntity.ok(quizExamPaperService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<QuizExamPaper>>> findAll() {
        return ResponseEntity.ok(quizExamPaperService.findAll());
    }

    @Override
    public ResponseEntity<Response<Page<QuizExamPaper>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn, String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<QuizExamPaper>> response = quizExamPaperService.findAllByPage(pageable, search);
        return ResponseEntity.ok(response);
    }
    
    
}
