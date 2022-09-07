package com.uit.user_service.security.service;


import com.uit.user_service.controller.repository.UserRepository;
import com.uit.user_service.entities.User;
import com.uit.user_service.security.dto.UserDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// ke thua userdetailservice de kiem tra cac thong tin can thiet trong userdetailservice
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndRole(username);
        Set<GrantedAuthority> authorities= getAuthorities(user.get().getRole());
        if(!user.isPresent())
            throw new UsernameNotFoundException("Username is not existed.");
        
       return new UserDetailDto(username, user.get().getPassword(), authorities);
    }

    private Set<GrantedAuthority> getAuthorities(String role) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(role));

        return authorities;
    }
}