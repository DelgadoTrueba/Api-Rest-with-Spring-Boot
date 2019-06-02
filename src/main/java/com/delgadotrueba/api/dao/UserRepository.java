package com.delgadotrueba.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delgadotrueba.api.orm.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByMobile(String mobile);
	
}
