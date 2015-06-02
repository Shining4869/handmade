package com.handmade.cn.mapper.sys;

import com.handmade.cn.entity.sys.LhRedOrder;

public interface LhRedOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(LhRedOrder record);

    int insertSelective(LhRedOrder record);

    LhRedOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(LhRedOrder record);

    int updateByPrimaryKey(LhRedOrder record);
    
    LhRedOrder findByOpenId(String openId);
    
    LhRedOrder findByOrderCode(String orderCode);
    
    Integer queryCountByProject(String project);
    
    Integer queryCountByProjectSpa();
}