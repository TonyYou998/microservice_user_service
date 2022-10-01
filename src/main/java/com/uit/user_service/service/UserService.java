package com.uit.user_service.service;

import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.UserDto;


public interface UserService {
    UserDto createUser(CreateUserDto dto);

    UserDto validateToken(String token);
}
