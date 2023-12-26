package com.iamdevelop.backend.business;

import com.iamdevelop.backend.entity.User;
import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.exception.UserException;
import com.iamdevelop.backend.mapper.UserMapper;
import com.iamdevelop.backend.model.*;
import com.iamdevelop.backend.service.TokenService;
import com.iamdevelop.backend.service.UserService;
import com.iamdevelop.backend.util.SecurityUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;

    private final TokenService tokenService;

    private final KafkaTemplate<String ,String> kafkaTemplate;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService, KafkaTemplate kafkaTemplate) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public UserResponse register(UserRegisterRequest request) throws BaseException {
        User user = userService.createUser(request.getEmail(),request.getUserName(), request.getPassword());

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("activation-email", "xxx gggg");
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Kafka send sucess"+result.getProducerRecord());
            } else {
                log.error("Kafka send failed");
            }
        });


        return userMapper.toRegisterResponse(user);
    }

    
    public String refreshToken() throws BaseException{
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()){
            throw UserException.unauthorized();
        }

        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (opt.isEmpty()){
            throw UserException.notFound();
        }

        User user = optUser.get();
        return tokenService.tokenize(user);
    }

    public String login(UserLoginRequest request) throws BaseException{
        Optional<User> opt = userService.findByEmail(request.getEmail());

        if(opt.isEmpty()){
            throw UserException.loginFailed();
        }
        User user = opt.get();

        return tokenService.tokenize(user);
    }

    public String getUser(UserRequest request) throws BaseException {
        Optional<User> opt = userService.findByEmail(request.getEmail());

        if(opt.isEmpty()){
            throw UserException.emailNull();
        }
        User user = opt.get();
        return user.getUserName();
    }

    public UserResponse updateUserName(MUpdateUserNameRequest request) throws BaseException{
        User user = userService.updateUserName(request.getEmail(), request.getUserName());
        return userMapper.toRegisterResponse(user);
    }

    public void deleteUser(UserRequest request){
        userService.deleteUser(request.getEmail());
    }
}
