package com.springbootmybatis.entity;

import java.util.List;

public class PageBean4History {
	private String userId;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;
	private History history;
	private List<History> histories;

	
	private String searchPrimaryKey;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


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


	public History getHistory() {
		return history;
	}


	public void setHistory(History history) {
		this.history = history;
	}


	public List<History> getHistories() {
		return histories;
	}


	public void setHistories(List<History> histories) {
		this.histories = histories;
	}


	public String getSearchPrimaryKey() {
		return searchPrimaryKey;
	}


	public void setSearchPrimaryKey(String searchPrimaryKey) {
		this.searchPrimaryKey = searchPrimaryKey;
	}


	@Override
	public String toString() {
		return "PageBean4History [userId=" + userId + ", currentPage=" + currentPage + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", history=" + history + ", histories=" + histories
				+ ", searchPrimaryKey=" + searchPrimaryKey + "]";
	}

	
	


	

}
