package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepostitory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceIMP implements StudentService {

    private final StudentRepostitory studentRepository;
    private final UserServiceIMP userServiceIMP;

    @Override
    public Response<?> prisist(Student student) {
        if (student.hasId()) {
            student.setId(null);
        }
        Response<?> response = userServiceIMP.prisist(student.getUser());
        System.out.println("Status: " + response.getStatus());
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            studentRepository.save(student);
            return new Response<>(ResponseStatus.SUCCESS, "Student saved successfully");
        } else {
            return response;
        }

    }

    @Override
    public Response<?> merge(Student student) {
        if (!student.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "Student ID is required");
        }
        

        Response<?> response = userServiceIMP.merge(student.getUser());
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            studentRepository.save(student);
            return new Response<>(ResponseStatus.SUCCESS, "Student updated successfully");
        } else {
            return response;
        }

    }

    @Override
    public Response<List<Student>> findAll() {
        List<Student> student = studentRepository.findAll();
        Response<List<Student>> response = new Response<>(ResponseStatus.SUCCESS, null, student);
        return response;
    }

    @Override
    public Response<String> deleteById(Integer id) {
        studentRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Student deleted successfully");

    }

    @Override
    public Response<Student> findById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, student);
    }

    @Override
    public Response<Page<Student>> findAllByPage(Pageable pageable, String search) {
        Page<Student> student;
        if (search == null || search.equals("*")) {
            student = studentRepository.findAll(pageable);
        } else {
            student = studentRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, student);
    }

}
