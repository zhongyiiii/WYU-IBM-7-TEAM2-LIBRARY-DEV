package com.springbootmybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmybatis.entity.Book;
import com.springbootmybatis.mapper.BookMapper;

@Service
public class BookService implements BookMapper{
	@Autowired
	BookMapper bookMapper;

	public Integer addBook(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.addBook(book);
	}

	public Integer deleteBookById(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.deleteBookById(book);
	}

	public Integer updateBookById(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.updateBookById(book);
	}



	public List<Book> findBooks1(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.findBooks1(book);
	}

	public List<Book> findBooks2(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.findBooks2(book);
	}

	public Integer borrowBook(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.borrowBook(book);
	}

	public Integer returnBook(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.returnBook(book);
	}

	public Book findBookById(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.findBookById(book);
	}

	public Integer getBooksCount(Book book) {
		return bookMapper.getBooksCount(book);
	}

	public List<Book> getBooksLike(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.getBooksLike(book);
	}

	public List<Book> getAllBooks(String bookPrimaryKey) {
		return bookMapper.getAllBooks(bookPrimaryKey);
	}

	public Integer searchBooksCount(String bookPrimaryKey) {
		// TODO Auto-generated method stub
		return bookMapper.searchBooksCount(bookPrimaryKey);
	}
	
	@Override
	public List<String> getBooksTypes() {
		return bookMapper.getBooksTypes();
	}

	@Override
	public List<String> getBooksThemes() {
		return bookMapper.getBooksThemes();
	}

	@Override
	public List<String> getAuthors() {
		return bookMapper.getAuthors();
	}

	public Integer getBooksCountByBookNameAndAuthor(String bookName, String author) {
		return bookMapper.getBooksCountByBookNameAndAuthor(bookName, author);
	}

	
}
