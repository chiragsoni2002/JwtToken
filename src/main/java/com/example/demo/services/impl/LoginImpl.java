package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositry.UserRepositry;
import com.example.demo.services.LoginServices;
import com.example.demo.services.util.GenricResponse;

@Service
public class LoginImpl implements LoginServices{
	
	@Autowired
	private UserRepositry userRepositry;

	@Override
	public ResponseEntity<?> addUserDetails(User user) {
		// TODO Auto-generated method stub
		
		String email = user.getEmail();
		User user1 = userRepositry.findByEmail(email);
		
		if(user1 == null) {
			userRepositry.save(user);
			return ResponseEntity.ok(new GenricResponse(201, "Success", user));
		}else {
			return ResponseEntity.ok(new GenricResponse(203, "Sorry Email id already exist", null));
		}
	}

}
