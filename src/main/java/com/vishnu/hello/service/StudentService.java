package com.vishnu.hello.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vishnu.hello.customException.StudentException;
import com.vishnu.hello.entity.Student;
import com.vishnu.hello.repo.StudentRepo;

@Service
public class StudentService {
   @Autowired
	private StudentRepo repo;
   //save
   public Student saveStudent(Student student)
   {
	   if(student == null)
		   throw new StudentException("student cannot be null");
	   else
		   return repo.save(student); 
	   
	   
   }
   
   //findbyid
   
   public Student findByID(int id)
   {
	// return  repo.findById(id).orElseThrow(() -> new StudentException("cannot fetch student  not found:+id"));
	   return repo.findById(id).orElseThrow(() -> new StudentException("cannot fetch student  not found:"+id));
      
   }
   
   //update
   
   public Student updateById(int id,Student student)
   {
	   Student student2=findByID(id);
	   student2.setStudentID(id);
	   student2.setStudentAge(student.getStudentAge());
	   student2.setStudentName(student.getStudentName());
	   student2.setStudentDob(student.getStudentDob());

	 return  repo.save(student2);
   }
   
   public Student deleteById(int id)
   {
	  Student student1=  repo.findById(id).get();
	  repo.delete(student1);
	  return student1;
	    
   }
   
   public Student saveAll(Student student,MultipartFile photo,MultipartFile resume) throws IllegalStateException, IOException
   {
	   String folder="D:/download/upload_files/";
	   File dir=new File(folder);
	   if(!dir.exists())
	   {
		   dir.mkdir();
	   }
	   String photoName=folder+" photo"+photo.getOriginalFilename();
	   photo.transferTo(new File(photoName));
	   student.setImagePath(photoName);
	   
	   String resumeName=folder+"resume"+resume.getOriginalFilename();
	   resume.transferTo(new File(resumeName));
	   student.setResumePath(resumeName);
	   return repo.save(student);
   }
}
