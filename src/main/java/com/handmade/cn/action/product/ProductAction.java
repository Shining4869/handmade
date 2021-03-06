package com.handmade.cn.action.product;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.handmade.cn.bean.product.ProductInfoBean;
import com.handmade.cn.conf.AppConfig;
import com.handmade.cn.entity.dianzan.Dianzanlog;
import com.handmade.cn.entity.material.MaterialInfo;
import com.handmade.cn.entity.product.ProductCategory;
import com.handmade.cn.entity.product.ProductInfo;
import com.handmade.cn.service.dianzan.DianzanService;
import com.handmade.cn.service.material.MaterialSerivice;
import com.handmade.cn.service.product.ProductCategoryService;
import com.handmade.cn.service.product.ProductService;
import com.handmade.cn.service.user.UserInfoService;
import com.handmade.cn.utils.JsonMapper;
import com.handmade.cn.utils.ParamUtils;
import com.handmade.cn.utils.sign;
import com.handmade.cn.web.action.BaseController;

@Controller
@RequestMapping("admin/product")
//@AdminUserLogin
public class ProductAction extends BaseController{
	
	File tempPathFile;
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialSerivice materialSerivice;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private DianzanService dianzanService;
	
	@RequestMapping("toAddProduct")
	public String toAddProduct(Model model,HttpServletRequest request,String codeQuality){
//		System.out.println("length"+id.length);
		String materialName="";
//		for (int i = 0; i < id.length; i++) {
//			
//		}
//		model.addAttribute("materialName", materialName);
		Integer[] ids = ParamUtils.getIntegerParameters(request, "id", null);
		List<MaterialInfo> materials = materialSerivice.findByIds(ids);
		if (ParamUtils.getFilteredParameters(request, "id", 0, "") != null) {
			materialName+=JsonMapper.toJsonStr(ParamUtils
					.getFilteredParameters(request, "id", 0, ""));
		}
		System.out.println("Name:"+materialName);
		model.addAttribute("materialName",materialName);
		model.addAttribute("materials",materials);
		model.addAttribute("codeQuality", codeQuality);
		List<ProductCategory> productCategories= productCategoryService.findAll();
		Map<String, Object> maps = materialSerivice.findAllMaterials();
		model.addAttribute("types", productCategories);
		model.addAttribute("maps", maps);
		return "admin/product/toaddproduct";
	}
	
	@ResponseBody
	@RequestMapping("checkQuality")
	public Object checkQuality(Double[] more2,Double[] unitQuality,String codeQuality){
		Double preSum=Double.parseDouble(codeQuality);
		Double sum=0.0;
		if (more2.length>0&&unitQuality.length>0) {
			for (int i = 0; i < unitQuality.length; i++) {
				sum+=more2[i]*unitQuality[i];
			}
		}
		System.out.println("sum:"+sum);
		if (preSum*0.95<=sum&&sum<=preSum*1.05) {
			return suc("符合预计");
		}else {
			return fail("请检查配料数量，特别是单位质量较大的耗材");
		}
		
	}
	
	@RequestMapping("addProduct")
	public String addProduct(ProductInfo productInfo,HttpServletRequest request) throws Exception{
		List<String> more2s = new ArrayList<String>();
		Random r = new Random();
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(40960); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(10485760); // 设置最大文件尺寸，这里是4MB
		List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
		Iterator<FileItem> i = items.iterator();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			String fieldName = fi.getFieldName();
			if (fi.isFormField()) {  
		        //如果是普通表单字段  
	            String value = fi.getString();
	            if(fieldName.equals("more2")){
	            	more2s.add(new String(value.getBytes("ISO8859_1"), "utf-8"));
	            }
	            map.put(fieldName, new String(value.getBytes("ISO8859_1"), "utf-8"));
		    }else{
		    	File fullFile = new File(fi.getName());
				String fileNameNew = String.valueOf(r.nextInt(100000)+899999)+df.format(new Date())+fullFile.getName();
				File savedFile = new File(AppConfig.getAppMap().get("imagepath"), fileNameNew);
				fi.write(savedFile);
				if(fieldName.equals("more1") && fileNameNew.length()<=12){
					continue;
				}
				if(fieldName.equals("file_upload") && fileNameNew.length()>12){
					System.out.println(fileNameNew);
					list.add(fileNameNew);
				}else{
					map.put(fieldName, fileNameNew);
				}
		    }
		}
		
