package com.handmade.cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handmade.cn.entity.sys.ClickOne;
import com.handmade.cn.mapper.sys.ClickOneMapper;

/**
 * @author zgdcool
 * @version 2015年2月17日 下午10:36:31
 *   
 */
@Service
public class ClickOneService {

	@Autowired
	private ClickOneMapper clickOneMapper;
	
	public Boolean saveOrUpdate(ClickOne clickOne){
		Integer count = null;
		if(clickOne.getId()!=null){
			count = clickOneMapper.updateByPrimaryKeySelective(clickOne);
		}else{
			count = clickOneMapper.insertSelective(clickOne);
		}
		if(count!=null){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public ClickOne findOne(Integer id){
		return clickOneMapper.selectByPrimaryKey(id);
	}
	
	public ClickOne findByOpenId(String openid){
		return clickOneMapper.findByOpenId(openid);
	}
	
	public Integer selectCount(){
		return clickOneMapper.selectCount();
	}
}
