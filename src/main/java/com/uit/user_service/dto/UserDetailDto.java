package com.uit.user_service.dto;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
//// lay all thong tin tu user tu csdl ==> tuong tac csdl
public class UserDetailDto extends User implements UserDetails {

    public UserDetailDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