		productInfo = JsonMapper.readValue(map, ProductInfo.class);
		if(!list.isEmpty() && list.size()>0){
			productInfo.setImage(JsonMapper.toJsonStr(list));
		}
		if(!more2s.isEmpty() && more2s.size()>0){
			productInfo.setMore2(JsonMapper.toJsonStr(more2s));
		}
		
		productService.saveOrUpdateProduct(productInfo);
		return "redirect:/admin/productInfo/list/p1";//suc("保存成功");
	}
	
	@RequestMapping("toEditProduct/{id}")
	public String toEditProduct(Model model,HttpServletRequest request,@PathVariable Integer id){
		ProductInfo productInfo = productService.findById(id);
		List<ProductCategory> productCategories= productCategoryService.findAll();
		model.addAttribute("types", productCategories);
		model.addAttribute("productInfo",productInfo);
		String images=productInfo.getImage();
		String materials=productInfo.getMaterials();
		String more2=productInfo.getMore2();
		List<String> list=new ArrayList<String>();
		List<MaterialInfo> list2 = new ArrayList<MaterialInfo>();
		if(StringUtils.isNotEmpty(images)){
			images=images.replace("\"", "").replace("[", "").replace("]", "");
			String [] arr=images.split(",");
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].length()>0) {
					list.add(arr[i]);
				}
			}
			model.addAttribute("imagePaths", list);
		}
		if(StringUtils.isNotEmpty(more2)){
			more2=more2.replace("\"", "").replace("[", "").replace("]", "").replace("\\", "");
			String [] arr1=more2.split(",");
		
			if(StringUtils.isNotEmpty(materials)){
				materials=materials.replace("\"", "").replace("[", "").replace("]", "").replace("\\", "");
				String [] arr=materials.split(",");
				for (int i = 0; i < arr.length; i++) {
					if (arr[i].length()>0) {
						MaterialInfo materialInfo = materialSerivice.selectByPrimaryKey(Integer.parseInt(arr[i]));
						materialInfo.setMore3(arr1[i]);
						list2.add(materialInfo);
					}
				}
				model.addAttribute("materials", list2);
			}
		}
		return "admin/product/toeditproduct";
	}
	
	@ResponseBody
	@RequestMapping("deleteProduct")
	public Object deleteProduct(Integer id){
		Integer n=productService.deleteByPk(id);
		if (n==null) {
			return fail("删除失败");
		}else {
			return suc("删除成功");
		}
	}
	
	@RequestMapping(value="toProductDetail")
	public String redpacket(@RequestParam Integer id){
		String backUri = AppConfig.getAppMap().get("fruitLocation")+"/admin/product/addNum1";
		backUri += "?id="+id;
		backUri = URLEncoder.encode(backUri);
		// scope scope=snsapi_base 不弹出授权页面直接授权,只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppConfig.appid+"&redirect_uri="+backUri
				+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		return "redirect:" + url;
	}
	
	@RequestMapping(value="addNum1")
	public String touPiao(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String code = request.getParameter("code");
		String sid = request.getParameter("id");
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ AppConfig.appid + "&secret=" + AppConfig.secret + "&code=" + code
				+ "&grant_type=authorization_code";
		if (StringUtils.isNotEmpty(code)) {
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(URL);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			String resultStr = getMethod.getResponseBodyAsString();
			Map<Object, Object> map = (Map<Object, Object>)JsonMapper.readStringValueToMap(resultStr);
			String openId = (String)map.get("openid");
			if(StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(sid)){
				model.addAttribute("openId", openId);
				ProductInfo productInfo = productService.findById(Integer.parseInt(sid));
				String images=productInfo.getImage();
				List<String> list=new ArrayList<String>();
				images=images.replace("\"", "").replace("[", "").replace("]", "");
				String [] arr=images.split(",");
				for (int i = 0; i < arr.length; i++) {
					if (arr[i].length()>0) {
						list.add(arr[i]);
					}
				}
				List<MaterialInfo> materialInfos = new ArrayList<MaterialInfo>();
				String materialIds =productInfo.getMaterials();
				materialIds=materialIds.replace("\"", "").replace("[", "").replace("]", "").replace("\\", "");
				String [] arr2=materialIds.split(",");
				for (int i = 0; i < arr2.length; i++) {
					if (arr2[i].length()>0) {
						MaterialInfo materialInfo = materialSerivice.selectByPrimaryKey(Integer.parseInt(arr2[i]));
						materialInfos.add(materialInfo);
					}
				}
				model.addAttribute("images", list);
				model.addAttribute("materialInfos", materialInfos);
				model.addAttribute("productInfo", productInfo);
				String url = AppConfig.getAppMap().get("fruitLocation")+request.getRequestURI();
				String query = request.getQueryString();
				if(StringUtils.isNotEmpty(query)){
					url = url + "?" +query;
				}
				String ticket = userInfoService.findSignAllInfo();
				Map<String, String> ret = sign.sign(ticket, url);
		        for (Map.Entry entry : ret.entrySet()) {
		            System.out.println(entry.getKey() + ", " + entry.getValue());
		        }
				model.addAttribute("map", ret);
				return "handmade/pad/product-detail";
			}
		}
		return "handmade/pad/product-detail";
	}
	
	@RequestMapping(value="receivecode")
	public String redpacket1(){
		String backUri = AppConfig.getAppMap().get("fruitLocation")+"/admin/product/addNum";
		backUri = URLEncoder.encode(backUri);
		// scope scope=snsapi_base 不弹出授权页面直接授权,只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppConfig.appid+"&redirect_uri="+backUri
				+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		return "redirect:" + url;
	}
	
	@RequestMapping(value="addNum")
	public String touPiao1(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		String code = request.getParameter("code");
//		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
//				+ AppConfig.appid + "&secret=" + AppConfig.secret + "&code=" + code
//				+ "&grant_type=authorization_code";
//		if (StringUtils.isNotEmpty(code)) {
//			HttpClient httpClient = new HttpClient();
//			GetMethod getMethod = new GetMethod(URL);
//			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//			int statusCode = httpClient.executeMethod(getMethod);
//			if (statusCode != HttpStatus.SC_OK) {
//				System.err.println("Method failed: " + getMethod.getStatusLine());
//			}
//			String resultStr = getMethod.getResponseBodyAsString();
//			Map<Object, Object> map = (Map<Object, Object>)JsonMapper.readStringValueToMap(resultStr);
//			String openId = (String)map.get("openid");
//			if(StringUtils.isNotEmpty(openId)){
//				model.addAttribute("openId", openId);
				ProductInfoBean productInfoBean = new ProductInfoBean();
				productInfoBean.setPageNo(1);
				productInfoBean.setPageSize(200);
				model.addAttribute("page",2);
				PageList<ProductInfo> productInfos= productService.orderByGreatNum(productInfoBean);
				model.addAttribute("products", productInfos);
				String url = AppConfig.getAppMap().get("fruitLocation")+request.getRequestURI();
				String query = request.getQueryString();
				if(StringUtils.isNotEmpty(query)){
					url = url + "?" +query;
				}
				String ticket = userInfoService.findSignAllInfo();
				Map<String, String> ret = sign.sign(ticket, url);
		        for (Map.Entry entry : ret.entrySet()) {
		            System.out.println(entry.getKey() + ", " + entry.getValue());
		        }
				model.addAttribute("map", ret);
				return "handmade/pad/ranking";
//			}
//		}
//		return "handmade/pad/ranking";
	}
	
	@RequestMapping("rankOfProduct/p{pageNo}")
	public String rankOfProduct(ProductInfoBean productInfoBean,Model model,@PathVariable Integer pageNo) throws Exception{
		productInfoBean.setPageNo(pageNo);
		productInfoBean.setPageSize(200);
		model.addAttribute("page", pageNo+1);
		PageList<ProductInfo> productInfos= productService.orderByGreatNum(productInfoBean);
		model.addAttribute("products", productInfos);
		String url = AppConfig.getAppMap().get("fruitLocation")+request.getRequestURI();
		String query = request.getQueryString();
		if(StringUtils.isNotEmpty(query)){
			url = url + "?" +query;
		}
		String ticket = userInfoService.findSignAllInfo();
		Map<String, String> ret = sign.sign(ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
		model.addAttribute("map", ret);
		return "handmade/pad/ranking";
	}
	
	@ResponseBody
	@RequestMapping("loadMore/p{page}")
	public Object loadMore(ProductInfoBean productInfoBean,Model model,@PathVariable Integer page){
		productInfoBean.setPageNo(page);
		productInfoBean.setPageSize(8);
		PageList<ProductInfo> productInfos= productService.orderByGreatNum(productInfoBean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("products", productInfos);
		map.put("page", page+1);
		return suc("加载成功", map);
	}
	
	@RequestMapping("toProductDetail1")
	public String  toProductDetail(Integer id,Model model) throws Exception{
		ProductInfo productInfo = productService.findById(id);
		String images=productInfo.getImage();
		List<String> list=new ArrayList<String>();
		images=images.replace("\"", "").replace("[", "").replace("]", "");
		String [] arr=images.split(",");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length()>0) {
				list.add(arr[i]);
			}
		}
		List<MaterialInfo> materialInfos = new ArrayList<MaterialInfo>();
		String materialIds =productInfo.getMaterials();
		materialIds=materialIds.replace("\"", "").replace("[", "").replace("]", "").replace("\\", "");
		String [] arr2=materialIds.split(",");
		for (int i = 0; i < arr2.length; i++) {
			if (arr2[i].length()>0) {
				MaterialInfo materialInfo = materialSerivice.selectByPrimaryKey(Integer.parseInt(arr2[i]));
				materialInfos.add(materialInfo);
			}
		}
		model.addAttribute("images", list);
		model.addAttribute("materialInfos", materialInfos);
		model.addAttribute("productInfo", productInfo);
		String url = AppConfig.getAppMap().get("fruitLocation")+request.getRequestURI();
		String query = request.getQueryString();
		if(StringUtils.isNotEmpty(query)){
			url = url + "?" +query;
		}
		String ticket = userInfoService.findSignAllInfo();
		Map<String, String> ret = sign.sign(ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
		model.addAttribute("map", ret);
		return "handmade/pad/product-detail";
	}
	
	@ResponseBody
	@RequestMapping("toPraise")
	public Object toPraise(Integer id,String openId){
		ProductInfo productInfo = productService.selectByPrimaryKey(id);
		//openId =(String) request.getSession().getAttribute("openId");
		Dianzanlog dianzanlog= dianzanService.findByProductId(id, openId);
		if (dianzanlog!=null && openId.equals(dianzanlog.getOpenid())) {
			return fail("您已对该作品点赞过",null);
		}
		Integer n= productInfo.getGreatNum();
		productInfo.setGreatNum(n+1);
		Boolean flag=dianzanService.toPrise(productInfo,id,openId);
		if (flag) {
			return suc("点赞成功");
		}else {
			return fail("点赞失败");
		}
		
	}

}
