package com.atguigu.ecpp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.PropertiesUtil;
import com.atguigu.vo.ecppItem;
import com.atguigu.vo.order;

/**
 * 
 * <p>Title: HttpClient.java</p>
 * <p>Description:HttpURLConnection调用方式</p>
 * <p>Company: www.itcast.com</p>
 * @author  
 * @date    
 * @version 1.0
 */
public class HttpClient_66addLineOrder {

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
		
		order ecppOrderResult = getParams();
		
		String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
		//String token = "Lujia2015200708";
		
		String soapXML = getXML(token,ecppOrderResult);
		
		OutputStream os = connection.getOutputStream();
		os.write(soapXML.getBytes());
		//第五步：接收服务端响应，打印
		int responseCode = connection.getResponseCode();
		System.out.println("---------响应码---------");
		System.out.println(responseCode);
		System.out.println("---------响应码---------");
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
			System.out.println("------------返回的字节结果------------");
			System.out.println(returnStr);
			System.out.println("------------返回的字节结果------------");
			
			String paypalIdStr = "MLpaypal07201422";
			String EcppHSNum = HttpClient_ConnectEcpp.jDom4EcppHSNum(returnStr,paypalIdStr);
			System.out.println("EcppHSNum:"+EcppHSNum);
			
			System.out.println("sb.toString()");
			is.close();
			isr.close();
			br.close();
		}

		os.close();
		
	}



	private static String getXML(String token,order ecppOrder) {
		
		
		String ecppItemStr="";
		
		List<ecppItem> ecppItemInfo = ecppOrder.getItem();
		
		ecppItemStr = getEcppItemInfoStr(ecppItemInfo);
		
		String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+"<soap:Body>"
				    +"<addLineOrder xmlns=\"http://s24.vekinerp.com/ecpp_server.php/\">"
				    	+"<token>"+token+"</token>"
				    	+"<orders>"
					    	+"<order>"
					    	+"<userid>"+ecppOrder.getUserid()+"</userid>"
					    	+"<paid_time>"+ecppOrder.getPaid_time()+"</paid_time>"
					    	+"<email>"+ecppOrder.getEmail()+"</email>"				    					
					    	+"<paypalid>"+ecppOrder.getPaypalid()+"</paypalid>"
					    	+"<sellsrecord>"+ecppOrder.getSellsrecord()+"</sellsrecord>"
					    	+"<order_amount>"+436.98+"</order_amount>"
							+"<currency>"+ecppOrder.getCurrency()+"</currency>"
							+"<shipping_id>"+ecppOrder.getShipping_id()+"</shipping_id>"
							+"<Sales_channels>"+ecppOrder.getSales_channels()+"</Sales_channels>"
							+"<Sales_account_id>"+ecppOrder.getSales_account_id()+"</Sales_account_id>"
							+"<enableStockControl>"+ecppOrder.getEnableStockControl()+"</enableStockControl>"
							
							+"<consignee>"+ecppOrder.getConsignee()+"</consignee>"
							+"<street1>"+ecppOrder.getStreet1()+"</street1>"
							+"<street2>"+ecppOrder.getStreet2()+"</street2>"
							+"<city>"+ecppOrder.getCity()+"</city>"
							+"<state>"+ecppOrder.getState()+"</state>"
							+"<country>"+ecppOrder.getCountry()+"</country>"
							+"<CountryCode>"+ecppOrder.getCountryCode()+"</CountryCode>"
							+"<tel>"+ecppOrder.getTel()+"</tel>"
							+"<zipcode>"+ecppOrder.getZipcode()+"</zipcode>"
							+ecppItemStr
							+"</order>"
				    	+"</orders>"
				    +"</addLineOrder>"
				  +"</soap:Body>"
				+"</soap:Envelope>";
		System.out.println("---------准备的xml信息---------");
		System.out.println(soapXML);
		System.out.println("---------准备的xml信息---------");;
		return soapXML;
	}
	
