package com.handmade.cn.mapper.dianzan;

import org.apache.ibatis.annotations.Param;

import com.handmade.cn.entity.dianzan.Dianzanlog;
import com.handmade.cn.mapper.BaseMapper;

public interface DianzanlogMapper extends BaseMapper<Dianzanlog, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(Dianzanlog record);

    int insertSelective(Dianzanlog record);

    Dianzanlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dianzanlog record);

    int updateByPrimaryKey(Dianzanlog record);
    
    Dianzanlog selectByProductId(@Param("productId") Integer productId, @Param("openid") String openid);
}