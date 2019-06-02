package com.delgadotrueba.api.business_controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delgadotrueba.api.business_servicies.JwtService;
import com.delgadotrueba.api.dao.UserRepository;
import com.delgadotrueba.api.dtos.TokenOutputDTO;
import com.delgadotrueba.api.orm.Role;
import com.delgadotrueba.api.orm.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public TokenOutputDTO login(String mobile) {
		 User user = this.userRepository.findByMobile(mobile)
	                .orElseThrow(() -> new RuntimeException("mobile not found: !!!" + mobile));
		 
		 List<Role>roles = user.getRoles();
		 String[] roles_array = new String[roles.size()];

		 for(int i = 0; i < roles.size(); i++) {
			 roles_array[i] = roles.get(i).toString();
		 }
		
	     
		 return new TokenOutputDTO(this.jwtService.createToken(user.getMobile(), user.getUsername(), roles_array));
	}

}
