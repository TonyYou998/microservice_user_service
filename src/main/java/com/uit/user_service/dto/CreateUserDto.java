package com.uit.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserDto {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
//    private UUID id;



}
