package com.uit.user_service.common.jwt;
import com.uit.user_service.dto.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

//	private final JwtUtils jwtUtils;
	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailService;
	public JwtAuthorizationFilter(UserDetailsService userDetailService, JwtUtils jwtUtils) {
		
		// TODO Auto-generated constructor stub
		this.userDetailService=userDetailService;
		this.jwtUtils=jwtUtils;
		System.out.println("hello");
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String token=jwtUtils.getjwtTokenFromRequest(request);

//			if(token!=null && new UserDto().isExist(jwtUtils.validateJwtToken(token))) {
			if(token !=null && new UserDto().isExist(jwtUtils.validateJwtToken(token))){
				String username = jwtUtils.getUsernameFromToken(token);
				UserDetails userDetails=userDetailService.loadUserByUsername(username);
				Authentication auth=new UsernamePasswordAuthenticationToken(username,	null,userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		filterChain.doFilter(request, response);
		
	}

}
