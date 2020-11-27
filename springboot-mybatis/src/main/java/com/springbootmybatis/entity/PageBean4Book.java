package com.springbootmybatis.entity;

import java.util.List;

public class PageBean4Book {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;
	private Book book;	
	private List<Book> books;
	private String bookPrimaryKey;
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
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getBookPrimaryKey() {
		return bookPrimaryKey;
	}
	public void setBookPrimaryKey(String bookPrimaryKey) {
		this.bookPrimaryKey = bookPrimaryKey;
	}
	@Override
	public String toString() {
		return "PageBean4Book [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", book=" + book + ", books=" + books + ", bookPrimaryKey=" + bookPrimaryKey + "]";
	}
	
	

	

}
