package com.atguigu.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.vo.EcppTrackItem;

public class EcppUpdateWebStatusUtil {
	
	public static String getXML(String token, String order_sn) {
		
		String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+"<soap:Body>"
				    +"<queryOrder xmlns=\"http://s24.vekinerp.com/ecpp_server.php/\">"
				    	+"<token>"+token+"</token>"
				    	+"<order_sn>"+order_sn+"</order_sn>"
				    +"</queryOrder>"
				  +"</soap:Body>"
				+"</soap:Envelope>";
		System.out.println("---准备查询单条EcppNum状态的报文---soapXML------");
		System.out.println(soapXML);
		System.out.println("---准备查询单条EcppNum状态的报文---soapXML------");
		return soapXML;
	}

	public static EcppTrackItem send(String token, String soapXML) throws Exception {
		
		EcppTrackItem ecppTrackItem = new EcppTrackItem();
		
		//第一步：创建服务地址，不是WSDL地址
		URL url = new URL("http://s24.vekinerp.com/ecpp_server.php");
		//第二步：打开一个通向服务地址的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//第三步：设置参数
		//3.1发送方式设置：POST必须大写
		connection.setRequestMethod("POST");
		//3.2设置数据格式：content-type
		connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
		//3.3设置输入输出，因为默认新创建的connection没有读写权限，
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		OutputStream os = connection.getOutputStream();
		os.write(soapXML.getBytes());
		//第五步：接收服务端响应，打印
		int responseCode = connection.getResponseCode();
		System.out.println(responseCode);
		
		if(200 == responseCode){//表示服务端响应成功
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while(null != (temp = br.readLine())){
				sb.append(temp);
			}
			String returnStr = sb.toString();
			System.out.println("------查询单条EcppNum状态的返回报文---soapXML------");
			System.out.println(returnStr);
			System.out.println("------查询单条EcppNum状态的报文---soapXML------");
			
			ecppTrackItem = jDom4(returnStr);
			
			//关流
			is.close();
			isr.close();
			br.close();
		}
		os.close();
		
		return ecppTrackItem;
	}
	
	private static EcppTrackItem jDom4(String returnStr) {
		
		EcppTrackItem ecppTrackItem = new EcppTrackItem();
        Document document;
		try {
			//2.加载string
			document = DocumentHelper.parseText(returnStr);
			
			//3.获取根节点
	        Element rootElement = document.getRootElement();
	        Iterator iterator = rootElement.elementIterator();
	        while (iterator.hasNext()){
	            Element stu = (Element) iterator.next();
	            List<Attribute> attributes = stu.attributes();
	            System.out.println("======获取属性值======");
	            for (Attribute attribute : attributes) {
	                System.out.println(attribute.getValue());
	            }
	            System.out.println("======遍历子节点======");
	            Iterator iterator1 = stu.elementIterator();
	            while (iterator1.hasNext()){
	                Element stuChild = (Element) iterator1.next();
	                System.out.println("节点名："+stuChild.getName()+"---节点值："+stuChild.getStringValue());
	                if("queryOrderResponse".equals(stuChild.getName())){
	                	System.out.println("------queryOrderResponse------");
	                	System.out.println(stuChild.getStringValue());
	                	String queryOrderResponseStr = stuChild.getStringValue();
	                	System.out.println("------queryOrderResponse------");
	                	
	                	JSONObject queryOrderResponseJSONObject= JSON.parseObject(queryOrderResponseStr);
	                	System.out.println("------------queryOrderResponseJSONObject------------");
	                	System.out.println(queryOrderResponseJSONObject.get("data"));
	                	System.out.println("------------queryOrderResponseJSONObject------------");
	                	
	                	JSONObject orderDataJSONObject = (JSONObject) queryOrderResponseJSONObject.get("data");
	                	
	                	String order_status = (String) orderDataJSONObject.get("order_status");
	                	String orderStatusCode = getOrderStatusCode(order_status);
	                	
	                	if("OOO".equals(orderStatusCode)){
	                		String track_no = (String) orderDataJSONObject.get("track_no");
	                		ecppTrackItem.setEcppOrderStatusCode(orderStatusCode);
	                		ecppTrackItem.setEcppOrderTrackNo(track_no);
	                		String shipping_name = (String) orderDataJSONObject.get("shipping_name");
	                		ecppTrackItem.setShippingName(shipping_name);
	                	}else{
	                		ecppTrackItem.setEcppOrderStatusCode(orderStatusCode);
	                	}
	                	System.out.println("------------ecppTrackItem.toString()------------");
	                	System.out.println(ecppTrackItem.toString());
	                	System.out.println("------------ecppTrackItem.toString()------------");
	                	return ecppTrackItem;
	                }
	            }
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return ecppTrackItem;
	
	}
	
	private static String getOrderStatusCode(String order_status) {
		String OrderStatusCode="";
		if("111".equals(order_status)){
			OrderStatusCode="UUU";
    	}else if("112".equals(order_status)){
			OrderStatusCode="UUO";
    	}else if("113".equals(order_status)){
			OrderStatusCode="UOO";//审核完成
    	}else if("114".equals(order_status)){
			OrderStatusCode="POO";//审核完成
    	}else if("115".equals(order_status)){
			OrderStatusCode="UUF";
    	}else if("116".equals(order_status)){
			OrderStatusCode="UOH";
    	}else if("117".equals(order_status)){
			OrderStatusCode="HOO";
    	}else if("118".equals(order_status)){
			OrderStatusCode="SSS";
    	}else if("119".equals(order_status)){
			OrderStatusCode="UFO";
    	}else if("120".equals(order_status)){
			OrderStatusCode="CMB";
    	}else if("121".equals(order_status)){
			OrderStatusCode="POH";
    	}else if("122".equals(order_status)){
			OrderStatusCode="PUO";
    	}else if("123".equals(order_status)){
			OrderStatusCode="HUO";
    	}else if("124".equals(order_status)){
			OrderStatusCode="DEL";
    	}else if("125".equals(order_status)){
			OrderStatusCode="UFF";
    	}else if("126".equals(order_status)){
			OrderStatusCode="FOO";
    	}else if("127".equals(order_status)){
			OrderStatusCode="FFF";
    	}else if("128".equals(order_status)){
			OrderStatusCode="PUH";
    	}else if("129".equals(order_status)){
			OrderStatusCode="PPP";//审核完成
    	}else if("130".equals(order_status)){
			OrderStatusCode="UPO";
    	}else if("131".equals(order_status)){
			OrderStatusCode="OOO";//发货完成
    	}else if("132".equals(order_status)){
			OrderStatusCode="UPP";
    	}else if("133".equals(order_status)){
			OrderStatusCode="OOF";
    	}else if("134".equals(order_status)){
			OrderStatusCode="PPS";
    	}else if("135".equals(order_status)){
			OrderStatusCode="UPS";
    	}else if("136".equals(order_status)){
			OrderStatusCode="DLY";
    	}
		return OrderStatusCode;
	}

}
