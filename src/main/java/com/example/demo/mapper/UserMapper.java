package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserCreationRequest userCreationRequest);
    
    public void updateUser(@MappingTarget User user,UserUpdateRequest updateRequest);
}