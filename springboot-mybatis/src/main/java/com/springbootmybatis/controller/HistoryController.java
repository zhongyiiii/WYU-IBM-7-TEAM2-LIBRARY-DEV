package com.springbootmybatis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.springbootmybatis.entity.BookHistory;
import com.springbootmybatis.entity.History;
import com.springbootmybatis.entity.HistoryCount;
import com.springbootmybatis.entity.PageBean4History;
import com.springbootmybatis.service.HistoryService;

@RestController
@CrossOrigin
public class HistoryController {
	@Autowired
	HistoryService historyService;
	/**
	 * 查所有借阅历史
	 * @return
	 */
	@RequestMapping("/findHistories")
	public PageBean4History findHistories(@RequestBody PageBean4History pageBean4History) {	
		//获取当前页码
		List<History> list2=historyService.findHistories(pageBean4History.getUserId(), pageBean4History.getSearchPrimaryKey());
		updateRemainday(list2);
		//分页
		PageHelper.startPage(pageBean4History.getCurrentPage(), pageBean4History.getPageSize());
		List<History> list=historyService.findHistories(pageBean4History.getUserId(), pageBean4History.getSearchPrimaryKey());
		//得到总数量
		Integer itemCount = historyService.getHistoriesCount(pageBean4History.getUserId(), pageBean4History.getSearchPrimaryKey());
		System.out.println("总条数"+itemCount);
		//根据分页条件得到总的页数

		
		pageBean4History.setTotalCount(itemCount);
		pageBean4History.setHistories(list);
		return pageBean4History;
	}
	/**
	 * 根据用户id查其当前未归还书籍（我的书架）
	 * @return
	 */
	@RequestMapping("/getHistoryByUId")//书架
	public List<History> getHistoryByUId(@RequestBody History history) {
		List<History> list=historyService.getHistoryByUId(history);
		updateRemainday(list);
		return historyService.getHistoryByUIdFormat(history);

	}
	/**
	 * 管理员根据书本id查该书本借阅历史
	 * @return
	 */
	@RequestMapping("/getHistoryByBId")
	public List<BookHistory> getHistoryByBId(@RequestBody History history) {
		List<History> list=historyService.getHistoryByBId(history);
		updateRemainday(list);
		System.out.println(history);
		return historyService.getBookHistory(history);
	}
	/**
	 *直接添加一条历史（暂时没用到）
	 * @return
	 */
	@RequestMapping("/addHistory")
	public Integer addHistory(@RequestBody History history) {
		// TODO Auto-generated method stub
		return historyService.addHistory(history);
	}
	/**
	 * 更新归还剩余时间的方法
	 * @param list
	 */
	public void updateRemainday (List<History> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getReturnStatus()==0) {
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
				String dateString=sdf.format(list.get(i).getReturnTime()).toString();
				Integer remainDay=getRemainDay(dateString);
				list.get(i).setRemainDay(remainDay);
				historyService.updateRemainDay(list.get(i));
			}
		}
	}	
	/**
	 *更新归还剩余时间的子方法： 输入归还时间字符串，算出归还剩余天数
	 * @param returndateString
	 * @return
	 */
	public Integer getRemainDay(String returndateString) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Date returndate=null;
		try {
			returndate = sdf.parse(returndateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar returnCalendar=new GregorianCalendar();
		returnCalendar.setTime(returndate);		
		Integer returnDay=returnCalendar.get(Calendar.DAY_OF_YEAR);	
		Date currentDate=new Date();
		Calendar currentCalendar=new GregorianCalendar();
		Integer currentDay=currentCalendar.get(Calendar.DAY_OF_YEAR);
		Integer currentYear=currentCalendar.get(Calendar.YEAR);	
		Integer MaxDay=currentCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);		
		if (currentDay>returnDay) {
			Integer remainDay=returnDay+MaxDay-currentDay;
			return remainDay;
		}else {
			Integer remainDay=returnDay-currentDay;
			return remainDay;
		}
	}
	/**
	 * 获取借阅和归还统计数据
	 */
	@RequestMapping("/borrowAndReturnCount")
	public HistoryCount borrowAndReturnCount(){
		HistoryCount historyCount =new HistoryCount();
		historyCount.setBorrowDay(historyService.borrowDayCount());
		historyCount.setBorrowMonth(historyService.borrowMouthCount());
		historyCount.setBorrowYear(historyService.borrowYearCount());
		historyCount.setReturnDay(historyService.returnDayCount());
		historyCount.setReturnMonth(historyService.returnMouthCount());
		historyCount.setReturnYear(historyService.returnYearCount());
		return historyCount;
	}
}
