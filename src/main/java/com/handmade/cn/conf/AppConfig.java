package com.handmade.cn.conf;

import java.util.HashMap;
import java.util.Map;

import org.springside.modules.utils.SpringContextHolder;

public class AppConfig {

	public static Map<String, String> appMap;
	
	private static Map<Integer, String> countryMobileMap ;

	static {
		appMap = SpringContextHolder.getBean("appConfig");
		if (appMap != null) {
			initConfig();
		}
		
		countryMobileMap = new HashMap<Integer, String>();
		countryMobileMap.put(1,"0044");//英国
		countryMobileMap.put(62,"00351");//葡萄牙
		countryMobileMap.put(104,"0034");//西班牙
		countryMobileMap.put(178,"0030");//希腊
		countryMobileMap.put(134,"0039"); //意大利
		
	}
	
	public static String appid = "wx156ab8abe9339584";
	public static String secret = "3beb6c0eee0983cf14bcd5532f971f7f";
	public static String partner = "1226923702";
	public static String partnerkey = "Zhy781525073AsDfGh12ZgdZxCvBnMlK";
	
	public static String getCountryMobileNo(Integer country){
		if(country == null){
			return null;
		}
		return countryMobileMap.get(country);
	}

	private static void initConfig() {
		//�????
	}

	public static Map<String, String> getAppMap() {
		return appMap;
	}

	public static void setAppMap(Map<String, String> appMap) {
		AppConfig.appMap = appMap;
	}

	public static String getConfig(String code) {
		return appMap.get(code);
	}

	public static void putConfig(String code, String value) {
		appMap.put(code, value);
	}

}
