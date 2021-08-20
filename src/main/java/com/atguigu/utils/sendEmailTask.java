package com.atguigu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.atguigu.bean.MlbackHtmlEmail;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.service.MlbackHtmlEmailService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackSmstypeService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;

@Component
public class sendEmailTask {
	
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
	
	@Autowired
	MlbackOrderStateEmailService mlbackOrderStateEmailService;
	
//	@Scheduled(cron = "0 0/10 * * * ?")
//	@Scheduled(cron = "0 0 0 5 * ?")
    public void doTask()  throws InterruptedException{
		
		String nowtime = DateUtil.strTime14s();//当前时间
		System.out.println("EmailTask定时任务启动,当前时间:"+nowtime);
        
        MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
        mlbackOrderStateEmailReq.setOrderstateemailName("AbondonEmail");
        
        List<MlbackOrderStateEmail> mlbackOrderStateEmailList = mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
        
        MlbackOrderStateEmail mlbackOrderStateEmailOne = mlbackOrderStateEmailList.get(0);
        String webSiteUrl = mlbackOrderStateEmailOne.getOrderstateemailOne();
        String lastHour = mlbackOrderStateEmailOne.getOrderstateemailTwo();//超过N分钟就开始发邮件
        String intervalTime = mlbackOrderStateEmailOne.getOrderstateemailThree();//间隔N-分钟
        
        Integer lastHourInt = Integer.parseInt(lastHour);
        Integer longTime = Integer.parseInt(intervalTime);
        
        String endTime = DateUtil.dateRollMinus(lastHourInt);//当前时间N-分钟
		String beforeTime = DateUtil.dateRollMinus(lastHourInt+longTime);
        
//        String endTime = DateUtil.dateRoll(lastHourInt);//当前时间N-分钟
//		String beforeTime = DateUtil.dateRoll(lastHourInt+longTime);
        
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoIfEmail(0);
		
		mlfrontPayInfoReq.setPayinfoCreatetime(beforeTime);
		System.out.println("EmailTask-beforeTime"+beforeTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		System.out.println("EmailTask-endTime"+endTime);
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndIfEmail(mlfrontPayInfoReq);
		System.out.println("EmailTask-mlfrontPayInfoList.size():"+mlfrontPayInfoList.size());
		
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
		sendEmailToUnpayList(webSiteUrl, trueUnPayIdList);
		//重复单标记状态2
		updateSameUnpay(sameUnPayIdList);
		//成功单标记状态3
		updateSuccessPay(mlfrontPayInfoSuccessList);
		
		System.out.println("EmailTask-trueUnPayIdList"+trueUnPayIdList.size());
		System.out.println("EmailTask-successPayIdList"+successPayIdList.size());
		System.out.println("EmailTask-sameUnPayIdList"+sameUnPayIdList.size());
		
    }
	
	private void sendEmailToUnpayList(String webSiteUrl,List<Integer> sameUnPayIdList) {

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
			readyToSendEmai(webSiteUrl, mlbackHtmlEmailOne,sameUnPayIdList);
		}else{
			mlbackHtmlEmailOne = null;
		}
	}
	
	//成功单标记成功单,无需发送
		private void updateSuccessPay(List<MlfrontPayInfo> mlfrontPayInfoSuccessList) {
			for(MlfrontPayInfo mlfrontPayInfoInto:mlfrontPayInfoSuccessList){
				Integer payinfoId = mlfrontPayInfoInto.getPayinfoId();
				MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
				mlfrontPayInfoOne.setPayinfoId(payinfoId);
				mlfrontPayInfoOne.setPayinfoIfEmail(3);
				mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
				System.out.println("EmailTask-本条是成功记录-无需发送,标记为成功状态3");
			}		
		}

		//把相同的单子表集成2-重复单子
		private void updateSameUnpay(List<Integer> sameUnPayIdList) {
			for(Integer payinfoId:sameUnPayIdList){
				MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
				mlfrontPayInfoOne.setPayinfoId(payinfoId);
				mlfrontPayInfoOne.setPayinfoIfEmail(2);
				mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
				System.out.println("EmailTask-本条是重复单-无需发送,标记为无需发送的状态2");
			}
		}
	
	private void readyToSendEmai(String webSiteUrl,MlbackHtmlEmail mlbackHtmlEmailOne, List<Integer> trueUnPayIdList) {
		//把发完的邮件变成状态1
		for(Integer payinfoIda:trueUnPayIdList){
			Integer resifSuccessed = SendOneAndUpdateOne(webSiteUrl,mlbackHtmlEmailOne,payinfoIda);
			if(resifSuccessed>0){
				MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
				mlfrontPayInfoOne.setPayinfoId(payinfoIda);
				mlfrontPayInfoOne.setPayinfoIfEmail(1);
				mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			}else{
				System.out.println("EmailTask-本条筛选发送单,标记为已经发送的状态1");
			}
		}
	}
	
	private Integer SendOneAndUpdateOne(String webSiteUrl,MlbackHtmlEmail mlbackHtmlEmailOne, Integer payinfoIda) {
		
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
					String recoverLink = getRecoverLink(webSiteUrl,poid);//点击链接
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
					System.out.println("EmailTask-异常信息");
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

	public String getRecoverLink(String webSiteUrl,Integer orderId) {
		Integer checkRecoverOrderId = orderId;
		
		String checkRecoverOrderIdStr = checkRecoverOrderId+"";
	
		String secretOrderIdStr = EncryptUtil.XORencode(checkRecoverOrderIdStr,"megalook");
		
		String websiteStr = webSiteUrl;
		
		String SendSecretStr = websiteStr+"recover/"+secretOrderIdStr+".html";
		
		System.out.println("客服人员点击,生成信息SendSecretStr:"+SendSecretStr);
		
		return SendSecretStr;	
	}

}
