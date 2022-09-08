package com.uit.user_service.service;

import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;
import com.uit.user_service.controller.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private final ModelMapper mapper;

//    private BCryptPasswordEncoder encoder;
    private PasswordEncoder encoder;
    @Override
    public UserDto createUser(CreateUserDto dto) {
        User newUser=new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setFirstname(dto.getFirstname());
        newUser.setPassword(dto.getPassword());
        newUser.setLastname(dto.getLastname());
        newUser.setPhone(dto.getPhone());
        newUser.setAddress(dto.getAddress());
        newUser.setRole("User");
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setIsActive(false);

//        newUser.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        try{
            userRepository.save(newUser);
        }
        catch (Exception e){
            LOGGER.info(e.getCause().getMessage());
        }
        return mapper.map(newUser, UserDto.class);
    }
}
