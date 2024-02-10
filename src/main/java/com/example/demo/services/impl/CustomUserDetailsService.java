package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositry.UserRepositry;
import com.example.demo.services.util.UserDetailsInfo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	 @Autowired
	    private UserRepositry repository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = repository.findByEmail(username);
	     
	        UserDetailsInfo detailsInfo = new UserDetailsInfo(user);
	        return detailsInfo;
	    }

	
}
