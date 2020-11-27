package com.springbootmybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmybatis.entity.Student;
import com.springbootmybatis.mapper.StudentMapper;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	public int saveStudent(Student student) {
		int flag = studentMapper.saveStudent(student);
		return flag;
	}
	
	public List<Student> findAllStudents(){
		return studentMapper.findAllStudents();
	}
	
	public int updateStudentById(Student student) {
		return studentMapper.updateStudentById(student);
	}
}
