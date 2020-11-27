package com.springbootmybatis.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmybatis.entity.User;
import com.springbootmybatis.mapper.UserMapper;

@Service
public class UserService implements UserMapper{
	@Autowired
	private UserMapper userMapper;
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}

	public Integer updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUser(user);
	}

	public Integer updatePassword(User user) {
		// TODO Auto-generated method stub
		return userMapper.updatePassword(user);
	}

	public User nameExists(User user) {
		// TODO Auto-generated method stub
		return userMapper.nameExists(user);
	}

	public User showUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.showUser(user);
	}

	public User loginUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.loginUser(user);
	}

	public List<User> findAllUsers(String userPrimaryKey) {
		return userMapper.findAllUsers(userPrimaryKey);
	}

	public Integer deleteUser(User user) {
		return userMapper.deleteUser(user);
	}

	public Integer getUsersCount(String userPrimaryKey) {
		return userMapper.getUsersCount(userPrimaryKey);
	}
	
	public Integer decreaseRemainingBooks(String uid) {
		return userMapper.decreaseRemainingBooks(uid);
	}

	public Integer increaceRemainingBooks(String uid) {
		return userMapper.increaceRemainingBooks(uid);
	}
	


	
	
	
}
