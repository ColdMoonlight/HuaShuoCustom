package com.atguigu.controller.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackHtmlEmail;
import com.atguigu.bean.MlbackSearch;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackHtmlEmailService;
import com.atguigu.service.MlbackSmstypeService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EmailHtmlUtil;
import com.atguigu.utils.EncryptUtil;
import com.atguigu.utils.URLLocationUtils;
@Controller
@RequestMapping("/MlfrontPayOrderQuazList")
public class MlfrontPayOrderQuazListController {
		
	@Autowired
	MlfrontOrderService mlfrontOrderService;

	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlbackSmstypeService mlbackSmstypeService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackHtmlEmailService mlbackHtmlEmailService;
	
	/**
	 * 1.0	执行方法	210302
	 * to	查询当前时间内需要发送邮件的订单,对订单进行批量操作;
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/selectOrderlistBySearch",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectOrderlistBySearch(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlbackSearch mlbackSearch) {
		
		/**
		 * 准备筛选前的数据
		 * 1自动查询12小时以内的payinfo订单List
		 * 存储付款成功的paySuccessList	包含成功名字
		 * 存储失败的UnPayList
		 * */
		//获取当前系统时间
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
//		
//		String nowtime = DateUtil.strTime14s();
//		String beforeTime = DateUtil.dateRoll(36);
		
		String startTime = mlbackSearch.getSearchCreatetime();
		String endTime = mlbackSearch.getSearchMotifytime();
		mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		mlfrontPayInfoReq.setPayinfoIfEmail(0);
		
//		mlfrontPayInfoReq.setPayinfoCreatetime("2021-02-25 23:59:59");
//		mlfrontPayInfoReq.setPayinfoMotifytime("2021-02-26 23:59:50");
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndIfEmail(mlfrontPayInfoReq);
		
		List<MlfrontPayInfo> mlfrontPayInfoSuccessList = new ArrayList<MlfrontPayInfo>();
		List<MlfrontPayInfo> mlfrontPayInfoUnPayList = new ArrayList<MlfrontPayInfo>();

        Map<String, String> successPayInfoOrderIdMap = new HashMap<String, String>();
        Map<String, String> successPayInfoUserNameMap = new HashMap<String, String>();
        
        List<Integer> successPayIdList = new ArrayList<Integer>();
        List<Integer> sameUnPayIdList = new ArrayList<Integer>();
        List<Integer> trueUnPayIdList = new ArrayList<Integer>();
			
		for(MlfrontPayInfo mlfrontPayInfoOne:mlfrontPayInfoList){
			Integer patStatus = mlfrontPayInfoOne.getPayinfoStatus();
			if(patStatus==0){
				//失败订单
				mlfrontPayInfoUnPayList.add(mlfrontPayInfoOne);
			}else{
				//成功订单
				mlfrontPayInfoSuccessList.add(mlfrontPayInfoOne);
				//存储成功Payinfo,成功orderid,成功uName
				successPayIdList.add(mlfrontPayInfoOne.getPayinfoId());
				successPayInfoOrderIdMap.put(mlfrontPayInfoOne.getPayinfoOid()+"", mlfrontPayInfoOne.getPayinfoOid()+"");
				successPayInfoUserNameMap.put(mlfrontPayInfoOne.getPayinfoUname(), mlfrontPayInfoOne.getPayinfoUname());
			}
		}
		/**遍历
		 * 准备已出已成功单含有相同的orderid
		 * */
		List<MlfrontPayInfo> mlfrontPayInfoRemoveSameOidNowList = new ArrayList<MlfrontPayInfo>();
			
