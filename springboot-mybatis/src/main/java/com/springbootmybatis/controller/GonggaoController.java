package com.springbootmybatis.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmybatis.entity.Gonggao;
import com.springbootmybatis.service.GonggaoService;

@RestController
@CrossOrigin
public class GonggaoController {
	@Autowired
	private GonggaoService gonggaoService;
	/**
	 * 编辑公告
	 * @param gonggao
	 * @return
	 */
	@RequestMapping("/updateGonggao")
	public Integer updateGonggao(@RequestBody Gonggao gonggao) {
		gonggao.setWriteTime(new Date());
		return gonggaoService.updateGonggao(gonggao);
	}
	/**
	 * 获取公告内容
	 * @return
	 */
	@RequestMapping("/showGonggao")
	public List<Gonggao> showGonggao() {
		return gonggaoService.showGonggao();
	}
	
	
}
