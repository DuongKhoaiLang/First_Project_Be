package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 5,message = "USER_NAME_UNVALIDED")
    String userName;

    @Email(message = "EMAIL_UNVALIDED")
    String userEmail;
    
    @Size(min = 8,message = "USER_PASSWORD_UNVALIDED")
    String userPassword;
    
}