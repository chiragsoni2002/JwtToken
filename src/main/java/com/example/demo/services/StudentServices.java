package com.example.demo.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.services.dto.StudentDto;

public interface StudentServices {

	ResponseEntity<?> addStudent(StudentDto studentDto);

	ResponseEntity<?> updateStudent(String id, Map<String, Object> fields);

	ResponseEntity<?> getStudent();

}
