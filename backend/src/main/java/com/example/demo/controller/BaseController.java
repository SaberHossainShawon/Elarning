package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.Response;

public interface BaseController<Entity, ID> {
    @PostMapping("/save")
    ResponseEntity<Response<?>> save(@RequestBody Entity student);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response<?>> deleteById(@PathVariable ID id);

    @GetMapping("/findByID/{id}")
    ResponseEntity<Response<Entity>> findById(@PathVariable ID id);

    @GetMapping("/findAll")
    ResponseEntity<Response<List<Entity>>> findAll();

    @GetMapping("/{pageNumber}/{pageSize}/{sortColumn}/{order}/{search}")
     ResponseEntity<Response<Page<Entity>>> findAllByPage(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortColumn", required = false) String sortColumn,
            @PathVariable(value = "order", required = false) String order,
            @PathVariable(value = "search", required = false) String search) ;

}