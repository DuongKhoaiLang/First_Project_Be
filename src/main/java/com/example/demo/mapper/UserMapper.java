package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.entity.UserforStudying;

@Mapper(componentModel = "spring")
public  interface UserMapper {
    UserforStudying toUserforStudying(UserCreationRequest userCreationRequest);
    void updateUserforStudying(@MappingTarget UserforStudying userforStudying,UserUpdateRequest userUpdateRequest);
}
