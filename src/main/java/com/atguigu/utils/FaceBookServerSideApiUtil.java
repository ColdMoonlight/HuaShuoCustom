package com.atguigu.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import com.atguigu.bean.MlPaypalShipAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.serverside.ActionSource;
import com.facebook.ads.sdk.serverside.Content;
import com.facebook.ads.sdk.serverside.CustomData;
import com.facebook.ads.sdk.serverside.DeliveryCategory;
import com.facebook.ads.sdk.serverside.Event;
import com.facebook.ads.sdk.serverside.EventRequest;
import com.facebook.ads.sdk.serverside.EventResponse;
import com.facebook.ads.sdk.serverside.UserData;

public class FaceBookServerSideApiUtil {
	
	
	//测试
//	public static final String ACCESS_TOKEN = "EAALqAx5gd40BALlNPxpfAAYGY7gMZAyXWivriYgfZATE9ZAn9WUhKo7al9P8GJ1TlBNYeTRJQUb8TS3xZAtsu0edh6VPgZB9Ft0336BIpIrgaZBPGDqSDAZBY9txCnwidE9TepgffqUZAFMzS5q9cUqqVWdyEfd3wFzZBQWtUCuZB2dhkZBeAbaf6540kVnStkaRr8ZD";
//	public static final String PIXEL_ID = "246433859565492";
			
	//真实
//	public static final String ACCESS_TOKEN = "EAALqAx5gd40BAGNZA2z9W0yATVUL9xoPdbbRWElEqCLH7KjZB5G5dls0pydZB6wAZBOpsmw1F3TVetMZAaoWAvY0pOYKSG4OVzZBYVhYXukTZBS5NQvwZBKRWt3u2Ov4dbAOElsutYIYX98NSbT4fC4hlXLkwlIrNoWGexSiVqxSdAa8z0DV9OhjeSppbnQAxPYZD";
//	public static final String PIXEL_ID = "667403967094866";

	  public static void toFbServiceApi(MlfrontPayInfo mlfrontPayInfoOne, MlfrontOrder mlfrontOrderPayOneRes, 
			  List<MlfrontOrderItem> mlfrontOrderItemList, MlPaypalShipAddress mlPaypalShipAddressRes,String FBTOKEN,String FBPIXELID) {
	    
		APIContext context = new APIContext(FBTOKEN).enableDebug(true);
	    context.setLogger(System.out);

	    String addressEmail = mlPaypalShipAddressRes.getShippingaddressEmail();
	    String addressTelNumber = mlPaypalShipAddressRes.getShippingaddressTelNumber();
	    
	    String addressEmail256 = JAVASHA256Util.getSHA256StrJava(addressEmail);
	    String addressTelNumber256 = JAVASHA256Util.getSHA256StrJava(addressTelNumber);
	    
	    UserData userData = new UserData()
	        .emails(Arrays.asList(addressEmail256))
	        .phones(Arrays.asList(addressTelNumber256));
//	    .fbc("fb.1.1554763741205.AbCdEfGhIjKlMnOpQrStUvWxYz1234567890")
//        .fbp("fb.1.1558571054389.1098115397");

	    String pidStr =  mlfrontOrderItemList.get(0).getOrderitemPid()+"";
	    Integer pQuantity =  mlfrontOrderItemList.get(0).getOrderitemPskuNumber();
	    
	    Long pQuantityL = pQuantity.longValue();
	    
	    Content content = new Content()
	        .productId(pidStr)
	        .quantity(pQuantityL)
	        .deliveryCategory(DeliveryCategory.home_delivery);
	    
	    BigDecimal moneyStrBig = mlfrontPayInfoOne.getPayinfoMoney();
	    float moneyStrF = moneyStrBig.floatValue();
	    CustomData customData = new CustomData()
	        .addContent(content)
	        .currency("usd")
	        .value(moneyStrF);

	    Event purchaseEvent = new Event();
	    String weblink = (String)PropertiesUtil.getProperty("megalook.properties", "weblink");
	    purchaseEvent.eventName("Purchase")
	    	.eventId(mlfrontPayInfoOne.getPayinfoId()+"")
	        .eventTime(System.currentTimeMillis() / 1000L)
	        .userData(userData)
	        .customData(customData)
	        .eventSourceUrl(weblink+"Success.html")
	        .actionSource(ActionSource.website);
	    //.actionSource(ActionSource.website);

//	    EventRequest eventRequest = new EventRequest(PIXEL_ID, context);
	    EventRequest eventRequest = new EventRequest(FBPIXELID, context);
	    eventRequest.addDataItem(purchaseEvent);

	    try {
	      EventResponse response = eventRequest.execute();
	      System.out.println(String.format("Standard API response : %s ", response));
	    } catch (APIException e) {
	      e.printStackTrace();
	    }
	  }

}
