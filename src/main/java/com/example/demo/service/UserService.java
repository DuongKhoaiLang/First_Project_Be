package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User>getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByID(String id){
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    }

    public User addUser(UserCreationRequest userCreationRequest){

        if(userRepository.existsByUserName(userCreationRequest.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if(userRepository.existsByUserEmail(userCreationRequest.getUserEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }


        User user = userMapper.toUser(userCreationRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPassword(passwordEncoder.encode(userCreationRequest.getUserPassword()));
        return userRepository.save(user);
    }

    public User updateUser(String id,UserUpdateRequest userUpdateRequest){
        User user = getUserByID(id);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(userUpdateRequest.getUserPassword(),user.getUserPassword())){
            userUpdateRequest.setUserPassword(passwordEncoder.encode(userUpdateRequest.getUserPassword()));
        }
        else
        {
            userUpdateRequest.setUserPassword(user.getUserPassword());
        }
        userMapper.updateUser(user, userUpdateRequest);
        return userRepository.save(user);
    }


    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
