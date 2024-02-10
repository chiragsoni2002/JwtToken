package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.StudentServices;
import com.example.demo.services.dto.StudentDto;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	
	@Autowired
	private StudentServices studentServices;
	
	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
		return studentServices.addStudent(studentDto);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody Map<String, Object> fields){
		
		return studentServices.updateStudent(id, fields);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getStudent(){
		
		return studentServices.getStudent();
	}

}
