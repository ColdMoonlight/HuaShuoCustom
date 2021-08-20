package com.atguigu.ecpp;

import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>Title: HttpClient.java</p>
 * <p>Description:HttpURLConnection调用方式</p>
 * <p>Company: www.itcast.com</p>
 * @version 1.0
 */
public class HttpClient_ConnectEcpp {
	
	public static String jDom4EcppHSNum(String returnStr,String paypalIdStr) {
		
        Document document;
        String returnOrder_sn = "";
		try {
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
	                if("addLineOrderResponse".equals(stuChild.getName())){
	                	System.out.println("------addLineOrderResponse------");
	                	System.out.println(stuChild.getStringValue());
	                	String addLineOrderStr = stuChild.getStringValue();
	                	System.out.println("------addLineOrderResponse------");
	                	
	                	JSONObject addLineOrderStrObject= JSON.parseObject(addLineOrderStr);
	                	System.out.println("------------addLineOrderStrObject------------");
	                	System.out.println(addLineOrderStrObject.get("data"));
	                	System.out.println("------------addLineOrderStrObject------------");
	                	
	                	JSONObject dataObject = (JSONObject) addLineOrderStrObject.get("data");
	                	System.out.println("------------dataObject------------");
	                	System.out.println(dataObject.get(paypalIdStr));
	                	System.out.println("------------dataObject------------");
	                	
	                	JSONObject paypalIdStrObject = (JSONObject)dataObject.get(paypalIdStr);
	                	System.out.println("------------returnOrder_sn------------");
	                	returnOrder_sn = (String) paypalIdStrObject.get("order_sn");
	                	System.out.println("返回来的物流追踪号returnOrder_sn:"+returnOrder_sn);
	                	System.out.println("------------returnOrder_sn------------");
	                }
	            }
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return returnOrder_sn;
	}
	
	public static String jDom4(String returnStr) {
        //2.加载xml
        Document document;
		try {
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
	                	System.out.println("------------orderDataJSONObject------------");
	                	System.out.println(orderDataJSONObject.get("track_no"));
	                	System.out.println("------------orderDataJSONObject------------");
	                }
	            }
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}

}
