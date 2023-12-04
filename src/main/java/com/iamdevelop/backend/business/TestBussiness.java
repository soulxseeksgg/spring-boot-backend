package com.iamdevelop.backend.business;

import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.exception.UserException;
import com.iamdevelop.backend.model.TestRequest;
import org.springframework.stereotype.Service;

@Service
public class TestBussiness {

    public String register(TestRequest request) throws BaseException {
        if(request.getEmail() == null){
            throw UserException.emailNull();
        }
        return "gg";
    }
}
