package com.delgadotrueba.api.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="mobile")
	private String mobile;

	@Column(name="password")
    private String password;
    
	
	@ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
	@Column(name = "roles", nullable = false)
	@Enumerated(EnumType.STRING)
    private List<Role> roles;
	
	public User() {
		
	}
	
	public User(String username, String password) {
        this.username = username;
        this.setPassword(password);
        this.roles = new ArrayList<Role>();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles( List<Role> roles) {
		this.roles = roles;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mobile=" + mobile + "]";
	}
	
}
