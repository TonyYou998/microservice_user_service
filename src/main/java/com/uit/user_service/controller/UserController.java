package com.uit.user_service.controller;



import com.uit.user_service.common.UserConstant;
import com.uit.user_service.common.jwt.JwtUtils;
import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.LoginDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;
import com.uit.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(UserConstant.BASE_URL+UserConstant.SERVICE_NAME)
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private  AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    public  final String USER_NAME_OR_PASSWORD_INVALID = "username or password are invalid";




    @PostMapping(UserConstant.CREATE_USER)
    public Object createUser(@Valid @RequestBody CreateUserDto dto, BindingResult result){
        if(result.hasErrors()){
            return UserConstant.ERROR;
        }
        return userService.createUser(dto);
    }
    @PostMapping(UserConstant.VALIDATE_TOKEN)
    public Object validateToken(@RequestParam String token){
          return   userService.validateToken(token);

    }


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


    @GetMapping("/test")
    public String test(){
        return "abc";
    }
    @GetMapping("/changeRoleByUuid")
    public User changeRoleByUuid(@RequestParam String uuid){

        User u= userService.getUserInfoByUuid(uuid);
        u.setRole("Host");
        return userService.saveUser(u);

    }
    @GetMapping(UserConstant.GET_USER_ID)
    public UUID getUserByToken(@RequestParam String token ){
           return userService.getUserId(token);
    }
}
