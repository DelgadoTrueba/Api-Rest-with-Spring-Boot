package com.delgadotrueba.api.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delgadotrueba.api.business_controllers.UserService;
import com.delgadotrueba.api.dtos.TokenOutputDTO;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("authenticated")
    @PostMapping("/token")
    public TokenOutputDTO login(@AuthenticationPrincipal User activeUser) {
		return this.userService.login(activeUser.getUsername());
    }

}
