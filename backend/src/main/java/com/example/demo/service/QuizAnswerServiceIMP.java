package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.QuizAnswer;
import com.example.demo.repository.QuizAnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAnswerServiceIMP implements QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;

    @Override
    public Response<?> prisist(QuizAnswer student) {
        quizAnswerRepository.save(student);
        return new Response<>(ResponseStatus.SUCCESS, "Student saved successfully");
    }

    @Override
    public Response<?> merge(QuizAnswer student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'merge'");
    }

    @Override
    public Response<List<QuizAnswer>> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Response<String> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Response<QuizAnswer> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Response<Page<QuizAnswer>> findAllByPage(Pageable pageable, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPage'");
    }
    
}
