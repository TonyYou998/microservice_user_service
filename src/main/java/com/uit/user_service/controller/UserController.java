package com.uit.user_service.controller;

import com.uit.user_service.common.Constant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.BASE_URL)
public class UserController {
    @PostMapping(Constant.CREATE_USER)
    public Object createUser(){
        return null;
    }

    @PostMapping(Constant.LOGIN)
    public Object loginUser(){
        return null;
    }
}
