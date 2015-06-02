package com.handmade.cn.action;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.handmade.cn.conf.AppConfig;
import com.handmade.cn.entity.sys.Xinren;
import com.handmade.cn.service.XinrenService;
import com.handmade.cn.utils.JsonMapper;
/**
 * 
* @ClassName: XinrenAction
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiangjianlong-蒋建龙
* @date 2015年4月30日 上午1:11:14
*
 */
@Controller
@RequestMapping(value="xinren")
public class XinrenAction {
	@Autowired
	private XinrenService xinrenService;
	
	@RequestMapping(value="beforeindex")
	public String index(){
		return "xinren/beforeindex";
	}
	
	@RequestMapping(value="receivecode")
	public String redpacket(){
		String backUri = AppConfig.getAppMap().get("fruitLocation")+"/xinren/index";
		backUri = URLEncoder.encode(backUri);
		// scope scope=snsapi_base 不弹出授权页面直接授权,只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppConfig.appid+"&redirect_uri="+backUri
				+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		return "redirect:" + url;
	}
	
	@RequestMapping(value="index")
	public String goback(HttpServletRequest request, Model model){
		String code = request.getParameter("code");
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ AppConfig.appid + "&secret=" + AppConfig.secret + "&code=" + code
				+ "&grant_type=authorization_code";
		if (StringUtils.isNotEmpty(code)) {
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(URL);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			try {
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + getMethod.getStatusLine());
				}
				String resultStr = getMethod.getResponseBodyAsString();
				Map<Object, Object> map = (Map<Object, Object>)JsonMapper.readStringValueToMap(resultStr);
				String openId = (String)map.get("openid");
				Xinren xinren = xinrenService.findByOpenId(openId);
				if(xinren!=null){
					return "xinren/justone";
				}else{
					Random random = new Random();
					int x = random.nextInt(99);
					Integer amount = xinrenService.selectCount();
					if(amount==null || amount.intValue() <=10000){
						if(x < 30){
							model.addAttribute("amount", 5);
						}else if(x < 60){
							model.addAttribute("amount", 2);
						}else if(x < 70){
							model.addAttribute("amount", 1);
						}else{
							return "xinren/fail";
						}
						model.addAttribute("openid", openId);
						return "xinren/form";
					}else{
						Xinren xr = new Xinren();
						xr.setOpenid(openId);
						xinrenService.saveOrUpdate(xr);
						return "xinren/end";
					}
				}
			} catch (Exception e) {
			}
		}
		return "xinren/fail";
	}
	
	@RequestMapping(value="result")
	public String success(){
		return "xinren/form";
	}
	
	@RequestMapping(value="justone")
	public String justone(){
		return "xinren/fail";
	}
	
	@ResponseBody
	@RequestMapping(value="save")
	public Boolean saveOrUpdate(Xinren xinren){
		return xinrenService.saveOrUpdate(xinren);
	}
}
