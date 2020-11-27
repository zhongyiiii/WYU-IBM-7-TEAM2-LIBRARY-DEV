package com.springbootmybatis.entity;

public class HistoryCount {
	private Integer borrowDay;
	private Integer borrowMonth;
	private Integer borrowYear;
	private Integer returnDay;
	private Integer returnMonth;
	private Integer returnYear;
	public Integer getBorrowDay() {
		return borrowDay;
	}
	public void setBorrowDay(Integer borrowDay) {
		this.borrowDay = borrowDay;
	}
	public Integer getBorrowMonth() {
		return borrowMonth;
	}
	public void setBorrowMonth(Integer borrowMonth) {
		this.borrowMonth = borrowMonth;
	}
	public Integer getBorrowYear() {
		return borrowYear;
	}
	public void setBorrowYear(Integer borrowYear) {
		this.borrowYear = borrowYear;
	}
	public Integer getReturnDay() {
		return returnDay;
	}
	public void setReturnDay(Integer returnDay) {
		this.returnDay = returnDay;
	}
	public Integer getReturnMonth() {
		return returnMonth;
	}
	public void setReturnMonth(Integer returnMonth) {
		this.returnMonth = returnMonth;
	}
	public Integer getReturnYear() {
		return returnYear;
	}
	public void setReturnYear(Integer returnYear) {
		this.returnYear = returnYear;
	}
	@Override
	public String toString() {
		return "HistoryCount [borrowDay=" + borrowDay + ", borrowMonth=" + borrowMonth + ", borrowYear=" + borrowYear
				+ ", returnDay=" + returnDay + ", returnMonth=" + returnMonth + ", returnYear=" + returnYear + "]";
	}
	
}
