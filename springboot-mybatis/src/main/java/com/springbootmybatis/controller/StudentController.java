package com.springbootmybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springbootmybatis.entity.Student;
import com.springbootmybatis.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("saveStudent")
	public String saveStudent(@RequestBody Student student) {
		System.out.println(student);
		if(student != null) {
			int flag =  studentService.saveStudent(student);
			return flag+"";
		}else {
			return null;
		}
	}
	
	@RequestMapping("findAll")
	public List<Student> findAllStudents() {
//		PageHelper.startPage(1, 2);
		List<Student> students = studentService.findAllStudents();
		return students;
	}
	
	@RequestMapping("update")
	public String updateStudent(@RequestBody Student student) {
		System.out.println(student);
		if(student != null) {
			int flag =  studentService.updateStudentById(student);
			return flag+"";
		}else {
			return null;
		}
	}
}
