package com.spring.employees.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final String [] PUBLIC_ENDPOINTS = {"/api/v1/auth"};
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    @Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
/*	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
           auth.inMemoryAuthentication().withUser("mounaim").password(passwordEncoder().encode("lucy1994")).roles("Admin");
	}
*/	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		    .cors().and().csrf().disable()
		    .sessionManagement()
		            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		            .and()
		    .authorizeRequests()
		             .antMatchers(PUBLIC_ENDPOINTS).permitAll()
		             .anyRequest().authenticated()
		             .and()
		    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
