package com.atguigu.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.common.Msg;
import com.atguigu.ship.Classes.Checkpoint;
import com.atguigu.ship.Classes.ConnectionAPI;
import com.atguigu.ship.Classes.Courier;
import com.atguigu.ship.Classes.Tracking;

/**
 * TestAfterShip
 * @author	zsh
 */
@Controller
@RequestMapping("/TestAfterShip")
public class TestAfterShipController {
	
//	afterShip的真实物流url环境
	private final static String ConnectionAPIid = "7b04f01f-4f04-4b37-bbb9-5b159af73ee1";
	
	/**
	 * 	zsh	20200103
	 * */
	@RequestMapping(value="/getAllShipCompany",method=RequestMethod.GET)
	@ResponseBody
	public Msg getAllShipCompany(HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		String connectionAPIStr = ConnectionAPIid;
		ConnectionAPI connection = new ConnectionAPI(connectionAPIStr);

		List<Courier> couriers;
		try {
			couriers = connection.getAllCouriers();
			System.out.println();
			for(int i=0;i<couriers.size();i++){
				
				System.out.println("getSlug()"+i+"------"+couriers.get(i).getSlug());
				System.out.println("getName()"+i+"------"+couriers.get(i).getName());
				System.out.println("getWeb_url()"+i+"----"+couriers.get(i).getWeb_url());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Msg.success().add("resMsg", "登陆成功");
	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping(value="/addShopInforToAfterShip",method=RequestMethod.GET)
	@ResponseBody
	public Msg addShopInforToAfterShip(HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		String connectionAPIStr = ConnectionAPIid;
		ConnectionAPI connection = new ConnectionAPI(connectionAPIStr);
		
		//First we have to create a Tracking
		Tracking tracking1 = new Tracking("395035200671");
	
		//Then we can add information;
		//tracking1.setSlug("fedex");
		tracking1.setTitle("ML202007231730");
		tracking1.addEmails("1020064691@qq.com");
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
		return Msg.success().add("resMsg", "登陆成功");
	}
	
	/**
	 * @Description: 查询物流信息通过,运单号+物流单位
     * @param String trackingNumber, String Slug
     * @Anthor:zsh
     * @return	Tracking
     */ 
	@RequestMapping(value="/getTrackingByTrackingNumberAndSlug",method=RequestMethod.GET)
	@ResponseBody
	public Msg getTrackingByTrackingNumberAndSlug(String trackingNumber, String Slug){
		
		String connectionAPIStr = ConnectionAPIid;
		ConnectionAPI connection = new ConnectionAPI(connectionAPIStr);
		
		//First we have to create a Tracking
		Tracking tracking = new Tracking(trackingNumber);
		
		Tracking trackingRes = new Tracking("");
		tracking.setSlug(Slug);
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
		return Msg.success().add("resMsg", "登陆成功").add("", trackingRes);
	}
}
