/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.handmade.cn.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.handmade.cn.bean.ResultBean;
import com.handmade.cn.utils.JsonMapper;

/**
 * 基础控制器
 * <p>User: Zhang Kaitao
 * <p>Date: 13-2-23 下午3:56
 * <p>Version: 1.0
 */
public abstract class BaseController{


    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
   
    
    protected static String SUCCESS = "成功";
	protected static String FAIL = "失败";
	
	@Autowired  
	protected  HttpServletRequest request; 
	
	
	
	protected Map<String,Object> suc(String... msg){
		ResultBean resultBean =null;
		if(msg == null || msg.length == 0){
			resultBean = ResultBean.suc(null);
		}else {
			resultBean = ResultBean.suc(msg[0]);
		}
		return JsonMapper.beanToMap(resultBean);
	}
	
	protected Map<String,Object> suc(String msg,Object result){
		return JsonMapper.beanToMap(ResultBean.suc(msg, result));
	}
	
	protected Map<String,Object> fail(String... msg){
		ResultBean resultBean =null;
		if(msg == null || msg.length == 0){
			resultBean = ResultBean.fail(null);
		}else {
			resultBean = ResultBean.fail(msg[0]);
		}
		
		return JsonMapper.beanToMap(resultBean);
	}
}
