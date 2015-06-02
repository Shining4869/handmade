package com.handmade.cn.action.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.product.ProductInfoBean;
import com.handmade.cn.entity.product.ProductCategory;
import com.handmade.cn.entity.product.ProductInfo;
import com.handmade.cn.service.material.MaterialSerivice;
import com.handmade.cn.service.product.ProductCategoryService;
import com.handmade.cn.service.product.ProductService;
import com.handmade.cn.utils.JsonMapper;
import com.handmade.cn.utils.ParamUtils;
import com.handmade.cn.web.action.BaseController;
import com.handmade.cn.web.interceptor.AdminUserLogin;

@Controller
@RequestMapping("app/product")
//@AdminUserLogin
public class ProductAppAction extends BaseController{
	
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialSerivice materialSerivice;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	@ResponseBody
	@RequestMapping("toProductDetail")
	public Object  toProductDetail(Integer id,Model model){
		ProductInfo productInfo = productService.selectByPrimaryKey(id);
		String images=productInfo.getImage();
		List<String> list=new ArrayList<String>();
		images=images.replace("\"", "").replace("[", "").replace("]", "");
		String [] arr=images.split(",");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length()>0) {
				list.add(arr[i]);
			}
		}
		List<Object> objects = new ArrayList<Object>();
		objects.add(productInfo);
		objects.add(list);
		return suc("查询成功",productInfo);
	}
	
	
	@ResponseBody
	@RequestMapping("rankOfProduct")
	public Object rankOfProduct(ProductInfoBean productInfoBean){
		PageList<ProductInfo> productInfos= productService.orderByGreatNum(productInfoBean);
		return suc("查询成功", productInfos);
	}
	
	@ResponseBody
	@RequestMapping("toPraise")
	public Object toPraise(Integer id){
		ProductInfo productInfo = productService.selectByPrimaryKey(id);
		productInfo.setGreatNum(productInfo.getGreatNum()+1);
		productService.saveOrUpdate(productInfo);
		return suc("点赞成功", productInfo.getGreatNum());
	}
	

}
