package com.springbootmybatis.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private String userId;
	private String loginId;
	private String userName;
	private String email;
	private String userPwd;
	private String gender;
	private Integer age;
	private String address;
	private String contact;
	private String description;
	private Integer remainingBooks;
	@JsonProperty("birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRemainingBooks() {
		return remainingBooks;
	}
	public void setRemainingBooks(Integer remainingBooks) {
		this.remainingBooks = remainingBooks;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginId=" + loginId + ", userName=" + userName + ", email=" + email
				+ ", userPwd=" + userPwd + ", gender=" + gender + ", age=" + age + ", address=" + address + ", contact="
				+ contact + ", description=" + description + ", remainingBooks=" + remainingBooks + ", birthday="
				+ birthday + "]";
	}
	
	
	
	

	
}
