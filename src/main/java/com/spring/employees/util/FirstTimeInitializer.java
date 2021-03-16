package com.spring.employees.util;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.employees.model.User;
import com.spring.employees.service.UserService;

@Component
public class FirstTimeInitializer implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	private final Log  logger = LogFactory.getLog(FirstTimeInitializer.class);
	
	@Override
	public void run(String... args) throws Exception {
		if(this.userService.getAllUser().isEmpty()) {
			logger.info("not users accounts fond");
			User user = new User("mounaim.lucy@gamil.com","lucy1994");
			this.userService.save(user);
		}
	}
}
