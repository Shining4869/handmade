package com.handmade.cn.action.dianzan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.handmade.cn.service.dianzan.DianzanService;
import com.handmade.cn.web.action.BaseController;

@Controller
public class DianzanAction extends BaseController{
	
	@Autowired
	private DianzanService dianzanService;
	

}
