package com.handmade.cn.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

// 动态构造调用串，灵活性更大
public class StaticHttpClientCall {

    private String namespace;
    private String methodName;
    private String wsdlLocation;
    private String soapResponseData;

    public StaticHttpClientCall(String namespace, String methodName,
            String wsdlLocation) {

        this.namespace = namespace;
        this.methodName = methodName;
        this.wsdlLocation = wsdlLocation;
    }

    private int invoke(Map<String, String> patameterMap) throws Exception {
        PostMethod postMethod = new PostMethod(wsdlLocation);
        String soapRequestData = buildRequestData(patameterMap);

        byte[] bytes = soapRequestData.getBytes("utf-8");
        InputStream inputStream = new ByteArrayInputStream(bytes, 0,
                bytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                bytes.length, "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(requestEntity);

        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        soapResponseData = postMethod.getResponseBodyAsString();
        return statusCode;
    }

    private String buildRequestData(Map<String, String> patameterMap) {
        StringBuffer soapRequestData = new StringBuffer();
        soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        soapRequestData
                .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                        + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
                        + " xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
        soapRequestData.append("<soap12:Body>");
        soapRequestData.append("<" + methodName + " xmlns=\"" + namespace
                + "\">");
        soapRequestData.append("<" + methodName + "Request>");

        Set<String> nameSet = patameterMap.keySet();
        for (String name : nameSet) {
            soapRequestData.append("<" + name + ">" + patameterMap.get(name)
                    + "</" + name + ">");
        }
        
        soapRequestData.append("</" + methodName + "Request>");
        soapRequestData.append("</" + methodName + ">");
        soapRequestData.append("</soap12:Body>");
        soapRequestData.append("</soap12:Envelope>");

        return soapRequestData.toString();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        StaticHttpClientCall dynamicHttpclientCall = new StaticHttpClientCall(
                "http://webservice.mas.lenovo.com", "getCoupons",
                "http://shop.xj.189.cn/service/CouponsApiService?wsdl");

        Map<String, String> patameterMap = new HashMap<String, String>();

        patameterMap.put("servId", "8a81812b4ce9b074014cea243c5b0031");
        patameterMap.put("phone", "15158125032");
        patameterMap.put("couponId", "8a81812b4ceaff4a014cee86c09c002e");
        patameterMap.put("remark", "散桃花游戏");

        String soapRequestData = dynamicHttpclientCall.buildRequestData(patameterMap);
        System.out.println(soapRequestData);

        int statusCode = dynamicHttpclientCall.invoke(patameterMap);
        if(statusCode == 200) {
            System.out.println("调用成功！");
            System.out.println(dynamicHttpclientCall.soapResponseData);
        }
        else {
            System.out.println("调用失败！错误码：" + statusCode);
        }
        
    }

}