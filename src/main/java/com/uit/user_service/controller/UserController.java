package com.uit.user_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @PostMapping("/create")
    public Object createUser(){
        return null;
    }

    @PostMapping("/login")
    public Object Login(){
        return null;
    }
}
