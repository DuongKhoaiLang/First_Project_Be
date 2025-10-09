package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.UserforStudying;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserTestRepository;

@Service
public class UserTestService {
    @Autowired
    private UserTestRepository userTestRepository;
    
    @Autowired
    private UserMapper userMapper;

    List<UserforStudying> getAllUser(){
        return userTestRepository.findAll();
    }

    UserforStudying getUserById(String id){
        return userTestRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    }

}
