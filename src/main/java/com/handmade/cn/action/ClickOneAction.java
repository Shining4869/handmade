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

import com.handmade.cn.conf.AppConfig;
import com.handmade.cn.entity.sys.ClickOne;
import com.handmade.cn.service.ClickOneService;
import com.handmade.cn.utils.JsonMapper;

/**
 * @author zgdcool
 * @version 2015年2月17日 下午10:35:31
 *   
 */
@Controller
@RequestMapping(value="clickone")
public class ClickOneAction {
	@Autowired
	private ClickOneService clickOneService;
	
	@RequestMapping(value="beforeindex")
	public String beforeindex(String zfdesc, String fromuser, Model model){
		model.addAttribute("zfdesc", zfdesc);
		model.addAttribute("fromuser", fromuser);
		return "click/beforeindex";
	}

	@RequestMapping(value="receivecode")
	public String redpacket(){
		String backUri = AppConfig.getAppMap().get("fruitLocation")+"/clickone/index";
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
				ClickOne clickOne = clickOneService.findByOpenId(openId);
				if(clickOne!=null){
					return "click/justone";
				}else{
					Random random = new Random();
					int x = random.nextInt(99);
					if(x==19){
						model.addAttribute("openId", openId);
						return "click/form";
					}else{
						ClickOne one = new ClickOne();
						one.setOpenid(openId);
						clickOneService.saveOrUpdate(one);
						return "click/fail";
					}
				}
			} catch (Exception e) {
			}
		}
		return "redirect:receivecode";
	}
	
	@RequestMapping(value="save")
	public String saveOrUpdate(ClickOne clickOne){
		clickOneService.saveOrUpdate(clickOne);
		return "click/success";
	}
	
	@RequestMapping(value="gameresult")
	public String gameresult(){
		if(clickOneService.selectCount()>10){
			return "click/fail";
		}
		Random random = new Random();
		int x = random.nextInt(99);
		if(x==19){
			return "click/form";
		}else{
			return "click/fail";
		}
	}
	
	@RequestMapping(value="gettoken")
	public String getToken(Model model){
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("https://api.weixin.qq.com/cgi-bin/token?"
				+ "grant_type=client_credential&appid="+AppConfig.appid+"&secret="+AppConfig.secret);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			String resultStr = getMethod.getResponseBodyAsString();
			Map<Object, Object> map = (Map<Object, Object>)JsonMapper.readStringValueToMap(resultStr);
			String access_token = (String)map.get("access_token");
			return "";
		} catch (Exception e) {
		}
		return "";
	}
	
	public static void main(String[] args) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("https://api.weixin.qq.com/cgi-bin/token?"
				+ "grant_type=client_credential&appid=wxacf21161e3c578f1&secret=a9edccfb3018342657334dabd8873357");
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			String resultStr = getMethod.getResponseBodyAsString();
			Map<Object, Object> map = (Map<Object, Object>)JsonMapper.readStringValueToMap(resultStr);
			String access_token = (String)map.get("access_token");
			System.out.println(access_token);
		} catch (Exception e) {
		}
	}
}
