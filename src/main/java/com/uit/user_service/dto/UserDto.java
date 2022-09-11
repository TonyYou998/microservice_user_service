package com.uit.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private String role;
    private UUID id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
