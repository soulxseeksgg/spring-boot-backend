package com.iamdevelop.backend.api;

import com.iamdevelop.backend.business.UserBusiness;
import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.model.*;
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
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest req) throws BaseException {
        UserResponse response = userBusiness.register(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken()throws BaseException{
        String response = userBusiness.refreshToken();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) throws BaseException {
        String token = userBusiness.login(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/getUserName")
    public ResponseEntity<String> getUser(@RequestBody UserRequest request) throws BaseException{
        String response = userBusiness.getUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateUserName")
    public ResponseEntity<UserResponse> updateUser(@RequestBody MUpdateUserNameRequest request) throws BaseException{
        UserResponse response = userBusiness.updateUserName(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestBody UserRequest request){
        userBusiness.deleteUser(request);
        return ResponseEntity.ok().build();
    }
}
