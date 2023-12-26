package com.iamdevelop.backend.service;

import com.iamdevelop.backend.entity.User;
import com.iamdevelop.backend.exception.BaseException;
import com.iamdevelop.backend.exception.UserException;
import com.iamdevelop.backend.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email,String userName, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Cacheable(value = "email", key = "#email", unless = "#result == null")
    public Optional<User> findByEmail(String email) {
        log.info(">> call in function findByEmail");
        return userRepository.findByEmail(email);
    }

    @CachePut(value = "user", key = "#email")
    public User updateUserName(String email, String userName) throws BaseException {

        Optional<User> opt = findByEmail(email);

        if(opt.isEmpty()){
            throw UserException.emailNull();
        }

        User user = opt.get();
        user.setUserName(userName);
        return userRepository.save(user);
    }

    public Optional<User> findById(String id){
        return userRepository.findById(id);
    }

    @CacheEvict(value = "user", key = "#email")
    public void deleteUser(String email){
        Optional<User> opt = findByEmail(email);

        User user = opt.get();
        String id = user.getId();

        userRepository.deleteById(id);
    }

}