		for(MlfrontPayInfo mlfrontPayInfoB:mlfrontPayInfoUnPayList){
			Integer unpOrderID = mlfrontPayInfoB.getPayinfoOid();
			String pOrderIDSuccessStr = successPayInfoOrderIdMap.get(unpOrderID+"");
			
			if(pOrderIDSuccessStr==null){
				//这一单起码orderid没有重复//存起来
				mlfrontPayInfoRemoveSameOidNowList.add(mlfrontPayInfoB);
				//这里面-剔除了--跟已支付同orderid的重复单(未支付的重复单子)
				//把此时的客户名字存起来放进Map成功map中
			}else{
				//这个是重复单子(最终客户付款之前的单子),跳过
				sameUnPayIdList.add(mlfrontPayInfoB.getPayinfoId());
			}
		}
		//没有跟成功单相同的orderid了.现在要删掉,剩下的非与成功相同的orderid.去除了跟成功单相同的orderid的payinfo单子
		/**遍历
		 * 准备移除未支付单子里面的重复orderid
		 * */
		List<MlfrontPayInfo> mlfrontPayInfoRemoveUnameList = new ArrayList<MlfrontPayInfo>();
        Map<String, String> reallyUnPayInfoOrderIdMap = new HashMap<String, String>();
        Map<String, String> reallyUnPayInfoUserNameMap = new HashMap<String, String>();
		for(MlfrontPayInfo mlfrontPayInfoC:mlfrontPayInfoRemoveSameOidNowList){
			String payinfoUname = mlfrontPayInfoC.getPayinfoUname();
			String pOrderUnameSuccessStr = successPayInfoUserNameMap.get(payinfoUname);
			if(pOrderUnameSuccessStr==null){
				//这一单起码orderid没有重复//存起来
				mlfrontPayInfoRemoveUnameList.add(mlfrontPayInfoC);
				//这里面-剔除了--跟已支付同Uname的重复单(未支付的重复单子)
				//把此时的客户名字存起来放进Map成功map中
				reallyUnPayInfoOrderIdMap.put(mlfrontPayInfoC.getPayinfoOid()+"", mlfrontPayInfoC.getPayinfoOid()+"");
				reallyUnPayInfoUserNameMap.put(mlfrontPayInfoC.getPayinfoUname(), mlfrontPayInfoC.getPayinfoUname());
			}else{
				//这个是人名重复重复单子(最终客户付款之前的单子),跳过
				sameUnPayIdList.add(mlfrontPayInfoC.getPayinfoId());
			}
		}
		//移除了与成功单相同Orderid的单子/与成功单相同UserName的单子
		Integer lastUnPayOid = 0;
		List<MlfrontPayInfo> mlfrontPayInfoRemoveUnPayOrderIdSameList = new ArrayList<MlfrontPayInfo>();
		for(MlfrontPayInfo mlfrontPayInfoD:mlfrontPayInfoRemoveUnameList){
			Integer unpOrderID = mlfrontPayInfoD.getPayinfoOid();
			if(lastUnPayOid.equals(unpOrderID)){
				//这一单重复啦(未支付里面的orderid重复)
				//仅仅记录到未成功付款的重复单里
				sameUnPayIdList.add(mlfrontPayInfoD.getPayinfoId());
			}else{
				mlfrontPayInfoRemoveUnPayOrderIdSameList.add(mlfrontPayInfoD);
				lastUnPayOid = unpOrderID;
			}
		}
		/**遍历
		 * 准备移除未支付单子里面的重复uname
		 * */
		List<MlfrontPayInfo> mlfrontPayInfoRemoveUnPayOrderUnameSameList = new ArrayList<MlfrontPayInfo>();
		String lastUnpOrderUname = "";
		for(MlfrontPayInfo mlfrontPayInfoE:mlfrontPayInfoRemoveUnPayOrderIdSameList){
			String unpOrderUname = mlfrontPayInfoE.getPayinfoUname();
			if(lastUnpOrderUname.equals(unpOrderUname)){
				//这一单重复啦(未支付里面的orderid重复)
				//仅仅记录到未成功付款的重复单里
				sameUnPayIdList.add(mlfrontPayInfoE.getPayinfoId());
			}else{
				mlfrontPayInfoRemoveUnPayOrderUnameSameList.add(mlfrontPayInfoE);
				trueUnPayIdList.add(mlfrontPayInfoE.getPayinfoId());
				lastUnpOrderUname = unpOrderUname;
			}
		}
		
		//先发邮件,把发完的邮件变成状态1
		sendEmailToUnpayList(rep, res, session, trueUnPayIdList);
		//重复单标记状态2
		updateSameUnpay(sameUnPayIdList);
		//成功单标记状态3
		updateSuccessPay(mlfrontPayInfoSuccessList);
		
