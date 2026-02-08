package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.User;
import com.library.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Register User API
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		
		//Temp Debug
	    System.out.println("✅ Register API Hit!");
		
		//Encrypt password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "Registration succesful!";
	}
}
