package com.atguigu.utils.app;

import java.util.List;
import com.atguigu.ship.Classes.Checkpoint;
import com.atguigu.ship.Classes.ConnectionAPI;
import com.atguigu.ship.Classes.Tracking;

public class shipInformation {
	
//	afterShip的真实物流url环境
	private final static String ConnectionAPIid = "7b04f01f-4f04-4b37-bbb9-5b159af73ee1";
	
	/**
	 * @Description: 01绑定物流单号进入app
     * @param String orderLogisticsname, String trackingNumber, String payinfoPlateNum
     * @Anthor:zsh
     * @return
     */ 
	public static String addTrackingNumberIntoAfterShip(String orderLogisticsname, String trackingNumber, String payinfoPlateNum){
		
		String connectionAPIStr = ConnectionAPIid;
		ConnectionAPI connection = new ConnectionAPI(connectionAPIStr);
		
		//First we have to create a Tracking
		Tracking tracking = new Tracking(trackingNumber);
		//Then we can add information;
		tracking.setSlug(orderLogisticsname);//
		tracking.setTitle(payinfoPlateNum);//订单号
		tracking.setOrderID(payinfoPlateNum);//Ecpp的订单号
		
		
		tracking.setCustomerName("shaohua");
		tracking.addEmails("mingyueqingl@163.com");
		tracking.addSmses("+8617600209637");

		//Even add customer fields
		tracking.addCustomFields("product_name","iPhone Case");
		tracking.addCustomFields("product_price","USD999.60");

		//Finally we add the tracking to our account
		Tracking trackingPosted;
		try {
			trackingPosted = connection.postTracking(tracking);

			System.out.println("trackingPosted.getTrackingNumber()------------------------");
			System.out.println(trackingPosted.getTrackingNumber());
			
			trackingPosted.getExpectedDelivery();
			trackingPosted.generateJSON();
			trackingPosted.getSlug();
			System.out.println("trackingPosted.getSlug()------------------------");
			System.out.println(trackingPosted.getSlug());
			return "0";
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "1";
		}
		
	}
	
	/**
	 * @Description: 查询物流信息通过,运单号+物流单位
     * @param String trackingNumber, String Slug
     * @Anthor:zsh
     * @return	Tracking
     */ 
	public static Tracking getTrackingByTrackingNumberAndSlug(String trackingNumber, String Slug) {
		
		String connectionAPIStr = ConnectionAPIid;
		ConnectionAPI connection = new ConnectionAPI(connectionAPIStr);
		
		//First we have to create a Tracking
		Tracking tracking = new Tracking(trackingNumber);
		tracking.setSlug(Slug);
		
		Tracking trackingRes = new Tracking("");
		try {
			trackingRes = connection.getTrackingByNumber(tracking);
			System.out.println(trackingRes);
			
			List<Checkpoint> CheckpointList =  trackingRes.getCheckpoints();
			
			int i=0;
			for(Checkpoint Checkpoint:CheckpointList){
				System.out.println(i+":"+Checkpoint);
				i++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		return trackingRes;
	}
	
}
