package com.handmade.cn.mapper.product;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.product.ProductInfoBean;
import com.handmade.cn.entity.product.ProductInfo;
import com.handmade.cn.mapper.BaseMapper;

public interface ProductInfoMapper extends BaseMapper<ProductInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
    
    PageList<ProductInfo> query(ProductInfoBean productInfoBean,PageBounds pageBounds);
    
    List<String> findAllType();
    
    PageList<ProductInfo> orderByGreatNum(ProductInfoBean productInfoBean,PageBounds pageBounds);
}