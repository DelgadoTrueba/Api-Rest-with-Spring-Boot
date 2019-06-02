package com.delgadotrueba.api.data_servicies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delgadotrueba.api.dao.UserRepository;
import com.delgadotrueba.api.orm.Role;
import com.delgadotrueba.api.orm.User;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(final String mobile) throws UsernameNotFoundException {
		// Se accede a UserDAO para buscar el usuario para obtener su clave y roles
		 User user = this.userRepository.findByMobile(mobile)
	                .orElseThrow(() -> new UsernameNotFoundException("mobile not found. " + mobile));
		 
		 
	     return this.userBuilder(user.getMobile(), user.getPassword(), new Role[]{Role.AUTHENTICATED});
		/*
		if("admin".equals(username)) {
			return this.userBuilder(username, new BCryptPasswordEncoder().encode("123456"), "ADMIN");
		} 
		else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		*/
	}
	
	private org.springframework.security.core.userdetails.User userBuilder(String username, String password, Role[] roles) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
				
		return new org.springframework.security.core.userdetails.User(username, password, enabled, credentialsNonExpired, accountNonExpired, accountNonLocked, authorities);
	}

}
