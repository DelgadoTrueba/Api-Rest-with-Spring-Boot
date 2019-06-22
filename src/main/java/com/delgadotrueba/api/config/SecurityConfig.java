package com.delgadotrueba.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        
        //auth.inMemoryAuthentication()
        //	.withUser("admin").password( this.passwordEncoder().encode("123456") ).roles("ADMIN");
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
        	.and()
        	.csrf().disable().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                	.addFilter(jwtTokenAuthenticationFilter())
                	.addFilter(jwtUsernameAndPasswordAuthenticationFilter());

    }
	
	
	@Bean //Muy importante pues nos permite usar el autowired
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() throws Exception {
        return new JwtTokenAuthenticationFilter(this.authenticationManager());
    }
	

	@Bean //Muy importante pues nos permite usar el autowired
    public JwtUsernameAndPasswordAuthenticationFilter jwtUsernameAndPasswordAuthenticationFilter() throws Exception {
        return new JwtUsernameAndPasswordAuthenticationFilter(this.authenticationManager());
    }
 
	 
}
