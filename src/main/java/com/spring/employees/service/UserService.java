package com.spring.employees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.employees.model.User;
import com.spring.employees.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = this.userRepository.findByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("user not found uith username" + username);
		}
		return user;
	}
	
	public void save(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		this.userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}

}
