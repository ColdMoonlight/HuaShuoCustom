package com.atguigu.ship.Tests;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import com.atguigu.ship.Classes.AftershipAPIException;
import com.atguigu.ship.Classes.Checkpoint;
import com.atguigu.ship.Classes.ConnectionAPI;
import com.atguigu.ship.Classes.Courier;
import com.atguigu.ship.Classes.Tracking;

public class getAllShipCompanyList {
	
	public static void main(String[] args) {
		
		//获取全部的物流公司
		//getAllShoppingCompany();
		//向AfterShip中添加物流单号
		//addShopInforToAfterShip();
		testTracking();
	}
	
	/**
	 * 获取全部的物流公司
	 * */
	private static void getAllShoppingCompany() {
		
		ConnectionAPI connection = new ConnectionAPI("7b04f01f-4f04-4b37-bbb9-5b159af73ee1");

		List<Courier> couriers;
		try {
			couriers = connection.getAllCouriers();
			System.out.println();
			for(int i=0;i<couriers.size();i++){
				System.out.println("couriers.get(i).getSlug()"+i+"------------------------");
				System.out.println(couriers.get(i).getSlug());
				couriers.get(i).getSlug();//Get slug of each element
				System.out.println("couriers.get(i).getName()"+i+"------------------------");
				System.out.println(couriers.get(i).getName());
				System.out.println("couriers.get(i).getWeb_url()"+i+"------------------------");
				System.out.println(couriers.get(i).getWeb_url());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向AfterShip中添加物流单号
	 * */
	private static void addShopInforToAfterShip() {
		
		ConnectionAPI connection = new ConnectionAPI("7b04f01f-4f04-4b37-bbb9-5b159af73ee1");
		
		//First we have to create a Tracking
		Tracking tracking1 = new Tracking("778708372986");

		//Then we can add information;
		//tracking1.setSlug("fedex");
		tracking1.setTitle("ML201910250000510");
		tracking1.addEmails("1020064691@qq.com");
		tracking1.addEmails("mingyueqingl@163.com");
		tracking1.addSmses("+8617600209637");
		tracking1.addSmses("+8617600209637");

		//Even add customer fields
		tracking1.addCustomFields("product_name","iPhone Case");
		tracking1.addCustomFields("product_price","USD999.60");

		//Finally we add the tracking to our account
		Tracking trackingPosted;
		try {
			trackingPosted = connection.postTracking(tracking1);

			//In the response we will have exactly the information of the server
			System.out.println("trackingPosted.getTrackingNumber()------------------------");
			System.out.println(trackingPosted.getTrackingNumber());
			trackingPosted.getExpectedDelivery();
			trackingPosted.generateJSON();
			trackingPosted.getSlug();
			System.out.println("trackingPosted.getSlug()------------------------");
			System.out.println(trackingPosted.getSlug());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 获取本单号下的物流信息
	 * */
	private static void testTracking() {
		
		ConnectionAPI connection = new ConnectionAPI("7b04f01f-4f04-4b37-bbb9-5b159af73ee1");
		Tracking tracking = new Tracking("394943150257");
		tracking.setSlug("fedex");
		try {
			Tracking tracking2 = connection.getTrackingByNumber(tracking);
			
			List<Checkpoint> CheckpointList =  tracking2.getCheckpoints();
			int i=0;
			for(Checkpoint Checkpoint:CheckpointList){
				System.out.println(i+":"+Checkpoint);
				i++;
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (AftershipAPIException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	}

}
