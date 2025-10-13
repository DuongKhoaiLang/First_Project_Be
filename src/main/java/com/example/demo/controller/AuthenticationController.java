package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.AuthenticatedResponse;
import com.example.demo.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/log-in")
    public ApiResponse<AuthenticatedResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        boolean result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticatedResponse>builder()
        .result(AuthenticatedResponse.builder().authenticated(result).build())
        .build();
    }
}
