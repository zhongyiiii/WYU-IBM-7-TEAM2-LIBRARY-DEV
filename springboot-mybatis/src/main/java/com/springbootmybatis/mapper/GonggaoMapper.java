package com.springbootmybatis.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbootmybatis.entity.Gonggao;
@Repository
public interface GonggaoMapper {
	Integer updateGonggao(Gonggao gonggao);
	List<Gonggao> showGonggao();
}
