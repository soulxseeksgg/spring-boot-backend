package com.iamdevelop.backend.model;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String userName;
    private String password;
}
