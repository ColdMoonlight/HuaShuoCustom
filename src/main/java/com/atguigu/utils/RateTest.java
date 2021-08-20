package com.atguigu.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.utils.HttpUtils;

public class RateTest {

	public static void main(String[] args) {
	    String host = "https://jisuhuilv.market.alicloudapi.com";
	    String path = "/exchange/convert";
	    String method = "GET";
	    String appcode = "cfecc4cf51c64bad8b403f94e13aef00";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("amount", "1");
	    querys.put("from", "USD");	//美元USD欧元EUR英镑GBP人民CNY
	    querys.put("to", "CNY");

	    try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	
	    	String rate =EntityUtils.toString(response.getEntity());

	    	JSONObject object = (JSONObject) JSONObject.parse(rate);
	    	System.out.println(object);

	    	System.out.println(object.getJSONObject("result").get("camount"));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
}
