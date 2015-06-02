package com.handmade.cn.mapper.material;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.material.MaterialInfoBean;
import com.handmade.cn.entity.material.MaterialInfo;
import com.handmade.cn.mapper.BaseMapper;

public interface MaterialInfoMapper extends BaseMapper<MaterialInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialInfo record);

    int insertSelective(MaterialInfo record);

    MaterialInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialInfo record);

    int updateByPrimaryKey(MaterialInfo record);
    
    PageList<MaterialInfo> query(MaterialInfoBean materialInfoBean,PageBounds pageBounds);
    
    List<MaterialInfo> selectByType(String type);
    
    List<String> findAllType();
    
    List<MaterialInfo> selectByName(String name);
}