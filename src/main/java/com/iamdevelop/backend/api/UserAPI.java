package com.iamdevelop.backend.api;

import com.iamdevelop.backend.business.UserBusiness;
import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.model.UserRequest;
import com.iamdevelop.backend.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserAPI {

    private final UserBusiness userBusiness;

    public UserAPI(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest req) throws BaseException {
        UserResponse response = userBusiness.register(req);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken()throws BaseException{
        String response = userBusiness.refreshToken();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest request) throws BaseException {
        String token = userBusiness.login(request);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
