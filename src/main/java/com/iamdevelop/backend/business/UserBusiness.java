package com.iamdevelop.backend.business;

import com.iamdevelop.backend.entity.User;
import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.mapper.UserMapper;
import com.iamdevelop.backend.model.UserRequest;
import com.iamdevelop.backend.model.UserResponse;
import com.iamdevelop.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserResponse register(UserRequest request) throws BaseException {
        User user = userService.createUser(request.getEmail(), request.getPassword());
        return userMapper.toRegisterResponse(user);
    }
}
