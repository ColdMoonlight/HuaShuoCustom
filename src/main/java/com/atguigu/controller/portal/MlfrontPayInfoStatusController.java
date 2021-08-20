package com.atguigu.controller.portal;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.common.Msg;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;

@Controller
@RequestMapping("/MlfrontPayStatus")
public class MlfrontPayInfoStatusController {
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	/**
	 * 1.0	zsh200820
	 * refundPayOrder
	 * @param MlfrontPayInfo
	 * @return 
	 */
	@RequestMapping(value="/refundPayOrder",method=RequestMethod.POST)
	@ResponseBody
	public Msg refundPayOrder(HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
		//PayinfoStatus(5);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款//5发送弃购//6重复单关
		Integer payinfoId = mlfrontPayInfo.getPayinfoId();
		Integer payinfoOid = mlfrontPayInfo.getPayinfoOid();
		//接受payinfoId
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payinfoId);
		mlfrontPayInfoReq.setPayinfoStatus(4);
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoReq);
		
		MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
		mlfrontOrderPay.setOrderId(payinfoOid);
		mlfrontOrderPay.setOrderStatus(5);
		//OrderStatus(6);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderPay);
		return Msg.success().add("resMsg", "订单退款修改完成");
	}
	
	/**
	 * 2.0	zsh200821
	 * closePayOrder
	 * @param MlfrontPayInfo
	 * @return 
	 */
	@RequestMapping(value="/closePayOrder",method=RequestMethod.POST)
	@ResponseBody
	public Msg closePayOrder(HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
		//PayinfoStatus(6);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款//5发送弃购//6重复单关闭
		Integer payinfoId = mlfrontPayInfo.getPayinfoId();
		Integer payinfoOid = mlfrontPayInfo.getPayinfoOid();
		//接受payinfoId
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payinfoId);
		mlfrontPayInfoReq.setPayinfoStatus(6);
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoReq);
		
		MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
		mlfrontOrderPay.setOrderId(payinfoOid);
		mlfrontOrderPay.setOrderStatus(7);
		//OrderStatus(7);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderPay);
		return Msg.success().add("resMsg", "重复单关闭");
	}
	//PayinfoStatus(5);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款//5发送弃购//6重复单关
	//OrderStatus(6);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
}