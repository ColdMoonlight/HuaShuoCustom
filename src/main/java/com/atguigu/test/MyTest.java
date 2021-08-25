package com.atguigu.test;

import com.atguigu.utils.PropertiesUtil;

public class MyTest {

	public static void main(String[] args) {
		
		String webLink = (String)PropertiesUtil.getProperty("megalook.properties", "weblink");
		System.out.println(webLink);
		
		String shipConnectionAPIid = (String)PropertiesUtil.getProperty("megalook.properties", "shipConnectionAPIid");
		System.out.println(shipConnectionAPIid);

		String MegaLook = (String)PropertiesUtil.getProperty("megalook.properties", "MegaLook");
		System.out.println(MegaLook);
		
		String FBTOKEN = (String)PropertiesUtil.getProperty("megalook.properties", "FBTOKEN");
		System.out.println(FBTOKEN);
		String FBPIXELID = (String)PropertiesUtil.getProperty("megalook.properties", "FBPIXELID");
		System.out.println(FBPIXELID);
		
		String newTracking = (String)PropertiesUtil.getProperty("megalook.properties", "newTracking");
		String trackingTitle = (String)PropertiesUtil.getProperty("megalook.properties", "trackingTitle");
		String trackingEmails = (String)PropertiesUtil.getProperty("megalook.properties", "trackingEmails");
		String trackingSmses = (String)PropertiesUtil.getProperty("megalook.properties", "trackingSmses");
		
		System.out.println(newTracking);
		System.out.println(trackingTitle);
		System.out.println(trackingEmails);
		System.out.println(trackingSmses);
		
		String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
		System.out.println(token);
		
		String trackingCustomerName = (String)PropertiesUtil.getProperty("megalook.properties", "trackingCustomerName");
		String trackingEmails1 = (String)PropertiesUtil.getProperty("megalook.properties", "trackingEmails1");
		System.out.println(trackingCustomerName);
		System.out.println(trackingEmails1);
	}

}
