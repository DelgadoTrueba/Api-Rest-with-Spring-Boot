package com.delgadotrueba.api.data_servicies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Se accede a UserDAO para buscar el usuario para obtener su clave y roles
		
		if("admin".equals(username)) {
			return this.userBuilder(username, new BCryptPasswordEncoder().encode("123456"), "ADMIN");
		} 
		else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}
	
	private User userBuilder(String username, String password, String... roles) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(String role: roles) {
			authorities.add( new SimpleGrantedAuthority("ROLE_" + role));
		}
		
		return new User(username, password, enabled, credentialsNonExpired, accountNonExpired, accountNonLocked, authorities);
	}

}
