package com.uit.user_service.service;

import com.uit.user_service.common.jwt.JwtUtils;
import com.uit.user_service.dto.CreateUserDto;
import com.uit.user_service.dto.GetPropertyDto;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;
import com.uit.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private  ModelMapper mapper;
    private JwtUtils jwtUtils;
    private RestTemplate restTemplate;

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

    @Override
    public UserDto validateToken(String token) {

        return  jwtUtils.validateJwtToken(token);

    }

    @Override
    public User getUserInfoByUuid(String uuid) {
        return userRepository.findById(UUID.fromString(uuid)).get();
    }

    @Override
    public User saveUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public UUID getUserId(String token) {
        try{
            UserDto userDto=validateToken(token.substring("Bearer".length(),token.length()));
            return userDto.getId();
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }


    }

    @Override
    public Object getRecentProperty() {
       Object o= restTemplate.getForObject("http://host-service/api/v1/host/get-recent",Object.class);
        return  o;
    }

    @Override
    public GetPropertyDto getPropertyById(String propertyId) {
        GetPropertyDto dto=restTemplate.getForObject("http://host-service/api/v1/host/get-property-by-id/"+propertyId,GetPropertyDto.class);
        User u= getUserInfoByUuid(dto.getHostUser());
        dto.setHostUser(u.getUsername());
        return  dto;
    }
}
