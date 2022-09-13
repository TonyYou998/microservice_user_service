package com.uit.user_service.controller;

import com.uit.user_service.common.UserConstant;
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
@RequestMapping(UserConstant.BASE_URL)
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private  AuthenticationManager authenticationManager;
    private  JwtUtils jwtUtils;
    public  final String USER_NAME_OR_PASSWORD_INVALID = "username or password are invalid";



    @CrossOrigin
    @PostMapping(UserConstant.CREATE_USER)
    public Object createUser(@Valid @RequestBody  CreateUserDto dto, BindingResult result){
        if(result.hasErrors()){
            return UserConstant.ERROR;
        }
        return userService.createUser(dto);
    }

    @CrossOrigin
    @PostMapping(UserConstant.LOGIN_USER)
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult err) {
        if(err.hasErrors())   return UserConstant.ERROR;

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
    @PostMapping(UserConstant.BECOME_A_HOST)
    public Object becomeAHost(){
            return "become a host";
    }
    @GetMapping("/test")
    public String test(){
        return "abc";
    }
}
