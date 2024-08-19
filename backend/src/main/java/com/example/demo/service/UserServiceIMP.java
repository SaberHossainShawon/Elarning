package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserServiceIMP implements UserService {
    private final UserRepository userRepository;    

    @Override
    public Response<?> prisist(User user) {
        boolean oldUser=userRepository.existsByEmail(user.getEmail());
        if(oldUser){
            return new Response<>(ResponseStatus.ERROR, "Email already exists Please choose another one");
        }
        userRepository.save(user);
        return new Response<>(ResponseStatus.SUCCESS, "User saved successfully");
    }

    @Override
    public Response<?> merge(User user) {
        User oldUser=userRepository.findById(user.getId()).orElse(null);
        if(!oldUser.getEmail().equals(user.getEmail())){
            if(userRepository.existsByEmail(user.getEmail())){
                return new Response<>(ResponseStatus.ERROR, "Email already exists");
            }
        }
        userRepository.save(user);
        return new Response<>(ResponseStatus.SUCCESS, "User updated successfully");
    }

    @Override
    public Response<List<User>> findAll() {
        List<User> users=userRepository.findAll();
        Response<List<User>> response=new Response<>(ResponseStatus.SUCCESS, null,users);
        return response;
    }

    @Override
    public Response<String> deleteById(Integer id) {
        Response<String> response=new Response<>(ResponseStatus.ERROR, "User deletion Not Allowed");
        return response;
    }

    @Override
    public Response<User> findById(Integer id) {
        User user=userRepository.findById(id).orElse(null);
        Response<User> response=new Response<>(ResponseStatus.SUCCESS, null,user);
        return response;
    }

    @Override
    public Response<String> login(User user) {
        User oldUser=userRepository.findByUsernameAndPassword(user.getEmail(), user.getPassword());
        if(oldUser==null){
            return new Response<>(ResponseStatus.ERROR, "User not found");
        }
        return new Response<>(ResponseStatus.SUCCESS, "Login successful");
    }

    @Override
    public Response<Page<User>> findAllByPage(Pageable pageable, String search) {
       Page <User> users;
        if (search == null || search.equals("*")) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, users);
    }
    
}
