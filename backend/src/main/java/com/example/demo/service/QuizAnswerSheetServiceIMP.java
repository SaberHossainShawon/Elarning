package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.QuizAnswerSheet;
import com.example.demo.repository.QuizAnswerSheetRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAnswerSheetServiceIMP implements QuizAnswerSheetService{
    private final QuizAnswerSheetRepository quizAnswerSheetRepository;
    private final UserRepository userRepository;

    @Override
    public Response<?> prisist(QuizAnswerSheet quizAnswerSheet) {
        if(!quizAnswerSheet.hasId()){
            quizAnswerSheet.setId(null);
        }

        if(userRepository.existsByEmail(quizAnswerSheet.getUserEmail())){
            quizAnswerSheetRepository.save(quizAnswerSheet);
            return new Response<>(ResponseStatus.SUCCESS, "QuizAnswerSheet saved successfully");
        }else{
            return new Response<>(ResponseStatus.ERROR, "User email not valid");
        }
    }

    @Override
    public Response<?> merge(QuizAnswerSheet quizAnswerSheet) {
        if(!quizAnswerSheet.hasId()){
            return new Response<>(ResponseStatus.ERROR, "QuizAnswerSheet ID is required");
        }
        quizAnswerSheetRepository.save( quizAnswerSheet );
        return new Response<>(ResponseStatus.SUCCESS, "QuizAnswerSheet updated successfully");
        
    }

    @Override
    public Response<List<QuizAnswerSheet>> findAll() {
        List< QuizAnswerSheet > quizAnswerSheet = quizAnswerSheetRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, quizAnswerSheet);
       
    }

    @Override
    public Response<String> deleteById(Integer id) {
       quizAnswerSheetRepository.deleteById(id);
       return new Response<>(ResponseStatus.SUCCESS, "QuizAnswerSheet deleted successfully");
    }

    @Override
    public Response<QuizAnswerSheet> findById(Integer id) {
        quizAnswerSheetRepository.findById(id);
        return new Response<>(ResponseStatus.SUCCESS, null, quizAnswerSheetRepository.findById(id).get());
    }

    @Override
    public Response<Page<QuizAnswerSheet>> findAllByPage(Pageable pageable, String search) {
        Page <QuizAnswerSheet> quizAnswerSheet;
        if (search == null || search.equals("*")) {
            quizAnswerSheet = quizAnswerSheetRepository.findAll(pageable);
        } else {
            quizAnswerSheet = quizAnswerSheetRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, quizAnswerSheet);
    }
    
}
