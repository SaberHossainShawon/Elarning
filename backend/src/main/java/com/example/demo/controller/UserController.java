package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController implements BaseController<User,Integer> {      
    private final UserService userService;
    @Override
    public ResponseEntity<Response<?>> save(User user) {
         if(user.hasId()){
             return ResponseEntity.ok(userService.merge(user));
         }else{
             return ResponseEntity.ok(userService.prisist(user));
         }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
         Response<?> response=userService.deleteById(id);
         return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<User>> findById(Integer id) {
       Response<User> response=userService.findById(id);
       return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<List<User>>> findAll() {
        Response<List<User>> user=userService.findAll();
        return ResponseEntity.ok(user);
    }
     
    @PostMapping("/login")
    public ResponseEntity<Response<?>> login(@RequestBody User user) {
       return ResponseEntity.ok(userService.login(user));
    }

    @Override
    public ResponseEntity<Response<Page<User>>> findAllByPage(Integer pageNumber, Integer pageSize, String sortColumn,
            String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<User>> response = userService.findAllByPage(pageable, search);
        return ResponseEntity.ok(response);
    }
    
}
