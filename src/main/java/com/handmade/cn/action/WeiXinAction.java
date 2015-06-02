package com.handmade.cn.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.handmade.cn.conf.AppConfig;
import com.handmade.cn.entity.sys.LhRedOrder;
import com.handmade.cn.service.LhRedOrderService;
import com.handmade.cn.service.SequenceService;
import com.handmade.cn.utils.JsonMapper;
import com.handmade.cn.utils.weixin.GetWxOrderno;
import com.handmade.cn.utils.weixin.RequestHandler;
import com.handmade.cn.utils.weixin.Sha1Util;
import com.handmade.cn.utils.weixin.TenpayUtil;

/**
 * @author zgdcool
 * @version 2015年2月5日 下午7:47:19
 * 
 */
@Controller
@RequestMapping(value = "weixin")
public class WeiXinAction {
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private LhRedOrderService lhRedOrderService;
	
	@RequestMapping(value = "testPay")
	public String testPay() {
		return "index";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "sendfirst")
	public String sendFirst(@RequestParam Integer orderId) {
		String backUri = AppConfig.getAppMap().get("fruitLocation")+"/weixin/receivecode";
		LhRedOrder order = lhRedOrderService.findOne(orderId);
		backUri = backUri + "?orderId=" + order.getOrderId();
		// URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		// scope scope=snsapi_base 不弹出授权页面直接授权,只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppConfig.appid+"&redirect_uri="+backUri
				+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		return "redirect:" + url;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "receivecode")
	public void receiveCode(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 网页授权后获取传递的参数
		String orderId = request.getParameter("orderId");
		LhRedOrder order = lhRedOrderService.findOne(Integer.parseInt(orderId));
		double sessionmoney = 0d;
		sessionmoney = 1;
		String code = request.getParameter("code");
		// 金额转化为分为单位
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		// 商户相关资料
		String openId = "";
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ AppConfig.appid + "&secret=" + AppConfig.secret + "&code=" + code
				+ "&grant_type=authorization_code";
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
				openId = (String)map.get("openid");
//				order.setOpenId(openId);
				lhRedOrderService.saveOrUpdate(order);
				
			} catch (Exception e) {
			}
		}
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		// 商户号
		String mch_id = AppConfig.partner;
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
//		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;
		// 商品描述根据情况修改
		String body = "redPacket";
		// 附加数据
		String attach = "others";
		// 商户订单号
		String out_trade_no = order.getOrderCode();
		int intMoney = Integer.parseInt(finalmoney);
		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = request.getRemoteAddr();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = AppConfig.getAppMap().get("fruitLocation")+"/weixin/urlnotify";
		String trade_type = "JSAPI";
		String openid = openId;
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", AppConfig.appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", String.valueOf(total_fee));
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(AppConfig.appid, AppConfig.secret, AppConfig.partnerkey);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + 
				"<appid>"+AppConfig.appid+"</appid>"+
				"<mch_id>"+mch_id+"</mch_id>"+
				"<nonce_str>"+nonce_str+"</nonce_str>"+
				"<sign>"+sign+"</sign>"+
				"<body><![CDATA["+body+"]]></body>"+
				"<attach>"+attach+"</attach>"+
				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
				//金额，这里写的1 分到时修改
				"<total_fee>"+total_fee+"</total_fee>"+
				//"<total_fee>"+finalmoney+"</total_fee>"+
				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
				"<notify_url>"+notify_url+"</notify_url>"+
				"<trade_type>"+trade_type+"</trade_type>"+
				"<openid>"+openid+"</openid>"+
				"</xml>";
		System.out.println(xml);
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (StringUtils.isEmpty(prepay_id)) {
				request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
				response.sendRedirect("/redpacket/");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = AppConfig.appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("appid", appid2);
		sParaTemp.put("timeStamp", timestamp);
		sParaTemp.put("nonceStr", nonceStr2);
		sParaTemp.put("package", packages);
		sParaTemp.put("sign", finalsign);
		String sHtmlText = buildRequest(sParaTemp, out_trade_no, total_fee);
		response.setContentType("text/html"); 
		response.setCharacterEncoding("gbk");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(sHtmlText);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	 /**
   * 建立请求，以表单HTML形式构造（默认）
   */
  public static String buildRequest(Map<String, String> sParaTemp, String out_trade_no, int total_fee) {
      //---------------------
      StringBuffer sbHtml = new StringBuffer();
      sbHtml.append("<title>微信安全支付</title>");
      sbHtml.append("<script>");
      sbHtml.append("document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {");
      sbHtml.append("WeixinJSBridge.invoke('getBrandWCPayRequest',{");
      sbHtml.append("'appId' : '"+sParaTemp.get("appid")+"','timeStamp' : '"+sParaTemp.get("timeStamp")+"', 'nonceStr' : '"+sParaTemp.get("nonceStr")+"', 'package' : '"+sParaTemp.get("package")+"','signType' : 'MD5', 'paySign' : '"+sParaTemp.get("sign")+"'");
      sbHtml.append("},function(res){");
      sbHtml.append("WeixinJSBridge.log(res.err_msg);");
      sbHtml.append("if(res.err_msg == 'get_brand_wcpay_request:ok'){");
	  sbHtml.append("alert('微信支付成功!'); window.location.href='notify?out_trade_no="+out_trade_no+"'");
      sbHtml.append("}else if(res.err_msg == 'get_brand_wcpay_request:cancel'){  ");
      sbHtml.append("alert('用户取消支付!');window.location.href='/redpacket/'");
      sbHtml.append("}else{alert('支付失败!'+res.err_msg);window.location.href='/redpacket/'}");
      sbHtml.append("});");
      sbHtml.append("}, false);");
      sbHtml.append("</script>");
      return sbHtml.toString();
  } 
	
	@RequestMapping(value="notify")
	public String weixinnotify(HttpServletRequest request) throws UnsupportedEncodingException{
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		LhRedOrder order = lhRedOrderService.findByOrderCode(out_trade_no);
		order.setOrderCode(out_trade_no);
		order.setPayStatus("pay_ok");
		order.setPayTime(new Date());
		lhRedOrderService.saveOrUpdate(order);
		return "redirect:/last/?orderCode="+out_trade_no;	
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="urlnotify")
	public Map<String, Object> urlnotify(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        System.out.println("-------------weixincanshu--------------"+sb.toString());
        Map resultMap = GetWxOrderno.doXMLParse(sb.toString());
		String return_code = (String) resultMap.get("return_code");
		String result_code = (String) resultMap.get("result_code");
		if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
			String out_trade_no = (String) resultMap.get("out_trade_no");
			LhRedOrder order = lhRedOrderService.findByOrderCode(out_trade_no);
			order.setOrderCode(out_trade_no);
			order.setPayStatus("pay_ok");
			order.setPayTime(new Date());
			lhRedOrderService.saveOrUpdate(order);
			map.put("return_code", "SUCCESS");
			return JsonMapper.beanToMap(map);
		}else{
			map.put("return_code", "FAIL");
			return JsonMapper.beanToMap(map);
		}
	}
	
}
