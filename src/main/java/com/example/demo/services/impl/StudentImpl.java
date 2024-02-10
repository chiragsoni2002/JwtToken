package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.model.Student;
import com.example.demo.repositry.StudentRepositry;
import com.example.demo.services.StudentServices;
import com.example.demo.services.dto.StudentDto;
import com.example.demo.services.util.GenricResponse;
import java.lang.reflect.Field;



@Service
public class StudentImpl implements StudentServices{
	
	@Autowired
	private StudentRepositry studentRepositry;

	@Override
	public ResponseEntity<?> addStudent(StudentDto studentDto) {
		// TODO Auto-generated method stub
		
		Student student = new Student();
		
		BeanUtils.copyProperties(studentDto, student);
		
		studentRepositry.save(student);
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", student));
	}

	@Override
	public ResponseEntity<?> updateStudent(String id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		
		Optional<Student> existingStudent =  studentRepositry.findById(id);
		
		if(existingStudent.isPresent()) {
			fields.forEach((key, value)->{
			
			  	Field filed = ReflectionUtils.findField(Student.class, key);
			  	filed.setAccessible(true);
			  	ReflectionUtils.setField(filed, existingStudent.get(), value);
			});
			studentRepositry.save(existingStudent.get());
		}
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", existingStudent));
	}

	@Override
	public ResponseEntity<?> getStudent() {
		// TODO Auto-generated method stub
		
		List<Student> student = studentRepositry.findAll();
		
		List<StudentDto> student1 = new ArrayList<>();
		
		student.forEach(student3 -> {
			StudentDto student2 = new StudentDto();
			
			student2.setId(student3.getId());
			student2.setName(student3.getName());
			student2.setEnrollmentNumber(student3.getEnrollmentNumber());
			student2.setBranch(student3.getBranch());
			student2.setMobileNumber(student3.getMobileNumber());
			student2.setAddress(student3.getAddress());
			
			student1.add(student2);
			
		});
			
		
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", student1));
	}

}
