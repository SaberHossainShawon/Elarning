package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.QuizExamPaper;
import com.example.demo.repository.QuizExamPaperRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizExamPaperServiceIMP implements QuizExamPaperService {
    private final QuizExamPaperRepository quizExamPaperRepository;

    @Override
    public Response<?> prisist(QuizExamPaper quizExamPaper) {
        quizExamPaperRepository.save(quizExamPaper);
        return new Response<>(ResponseStatus.SUCCESS, "Student saved successfully");
    }

    @Override
    public Response<?> merge(QuizExamPaper quizExamPaper) {
        if(!quizExamPaper.hasId()){
            return new Response<>(ResponseStatus.ERROR, "Course ID is required");
        }
        quizExamPaperRepository.save(quizExamPaper);
        return new Response<>(ResponseStatus.SUCCESS, "Course updated successfully");
    }

    @Override
    public Response<List<QuizExamPaper>> findAll() {
        List< QuizExamPaper > quizExamPaper = quizExamPaperRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, quizExamPaper);
    }

    @Override
    public Response<String> deleteById(Integer id) {
       
        quizExamPaperRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Course deleted successfully");
    }

    @Override
    public Response<QuizExamPaper> findById(Integer id) {
        quizExamPaperRepository.findById(id);
        return new Response<>(ResponseStatus.SUCCESS, null, quizExamPaperRepository.findById(id).get());
    }

    @Override
    public Response<Page<QuizExamPaper>> findAllByPage(Pageable pageable, String search) {
        Page<QuizExamPaper> quizExamPaper;
        if (search == null || search.equals("*")) {
            quizExamPaper = quizExamPaperRepository.findAll(pageable);
        } else {
            quizExamPaper = quizExamPaperRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, quizExamPaper);
    }

}
