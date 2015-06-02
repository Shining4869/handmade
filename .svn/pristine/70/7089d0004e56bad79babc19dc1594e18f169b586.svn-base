package com.handmade.cn.action;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.handmade.cn.utils.JsonMapper;

public class Test {
	public static void main(String[] args) {
		System.out.println(getUserMoneyBySkyId("402053760"));
	}
	
	public static double getUserMoneyBySkyId(String skyId) {
		String url ="http://shop.xj.189.cn/service/CreditService";
		
		Service service = new Service();
		Call call;
		double money = 0.0;
		String res = "";
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("getCreditByPhone");// 这是要调用的方法
			res = (String) call.invoke(new Object[] { "8a81812b4ce9b074014cea243c5b0031", "15158126743" });
			System.out.println(res);
			System.out.println("xxxsxxx==="+ res);
			JsonMapper.readStringValueToMap(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return money;
	}

}
