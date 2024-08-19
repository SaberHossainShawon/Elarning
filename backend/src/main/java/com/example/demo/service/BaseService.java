package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Response;

public interface BaseService<ENTITY, ID> {
    Response<?> prisist(ENTITY student);

    Response<?> merge(ENTITY student);

    Response<List<ENTITY>> findAll();

    Response<String> deleteById(ID id);

    Response<ENTITY> findById(ID id);

    Response<Page<ENTITY>> findAllByPage(Pageable pageable, String search);

}