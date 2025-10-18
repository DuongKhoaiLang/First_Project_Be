package com.example.demo.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SINGER_KEY;



    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException{
        var token = introspectRequest.getToken();
        JWSVerifier jwsVerifier = new MACVerifier(SINGER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean varify = signedJWT.verify(jwsVerifier);
        Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();
        return IntrospectResponse
        .builder()
            .valid(varify && expiry.after(new Date()))
        .build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws KeyLengthException, JOSEException{
        User user = userRepository.findByUserName(authenticationRequest.getUserName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean isValided = passwordEncoder.matches(authenticationRequest.getUserPassword(),user.getUserPassword());

        if(!isValided) throw new AppException(ErrorCode.NOT_AUTHENTICATED);

        String token = renderToken(authenticationRequest.getUserName());

        return AuthenticationResponse.builder().authenticated(isValided).token(token).build();
    }

    private String renderToken(String userName) throws KeyLengthException, JOSEException{


        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet
        .Builder()
            .subject(userName)
            .issuer("Anh Trinh Minh Vi")
            .issueTime(new Date())
            .expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
        .build();


        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));

        return jwsObject.serialize();

    }   

}