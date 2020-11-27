package com.springbootmybatis.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbootmybatis.entity.Student;
@Repository
public interface StudentMapper {
	int saveStudent(Student student);
	
	List<Student> findAllStudents();
	
	int updateStudentById(Student student);
	
}
