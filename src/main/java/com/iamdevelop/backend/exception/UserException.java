package com.iamdevelop.backend.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user:"+code);
    }

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }
}
