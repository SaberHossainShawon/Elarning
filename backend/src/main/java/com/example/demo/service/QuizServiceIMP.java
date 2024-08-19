package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.Quez;
import com.example.demo.repository.QuizRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceIMP implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public Response<?> prisist(Quez quiz) {
        if (!quiz.hasId()) {
            quiz.setId(null);
        }
        if (quizRepository.existsByQuestion(quiz.getQuestion())) {
            return new Response<>(ResponseStatus.ERROR, "Question already exists");
        }
        quizRepository.save(quiz);
        return new Response<>(ResponseStatus.SUCCESS, "Question saved successfully");
    }

    @Override
    public Response<?> merge(Quez quiz) {
        if( !quiz.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "Question ID is required");
        }
        quizRepository.save(quiz);
        return new Response<>(ResponseStatus.SUCCESS, "Question updated successfully");
    }

    @Override
    public Response<List<Quez>> findAll() {
        List<Quez> quiz = quizRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, quiz);
    }

    @Override
    public Response<String> deleteById(Integer id) {
        quizRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Question deleted successfully");
    }

    @Override
    public Response<Quez> findById(Integer id) {
        Quez quiz = quizRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, quiz);
    }

    @Override
    public Response<Page<Quez>> findAllByPage(Pageable pageable, String search) {
        Page <Quez> quiz;
        if (search == null || search.equals("*")) {
            quiz = quizRepository.findAll(pageable);
        } else {
            quiz = quizRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, quiz);
    }

}
