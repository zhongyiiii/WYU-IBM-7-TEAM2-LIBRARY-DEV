package com.springbootmybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmybatis.entity.BookHistory;
import com.springbootmybatis.entity.History;
import com.springbootmybatis.mapper.HistoryMapper;
@Service
public class HistoryService implements HistoryMapper{
	@Autowired
	HistoryMapper historyMapper;

	public List<History> findHistories(String userId, String searchPrimaryKey) {
		// TODO Auto-generated method stub
		return historyMapper.findHistories(userId, searchPrimaryKey);
	}

	public List<History> getHistoryByUId(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getHistoryByUId(history);
	}

	public List<History> getHistoryByBId(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getHistoryByBId(history);
	}

	public Integer addHistory(History history) {
		// TODO Auto-generated method stub
		return historyMapper.addHistory(history);
	}

	public Integer returnBook(History history) {
		// TODO Auto-generated method stub
		return historyMapper.returnBook(history);
	}

	public List<History> getAllHistoryByUId(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getAllHistoryByUId(history);
	}

	public History getOneHistoryByUIdAndBId(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getOneHistoryByUIdAndBId(history);
	}

	public Integer updateRemainDay(History history) {
		// TODO Auto-generated method stub
		return historyMapper.updateRemainDay(history);
	}

	public History getHistoryByHistoryId(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getHistoryByHistoryId(history);
	}

	public Integer getHistoriesCount(String userId, String searchPrimaryKey) {
		// TODO Auto-generated method stub
		return historyMapper.getHistoriesCount(userId, searchPrimaryKey);
	}

	public List<History> getHistoryByUIdFormat(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getHistoryByUIdFormat(history);
	}

	public List<BookHistory> getBookHistory(History history) {
		// TODO Auto-generated method stub
		return historyMapper.getBookHistory(history);
	}

	public Integer borrowDayCount() {
		// TODO Auto-generated method stub
		return historyMapper.borrowDayCount();
	}

	public Integer borrowMouthCount() {
		// TODO Auto-generated method stub
		return historyMapper.borrowMouthCount();
	}

	public Integer borrowYearCount() {
		// TODO Auto-generated method stub
		return historyMapper.borrowYearCount();
	}

	public Integer returnDayCount() {
		// TODO Auto-generated method stub
		return historyMapper.returnDayCount();
	}

	public Integer returnMouthCount() {
		// TODO Auto-generated method stub
		return historyMapper.returnMouthCount();
	}

	public Integer returnYearCount() {
		// TODO Auto-generated method stub
		return historyMapper.returnYearCount();
	}
	
	//根据用户id删除历史记录
	public Integer deleteHistoryByUId(String userId) {
		return historyMapper.deleteHistoryByUId(userId);
	}



}
