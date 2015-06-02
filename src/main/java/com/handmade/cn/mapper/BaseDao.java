package com.handmade.cn.mapper;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface BaseDao<T,Pk> {
	 int deleteByPrimaryKey(Pk id);

//	    int insert(T record);

	    int insertSelective(T record);

	    T selectByPrimaryKey(Pk id);
	    
	  

	    int updateByPrimaryKeySelective(T record);
	    
	    int updateByPrimaryKey(T record);
	    
	    List<T>  query(T t);
	    
	    PageList<T>  query(T links, PageBounds pageBounds);
}
