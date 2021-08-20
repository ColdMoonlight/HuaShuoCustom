package com.atguigu.utils;

import java.util.Calendar;

public class ImageNameUtil {

	//获取图片的类型前缀
	public static String gettypeName(String type) {
		String typeName = "";
		if("category".equals(type)){
			typeName="cateid";
		}else if("categorypc".equals(type)){
			typeName="catepcid";
		}else if("product".equals(type)){
			typeName="proid";
		}else if("productDiscout".equals(type)){
			typeName="proidDiscout";
		}else if("productVideo".equals(type)){
			typeName="productVideo";
		}else if("reviewDetail".equals(type)){
			typeName="reviewDetail";
		}else if("slideWap".equals(type)){
			typeName="slideWap";
		}else if("slidePc".equals(type)){
			typeName="slidePc";
		}else if("showareaWap".equals(type)){
			typeName="showareaWap";
		}else if("showareaPc".equals(type)){
			typeName="showareaPc";
		}else if("actShowProWap".equals(type)){
			typeName="actShowProWap";
		}else if("actShowProPc".equals(type)){
			typeName="actShowProPc";
		}else if("VideoShowAreaWap".equals(type)){
			typeName="VideoShowAreaWap";
		}else if("VideoShowAreaPc".equals(type)){
			typeName="VideoShowAreaPc";
		}else if("VideoYTB".equals(type)){
			typeName="VideoYTBid";
		}else if("couponDescTitle".equals(type)){
			typeName="couponDescTitle";
		}else if("coupon".equals(type)){
			typeName="coupon";
		}else if("proSku".equals(type)){
			typeName="proSku";
		}else if("blog".equals(type)){
			typeName="blog";
		}
		
		return typeName;
	}
	
	//获取图片的转化名
	public static String getfilename(String typeName,String typeIdStr) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int date = c.get(Calendar.DATE);    
		int hour = c.get(Calendar.HOUR_OF_DAY);   
		int minute = c.get(Calendar.MINUTE);   
		int second = c.get(Calendar.SECOND);
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);    
		String newfilename = typeName+ typeIdStr +"time"+date+hour+minute+second+".jpg";
		System.out.println(newfilename);
		return newfilename;
	}
	
	//获取图片下全部小图的转化名
	public static String getImageAllfilename(String productIdstr, String productimgSortIdstr) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int date = c.get(Calendar.DATE);    
		int hour = c.get(Calendar.HOUR_OF_DAY);   
		int minute = c.get(Calendar.MINUTE);   
		int second = c.get(Calendar.SECOND);
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);    
		String newfilename = "productId"+ productIdstr +"SortId"+productimgSortIdstr+"time"+date+hour+minute+second+".jpg";
		System.out.println(newfilename);
		return newfilename;
	}
	
	//获取图片的转化名
	public static String getReviewFilename(String typeName,String productSeo, String reviewIdStr, String reviewimgSortOrderStr) {
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int date = c.get(Calendar.DATE);    
		int hour = c.get(Calendar.HOUR_OF_DAY);   
		int minute = c.get(Calendar.MINUTE);   
		int second = c.get(Calendar.SECOND);
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);    
		String newfilename = typeName+"productSeo"+ productSeo +"reviewId"+reviewIdStr+"reviewimgSort"+reviewimgSortOrderStr+"time"+date+hour+minute+second+".jpg";
		System.out.println(newfilename);
		return newfilename;
	}

	public static String getfilenamePsku(String typeName, String pskuIdStr, String proIdStr) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int date = c.get(Calendar.DATE);    
		int hour = c.get(Calendar.HOUR_OF_DAY);   
		int minute = c.get(Calendar.MINUTE);   
		int second = c.get(Calendar.SECOND);
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);    
		String newfilename = typeName+"-productId"+ proIdStr +"PskuIdStr"+pskuIdStr+"time"+date+hour+minute+second+".jpg";
		System.out.println(newfilename);
		return newfilename;
	}
	
	public static String getfilenameBlog(String typeName, String blogSeoname) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int date = c.get(Calendar.DATE);    
		int hour = c.get(Calendar.HOUR_OF_DAY);   
		int minute = c.get(Calendar.MINUTE);   
		int second = c.get(Calendar.SECOND);
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);    
		String newfilename = typeName+"-"+ blogSeoname +"time"+date+hour+minute+second+".jpg";
		System.out.println(newfilename);
		return newfilename;
	}

}
