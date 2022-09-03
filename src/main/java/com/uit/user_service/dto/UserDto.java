package com.uit.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UserDto {
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private UUID id;
}
