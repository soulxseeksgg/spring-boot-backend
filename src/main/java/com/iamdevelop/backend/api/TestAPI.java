package com.iamdevelop.backend.api;

import com.iamdevelop.backend.business.TestBussiness;
import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.model.TestRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAPI {

    private final TestBussiness testBussiness;

    public TestAPI(TestBussiness testBussiness) {
        this.testBussiness = testBussiness;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody TestRequest request) throws BaseException{
        String response = testBussiness.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
