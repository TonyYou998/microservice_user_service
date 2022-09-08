package com.uit.user_service.security.controller;
import com.uit.user_service.common.Constant;
import com.uit.user_service.dto.LoginDto;
import com.uit.user_service.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(Constant.BASE_URL)
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    public  final String USER_NAME_OR_PASSWORD_INVALID = "username or password are invalid";


    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(Constant.RETURN_TOKEN)
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult err) {
        if(err.hasErrors())   return Constant.ERROR;

        Authentication auth = null;
        try{
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            return token;
        } catch (Exception e) {
            System.out.println(e);
        }
        return USER_NAME_OR_PASSWORD_INVALID;
    }
}
