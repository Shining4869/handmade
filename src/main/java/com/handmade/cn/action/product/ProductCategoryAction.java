package com.handmade.cn.action.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.entity.product.ProductCategory;
import com.handmade.cn.service.product.ProductCategoryService;
import com.handmade.cn.web.action.BaseController;
import com.handmade.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年5月17日 下午7:36:34
 *   
 */
@Controller
@AdminUserLogin
@RequestMapping(value="admin/procate")
public class ProductCategoryAction extends BaseController{
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value="list/p{pageNo}")
	public String list(@Valid ProductCategory bean, Model model, @PathVariable Integer pageNo){
		if(pageNo!=null){
			bean.setPageNo(pageNo);
		}
		PageList<ProductCategory> list = productCategoryService.query(bean, bean.toPageBounds());
		if(list!=null && !list.isEmpty()){
			model.addAttribute("page", list.getPaginator());
		}
		model.addAttribute("infos", list);
		return "admin/product/pc-list";
	}
	
	@ResponseBody
	@RequestMapping(value="save")
	public Object saveOrUpdate(ProductCategory pc){
		if(productCategoryService.save(pc)){
			return suc("保存成功");
		}else {
			return fail("保存失败");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="delete/{id}")
	public Boolean delete(@PathVariable Integer id){
		return productCategoryService.delete(id);
	}

}
