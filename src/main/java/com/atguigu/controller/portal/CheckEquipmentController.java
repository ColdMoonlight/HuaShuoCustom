package com.atguigu.controller.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CheckEquipment")
public class CheckEquipmentController {
	
	/**
	 *	onuse	20200103	检查
	 * 返回信息
	 * */
	@RequestMapping("/isMobileOrPc")
	public static String  isMobileOrPc(HttpServletResponse reponse,HttpServletRequest request){
		
		String requestHeader = request.getHeader("user-agent");
		if(isMobileDevice(requestHeader)){
			System.out.println("使用手机浏览器");
			return "mfront/index";//手机首页
		}else{
			System.out.println("使用web浏览器");
			return "front/index";//PC首页
		}
	}
	
	/**
	 * 	onuse	20200103	检查
	 * 判断设备型号
	 * */
	public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;//pc
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
        	System.out.println("deviceArray[i]:"+deviceArray[i]);
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;//移动
            }
        }
        return false;
	}
	
}
