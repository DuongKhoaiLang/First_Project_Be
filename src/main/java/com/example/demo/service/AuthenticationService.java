package com.example.demo.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.AuthenticatedResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserTestRepository;
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
    private UserTestRepository userTestRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNNER_KEY;



    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException{
        var token = request.getToken();
        
        JWSVerifier jwsVerifier = new MACVerifier(SIGNNER_KEY.getBytes());
        
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(jwsVerifier);

        return IntrospectResponse.builder()
        .valid(verified && expiry.after(new Date()))
        .build();
    }

    public AuthenticatedResponse authenticate(AuthenticationRequest authenticationRequest){
        var user = userTestRepository.findByUserName(authenticationRequest.getUserName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean result = passwordEncoder.matches(authenticationRequest.getUserPassword(),user.getUserPassword());

        if(!result){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        var token = generateToken(authenticationRequest.getUserName());
        return AuthenticatedResponse.builder().token(token).authenticated(true).build();
    }

    private String generateToken(String userName){

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
        .Builder()
            .subject(userName)
            .issuer("Trinh Minh Vi")
            .issueTime(new Date())
            .expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
            .claim("CustomeClaim", "My Claim")
        .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        (Logger.getLogger(AuthenticationService.class.getName())).info(jwtClaimsSet.toString());;
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNNER_KEY.getBytes()));
        } catch (KeyLengthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JOSEException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jwsObject.serialize();

    }
}
