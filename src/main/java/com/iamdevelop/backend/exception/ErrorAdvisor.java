package com.iamdevelop.backend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse response1 = new ErrorResponse();
        response1.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        response1.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(response1,HttpStatus.EXPECTATION_FAILED);
    }

    @Data
    public class ErrorResponse{
        private Date date = new Date();
        private int status;
        private String errorMessage;
    }
}
