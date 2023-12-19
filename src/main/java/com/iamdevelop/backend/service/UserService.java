package com.iamdevelop.backend.service;

import com.iamdevelop.backend.entity.Address;
import com.iamdevelop.backend.entity.User;
import com.iamdevelop.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

}
