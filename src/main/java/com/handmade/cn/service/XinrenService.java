package com.handmade.cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handmade.cn.entity.sys.ClickOne;
import com.handmade.cn.entity.sys.Xinren;
import com.handmade.cn.mapper.sys.XinrenMapper;

/**
 * @author zgdcool
 * @version 2015年2月18日 下午4:38:32
 *   
 */
@Service
public class XinrenService {
	@Autowired
	private XinrenMapper xinrenMapper;
	
	public Boolean saveOrUpdate(Xinren xinren){
		Integer count = null;
		if(xinren.getId()!=null){
			count = xinrenMapper.updateByPrimaryKeySelective(xinren);
		}else{
			count = xinrenMapper.insertSelective(xinren);
		}
		if(count!=null){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public Xinren findOne(Integer id){
		return xinrenMapper.selectByPrimaryKey(id);
	}
	
	public Xinren findByOpenId(String openid){
		return xinrenMapper.findByOpenId(openid);
	}
	
	public Integer selectCount(){
		return xinrenMapper.findSum();
	}
}
