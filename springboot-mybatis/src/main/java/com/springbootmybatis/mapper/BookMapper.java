package com.springbootmybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.springbootmybatis.entity.Book;
@Repository
public interface BookMapper {
	public Integer addBook(Book book); //管理员添加书籍

	public Integer deleteBookById(Book book);  //管理员删除书籍

	public Integer updateBookById(Book book);  //管理员更新书籍
	
	
	
	public Book findBookById(Book book);//根据id查询单本书

	public List<Book> findBooks1(Book book);//查询书籍(固定模糊查询)
	
	public List<Book> findBooks2(Book book);//查询书籍(非固定模糊查询)	
	
	public Integer borrowBook(Book book);//用户借书
	
	public Integer returnBook(Book book);//用户还书
	
	public Integer getBooksCount(Book book);//查询总记录数
	
	public List<Book> getBooksLike(Book book);  //模糊查询书籍
	
	public List<Book> getAllBooks(@Param ("bookPrimaryKey") String bookPrimaryKey);  //查询所有书籍

	public Integer searchBooksCount(@Param ("bookPrimaryKey") String bookPrimaryKey);
	
	
	public List<String> getBooksTypes();
	
	public List<String> getBooksThemes();
	
	public List<String> getAuthors();

	public Integer getBooksCountByBookNameAndAuthor(@Param("bookName") String bookName,@Param("author") String author);

}
