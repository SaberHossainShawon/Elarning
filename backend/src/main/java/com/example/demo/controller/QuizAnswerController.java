package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.QuizAnswer;
import com.example.demo.service.QuizAnswerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quizanswer")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class QuizAnswerController implements BaseController<QuizAnswer, Integer> {
    
    private final QuizAnswerService quizAnswerService;
    @Override
    public ResponseEntity<Response<?>> save(QuizAnswer quizAnswer) {
        if (quizAnswer.hasId()) {
            return ResponseEntity.ok(quizAnswerService.merge(quizAnswer));
        }else {
            return ResponseEntity.ok(quizAnswerService.prisist(quizAnswer));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public ResponseEntity<Response<QuizAnswer>> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ResponseEntity<Response<List<QuizAnswer>>> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public ResponseEntity<Response<Page<QuizAnswer>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn,
            String order, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPage'");
    }
}
