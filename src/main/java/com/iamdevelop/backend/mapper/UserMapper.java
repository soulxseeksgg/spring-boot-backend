package com.iamdevelop.backend.mapper;


import com.iamdevelop.backend.entity.User;
import com.iamdevelop.backend.model.UserResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper{
    UserResponse toRegisterResponse(User user);

}
