package com.handmade.cn.action.material;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.material.MaterialInfoBean;
import com.handmade.cn.entity.material.MaterialInfo;
import com.handmade.cn.service.material.MaterialSerivice;
import com.handmade.cn.web.action.BaseController;
import com.handmade.cn.web.interceptor.AdminUserLogin;

@Controller
@RequestMapping("admin/material")
@AdminUserLogin
public class MaterialAction extends BaseController{
	
	@Autowired
	private MaterialSerivice materialSerivice;
	
	@RequestMapping("toChoseMaterial/p{pageNo}")
	public String toChoseMaterial(MaterialInfoBean materialInfoBean,Model model,@PathVariable Integer pageNo){
		materialInfoBean.setPageSize(5);
		materialInfoBean.setPageNo(pageNo);
		PageList<MaterialInfo> materialInfos = (PageList<MaterialInfo>) materialSerivice.query(materialInfoBean, materialInfoBean.toPageBounds());
		if (materialInfos!=null) {
			model.addAttribute("page", materialInfos.getPaginator());
		}
		model.addAttribute("materials", materialInfos);
		model.addAttribute("searchBean", materialInfoBean);
		return "admin/product/tochosematerial";
	}
	
	@ResponseBody
	@RequestMapping("toSearch/p{pageNo}")
	public Object toSearch(MaterialInfoBean materialInfoBean,Model model,@PathVariable Integer pageNo){
		materialInfoBean.setPageSize(15);
		materialInfoBean.setPageNo(pageNo);
		PageList<MaterialInfo> materialInfos = (PageList<MaterialInfo>) materialSerivice.query(materialInfoBean, materialInfoBean.toPageBounds());
		Map<String, Object> map= new HashMap<String, Object>();
		if (materialInfos!=null) {
			map.put("page", materialInfos.getPaginator());
		}
		map.put("materials", materialInfos);
		map.put("searchBean", materialInfoBean);
//		model.addAttribute("materials", materialInfos);
//		model.addAttribute("searchBean", materialInfoBean);
		return suc("查询成功", map);
	}
	
	@RequestMapping(value="list/p{pageNo}")
	public String list(@Valid MaterialInfo materialInfo, @PathVariable Integer pageNo, Model model){
		if(pageNo!=null){
			materialInfo.setPageNo(pageNo);
		}
		PageList<MaterialInfo> list = materialSerivice.query(materialInfo, materialInfo.toPageBounds());
		if(list!=null && !list.isEmpty()){
			model.addAttribute("page", list.getPaginator());
		}
		model.addAttribute("infos", list);
		return "admin/material/list";
	}
	
	@ResponseBody
	@RequestMapping(value="delete/{id}")
	public Boolean delete(@PathVariable Integer id){
		return materialSerivice.delete(id);
	}
	
	@RequestMapping(value="input/{id}")
	public String input(@PathVariable Integer id, Model model){
		model.addAttribute("info", materialSerivice.selectByPrimaryKey(id));
		return "admin/material/input";
	}
	
	@RequestMapping(value="add")
	public String input(Model model){
		return "admin/material/input";
	}
	
	@ResponseBody
	@RequestMapping(value="save")
	public Boolean saveOrUpdate(MaterialInfo pc){
		return materialSerivice.save(pc);
	}

}
