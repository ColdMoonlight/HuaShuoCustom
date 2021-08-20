package com.atguigu.ecpp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Title: HttpClient.java</p>
 * <p>Description:HttpURLConnection调用方式</p>
 * <p>Company: www.itcast.com</p>
 * @version 1.0
 */
public class HttpClient_07getOrderList {

	public static void main(String[] args) throws IOException {
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

		//第四步：组织SOAP数据，发送请求
		String token = "Lujia2015200708";
		String startimeBefore = "2020-07-06 10:32:42";
		String endtimeBefore = "2020-07-06 10:34:42";
		
		String startime = timeUtil.date2TimeStamp(startimeBefore, "yyyy-MM-dd HH:mm:ss"); 
//        System.out.println(startime);  //运行输出:1470278082
        
        String endtime = timeUtil.date2TimeStamp(endtimeBefore, "yyyy-MM-dd HH:mm:ss"); 
//        System.out.println(endtime);  //运行输出:1470278082
		Integer p = 1;
		String soapXML = getXML(token,startime,endtime,p);
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
			System.out.println(sb.toString());
			
			is.close();
			isr.close();
			br.close();
		}

		os.close();
	}
	
	public static String getXML(String token,String startime,String endtime,Integer p){
		String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
		+"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
			+"<soap:Body>"
		    +"<getOrderList xmlns=\"http://s24.vekinerp.com/ecpp_server.php/\">"
		    	+"<token>"+token+"</token>"
		    	+"<startime>"+startime+"</startime>"
		    	+"<endtime>"+endtime+"</endtime>"
		    	+"<p>"+p+"</p>"
		    +"</getOrderList>"
		  +"</soap:Body>"
		+"</soap:Envelope>";
		return soapXML;
	}

}
