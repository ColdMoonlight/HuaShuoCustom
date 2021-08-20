package com.atguigu.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URLLocationUtils {
	
	public static String getcontextPathStr(HttpServletResponse rep,HttpServletRequest res) {
		String contextPathStr = res.getContextPath();    
        System.out.println("工具类中初始的contextPathStr:"+contextPathStr);
        return contextPathStr;
    }
	
	public static String getrealPathStr(HttpServletResponse rep,HttpServletRequest res) {
        String realPathStr = res.getSession().getServletContext().getRealPath("/");    
        System.out.println("工具类中初始的realPathStr:"+realPathStr);
        return realPathStr;
    }
	
	public static String getbasePathStr(HttpServletResponse rep,HttpServletRequest res) {
		String contextPathStr = res.getContextPath();    
        String basePathStrAll = res.getScheme()+"://"+res.getServerName()+":"+res.getServerPort()+contextPathStr+"/";
        
//      basePathStrAll = "http://megalook.com:80/";		//测试初始值
        
        String basePathStr =basePathStrAll;
        System.out.println("工具类中初始的basePathStr:"+basePathStr);
        
        if(basePathStrAll.contains("megalook")){
        	basePathStr="https://www.megalook.com/";
        }else if(basePathStrAll.contains("huashuohair")){
        	basePathStr="https://www.huashuohair.com/";
        }
        return basePathStr;
    }

}
