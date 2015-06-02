package com.handmade.cn.service.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.handmade.cn.bean.material.MaterialInfoBean;
import com.handmade.cn.entity.material.MaterialInfo;
import com.handmade.cn.mapper.BaseMapper;
import com.handmade.cn.mapper.material.MaterialInfoMapper;
import com.handmade.cn.service.BaseService;

@Service
public class MaterialSerivice extends BaseService<MaterialInfo, Integer>{
	
	@Autowired
	private MaterialInfoMapper materialInfoMapper;

	@Override
	public BaseMapper<MaterialInfo, Integer> getDao() {
		return materialInfoMapper;
	}

	@Override
	public int saveOrUpdate(MaterialInfo t) {
		return 0;
	}
	
	public List<MaterialInfo> findByIds(Integer[] ids){
		List<MaterialInfo> list = new ArrayList<MaterialInfo>();
		for (Integer id : ids) {
			MaterialInfo info = materialInfoMapper.selectByPrimaryKey(id);
			if(info!=null){
				list.add(info);
			}
		}
		return list;
	}
	
	//返回所有的材料
	public Map<String, Object> findAllMaterials(){
		Map<String, Object> map= new  HashMap<String, Object>();
		List<String> types=materialInfoMapper.findAllType();
		for (String string : types) {
			List<MaterialInfo> materialInfos = materialInfoMapper.selectByType(string);
			map.put(string, materialInfos);
		}
		return map;
	}
	
	public List<MaterialInfo> findByName(String name){
		return materialInfoMapper.selectByName(name);
	}
	
	public List<MaterialInfo> query(MaterialInfoBean materialInfoBean,PageBounds pageBounds){
		return materialInfoMapper.query(materialInfoBean, materialInfoBean.toPageBounds());
	}

	public Boolean save(MaterialInfo t){
		Integer count = 0;
		if(t.getId()!=null){
			t.setUpdateTime(new Date());
			count = materialInfoMapper.updateByPrimaryKeySelective(t);
		}else{
			t.setCreateTime(new Date());
			t.setUpdateTime(new Date());
			count = materialInfoMapper.insertSelective(t);
		}
		if(count!=0){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public Boolean delete(Integer id){
		Integer count = 0;
		count = materialInfoMapper.deleteByPrimaryKey(id);
		if(count!=0){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
