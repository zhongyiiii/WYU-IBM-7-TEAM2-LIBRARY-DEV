package com.springbootmybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.springbootmybatis.entity.BookHistory;
import com.springbootmybatis.entity.History;
@Repository
public interface HistoryMapper {
	//查询所有用户借阅历史
	public List<History> findHistories(@Param("userId") String userId, @Param("searchPrimaryKey") String  searchPrimaryKey); 
	//根据用户id查询某用户未归还的借阅历史(我的书架)
	public List<History> getHistoryByUId(History history); 
	//格式化根据用户id查询某用户未归还的借阅历史(我的书架)
	public List<History> getHistoryByUIdFormat(History history); 
	// 根据书本id查询某书本借阅历史History
	public List<History> getHistoryByBId(History history); 
	//增加一条借阅历史
	public Integer addHistory(History history);
	//还书改借阅状态为1
	public Integer returnBook(History history);
	//根据用户id查询某用户所有借阅历史
	public List<History> getAllHistoryByUId(History history);
	//根据用户id和书本bid查询某一条未归还的借阅历史
	public History getOneHistoryByUIdAndBId(History history);
	//取出数据时先更新归还剩余时间
	public Integer updateRemainDay(History history);
	//根据historyId查询某一条借阅历史
	public History getHistoryByHistoryId(History history);
	//历史条数
	public Integer getHistoriesCount(@Param("userId") String userId, @Param("searchPrimaryKey") String  searchPrimaryKey);
	//根据书本id查询某书本借阅历史BookHistory
	public List<BookHistory> getBookHistory(History history);
	//统计方法
	public Integer returnYearCount();
	public Integer returnMouthCount();
	public Integer returnDayCount();
	public Integer borrowYearCount();
	public Integer borrowMouthCount();
	public Integer borrowDayCount();
	//根据用户id删除历史记录
	public Integer deleteHistoryByUId(@Param("userId") String userId);
}
