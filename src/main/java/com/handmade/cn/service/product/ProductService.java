package com.handmade.cn.service.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.product.ProductInfoBean;
import com.handmade.cn.entity.product.ProductInfo;
import com.handmade.cn.mapper.BaseMapper;
import com.handmade.cn.mapper.product.ProductInfoMapper;
import com.handmade.cn.service.BaseService;

@Service
public class ProductService extends BaseService<ProductInfo, Integer>{
	
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public BaseMapper<ProductInfo, Integer> getDao() {
		return productInfoMapper;
	}

	public Boolean saveOrUpdateProduct(ProductInfo t) {
		Integer flag=-1;
		if (t.getId()==null) {
			t.setCreateTime(new Date());
			t.setGreatNum(0);
			flag=productInfoMapper.insertSelective(t);
		}else {
			t.setUpdateTime(new Date());
			flag=productInfoMapper.updateByPrimaryKeySelective(t);
		}
		if (flag != -1 && flag!=null) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
	
	public List<String> findAllTypes(){
		return productInfoMapper.findAllType();
	}

	@Override
	public int saveOrUpdate(ProductInfo t) {
		if (t.getId()==null) {
			return productInfoMapper.insertSelective(t);
		}else {
			return productInfoMapper.updateByPrimaryKeySelective(t);
		}
	}
	
	public ProductInfo findById(Integer id){
		ProductInfo productInfo =productInfoMapper.selectByPrimaryKey(id);
		productInfo.setContent(doEditor(productInfo.getContent()));
		productInfo.setMaterials(doEditor(productInfo.getMaterials()));
		return productInfo;
	}
	
	public PageList<ProductInfo> orderByGreatNum(ProductInfoBean productInfoBean){
		return productInfoMapper.orderByGreatNum(productInfoBean, productInfoBean.toPageBounds());
	}
	
	protected String doEditor(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return StringEscapeUtils.unescapeHtml3(str).replace("\n", "")
					.replace("\"", "\\\"")
					.replace("\t", "").replace("\r", "");
		}
		return str;
	}

	

}
