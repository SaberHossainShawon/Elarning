package com.example.demo.service;

import com.example.demo.dto.Response;
import com.example.demo.entity.User;

public interface UserService extends BaseService<User,Integer>{

    Response<String> login(User user);
    
} 