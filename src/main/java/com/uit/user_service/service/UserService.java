package com.uit.user_service.service;

import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.GetPropertyDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDto createUser(CreateUserDto dto);

    UserDto validateToken(String token);

    User getUserInfoByUuid(String uuid);

    User saveUser(User u);

    UUID getUserId(String token);

    Object getRecentProperty();
}
