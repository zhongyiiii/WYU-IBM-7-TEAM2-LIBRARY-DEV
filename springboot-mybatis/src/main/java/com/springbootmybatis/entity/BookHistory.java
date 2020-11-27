package com.springbootmybatis.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookHistory {
	private String userName;
	@JsonProperty("borrowTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date borrowTime;
	private Integer remainDay;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Integer getRemainDay() {
		return remainDay;
	}
	public void setRemainDay(Integer remainDay) {
		this.remainDay = remainDay;
	}
	@Override
	public String toString() {
		return "BookHistory [userName=" + userName + ", borrowTime=" + borrowTime + ", remainDay=" + remainDay + "]";
	}
	
	
	
}
