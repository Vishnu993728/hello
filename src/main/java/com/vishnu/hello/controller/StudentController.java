package com.vishnu.hello.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vishnu.hello.entity.Student;
import com.vishnu.hello.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
@Autowired
	private StudentService service;
 @PostMapping("/saveStudent")
public ResponseEntity<?> saveStudent(@RequestBody Student student)
{
      Student saved=service.saveStudent(student);
      return new ResponseEntity<>(student, HttpStatus.CREATED);
      
}
@GetMapping("/findById")
public ResponseEntity<Student> findById(@RequestParam int id)
{
     Student student=service.findByID(id);
     
         
     return new ResponseEntity<Student>(student, HttpStatus.OK);
}
@PutMapping("/updateById")
public ResponseEntity< Student> updateById(@RequestParam int id,@RequestBody Student student)
{
	 service.updateById(id, student);
	 return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

@DeleteMapping("/deleteById")
public ResponseEntity< Student> deleteById(@RequestParam int id)
{
	   Student student=service.deleteById(id);
	return new ResponseEntity<Student>(student, HttpStatus.NO_CONTENT);
	}
@PostMapping(value="/saveAllI", consumes ="multipart/form-data")
public Student saveAll(@RequestPart("student") Student student,@RequestPart MultipartFile image,@RequestPart MultipartFile resume) throws IllegalStateException, IOException
{
	return service.saveAll(student, image, resume);
	}

}


