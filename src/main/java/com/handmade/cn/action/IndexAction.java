package com.handmade.cn.action;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.handmade.cn.conf.AppConfig;
import com.handmade.cn.entity.sys.LhRedOrder;
import com.handmade.cn.service.LhRedOrderService;
import com.handmade.cn.utils.JsonMapper;

/**
 * @author zgdcool
 * @version 2014年10月18日 下午1:00:49
 */
@Controller
public class IndexAction {
	@Autowired
	private LhRedOrderService lhRedOrderService;
	
	@RequestMapping(value="sample/{code}")
	public String sample(@PathVariable String code){
		return "sample/"+code;
	}
	
	@RequestMapping(value="redpacket")
	public String redpacket(){
		String backUri = AppConfig.getAppMap().get("fruitLocation")+"/goback";
		backUri = URLEncoder.encode(backUri);
		// scope scope=snsapi_base 不弹出授权页面直接授权,只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppConfig.appid+"&redirect_uri="+backUri
				+ "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		return "redirect:" + url;
	}
	
	@RequestMapping(value="goback")
	public String goback(HttpServletRequest request, Model model){
		String code = request.getParameter("code");
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ AppConfig.appid + "&secret=" + AppConfig.secret + "&code=" + code
				+ "&grant_type=authorization_code";
		System.out.println("code----------------"+code);
		if (StringUtils.isNotEmpty(code)) {
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(URL);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			try {
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: "
							+ getMethod.getStatusLine());
				}
				String resultStr = getMethod.getResponseBodyAsString();
				Map<Object, Object> map = (Map<Object, Object>)JsonMapper.readStringValueToMap(resultStr);
				String openId = (String)map.get("openid");
				System.out.println("map----------------"+map.toString());
				LhRedOrder order = lhRedOrderService.findByOpenId(openId);
				if(order!=null){
					if(order.getPayTime()!=null){
						model.addAttribute("orderCode", order.getOrderCode());
						return "last";
					}else{
						model.addAttribute("orderId", order.getOrderId());
						model.addAttribute("isGame", isGame(order.getProject()));
						return "game";
					}
				}else{
					model.addAttribute("openId", openId);
					return "first";
				}
			} catch (Exception e) {
			}
		}
		return "first";
	}
	
	@RequestMapping(value="startgame")
	public String startGame(LhRedOrder order, Model model){
		lhRedOrderService.saveOrUpdate(order);
		model.addAttribute("isGame", isGame(order.getProject()));
		model.addAttribute("orderId", order.getOrderId());
		return "game";
	}
	
	@RequestMapping(value="last")
	public String last(String orderCode, Model model){
		model.addAttribute("orderCode", orderCode);
		return "last";
	}
	
	@RequestMapping(value="fist")
	public String test(){
		return "first";
	}
	
	public Boolean isGame(String project){
		if(project.equals("春漫里") || project.equals("柳映坊") || project.equals("堂前") || project.equals("秋荷坊") || project.equals("探梅里") || project.equals("阳光天际")){
			Integer count = lhRedOrderService.queryCountByProjectSpa();
			if(count!=null && count.intValue() > 100){
				return Boolean.FALSE;
			}else{
				return Boolean.TRUE;
			}
		}else if(project.equals("七贤郡")){
			Integer count = lhRedOrderService.queryCountByProject("七贤郡");
			if(count!=null && count.intValue() > 150){
				return Boolean.FALSE;
			}else{
				return Boolean.TRUE;
			}
		}else if(project.equals("未来城")){
			Integer count = lhRedOrderService.queryCountByProject("未来城");
			if(count!=null && count.intValue() > 100){
				return Boolean.FALSE;
			}else{
				return Boolean.TRUE;
			}
		}else if(project.equals("北宸之光")){
			Integer count = lhRedOrderService.queryCountByProject("北宸之光");
			if(count!=null && count.intValue() > 100){
				return Boolean.FALSE;
			}else{
				return Boolean.TRUE;
			}
		}else if(project.equals("西庐")){
			Integer count = lhRedOrderService.queryCountByProject("西庐");
			if(count!=null && count.intValue() > 100){
				return Boolean.FALSE;
			}else{
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}
