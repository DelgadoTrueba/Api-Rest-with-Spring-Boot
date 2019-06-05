package com.delgadotrueba.api.config;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.delgadotrueba.api.business_servicies.JwtService;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	
	@Autowired
	private JwtService jwtService;
		
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager) {
		super.setAuthenticationManager(authManager);
		
		// By default, UsernamePasswordAuthenticationFilter listens to "/login" path. 
		// In our case, we use "/auth". So, we need to override the defaults.
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/users/token", "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			// 1. Get credentials from header
			String header = request.getHeader("Authorization");
			
			if(header == null) throw new AuthenticationCredentialsNotFoundException("No credentials found in context");
			
			String[] tokens = extractAndDecodeHeader(header, request);
			
			String username = tokens[0];
			String password =  tokens[1];
			
			// 2. Create auth object (contains credentials) which will be used by auth manager
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					username, password, Collections.emptyList());
			
			// 3. Authentication manager authenticate the user, 
			// and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
			AuthenticationManager authManager = super.getAuthenticationManager();
			
			return authManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Upon successful authentication, generate a token.
	// The 'auth' passed to successfulAuthentication() is the current authenticated user.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		// Create token
		String username = auth.getName();
				
		
		String[] roles = getRoles(auth);
		
		
		String token = this.jwtService.createToken(username, roles);
		
		// Add token to header
		response.addHeader("Authorization", "Bearer " + token);
	}
	
	private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
			throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.getDecoder().decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}
	
	private String[] getRoles(Authentication auth) {
		
		List<String> roles = auth.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		
		String[] roles_aux = new String[roles.size()];
		
		for(int i = 0; i < roles.size(); i++) {
			roles_aux[i] = roles.get(i).substring(5);
		}
		
		return roles_aux;
	}
	
	
}
