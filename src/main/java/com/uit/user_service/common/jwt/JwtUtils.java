package com.uit.user_service.common.jwt;


import com.uit.user_service.entities.User;
import com.uit.user_service.common.repository.UserRepository;
import dto.UserDto;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component

public class JwtUtils {
	public JwtUtils() {}


	@Autowired
	private  UserRepository userRepository;
	private Dotenv dotenv=Dotenv.load();
	@Autowired
	private ModelMapper mapper;


	private Long jwtExpiration = 36000000000L;
	private String jwtSecret =dotenv.get("JWT_SECRET_KEY");
	private String authHeader = "Authorization";
	private String tokenPrefix = "Bearer ";
	public String generateJwtToken(Authentication authentication) {

		UserDetails userDetails=(UserDetails) authentication.getPrincipal();
		Date now= new Date();

		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setHeaderParam("role",userDetails.getAuthorities().stream().findFirst().get().toString())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime()+jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	public String getjwtTokenFromRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String header= request.getHeader(authHeader);
		if(StringUtils.hasText(header)&&header.startsWith(tokenPrefix)) {
			return header.substring(tokenPrefix.length(),header.length());
		}
		return null;
	}
	public UserDto validateJwtToken(String token) {
		// TODO Auto-generated method stub
		String username;
		try {
			  username= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
				User user= userRepository.findByUsername(username);
			 return mapper.map(user,UserDto.class);


		}  catch (SignatureException e1) {
			System.out.println( e1.getMessage());
		} catch (ExpiredJwtException e2) {
			System.out.println( e2.getMessage());
		} catch (MalformedJwtException e3) {
			System.out.println( e3.getMessage());
		} catch (IllegalArgumentException e4) {
			System.out.println( e4.getMessage());
		} catch (UnsupportedJwtException e5) {
			System.out.println( e5.getMessage());
		}
		return null;
	}
	public String getUsernameFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}
