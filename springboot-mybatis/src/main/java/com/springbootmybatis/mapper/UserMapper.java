package com.springbootmybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.springbootmybatis.entity.User;



@Repository
public interface UserMapper {
	
	
	//添加用户
    Integer addUser(User user);
	//编辑用户信息
    Integer updateUser(User user); 
	//修改用户密码
    Integer updatePassword(User user);
    //查询用户名是否已存在
    User nameExists(User user);
    //显示用户信息
    User showUser(User user);
    //用户登陆验证
    User loginUser(User user);   
    //查询所有User
	List<User> findAllUsers(@Param("userPrimaryKey") String userPrimaryKey);
	//注销用户
	Integer deleteUser(User user);
	//查询总记录数
	Integer getUsersCount(@Param("userPrimaryKey") String userPrimaryKey);
	//更新用户借阅权限
	Integer decreaseRemainingBooks(@Param("uid") String uid);
	Integer increaceRemainingBooks(@Param("uid") String uid);
}
