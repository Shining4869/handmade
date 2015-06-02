package com.handmade.cn.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handmade.cn.entity.sys.LhRedOrder;
import com.handmade.cn.mapper.sys.LhRedOrderMapper;

/**
 * @author zgdcool
 * @version 2015年2月13日 下午8:42:41
 *   
 */
@Service
public class LhRedOrderService {

	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private LhRedOrderMapper lhRedOrderMapper;

	private static ThreadLocal<SimpleDateFormat> TRADE_DATE_FORMATs = new ThreadLocal<SimpleDateFormat>();
	
	public static  SimpleDateFormat ymdFormat() {
		SimpleDateFormat simpleDateFormat = TRADE_DATE_FORMATs.get();
		if(simpleDateFormat == null){
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			TRADE_DATE_FORMATs.set(simpleDateFormat);
		}
		return simpleDateFormat;
	}
	
	public Boolean saveOrUpdate(LhRedOrder lhRedOrder){
		Integer count = null;
		if(lhRedOrder.getOrderId()!=null){
			count = lhRedOrderMapper.updateByPrimaryKeySelective(lhRedOrder);
		}else{
			lhRedOrder.setOrderCode("REDO"+ymdFormat().format(Calendar.getInstance().getTime()) + StringUtils.leftPad(sequenceService.nextSequence("order_number", true).getValue().toString(), 6, "0"));
			count = lhRedOrderMapper.insertSelective(lhRedOrder);
		}
		if(count!=null){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public LhRedOrder findOne(Integer orderId){
		return lhRedOrderMapper.selectByPrimaryKey(orderId);
	}
	
	public LhRedOrder findByOpenId(String openId){
		return lhRedOrderMapper.findByOpenId(openId);
	}
	
	public LhRedOrder findByOrderCode(String orderCode){
		return lhRedOrderMapper.findByOrderCode(orderCode);
	}
	
	public Integer queryCountByProjectSpa(){
		return lhRedOrderMapper.queryCountByProjectSpa();
	}
	
	public Integer queryCountByProject(String project){
		return lhRedOrderMapper.queryCountByProject(project);
	}
}
