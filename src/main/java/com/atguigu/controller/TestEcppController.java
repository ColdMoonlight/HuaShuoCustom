package com.atguigu.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.common.Msg;
import com.atguigu.ecpp.HttpClient_ConnectEcpp;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.PropertiesUtil;
import com.atguigu.vo.ecppItem;
import com.atguigu.vo.order;

/**
 * ImageUploadPage
 * @author	zsh
 */
@Controller
@RequestMapping("/TestEcpp")
public class TestEcppController {
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/testEcppById",method=RequestMethod.GET)
	@ResponseBody
	public Msg testEcppById(@RequestParam("payinfoId") Integer payinfoId,HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoId(payinfoId);
		List<MlfrontPayInfo> MlfrontPayInfoList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoNew);
		MlfrontPayInfo mlfrontPayInfoIOne = MlfrontPayInfoList.get(0);
		System.out.println(mlfrontPayInfoIOne.toString());
		
		//获取orderId
		Integer orderId = mlfrontPayInfoIOne.getPayinfoOid();
		
		String paypalIdStr = mlfrontPayInfoIOne.getPayinfoTransidnum();
		
		//封装MlfrontOrderReq
		MlfrontOrder mlfrontOrderPayReq = new MlfrontOrder();
		mlfrontOrderPayReq.setOrderId(orderId);
		//查回结果
		List<MlfrontOrder> mlfrontOrderList =  mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPayReq);
		System.out.println("mlfrontOrderList:"+mlfrontOrderList.toString());
		MlfrontOrder mlfrontOrderResOne = mlfrontOrderList.get(0);
		
		String orderitemidstr = mlfrontOrderResOne.getOrderOrderitemidstr();
		String orderitemidArr[] = orderitemidstr.split(",");
		List<MlfrontOrderItem> mlfrontOrderItemEcppNeedList =new ArrayList<MlfrontOrderItem>();
		for(int x=0;x<orderitemidArr.length;x++){
			MlfrontOrderItem mlfrontOrderItem = new MlfrontOrderItem();
			int orderItemId = Integer .parseInt(orderitemidArr[x]);
			mlfrontOrderItem.setOrderitemId(orderItemId);
			List<MlfrontOrderItem>  mlfrontOrderItemList= mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItem);
			MlfrontOrderItem mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			mlfrontOrderItemEcppNeedList.add(mlfrontOrderItemRes);
		}
		
		//准备从中获取地址id
		Integer payAddressinfoId = mlfrontOrderResOne.getOrderAddressinfoId();
		MlfrontAddress mlfrontAddress =new MlfrontAddress();
		mlfrontAddress.setAddressId(payAddressinfoId);
		List<MlfrontAddress> mlfrontAddressToPayList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddress);
		
		MlfrontAddress mlfrontAddressToPay = mlfrontAddressToPayList.get(0);
		
		order ecppOrderResult = getEcppNeedOrder(mlfrontPayInfoIOne,mlfrontOrderResOne,mlfrontOrderItemEcppNeedList,mlfrontAddressToPay);
		
		String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
		//String token="Lujia2015200708";
		
		String soapXML = getXML(token,ecppOrderResult);
		
		try {
			send(token,soapXML,paypalIdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Msg.success().add("resMsg", "登陆成功");
	}
	
	private static void send(String token, String soapXML,String paypalIdStr) throws Exception {
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
		Object connectText = connection.getContent();
		System.out.println("connectText.toString()");
		System.out.println(connectText.toString());
		System.out.println("connectText.toString()");
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
			
			System.out.println(returnStr);
			
			String EcppHSNum = HttpClient_ConnectEcpp.jDom4EcppHSNum(returnStr,paypalIdStr);
			System.out.println("EcppHSNum:"+EcppHSNum);
			//解析数据,那会
			is.close();
			isr.close();
			br.close();
		}

		os.close();
		
	}

	private static String getXML(String token, order ecppOrder) {
		
		List<ecppItem> ecppItemList = ecppOrder.getItem();
		
		String ecppItemListStr = getEcppItemListStr(ecppItemList);
		
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
					    	+"<order_amount>"+ecppOrder.getOrder_amount()+"</order_amount>"
							+"<currency>"+ecppOrder.getCurrency()+"</currency>"
							+"<shipping_id>"+ecppOrder.getShipping_id()+"</shipping_id>"
							+"<Sales_channels>"+ecppOrder.getSales_channels()+"</Sales_channels>"
							+"<Sales_account_id>"+ecppOrder.getSales_account_id()+"</Sales_account_id>"
							+"<enableStockControl>"+ecppOrder.getEnableStockControl()+"</enableStockControl>"
							
							+"<consignee>"+ecppOrder.getConsignee()+"</consignee>"
							+"<street1>"+ecppOrder.getStreet1()+"</street1>"
							+"<street2 >"+ecppOrder.getStreet2()+"</street2>"
							+"<city>"+ecppOrder.getCity()+"</city>"
							+"<state>"+ecppOrder.getState()+"</state>"
							+"<country>"+ecppOrder.getCountry()+"</country>"
							+"<CountryCode>"+ecppOrder.getCountryCode()+"</CountryCode>"
							+"<tel>"+ecppOrder.getTel()+"</tel>"
							+"<zipcode>"+ecppOrder.getZipcode()+"</zipcode>"
							+"<items>"+ecppItemListStr+"</items>"
							+"</order>"
				    	+"</orders>"
				    +"</addLineOrder>"
				  +"</soap:Body>"
				+"</soap:Envelope>";
		System.out.println(soapXML);
		return soapXML;
	}
		
		private static String getEcppItemListStr(List<ecppItem> ecppItemList) {
			
			String getEcppItemListStr = "";
			String ecppItrmOneStr = "";
			for(ecppItem ecppItem:ecppItemList){
				ecppItrmOneStr+="<item>";
				ecppItrmOneStr=ecppItrmOneStr+"<goods_sn>"+ecppItem.getGoods_sn()+"</goods_sn>"
						+"<goods_name>"+ecppItem.getGoods_name()+"</goods_name>"
						+"<goods_qty>"+ecppItem.getGoods_qty()+"</goods_qty>"
						+"<goods_price>"+ecppItem.getGoods_price()+"</goods_price>";
				
				ecppItrmOneStr+="</item>";
				getEcppItemListStr+=ecppItrmOneStr;
			}
			System.out.println("------getEcppItemListStr-----");
			System.out.println(getEcppItemListStr);
			System.out.println("------getEcppItemListStr-----");
			return getEcppItemListStr;
		}
	
	private static order getEcppNeedOrder(MlfrontPayInfo mlfrontPayInfoIOne, MlfrontOrder mlfrontOrderResOne,
			List<MlfrontOrderItem> mlfrontOrderItemEcppNeedList, MlfrontAddress mlfrontAddressToPay) {
		
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
		ecppOrder.setUserid(mlfrontAddressToPay.getAddressUserfirstname()+" "+mlfrontAddressToPay.getAddressUserlastname());
		ecppOrder.setPaid_time(nowTime);
		ecppOrder.setEmail(mlfrontAddressToPay.getAddressEmail());
		ecppOrder.setPaypalid(mlfrontPayInfoIOne.getPayinfoTransidnum());
		ecppOrder.setSellsrecord(mlfrontPayInfoIOne.getPayinfoPlatenum());
		ecppOrder.setOrder_amount(mlfrontPayInfoIOne.getPayinfoMoney());
		ecppOrder.setCurrency("USD");
//		ecppOrder.setShipping_id(1);//发货方式	参见字典查询接口 name为shipping
		ecppOrder.setSales_channels(220);//销售渠道(220,)	参见字典查询接口		name为Sales_channels
		ecppOrder.setSales_account_id(32);//(30Magalook mall/32Magalook test )		渠道账号	参见渠道账号查询列表
		ecppOrder.setEnableStockControl(0);
		
		ecppOrder.setConsignee(mlfrontAddressToPay.getAddressUserfirstname()+" "+mlfrontAddressToPay.getAddressUserlastname());
		ecppOrder.setStreet1(mlfrontAddressToPay.getAddressDetail());
		ecppOrder.setStreet2(" ");
		ecppOrder.setCity(mlfrontAddressToPay.getAddressCity());
		ecppOrder.setState(mlfrontAddressToPay.getAddressProvince());
		ecppOrder.setCountry(mlfrontAddressToPay.getAddressCountry());
		ecppOrder.setCountryCode(mlfrontAddressToPay.getAddressCountryCode());
		ecppOrder.setZipcode(mlfrontAddressToPay.getAddressPost());
		ecppOrder.setTel(mlfrontAddressToPay.getAddressTelephone());
		
		List<ecppItem> ecppItemList = new ArrayList<ecppItem>();
		for(MlfrontOrderItem mlfrontOrderItem:mlfrontOrderItemEcppNeedList){
			
			ecppItem ecppItemOne = new ecppItem();
			ecppItemOne.setGoods_sn(mlfrontOrderItem.getOrderitemPskuCode());
			ecppItemOne.setGoods_name(mlfrontOrderItem.getOrderitemPseo()+", "+mlfrontOrderItem.getOrderitemPskuName());
			ecppItemOne.setGoods_qty(mlfrontOrderItem.getOrderitemPskuNumber());
			ecppItemOne.setGoods_price(mlfrontOrderItem.getOrderitemPskuMoneystr());
			ecppItemList.add(ecppItemOne);
		}
		
		ecppOrder.setItem(ecppItemList);
		
		return ecppOrder;
	}
	
	
}
