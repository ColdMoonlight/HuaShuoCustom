package com.atguigu.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.atguigu.utils.PropertiesUtil;

public class MyTest {

	
	private static final Logger logger = LogManager.getLogger(MyTest.class);

	public static void main(String[] args) {
		
		System.out.println("------开始打印日志-----------");
		// 打印日志
        logger.trace("entry");
        logger.error("Did it again!");
        logger.info("Did it again!");
        logger.trace("exit");
        System.out.println("-------打印日志结束-----------");
        
        
//		String webLink = (String)PropertiesUtil.getProperty("megalook.properties", "weblink");
//		System.out.println(webLink);
//		
//		String shipConnectionAPIid = (String)PropertiesUtil.getProperty("megalook.properties", "shipConnectionAPIid");
//		System.out.println(shipConnectionAPIid);
//
//		String MegaLook = (String)PropertiesUtil.getProperty("megalook.properties", "MegaLook");
//		System.out.println(MegaLook);
//		
//		String FBTOKEN = (String)PropertiesUtil.getProperty("megalook.properties", "FBTOKEN");
//		System.out.println(FBTOKEN);
//		String FBPIXELID = (String)PropertiesUtil.getProperty("megalook.properties", "FBPIXELID");
//		System.out.println(FBPIXELID);
//		
//		String newTracking = (String)PropertiesUtil.getProperty("megalook.properties", "newTracking");
//		String trackingTitle = (String)PropertiesUtil.getProperty("megalook.properties", "trackingTitle");
//		String trackingEmails = (String)PropertiesUtil.getProperty("megalook.properties", "trackingEmails");
//		String trackingSmses = (String)PropertiesUtil.getProperty("megalook.properties", "trackingSmses");
//		
//		System.out.println(newTracking);
//		System.out.println(trackingTitle);
//		System.out.println(trackingEmails);
//		System.out.println(trackingSmses);
//		
//		String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
//		System.out.println(token);
//		
//		String trackingCustomerName = (String)PropertiesUtil.getProperty("megalook.properties", "trackingCustomerName");
//		String trackingEmails1 = (String)PropertiesUtil.getProperty("megalook.properties", "trackingEmails1");
//		System.out.println(trackingCustomerName);
//		System.out.println(trackingEmails1);
	}

}