//    private String goods_sn;//产品编码
//    private String goods_name;//产品名称
//    private Integer goods_qty;//产品数量
//    private BigDecimal goods_price;//产品价格
	
	private static String getEcppItemInfoStr(List<ecppItem> ecppItemInfoList) {
		
		String ecppItemInfoStr = "";
		Integer ecppItemListNums = ecppItemInfoList.size();
		if(ecppItemListNums>1){
			for(ecppItem ecppItemOne:ecppItemInfoList){
				ecppItemInfoStr+="<items>"
				+"<goods_sn>"+ecppItemOne.getGoods_sn()+"</goods_sn>"
				+"<goods_name>"+ecppItemOne.getGoods_name()+"</goods_name>"
				+"<goods_qty>"+ecppItemOne.getGoods_qty()+"</goods_qty>"
				+"<goods_price>"+ecppItemOne.getGoods_price()+"</goods_price>"
				+"</items>";
			}
		}else{
			ecppItem ecppItemOne = ecppItemInfoList.get(0);
			ecppItemInfoStr+="<items>"
								+"<item>"
								+"<goods_sn>"+ecppItemOne.getGoods_sn()+"</goods_sn>"
								+"<goods_name>"+ecppItemOne.getGoods_name()+"</goods_name>"
								+"<goods_qty>"+ecppItemOne.getGoods_qty()+"</goods_qty>"
								+"<goods_price>"+ecppItemOne.getGoods_price()+"</goods_price>"
								+"</item>"
							+"</items>";
		}
		System.out.println("ecppItemInfoStr:"+ecppItemInfoStr);
		return ecppItemInfoStr;
	}



	private static order getParams() {
		order ecppOrder = new order();
//	    private String userid;//买家ID
//	    private String paid_time;//交易时间:Y-m-d H:i:s	默认为当前日期
//	    private String email;//买家邮箱
//	    private String paypalid;//交易号/订单号	唯一
//	    private String sellsrecord;//订单号/流水号
//	    private BigDecimal order_amount;//订单金额	两位小数
//	    private String currency;//币种	三字代码，例：USD
//	    private Integer shipping_id;//发货方式	参见字典查询接口 name为shipping
//	    private Integer Sales_channels;//销售渠道	参见字典查询接口		name为Sales_channels
//	    private Integer Sales_account_id;//渠道账号	参见渠道账号查询列表
//	    private Integer enableStockControl;//库存控制		0 默认 //1锁定扣除OOO//2锁定PPP
//	    private String consignee;//*收件人
//	    private String street1;//*地址1
//	    private String street2;//地址2
//	    private String city;//*城市
//	    private String state;//*省
//	    private String country;//*国家
//	    private String CountryCode;//*国家二字代码
//	    private String tel;//*电话
//	    private String zipcode;//*邮编
		
		String nowTime = DateUtil.strTime14s();
		ecppOrder.setUserid("shaohua200714 Zhou");
		ecppOrder.setPaid_time(nowTime);
		ecppOrder.setEmail("1020064691@qq.com");
		ecppOrder.setPaypalid("MLpaypal07201422");
		ecppOrder.setSellsrecord("ML46912007201053");
		
		
		BigDecimal totalprice = new BigDecimal(191.32);	//初始化最终价格参数
		ecppOrder.setOrder_amount(totalprice);
		ecppOrder.setCurrency("USD");
//		ecppOrder.setShipping_id(1);//发货方式	参见字典查询接口 name为shipping
		ecppOrder.setSales_channels(220);//销售渠道(220,)	参见字典查询接口		name为Sales_channels
		ecppOrder.setSales_account_id(30);//(30	Magalook test)		渠道账号	参见渠道账号查询列表
		ecppOrder.setEnableStockControl(0);
		
		ecppOrder.setConsignee("shaohua200714 Zhou");
		ecppOrder.setStreet1("2 Manners Ave");
		ecppOrder.setStreet2(" ");
		ecppOrder.setCity("Atlanta");
		ecppOrder.setState("Georgia");
		ecppOrder.setCountry("United States");
		ecppOrder.setCountryCode("US");
		ecppOrder.setZipcode("30314");
		ecppOrder.setTel("17600209637");
		
		List<ecppItem> ecppItemList = new ArrayList<ecppItem>();
		
		ecppItem ecppItemOne = new ecppItem();
		ecppItemOne.setGoods_sn("Goods_sn001");
		ecppItemOne.setGoods_name("testGoods_name001");
		ecppItemOne.setGoods_qty(2);
		ecppItemOne.setGoods_price("145.66");
		ecppItemList.add(ecppItemOne);
//		
//		ecppItem ecppItemTwo = new ecppItem();
//		ecppItemTwo.setGoods_sn("Goods_sn002");
//		ecppItemTwo.setGoods_name("testGoods_name002");
//		ecppItemTwo.setGoods_qty(1);
//		ecppItemTwo.setGoods_price("145.66");
//		ecppItemList.add(ecppItemTwo);
//		
//		ecppItem ecppItemThree = new ecppItem();
//		ecppItemThree.setGoods_sn("Goods_sn003");
//		ecppItemThree.setGoods_name("testGoods_name003");
//		ecppItemThree.setGoods_qty(1);
//		ecppItemThree.setGoods_price("145.66");
//		ecppItemList.add(ecppItemThree);
		
		ecppOrder.setItem(ecppItemList);
		
//		System.out.println(ecppOrder.toString());	
		System.out.println("----------封装的order信息----------");	
		String json=JSON.toJSONString(ecppOrder);//关键
		System.out.println(json);
		System.out.println("----------封装的order信息----------");	
		
		return ecppOrder;
	}

}
