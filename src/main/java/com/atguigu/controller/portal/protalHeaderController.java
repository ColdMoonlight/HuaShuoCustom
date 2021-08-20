package com.atguigu.controller.portal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.common.Msg;
import com.atguigu.utils.HttpUtils;
import com.atguigu.vo.MoneyRate;

@Controller
@RequestMapping("/protalHeader")
public class protalHeaderController {
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/getMoneyRate",method=RequestMethod.GET)
	@ResponseBody
	public Msg getMoneyRate(HttpSession session) throws Exception{
		
		String host = "https://jisuhuilv.market.alicloudapi.com";
	    String path = "/exchange/convert";
	    String method = "GET";
	    String appcode = "cfecc4cf51c64bad8b403f94e13aef00";
	    String fromCode = "USD";
	    String toCode = "EUR";
	    String rate ="";//返回汇率
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("amount", "1");
	    querys.put("from", fromCode);	//美元USD欧元EUR英镑GBP人民CNY
	    querys.put("to", toCode);
	    try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println("response :" + response.toString());
	    	//获取response的body
	    	String rateResult =EntityUtils.toString(response.getEntity());
	    	
	    	JSONObject object = (JSONObject) JSONObject.parse(rateResult);
	    	System.out.println(object);
	    	
	    	rate = object.getJSONObject("result").get("camount").toString();
	    	System.out.println(rate);
	    } catch (Exception e) {
	    	//e.printStackTrace();
	    }
	    return Msg.success().add("rate", rate);
	    
	   /* List<MoneyRate> moneyRateList = new ArrayList<MoneyRate>();
	    
	    BigDecimal rateZero = new BigDecimal(0);	//初始化最终价格参数
		DecimalFormat rateFinal = new DecimalFormat("0.00");
	    String arr[] = {"EUR","GBP"};
	    moneyRateList.add(new MoneyRate("USD","1"));
	    for(int i=0;i<arr.length;i++){
	    	
	    	System.out.println("-----------USD-----to-----"+arr[i]+"-----------");
	    	String toCodeRate = "";
	    	
	    	String toCode = arr[i];
		    querys.put("to", toCode);
		    try {
		    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
		    	
		    	String rate =EntityUtils.toString(response.getEntity());

		    	JSONObject object = (JSONObject) JSONObject.parse(rate);

		    	rateZero=(BigDecimal) object.getJSONObject("result").get("camount");
		    	
		    	toCodeRate = rateFinal.format(rateZero);
		    	System.out.println("toCode:"+toCode+"toCodeRate:"+toCodeRate);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    
		    MoneyRate moneyRate = new MoneyRate();
		    moneyRate.setMoneyRateCode(toCode);
		    moneyRate.setMoneyRate(toCodeRate);
		    moneyRateList.add(moneyRate);
	    }*/
	}
}
