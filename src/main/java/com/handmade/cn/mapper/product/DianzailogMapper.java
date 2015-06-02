package com.handmade.cn.mapper.product;

import com.handmade.cn.entity.product.Dianzailog;

public interface DianzailogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dianzailog record);

    int insertSelective(Dianzailog record);

    Dianzailog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dianzailog record);

    int updateByPrimaryKey(Dianzailog record);
}