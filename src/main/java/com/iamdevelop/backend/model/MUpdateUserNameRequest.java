package com.iamdevelop.backend.model;

import lombok.Data;

@Data
public class MUpdateUserNameRequest {
    private String email;
    private String userName;
}
