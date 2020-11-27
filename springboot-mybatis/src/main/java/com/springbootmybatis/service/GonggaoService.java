package com.springbootmybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmybatis.entity.Gonggao;
import com.springbootmybatis.mapper.GonggaoMapper;

@Service
public class GonggaoService implements GonggaoMapper{
	@Autowired
	private GonggaoMapper gonggaoMapper;

	public Integer updateGonggao(Gonggao gonggao) {
		// TODO Auto-generated method stub
		return gonggaoMapper.updateGonggao(gonggao);
	}

	public List<Gonggao> showGonggao() {
		// TODO Auto-generated method stub
		return gonggaoMapper.showGonggao();
	}
	
	
}
