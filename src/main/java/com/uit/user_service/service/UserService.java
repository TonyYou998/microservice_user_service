package com.uit.user_service.service;

import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public interface UserService {
    UserDto createUser(CreateUserDto dto,String siteURL);

    UserDto validateToken(String token);

    private void sendVerificationEmail(User user, String siteURL) {}
    User getUserInfoByUuid(String uuid);

    User saveUser(User u);

    public boolean verify(String verificationCode) ;

}
