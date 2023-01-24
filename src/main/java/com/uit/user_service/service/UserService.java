package com.uit.user_service.service;


import com.uit.user_service.entities.User;


import dto.CreateUserDto;
import dto.GetPropertyDto;
import dto.UserDto;
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

    GetPropertyDto getPropertyById(String propertyId);
}
