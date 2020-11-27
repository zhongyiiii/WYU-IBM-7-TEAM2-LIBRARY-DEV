package com.springbootmybatis.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.springbootmybatis.entity.Book;
import com.springbootmybatis.entity.History;
import com.springbootmybatis.entity.PageBean4Book;
import com.springbootmybatis.entity.SelectList;
import com.springbootmybatis.service.BookService;
import com.springbootmybatis.service.HistoryService;
import com.springbootmybatis.service.UserService;

@RestController
@CrossOrigin
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public SelectList testList() {
		List<String> authors = new ArrayList<String>();
		authors.add("aaa");
		authors.add("CCC");
		authors.add("ddd");
		authors.add("ddd");
		SelectList sList = new SelectList();
		sList.setAuthors(authors);
		sList.setBookThemes(authors);
		sList.setBookType(authors);
		
		return sList;
	}
	/**
	 * 按照作者和书名查找数量
	 * @param book
	 * @return
	 */
	@RequestMapping("/checkBook")
	public Integer checkBook(@RequestBody Book book) {
		Integer isAlreadyExistBook = bookService.getBooksCountByBookNameAndAuthor(book.getBookName(), book.getAuthor());
		return isAlreadyExistBook;
	}
	
	/**
	 * 添加书本
	 * @param book
	 * @return
	 */
	@RequestMapping("/addBook")
	public Integer addBook(@RequestBody Book book) {
		Integer isAlreadyExistBook = bookService.getBooksCountByBookNameAndAuthor(book.getBookName(), book.getAuthor());
		if(isAlreadyExistBook > 0) {
			return 2;
		}else {
			book.setBookId(String.valueOf(System.currentTimeMillis()));
			book.setRemainCount(book.getInboundCount());
			book.setInboundTime(new Date());
			book.setBorrowCount(0);
			return bookService.addBook(book);
		}
	}
	/**
	 * 根据书本id删除书本
	 * @param book
	 * @return
	 */
	@RequestMapping("/deleteBookById")
	public Integer deleteBookById(@RequestBody Book book) {
		// TODO Auto-generated method stub
		System.out.println(book);
		Book book2=bookService.findBookById(book);
		System.out.println(book2);
		if (book2!=null) {
			if (book2.getInboundCount()==book2.getRemainCount()) {
				return bookService.deleteBookById(book);
			}else {
				return 0;//没有归还完，不能删
			}
		}else {
			return 2;
		}
		
	}
	/**
	 * 根据书本id更新书本信息
	 * @param book
	 * @return
	 */
	@RequestMapping("/updateBookById")
	public Integer updateBookById(@RequestBody Book book) {
		System.out.println(book);
		Book book2=bookService.findBookById(book);
		if (book2!=null) {
			if (book2.getInboundCount()<book.getInboundCount()) {//如果上架数量增加了
				book.setRemainCount(book.getInboundCount()-book2.getInboundCount()+book2.getRemainCount());
				book.setBorrowCount(book2.getBorrowCount());
				book.setInboundTime(book2.getInboundTime());
				return bookService.updateBookById(book);
			}else {//如果上架数量减少了或者不变
				if (book.getInboundCount()<book2.getBorrowCount()) {//如果上架数量小于已借出数量
					return 2;//上架数量小于已借出数量不允许更改
				}else {
					book.setRemainCount(book.getInboundCount()-book2.getInboundCount()+book2.getRemainCount());
					book.setBorrowCount(book2.getBorrowCount());
					book.setInboundTime(book2.getInboundTime());
					return bookService.updateBookById(book);
				}
			}
			
		}else {
			return 3;//传参错误
		}
		
	}
	/**
	 * 传入bid uid借书
	 * @param history
	 * @return
	 */
	@RequestMapping("/borrowBook")
	public Integer borrowBook(@RequestBody History history) {
		// TODO Auto-generated method stub
		Book book2=new Book();
		book2.setBookId(history.getbId());
		Book book3=bookService.findBookById(book2);
		if (book3!=null) {//如果找到那本书
			if (book3.getRemainCount()!=0) {//如果书本可借数量不为0				
				if (historyService.getHistoryByUId(history)==null) {//如果那个人没借书
					history.setBorrowTime(new Date());				
					Date date=new Date();
					Calendar calendar=new GregorianCalendar();
					calendar.setTime(date);
					calendar.add(calendar.DATE, 30);
					history.setReturnTime(calendar.getTime());
					history.setReturnStatus(0);
					history.setRemainDay(30);
					System.out.println(history);
					System.out.println(book3);					
					Integer integer=historyService.addHistory(history);
					book3.setBorrowCount(book3.getBorrowCount()+1);
					book3.setRemainCount(book3.getRemainCount()-1);
					Integer flag = userService.decreaseRemainingBooks(history.getuId());
					if(flag == 1) {
						return bookService.updateBookById(book3);//返回1借书成功
					}else {
						return 0;
					}
				}else{//如果借了书
					if (historyService.getHistoryByUId(history).size()<3) {
						if (historyService.getOneHistoryByUIdAndBId(history)==null) {//如果那本书没被他正在借
							history.setBorrowTime(new Date());				
							Date date=new Date();
							Calendar calendar=new GregorianCalendar();
							calendar.setTime(date);
							calendar.add(calendar.DATE, 30);
							history.setReturnTime(calendar.getTime());
							history.setReturnStatus(0);
							history.setRemainDay(30);
							System.out.println(history);
							System.out.println(book3);
							Integer integer=historyService.addHistory(history);
							book3.setBorrowCount(book3.getBorrowCount()+1);
							book3.setRemainCount(book3.getRemainCount()-1);
							Integer flag = userService.decreaseRemainingBooks(history.getuId());
							if(flag == 1) {
								return bookService.updateBookById(book3);//返回1借书成功
							}else {
								return 0;
							}
						}else {
							return 3;//那本书已被该用户借阅且未归还
						}
						
					}else {
						return 4;//借书数量已达三本，不能借
					}
				}
				
			}else {
				return 2;//书本可借数量为0
			}
		}else {
			return 0;//找不到那本书(传参错误)
		}		
	}
	/**
	 * 传入historyId bookId还书
	 * @param history
	 * @return
	 */
	@RequestMapping("/returnBook")
	public Integer returnBook(@RequestBody History history) {
		History history2=historyService.getHistoryByHistoryId(history);		
		Book book2=new Book();
		book2.setBookId(history2.getbId());
		Book book4=bookService.findBookById(book2);
		if (book4!=null) {
				historyService.returnBook(history2);
				book4.setBorrowCount(book4.getBorrowCount()-1);
				book4.setRemainCount(book4.getRemainCount()+1);
				Integer flag = userService.increaceRemainingBooks(history2.getuId());
				if (flag == 1) {
					return bookService.updateBookById(book4);	//返回1，表明还书成功
				}else {
					return 3;
				}
				
		}else {
			return 2;
		}		
	}
	
	/**
	 * 模糊查询显示书籍信息
	 * @param pageBean4Book
	 * @return
	 */
	@RequestMapping("/getBooksLike")
	public PageBean4Book getBooksLike(@RequestBody PageBean4Book pageBean4Book){
		//获取当前页码
//		Integer index = (pageBean4Book.getCurrentPage() -1)*pageBean4Book.getPageSize();
		PageHelper.startPage(pageBean4Book.getCurrentPage(), pageBean4Book.getPageSize());		
		List<Book> list2 = bookService.getBooksLike(pageBean4Book.getBook());
		//得到书的总数量	
		Integer itemCount = bookService.getBooksCount(pageBean4Book.getBook());
		//根据分页条件得到总的页数
		
		pageBean4Book.setTotalCount(itemCount);
		pageBean4Book.setBooks(list2);
		return pageBean4Book;
		
	}
	
	/**
	 * 查询所有书籍信息
	 * @param pageBean4Book
	 * @return
	 */
	@RequestMapping("/searchBooks")
	public PageBean4Book getAllBooks(@RequestBody PageBean4Book pageBean4Book) {
		//分页
		PageHelper.startPage(pageBean4Book.getCurrentPage(), pageBean4Book.getPageSize());
		List<Book> list = bookService.getAllBooks(pageBean4Book.getBookPrimaryKey());
		//得到书的总数量
		Integer itemCount = bookService.searchBooksCount(pageBean4Book.getBookPrimaryKey());
		System.out.println(itemCount);

		
		pageBean4Book.setTotalCount(itemCount);
		pageBean4Book.setBooks(list);

		return pageBean4Book;
	}
	
	/**
	 * 模糊查询下拉框
	 * @param selectList
	 * @return
	 */
	@RequestMapping("/getBooksLists")
	public SelectList getBooksLists(SelectList selectList) {
		List<String> typeList=bookService.getBooksTypes();
		List<String> themesList=bookService.getBooksThemes();
		List<String> authorsList=bookService.getAuthors();
		selectList.setBookType(typeList);
		selectList.setBookThemes(themesList);
		selectList.setAuthors(authorsList);		
		return selectList;
		
	}


}
