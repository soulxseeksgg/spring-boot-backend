package com.iamdevelop.backend;

import com.iamdevelop.backend.entity.User;
import com.iamdevelop.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void createUser(){
        User user = userService.createUser(TestData.email, TestData.password);
        Assertions.assertNotNull(user);
    }

    interface TestData{
        String email = "toon@mail.com";
        String password ="123";
    }
}
