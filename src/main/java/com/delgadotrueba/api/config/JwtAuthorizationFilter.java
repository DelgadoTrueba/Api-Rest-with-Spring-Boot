package com.delgadotrueba.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.delgadotrueba.api.business_servicies.JwtService;
import com.delgadotrueba.api.orm.Role;

import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthorizationFilter  extends BasicAuthenticationFilter{
	   private static final String AUTHORIZATION = "Authorization";

	   @Autowired
	   private JwtService jwtService;
	   
	   public JwtAuthorizationFilter(AuthenticationManager authManager) {
	        super(authManager);
	   }
	   
	   @Override
	   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
		   
		   LogManager.getLogger(this.getClass().getName()).debug(">>> FILTER JWT >>>");	   
	        String authHeader = request.getHeader(AUTHORIZATION);
	        
	        if (this.jwtService.isBearer(authHeader)) {
	        	
	            List<GrantedAuthority> authorities;
	            
	            authorities = this.jwtService.roles(authHeader).stream()
	                    .map(role -> new SimpleGrantedAuthority(Role.valueOf(role).roleName())).collect(Collectors.toList());
	            
	            UsernamePasswordAuthenticationToken authentication =
	                    new UsernamePasswordAuthenticationToken(this.jwtService.user(authHeader), null, authorities);
	            
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	        chain.doFilter(request, response);
	   }
}
