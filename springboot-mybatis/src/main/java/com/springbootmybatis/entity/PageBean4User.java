package com.springbootmybatis.entity;

import java.util.List;

public class PageBean4User {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;
	private String userPrimaryKey;
	private List<User> users;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getUserPrimaryKey() {
		return userPrimaryKey;
	}
	public void setUserPrimaryKey(String userPrimaryKey) {
		this.userPrimaryKey = userPrimaryKey;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "PageBean4User [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", userPrimaryKey=" + userPrimaryKey + ", users=" + users + "]";
	}

	
	
	
	

}
