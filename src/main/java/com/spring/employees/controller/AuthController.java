package com.spring.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employees.model.UserSignIn;
import com.spring.employees.security.JWTGenerated;
import com.spring.employees.security.Token;
import com.spring.employees.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTGenerated jwtGenerated;
	
	

	@PostMapping({"","/"})
	public Token signIn(@RequestBody UserSignIn userSignIn) {
		
		/*do Authentication */
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userSignIn.getUsername(), userSignIn.getPassword())
				                                                               );
		 
		/*register authentication in security Context holder*/
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/*Retrieve the user*/
		UserDetails userDetails = this.userService.loadUserByUsername(userSignIn.getUsername());
		
		/*Token creation*/
		String jwtToken = this.jwtGenerated.generatToken(userDetails);
		
		Token token = new Token(jwtToken);
		
		return token;
	}
}