		return Msg.success().add("trueUnPayIdList", trueUnPayIdList).add("successPayIdList", successPayIdList).add("sameUnPayIdList", sameUnPayIdList);
		
	}
	//成功单标记成功单,无需发送
	private void updateSuccessPay(List<MlfrontPayInfo> mlfrontPayInfoSuccessList) {
		for(MlfrontPayInfo mlfrontPayInfoInto:mlfrontPayInfoSuccessList){
			Integer payinfoId = mlfrontPayInfoInto.getPayinfoId();
			MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
			mlfrontPayInfoOne.setPayinfoId(payinfoId);
			mlfrontPayInfoOne.setPayinfoIfEmail(3);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			System.out.println("本条是成功记录-无需发送,标记为成功状态3");
		}		
	}

	//把相同的单子表集成2-重复单子
	private void updateSameUnpay(List<Integer> sameUnPayIdList) {
		for(Integer payinfoId:sameUnPayIdList){
			MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
			mlfrontPayInfoOne.setPayinfoId(payinfoId);
			mlfrontPayInfoOne.setPayinfoIfEmail(2);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			System.out.println("本条是重复单-无需发送,标记为无需发送的状态2");
		}
	}

	private void sendEmailToUnpayList(HttpServletResponse rep,HttpServletRequest res,HttpSession session,List<Integer> sameUnPayIdList) {

		//接受categoryId
		MlbackHtmlEmail MlbackHtmlEmailReq = new MlbackHtmlEmail();
		MlbackHtmlEmailReq.setHtmlemailName("checkEmail");
		MlbackHtmlEmailReq.setHtmlemailStatus(1);
		//查询本条
		List<MlbackHtmlEmail> mlbackHtmlEmailResList =mlbackHtmlEmailService.selectMlbackHtmlEmailListByName(MlbackHtmlEmailReq);
		MlbackHtmlEmail mlbackHtmlEmailOne = new MlbackHtmlEmail();
		if(mlbackHtmlEmailResList.size()>0){
			mlbackHtmlEmailOne =mlbackHtmlEmailResList.get(0);
			
			//准备发送消息
			readyToSendEmai(rep, res, session, mlbackHtmlEmailOne,sameUnPayIdList);
		}else{
			mlbackHtmlEmailOne = null;
		}
		
	}
	
	private void readyToSendEmai(HttpServletResponse rep,HttpServletRequest res,HttpSession session,MlbackHtmlEmail mlbackHtmlEmailOne, List<Integer> trueUnPayIdList) {
		//把发完的邮件变成状态1
		for(Integer payinfoIda:trueUnPayIdList){
			Integer resifSuccessed = SendOneAndUpdateOne(rep,res,session,mlbackHtmlEmailOne,payinfoIda);
			if(resifSuccessed>0){
				MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
				mlfrontPayInfoOne.setPayinfoId(payinfoIda);
				mlfrontPayInfoOne.setPayinfoIfEmail(1);
				mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			}else{
				System.out.println("本条筛选发送单,标记为已经发送的状态1");
			}
		}
	}

	private Integer SendOneAndUpdateOne(HttpServletResponse rep,HttpServletRequest res,HttpSession session,MlbackHtmlEmail mlbackHtmlEmailOne, Integer payinfoIda) {
		
		//mlbackHtmlEmailOne.get
		//查询本条的orderid,email,生成信息
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		mlfrontPayInfoOne.setPayinfoId(payinfoIda);
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoOne);
		if(mlfrontPayInfoList.size()>0){
			//这一单存在
			MlfrontPayInfo mlfrontPayInfoRes = mlfrontPayInfoList.get(0);
			Integer poid = mlfrontPayInfoRes.getPayinfoOid();
			
			MlfrontOrder mlfrontOrderOneReq = new MlfrontOrder();
			mlfrontOrderOneReq.setOrderId(poid);
			List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderOneReq);
			
			if(mlfrontOrderList.size()>0){
				MlfrontOrder mlfrontOrderOne = mlfrontOrderList.get(0);
				Integer orderAddressId = mlfrontOrderOne.getOrderAddressinfoId();
				
				MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
				mlfrontAddressReq.setAddressId(orderAddressId);
				List<MlfrontAddress> mlfrontAddressList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
				if(mlfrontAddressList.size()>0){
					MlfrontAddress mlfrontAddressOne = mlfrontAddressList.get(0);
					//发送邮箱
					String userEmail = mlfrontAddressOne.getAddressEmail();
					System.out.println("userEmail:"+userEmail);
					//userEmail ="mingyueqingl@163.com";
					//产品文案
					String htmlProductStr = getOrderProductInfo(mlfrontOrderOne);
					//获取链接信息
					String recoverLink = getRecoverLink(rep,res,session,poid);//点击链接
					//获取最终模板信息
					String emailContent = getEmailTemplateIfno(recoverLink,mlbackHtmlEmailOne,htmlProductStr);
					EmailHtmlUtil.sendHtmlUnCheckoutEmil(userEmail,emailContent) ;
					Integer payInfoId = payinfoIda;
					MlfrontPayInfo mlfrontPayInfoIfEmail = new MlfrontPayInfo();
					mlfrontPayInfoIfEmail.setPayinfoId(payInfoId);
					mlfrontPayInfoIfEmail.setPayinfoIfEmail(1);
					//2-需要把这一单的payinfo_SMS更新过来
					mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoIfEmail);
				}else{
					System.out.println("异常信息");
				}
			}else{
				//该orderid不存在本条没了,没了就不操作
			}
		}else{
			//这一单不存在
		}
		return 0;
	}

	private String getOrderProductInfo(MlfrontOrder mlfrontOrderOne) {
		String orderProductStr="";
		String orderItemIdstr = mlfrontOrderOne.getOrderOrderitemidstr();
		String orderItemArr[] =orderItemIdstr.split(",");
		int orginalItemNum = orderItemArr.length;
		for(int i= 0;i<orginalItemNum;i++){
			String orderItemId = orderItemArr[i];
			MlfrontOrderItem mlfrontOrderItemReq  =new MlfrontOrderItem();
			mlfrontOrderItemReq.setOrderitemId(Integer.parseInt(orderItemId));
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			if(mlfrontOrderItemResList.size()>0){
				MlfrontOrderItem mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
				String proImg = mlfrontOrderItemOne.getOrderitemProductMainimgurl();
				String pname = mlfrontOrderItemOne.getOrderitemPname();
				String pmoney = mlfrontOrderItemOne.getOrderitemPskuReamoney();
				orderProductStr+="<tr style='font-size: 16px;'>" +
			            "<td align='left' valign='top' style='padding-bottom: 10px;'>" +
		              "<img width='100' height='100' src='"+ proImg +"' />" +
		            "</td>" +
		            "<td align='left' valign='top' style='padding-left: 10px; padding-bottom: 10px; max-width: 302px;'>"+ pname +"</td>" +
		            "<td align='left' valign='top' style='padding-left: 10px; padding-bottom: 10px;'>"+ pmoney +"</td>" +
		          "</tr>";
			}
		}
		return orderProductStr;
	}

	private String getEmailTemplateIfno(String recoverLink, MlbackHtmlEmail mlbackHtmlEmailOne, String htmlProductStr) {
		
		String htmlemailRetrievecode =mlbackHtmlEmailOne.getHtmlemailRetrievecode();
		String htmlemailRetrievecodeNow=htmlemailRetrievecode.replace("\n", "<br>");
		
		String htmlEmailStr = "<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 666px !important;border-collapse: collapse;mso-table-lspace: 0pt;mso-table-rspace: 0pt;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;border: 0;background-color: #fafafa;'><tbody><tr><td style='padding: 16px;'>"+
				"<table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
				"<tbody>" +
					"<tr>" +
						"<td style='padding-left: 16px; padding-right: 16px; border-top: 0; border-bottom: 2px solid #EAEAEA; background-color: #FFFFFF;'>" +
							"<table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
								"<tbody>" +
									"<tr><td align='center' style='padding-top: 40px; padding-bottom: 20px;'><img src='"+ mlbackHtmlEmailOne.getHtmlemailHeadimgurl() +"' alt='' width='196' style='display: inline-block;'></td></tr>" +
									"<tr><td align='center' style='padding: 6px 0; font-size: 22px; font-weight: bold;'>"+ mlbackHtmlEmailOne.getHtmlemailTitle() +"</td></tr>" +
									"<tr><td style='padding: 8px 0; font-size: 18px;'>"+ mlbackHtmlEmailOne.getHtmlemailRetrieve() +"</td></tr>" +
									"<tr><td style='padding: 8px 0; font-size: 16px; font-weight: bold; color: red;'>"+ htmlemailRetrievecodeNow +"</td></tr>" +
									"<tr>" +
										"<td style='padding: 20px 0;'>" +
											"<table border='0' cellpadding='0' cellspacing='0' width='100%'>" + htmlProductStr + "</table>" +
										"</td>" +
									"</tr>" +
									"<tr>" +
										"<td align='center' style='padding-bottom: 36px;'>" +
											"<a href='"+ recoverLink +"' style='display: inline-block; padding: 8px 16px; font-weight:bold; letter-spacing:normal; text-decoration:none; color:#FFFFFF; border-radius: 3px; background-color: #2BAADF;' title='Return to Checkout'>Return to Checkout</a>" +
										"</td>" +
									"</tr>" +
								"</tbody>" +
							"</table>" +
						"</td>" +
					"</tr>" +
					"<tr>" +
						"<td style='padding-top: 16px; padding-bottom: 8px;'>Copyright © 2021 Megalook Hair, All rights reserved.<br>You are receiving this email because you opted in via our website.</td>" +
					"</tr>" +
					"<tr><td style='padding-top: 16px; font-size: 12px; font-weight: bold;'>Our mailing address is:</td></tr>" +
					"<tr><td style='padding: 6px 0; font-size: 12px;'>Megalook Hair<br>Huashuo Hair<br>jin rong zhong xin<br>xu chang, He Nan 461000<br>China</td></tr>" +
				"</tbody>" +
			"</table>" +
		"</td></tr></tbody><table>";
		return htmlEmailStr;
	}
	
	/**
	 * 5.1	zsh20210223
	 * 查看单条类目的详情细节
	 * @param MlbackHtmlEmail
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackHtmlEmailOneAllDetailByName",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackHtmlEmailOneAllDetailByName(@RequestBody MlbackHtmlEmail mlbackHtmlEmailInto){
		
		String htmlemailName = mlbackHtmlEmailInto.getHtmlemailName();
		
		//接受categoryId
		MlbackHtmlEmail MlbackHtmlEmailReq = new MlbackHtmlEmail();
		MlbackHtmlEmailReq.setHtmlemailName(htmlemailName);
		MlbackHtmlEmailReq.setHtmlemailStatus(1);
		//查询本条
		List<MlbackHtmlEmail> mlbackHtmlEmailResList =mlbackHtmlEmailService.selectMlbackHtmlEmailListByName(MlbackHtmlEmailReq);
		MlbackHtmlEmail mlbackHtmlEmailOne = new MlbackHtmlEmail();
		if(mlbackHtmlEmailResList.size()>0){
			mlbackHtmlEmailOne =mlbackHtmlEmailResList.get(0);
		}else{
			mlbackHtmlEmailOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackHtmlEmailOne的详情细节完毕")
					.add("mlbackHtmlEmailOne", mlbackHtmlEmailOne);
	}
	
	public String getRecoverLink(HttpServletResponse rep,HttpServletRequest res,HttpSession session,Integer orderId) {
		Integer checkRecoverOrderId = orderId;
		
		String checkRecoverOrderIdStr = checkRecoverOrderId+"";
		
		String secretOrderIdStr = EncryptUtil.XORencode(checkRecoverOrderIdStr,"megalook");
		
		String websiteStr = getNowWeb(rep,res,session);
		
		String SendSecretStr = websiteStr+"recover/"+secretOrderIdStr+".html";
		
		System.out.println("客服人员点击,生成信息SendSecretStr:"+SendSecretStr);
		
		return SendSecretStr;	
	}
	private String getNowWeb(HttpServletResponse rep, HttpServletRequest res, HttpSession session) {

		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		return basePathStr;
	}
}
