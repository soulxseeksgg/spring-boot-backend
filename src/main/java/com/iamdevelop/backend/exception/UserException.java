package com.iamdevelop.backend.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user:"+code);
    }

    public static UserException unauthorized(){
        return new UserException("unauthorized");
    }

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }

    public static UserException loginFailed(){
        return new UserException("user.login.failed");
    }

    public static UserException notFound(){
        return new UserException("refresh-token.notfound");
    }
}
