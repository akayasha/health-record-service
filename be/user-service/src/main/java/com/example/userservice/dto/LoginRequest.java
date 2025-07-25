package com.example.userservice.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

    // Additional fields can be added if needed, such as rememberMe, etc.

}
