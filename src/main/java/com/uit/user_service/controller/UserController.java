package com.uit.user_service.controller;

import com.uit.user_service.common.Constant;
import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.LoginDto;
import com.uit.user_service.common.jwt.JwtUtils;
import com.uit.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constant.BASE_URL)
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private  AuthenticationManager authenticationManager;
    private  JwtUtils jwtUtils;
    public  final String USER_NAME_OR_PASSWORD_INVALID = "username or password are invalid";


    @PostMapping(Constant.CREATE_USER)
    public Object createUser(@Valid @RequestBody  CreateUserDto dto, BindingResult result){
        if(result.hasErrors()){
            return Constant.ERROR;
        }
        return userService.createUser(dto);
    }
    @PostMapping(Constant.LOGIN_USER)
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult err) {
        if(err.hasErrors())   return Constant.ERROR;

        Authentication auth = null;
        try{
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return jwtUtils.generateJwtToken(auth);
        } catch (Exception e) {
            System.out.println(e);
        }
        return USER_NAME_OR_PASSWORD_INVALID;
    }


//    @PostMapping(Constant.LOGIN_USER)
//    public Object loginUser(){
//        return null;
//    }

    @GetMapping("/test")
    public String test(){
        return "abc";
    }
}
