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
import com.example.demo.entity.UserforStudying;
import com.example.demo.service.UserTestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserTestService userService;


    @GetMapping
    public ApiResponse<List<UserforStudying>> getAllUser(){
        ApiResponse<List<UserforStudying>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    } 

    @GetMapping("/{id}")
    public ApiResponse<UserforStudying> getUserById(@PathVariable String id){
        ApiResponse<UserforStudying> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @PostMapping
    public ApiResponse<UserforStudying> addUser(@Valid @RequestBody UserCreationRequest userCreationRequest){
        ApiResponse<UserforStudying> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.addUser(userCreationRequest));
        return apiResponse;
    }


    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id,@RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUser(id, userUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        userService.deleteUser(id);
    }

}