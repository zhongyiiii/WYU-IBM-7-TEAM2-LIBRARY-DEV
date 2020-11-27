package com.springbootmybatis.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class History {
	private Integer historyId;
	private String uId;
	private String bId;
	
	@JsonProperty("borrowTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date borrowTime;
	@JsonProperty("returnTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	
	private Date returnTime;
	private Integer remainDay;
	private Integer returnStatus;
	
	
	private String bookId;
	private String bookName;
	private String bookType;
	private String bookTheme;
	private String author;
	private String description;
	@JsonProperty("inboundTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date inboundTime;
	
	private Integer inboundCount;
	
	private Integer remainCount;
	private Integer borrowCount;
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public Integer getRemainDay() {
		return remainDay;
	}
	public void setRemainDay(Integer remainDay) {
		this.remainDay = remainDay;
	}
	public Integer getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookTheme() {
		return bookTheme;
	}
	public void setBookTheme(String bookTheme) {
		this.bookTheme = bookTheme;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getInboundTime() {
		return inboundTime;
	}
	public void setInboundTime(Date inboundTime) {
		this.inboundTime = inboundTime;
	}
	public Integer getInboundCount() {
		return inboundCount;
	}
	public void setInboundCount(Integer inboundCount) {
		this.inboundCount = inboundCount;
	}
	public Integer getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}
	public Integer getBorrowCount() {
		return borrowCount;
	}
	public void setBorrowCount(Integer borrowCount) {
		this.borrowCount = borrowCount;
	}
	@Override
	public String toString() {
		return "History [historyId=" + historyId + ", uId=" + uId + ", bId=" + bId + ", borrowTime=" + borrowTime
				+ ", returnTime=" + returnTime + ", remainDay=" + remainDay + ", returnStatus=" + returnStatus
				+ ", bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookTheme="
				+ bookTheme + ", author=" + author + ", description=" + description + ", inboundTime=" + inboundTime
				+ ", inboundCount=" + inboundCount + ", remainCount=" + remainCount + ", borrowCount=" + borrowCount
				+ "]";
	}
	
	
	
	
	
	
	
	
}
