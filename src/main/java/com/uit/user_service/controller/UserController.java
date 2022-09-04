package com.uit.user_service.controller;

import com.uit.user_service.common.Constant;
import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Constant.BASE_URL)
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @PostMapping(Constant.CREATE_USER)
    public Object createUser(@Valid @RequestBody  CreateUserDto dto, BindingResult result){
        if(result.hasErrors()){
            return "loi";
        }
        return userService.createUser(dto);


    }

    @PostMapping(Constant.LOGIN_USER)
    public Object loginUser(){
        return null;
    }
}
