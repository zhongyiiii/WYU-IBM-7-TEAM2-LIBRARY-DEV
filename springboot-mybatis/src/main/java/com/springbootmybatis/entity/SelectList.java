package com.springbootmybatis.entity;

import java.util.List;

public class SelectList {
	private List<String> authors;
	private List<String> bookType;
	private List<String> bookThemes;
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public List<String> getBookType() {
		return bookType;
	}
	public void setBookType(List<String> bookType) {
		this.bookType = bookType;
	}
	public List<String> getBookThemes() {
		return bookThemes;
	}
	public void setBookThemes(List<String> bookThemes) {
		this.bookThemes = bookThemes;
	}
	@Override
	public String toString() {
		return "SelectList [authors=" + authors + ", bookType=" + bookType + ", bookThemes=" + bookThemes + "]";
	}
	
	
}
