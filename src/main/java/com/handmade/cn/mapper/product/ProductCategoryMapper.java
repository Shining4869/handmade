package com.handmade.cn.mapper.product;

import java.util.List;

import com.handmade.cn.entity.product.ProductCategory;
import com.handmade.cn.mapper.BaseMapper;

public interface ProductCategoryMapper extends BaseMapper<ProductCategory,Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
    
    List<ProductCategory> findAll();
}