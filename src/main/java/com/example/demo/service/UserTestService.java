package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
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

    public List<UserforStudying> getAllUser(){
        return userTestRepository.findAll();
    }

    public UserforStudying getUserById(String id){
        return userTestRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    }

    public UserforStudying addUser(UserCreationRequest user){
        if(userTestRepository.existsByUserName(user.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserforStudying us = userMapper.toUserforStudying(user);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        us.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userTestRepository.save(us);
    }

    public void updateUser(String id,UserUpdateRequest userUpdateRequest){
        UserforStudying user = getUserById(id);
        userMapper.updateUserforStudying(user, userUpdateRequest);
        userTestRepository.save(user);
    }

    public void deleteUser(String id){
        userTestRepository.deleteById(id);
    }
}
