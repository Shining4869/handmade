package com.handmade.cn.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handmade.cn.entity.admin.AdminInfo;
import com.handmade.cn.mapper.BaseMapper;
import com.handmade.cn.mapper.admin.AdminInfoMapper;
import com.handmade.cn.service.BaseService;

@Service
public class AdminService extends BaseService<AdminInfo, Integer>{
	
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	
	public AdminInfo findByName(String name){
		return adminInfoMapper.findByName(name);
	}
	
	public AdminInfo findByMobile(String mobile){
		return adminInfoMapper.findByMobile(mobile);
	}

	@Override
	public BaseMapper<AdminInfo, Integer> getDao() {
		return adminInfoMapper;
	}

	@Override
	public int saveOrUpdate(AdminInfo t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public AdminInfo findByMore1(String more1){
		return adminInfoMapper.selectByMore1(more1);
	}

}
