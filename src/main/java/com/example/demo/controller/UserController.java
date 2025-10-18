package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ApiResponse<List<User>> getAllUser(){
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable String id){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserByID(id));
        return apiResponse;
    }

    @PostMapping
    public ApiResponse<User> addUser(@Valid @RequestBody UserCreationRequest userCreationRequest){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.addUser(userCreationRequest));
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable String id,@RequestBody @Valid UserUpdateRequest userUpdateRequest){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(id, userUpdateRequest));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable String id){
        ApiResponse apiResponse = new ApiResponse<>();
        userService.deleteUser(id);
        apiResponse.setMassage("user delete successful");
        return apiResponse;
    }

}
