package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repositry.UserRepositry;
import com.example.demo.services.LoginServices;
import com.example.demo.services.dto.LoginDto;
import com.example.demo.services.dto.TokenResponse;
import com.example.demo.services.dto.UserDetails;
import com.example.demo.services.util.GenricResponse;
import com.example.demo.services.util.JwtUtil;
import com.example.demo.services.util.UserDetailsInfo;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private LoginServices loginServices;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepositry userRepositry;
	
	@GetMapping("/login")
	public ResponseEntity<?> login(){
		
		LoginDto loginDto = new LoginDto();
		//loginDto.setUsername("Chirag");
		loginDto.setPassword("5673");
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", loginDto));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user){
		
		String password= bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		return loginServices.addUserDetails(user);
		
	}
	
	@GetMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody LoginDto loginDto ) throws Exception {
		
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		 if (authentication.isAuthenticated()) { 
			 
			 TokenResponse tokenResponse = new TokenResponse();
			 
			 User user = userRepositry.findByEmail(loginDto.getEmail());
			 
			 tokenResponse.setUser(user);
			 tokenResponse.setToken( jwtUtil.generateToken(loginDto.getEmail()));
			 
			 return ResponseEntity.ok(new GenricResponse(201, "Success", tokenResponse));
	        } else { 
	            throw new UsernameNotFoundException("invalid user request !"); 
	        } 
		
	}
	
	@GetMapping("/check")
	public ResponseEntity<?> check() {
		
		Authentication user =  SecurityContextHolder.getContext().getAuthentication();
		UserDetailsInfo user1 =  (UserDetailsInfo) user.getPrincipal();
		
		UserDetails userDetails = new UserDetails();
		userDetails.setId(user1.getUser().getId());
		userDetails.setEmail(user1.getUser().getEmail());
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", userDetails));
	}
	
	
	


}
