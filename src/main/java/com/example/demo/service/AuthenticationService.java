package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.response.AuthenticatedResponse;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserTestRepository;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;

@Service
public class AuthenticationService {
    @Autowired
    private UserTestRepository userTestRepository;


    public AuthenticatedResponse authenticate(AuthenticationRequest authenticationRequest){
        var user = userTestRepository.findByUserName(authenticationRequest.getUserName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean result = passwordEncoder.matches(authenticationRequest.getUserPassword(),user.getUserPassword());

        if(!result){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }

    }

    // private String generateToken(String userName){

    //     JWSHeader jwsHeader = new JWSHeader()

    //     JWSObject jwsObject = new JWSObject(null, null);
    // }
}
