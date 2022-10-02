package com.uit.user_service.service;

import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;


public interface UserService {
    UserDto createUser(CreateUserDto dto);

    UserDto validateToken(String token);

    User getUserInfoByUuid(String uuid);

    User saveUser(User u);
}
