package com.handmade.cn.action.material;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.material.MaterialInfoBean;
import com.handmade.cn.entity.material.MaterialInfo;
import com.handmade.cn.service.material.MaterialSerivice;
import com.handmade.cn.web.action.BaseController;
import com.handmade.cn.web.interceptor.AdminUserLogin;

@Controller
@RequestMapping("app/material")
@AdminUserLogin
public class MaterialAppAction extends BaseController{
	
	@Autowired
	private MaterialSerivice materialSerivice;
	
	
	
	
	

}
