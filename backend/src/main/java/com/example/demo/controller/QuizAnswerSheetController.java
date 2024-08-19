package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.QuizAnswerSheet;
import com.example.demo.service.QuizAnswerSheetService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/quizAnswerSheet")
@RequiredArgsConstructor
public class QuizAnswerSheetController implements BaseController<QuizAnswerSheet, Integer>  {
    private final QuizAnswerSheetService quizAnswerSheetService;
    @Override
    public ResponseEntity<Response<?>> save(QuizAnswerSheet quizAnswerSheet) {
        if(quizAnswerSheet.hasId()){
            return ResponseEntity.ok(quizAnswerSheetService.merge(quizAnswerSheet));
        }else{
            return ResponseEntity.ok(quizAnswerSheetService.prisist(quizAnswerSheet));
        }
    }
    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        return ResponseEntity.ok(quizAnswerSheetService.deleteById(id));
    }
    @Override
    public ResponseEntity<Response<QuizAnswerSheet>> findById(Integer id) {
        return ResponseEntity.ok(quizAnswerSheetService.findById(id));
    }
    @Override
    public ResponseEntity<Response<List<QuizAnswerSheet>>> findAll() {
        return ResponseEntity.ok(quizAnswerSheetService.findAll());
        
    }
    @Override
    public ResponseEntity<Response<Page<QuizAnswerSheet>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn, String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<QuizAnswerSheet>> response = quizAnswerSheetService.findAllByPage(pageable, search);
        return ResponseEntity.ok(response);
    }
}
