package com.handmade.cn.action.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.conf.DataConfig;
import com.handmade.cn.entity.admin.AdminInfo;
import com.handmade.cn.entity.product.ProductCategory;
import com.handmade.cn.entity.product.ProductInfo;
import com.handmade.cn.entity.user.UserInfo;
import com.handmade.cn.service.admin.AdminService;
import com.handmade.cn.service.material.MaterialSerivice;
import com.handmade.cn.service.product.ProductCategoryService;
import com.handmade.cn.service.product.ProductService;
import com.handmade.cn.service.user.UserInfoService;
import com.handmade.cn.utils.PasswordUtils;
import com.handmade.cn.web.action.BaseController;
import com.handmade.cn.web.interceptor.AdminUserLogin;

@Controller
@RequestMapping("admin")
public class AdminAction extends BaseController{
	

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialSerivice materialSerivice;
	@Autowired
	private ProductCategoryService productCategoryService;

	
	@RequestMapping("login")
	public String toLogin(){
		return "admin/login/login";
	}
	
	@ResponseBody
	@RequestMapping("loginIn")
	public Object loginIn( Model model, String username, String password, String yzm ) {
		AdminInfo adminInfo =adminService.findByName(username);
		if (StringUtils.isEmpty(yzm)
				|| !yzm.equalsIgnoreCase(String.valueOf(request.getSession().getAttribute("yanzhengma")))) {
			return fail("验证码错误");
		}
		if (adminInfo != null) {
			if (PasswordUtils.validPassword(adminInfo.getPassword(), password)) {
				request.getSession().setAttribute(DataConfig.SESSION_ADMIN_USER, adminInfo);
				request.getSession().removeAttribute("yanzhengma");
				return suc("登录成功",null);
			} else {
				return fail("密码错误！");
			}
		} else {
			return fail("用户名不存在！");
		}
	}
	
	@AdminUserLogin
	@RequestMapping("/logout")
	public String shopLogout(){
		request.getSession().removeAttribute(DataConfig.SESSION_ADMIN_USER);
		return "admin/login/login";
	}
	
	@AdminUserLogin
	@RequestMapping("userInfo/list/p{pageNo}")
	public String userInfoList(@Valid UserInfo userInfo,Model model,@PathVariable Integer pageNo){
		userInfo.setPageNo(pageNo);
		userInfo.setPageSize(5);
		PageList<UserInfo> userInfos = userInfoService.query(userInfo, userInfo.toPageBounds());
		if (userInfos != null && !userInfos.isEmpty()) {
			model.addAttribute("page", userInfos.getPaginator());
		}
		model.addAttribute("userInfos", userInfos);
		model.addAttribute("searchBean", userInfo);
		return "admin/userinfo/userinfo-list";
	}
	
	
	@ResponseBody
	@AdminUserLogin
	@RequestMapping("userInfo/saveOrUpdateUser")
	public Object saveOrUpdateUser(UserInfo userInfo,String FTime,String STime,String firAp,String secAp){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (FTime!=null && FTime!= "") {
			try {
				userInfo.setFirstTime(format.parse(FTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (STime!=null && STime !="") {
			try {
				userInfo.setSecondTime(format.parse(STime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		userInfo.setFirstAp(firAp);
		userInfo.setSecondAp(secAp);
		if (userInfoService.isSaveOrUpdate(userInfo)) {
			return suc("保存成功");
		}else {
			return fail("保存失败");
		}
	}
	
//	@AdminUserLogin
	@RequestMapping("productInfo/list/p{pageNo}")
	public String productList(@Valid ProductInfo productInfo,Model model,@PathVariable Integer pageNo){
		productInfo.setPageNo(pageNo);
		productInfo.setPageSize(5);
		PageList<ProductInfo> productInfos = productService.query(productInfo, productInfo.toPageBounds());
		if (productInfos != null && !productInfos.isEmpty()) {
			model.addAttribute("page", productInfos.getPaginator());
		}
		List<ProductCategory> productCategories= productCategoryService.findAll();
		model.addAttribute("types", productCategories);
		model.addAttribute("productInfos", productInfos);
		model.addAttribute("searchBean", productInfo);
		return "admin/product/product-list";
	}
	
	@ResponseBody
	@RequestMapping("findByPassword")
	public Object findByPassword(String more1){
		AdminInfo adminInfo =adminService.findByMore1(more1);
		if (adminInfo == null) {
			return fail("查询失败");
		}else {
			return suc("查询成功");
		}
	}
	
//	@AdminUserLogin
//	@RequestMapping("material/list/p{pageNo}")
//	public String materialList(@Valid MaterialInfo materialInfo,Model model,@PathVariable Integer pageNo){
//		materialInfo.setPageNo(pageNo);
//		materialInfo.setPageSize(5);
//		PageList<MaterialInfo> materialInfos = materialSerivice.query(materialInfo, materialInfo.toPageBounds());
//		if (materialInfos != null && !materialInfos.isEmpty()) {
//			model.addAttribute("page", materialInfos.getPaginator());
//		}
//		model.addAttribute("materialInfos", materialInfos);
//		model.addAttribute("searchBean", materialInfo);
//		return "admin/material/material-list";
//	}

}
