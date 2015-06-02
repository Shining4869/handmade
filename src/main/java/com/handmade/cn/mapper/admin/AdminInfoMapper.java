package com.handmade.cn.mapper.admin;

import com.handmade.cn.entity.admin.AdminInfo;
import com.handmade.cn.mapper.BaseMapper;

public interface AdminInfoMapper extends BaseMapper<AdminInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
    
    AdminInfo findByName(String userName);
    
    AdminInfo findByMobile(String mobile);
    
    AdminInfo selectByMore1(String more1);
    
}