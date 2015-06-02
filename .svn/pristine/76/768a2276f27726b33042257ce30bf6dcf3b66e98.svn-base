package com.handmade.cn.service.dianzan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.handmade.cn.entity.dianzan.Dianzanlog;
import com.handmade.cn.entity.product.ProductInfo;
import com.handmade.cn.mapper.BaseMapper;
import com.handmade.cn.mapper.dianzan.DianzanlogMapper;
import com.handmade.cn.service.BaseService;
import com.handmade.cn.service.product.ProductService;


@Service
public class DianzanService extends BaseService<Dianzanlog, Integer>{
	
	@Autowired
	private DianzanlogMapper dianzanlogMapper;
	@Autowired
	private ProductService productService;

	@Override
	public BaseMapper<Dianzanlog, Integer> getDao() {
		return dianzanlogMapper;
	}

	@Override
	public int saveOrUpdate(Dianzanlog t) {
		if (t.getId()==null) {
			return dianzanlogMapper.insertSelective(t);
		}else {
			return dianzanlogMapper.updateByPrimaryKeySelective(t);
		}
	}
	
	public Dianzanlog findByProductId(Integer productId, String openid){
		return dianzanlogMapper.selectByProductId(productId, openid);
	}
	
	@Transactional
	public Boolean toPrise(ProductInfo productInfo,Integer producId,String openId){
		Boolean boolean1=productService.saveOrUpdateProduct(productInfo);
		Dianzanlog dianzanlog = new Dianzanlog();
		dianzanlog.setProductid(producId);
		dianzanlog.setOpenid(openId);
		Integer n=dianzanlogMapper.insertSelective(dianzanlog);
		if (boolean1 && n!=null) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
	
	

}
