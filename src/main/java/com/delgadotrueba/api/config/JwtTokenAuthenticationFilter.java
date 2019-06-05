package com.delgadotrueba.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.delgadotrueba.api.business_servicies.JwtService;
import com.delgadotrueba.api.orm.Role;

import java.util.List;
import java.util.stream.Collectors;


public class JwtTokenAuthenticationFilter extends BasicAuthenticationFilter{
	   private static final String AUTHORIZATION = "Authorization";

	   @Autowired
	   private JwtService jwtService;
	   
	   public JwtTokenAuthenticationFilter(AuthenticationManager authManager) {
	        super(authManager);
	   }
	   
	   @Override
	   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			   throws IOException, ServletException {
		   
			// 1. get the authentication header. 
		    // Tokens are supposed to be passed in the authentication header
	        String authHeader = request.getHeader(AUTHORIZATION);
	        
	        // 2. validate the header and check the token. 
	        if (this.jwtService.isBearer(authHeader)) {
	        	
	            String username = this.jwtService.user(authHeader);
	            
	            List<GrantedAuthority> authorities 
	            		= this.jwtService.roles(authHeader)
	            			.stream()
	            			.map(role -> new SimpleGrantedAuthority(Role.valueOf(role).roleName()))
	            			.collect(Collectors.toList());
	            
	            // 3. Create auth object
				// UsernamePasswordAuthenticationToken: 
	            // A built-in object, used by spring to represent the current authenticated / being authenticated user.
	            UsernamePasswordAuthenticationToken authentication =
	                    new UsernamePasswordAuthenticationToken(username, null, authorities);
	            
	            // 4. Authenticate the user
				// Now, user is authenticated
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	        // 2.2 If there is no token provided and hence the user won't be authenticated. 
			// It's Ok. Maybe the user accessing a public path or asking for a token.
	        chain.doFilter(request, response);
	   }
}
