package com.uit.user_service.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class LoginDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
