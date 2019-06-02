package com.delgadotrueba.api.business_controllers;

import com.delgadotrueba.api.dtos.TokenOutputDTO;

public interface UserService {
	
	public TokenOutputDTO login(String username);
	
}
