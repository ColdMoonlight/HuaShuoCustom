package com.atguigu.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.atguigu.bean.MlbackHtmlEmail;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MoneyEmailcheckout;
import com.atguigu.service.MlbackHtmlEmailService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackSmstypeService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.MoneyEmailcheckoutService;

@Component
public class sendEmailBySellectTask {
	
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
	
	@Autowired
	MoneyEmailcheckoutService moneyEmailcheckoutService;
	
	@Scheduled(cron = "0 0/10 * * * ?")
    public void doTask()  throws InterruptedException{
		
		
		String nowtime = DateUtil.strTime14s();//当前时间
		System.out.println("SMS定时启动nowtime:"+nowtime);
		
		//获取弃购短信的定制--时间
		//1.0
		MlbackOrderStateEmail mlbackOrderStateEmailOne = getAbondonSMSData();
		//从中取出需要的参数
        String webSiteUrl = mlbackOrderStateEmailOne.getOrderstateemailOne();//服务器存储的真实路径
        String lasMinute = mlbackOrderStateEmailOne.getOrderstateemailTwo();//超过N分钟就开始发邮件
        String intervalTime = mlbackOrderStateEmailOne.getOrderstateemailThree();//间隔N分钟
		
		//2.0
        //获取当前时间段内的支付-未支付(未发送过邮件)的单子list
        List<MlfrontPayInfo> MlfrontPayInfoList = getPayInfoLIstByTime(lasMinute,intervalTime);		
        
        //3.0
        //获取当前时间段内的支付-未支付的单子list
        List<MlfrontPayInfo> MlfrontPayInfoNeedSMSList = getNeedSendFromPayInfoList(MlfrontPayInfoList);
        
       //4.0准备发邮件
       //先发邮件,把发完的邮件变成状态1
       sendEmailToUnpayList(webSiteUrl, MlfrontPayInfoNeedSMSList);
		
	}
	
	/***************************************子方法**开始********************************************/

	//1.0获取弃购短信的文案;从邮件模板里面
	private MlbackOrderStateEmail getAbondonSMSData() {
		
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
        mlbackOrderStateEmailReq.setOrderstateemailName("AbondonEmail");
        
        List<MlbackOrderStateEmail> mlbackOrderStateEmailList = mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
        
        MlbackOrderStateEmail mlbackOrderStateEmailOne = mlbackOrderStateEmailList.get(0);
		return mlbackOrderStateEmailOne;
	}
	
	
	//2.0获取当前时间段内的支付-未支付(未发送过邮件)的单子list
	private List<MlfrontPayInfo> getPayInfoLIstByTime(String lasMinute, String intervalTime) {
	    
		//准备编译时间参数
        Integer lasMinuteInt = Integer.parseInt(lasMinute);
        Integer longTime = Integer.parseInt(intervalTime);
		
        String endTime = DateUtil.dateRollMinus(lasMinuteInt);//当前时间N分钟
		String startTime = DateUtil.dateRollMinus(lasMinuteInt+longTime);
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		
		mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		mlfrontPayInfoReq.setPayinfoIfEmail(0);//查询本Pay单里面的IFEmail=0,未发送的单子
		
		mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
		System.out.println("IfEmail-startTime"+startTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		System.out.println("IfEmail-endTime"+endTime);
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndIfEmail(mlfrontPayInfoReq);
		System.out.println("EmailTask-mlfrontPayInfoList.size():"+mlfrontPayInfoList.size());
		
		return mlfrontPayInfoList;
	}
		
		
	/**
	 * 3.0
     * 遍历payinfolist
     * 成功的插入其中,并移除List中当前的数据,将本条数据插入发送表,标记无需发送（成功单标记状态3）
     * 
     * 剩下的遍历,
     * 找到跟成功的相同的orderID 的单子,remove掉。全部标记为重复单（IfEmail==2重复单标记状态2）--
     * 
     * 剩下的单子,
     * 第一条,拿orderid去重复发送表中查询,在之前的发送表中查询是否有记录（只查询最后的100条）,如果有记录则remove掉；
     * 如果没有记录,继续查这一条的name去查询,如果在发送表中查询有记录（）,如果有则remove掉,
     * 如果还没有查到,本条存进到发送表中------|------|------并且将本条记录到一个新的list中
     * 
     * 然后就拿到了要发送的List
     * 
     * 开始遍历,查询取出每条的orderid,查询单条OrderOne,从中取出addressId,再找到addressOne,再发短信
     * 
     * */
	private List<MlfrontPayInfo> getNeedSendFromPayInfoList(List<MlfrontPayInfo> mlfrontPayInfoList) {
		
		Integer orderIdLast = 0;
		
		/****************************1次****************************/
		List<MlfrontPayInfo> mlfrontPayInfoUnPayList = new ArrayList<MlfrontPayInfo>();
		
		for(MlfrontPayInfo mlfrontPayInfoOne:mlfrontPayInfoList){
			orderIdLast = mlfrontPayInfoOne.getPayinfoOid();
			Integer patStatus = mlfrontPayInfoOne.getPayinfoStatus();
			if(patStatus==0){
				//失败订单
				mlfrontPayInfoUnPayList.add(mlfrontPayInfoOne);
			}else{
				//成功订单
				//4.1封装新参数,把数据放进去,直接插入新表中
				insertNowPayRecord(mlfrontPayInfoOne,"1");
				//4.1.1更改成功订单的状态IfEmail--3		//把发完的邮件变成状态1;重复单标记状态2;成功单标记状态3;
				updateSuccessOnePay(mlfrontPayInfoOne);
				
			}
		}
		
		System.out.println("orderIdLast:"+orderIdLast);
		
		/****************************2次****************************/
		List<MlfrontPayInfo> mlfrontPayInfoUnPayRemoveSameOidList = new ArrayList<MlfrontPayInfo>();
		
		for(MlfrontPayInfo mlfrontPayInfoSuccessSameOidUnPayOne:mlfrontPayInfoUnPayList){
			
			Integer orderId = mlfrontPayInfoSuccessSameOidUnPayOne.getPayinfoOid();
			
			//4.2在弃购邮件的发送记录表，查询中检查的
			Integer ifHave = checkIfAlreadyHaveByOid(orderId);
			
			if(ifHave>0){
				//4.2.1这一单是重复单子,需要标记重复2		//把发完的邮件变成状态1;重复单标记状态2;成功单标记状态3;
				updateSameUnpayOne(mlfrontPayInfoSuccessSameOidUnPayOne);
			}else{
				//存下来,继续下一关的遍历
				mlfrontPayInfoUnPayRemoveSameOidList.add(mlfrontPayInfoSuccessSameOidUnPayOne);
			}
		}
		
		
		List<MlfrontPayInfo> mlfrontPayInfoUnPayRemoveSameOnameList = new ArrayList<MlfrontPayInfo>();
		
		for(MlfrontPayInfo mlfrontPayInfoSuccessSameOnameUnPayOne:mlfrontPayInfoUnPayRemoveSameOidList){
			
			String orderName = mlfrontPayInfoSuccessSameOnameUnPayOne.getPayinfoUname();
			
			Integer ifHave = checkIfAlreadyHaveByOname(orderName,orderIdLast);
			
			if(ifHave>0){
				//4.2.1这一单是重复单子,需要标记重复2		//把发完的邮件变成状态1;重复单标记状态2;成功单标记状态3;
				updateSameUnpayOne(mlfrontPayInfoSuccessSameOnameUnPayOne);
			}else{
				//存下来,继续下一关的遍历
				mlfrontPayInfoUnPayRemoveSameOnameList.add(mlfrontPayInfoSuccessSameOnameUnPayOne);
			}
		}
		
		/****************************4次****************************/
		//准备所有未支付订单里面的重复单子
		List<MlfrontPayInfo> mlfrontPayInfoNeedList = new ArrayList<MlfrontPayInfo>();
		
		for(MlfrontPayInfo mlfrontPayInfoUnPayOne:mlfrontPayInfoUnPayRemoveSameOnameList){
			
			//拿着这一条的orderId去里面查,如果没有,就插入
			Integer orderId = mlfrontPayInfoUnPayOne.getPayinfoOid();
			
			Integer ifHave = checkIfAlreadyHaveByOid(orderId);
			if(ifHave>0){
				//跳过
				//4.2.1这一单是重复单子(name重复),需要标记重复2
				updateSameUnpayOne(mlfrontPayInfoUnPayOne);
			}else{
				
				//再查这一条是不是人名相同,如果有,删掉//如果也没查到,记录,准备发消息
				String orderName = mlfrontPayInfoUnPayOne.getPayinfoUname();
				Integer ifHaveName = checkIfAlreadyHaveByOname(orderName,orderIdLast);
				
				if(ifHaveName>0){
					//4.2.1这一单是重复单子(name重复),需要标记重复2
					updateSameUnpayOne(mlfrontPayInfoUnPayOne);
				}else{
					//这一条存进去
					//然后记录,方便后面操作
					insertNowPayRecord(mlfrontPayInfoUnPayOne,"0");
					//记录本条的信息
					mlfrontPayInfoNeedList.add(mlfrontPayInfoUnPayOne);
				}
				
			}
		}
		return mlfrontPayInfoNeedList;
	}
		
		
		//4.1这是成功单，直接插入存入弃购已发（不需要发）的表中，但标记为成功标识3
		private void insertNowPayRecord(MlfrontPayInfo mlfrontPayInfoOne, String IfSuccessPayOrderStr) {
			
			MoneyEmailcheckout moneyEmailcheckout = new MoneyEmailcheckout();
			moneyEmailcheckout.setEmailcheckoutPayid(mlfrontPayInfoOne.getPayinfoId());
			moneyEmailcheckout.setEmailcheckoutOrderid(mlfrontPayInfoOne.getPayinfoOid());
			moneyEmailcheckout.setEmailcheckoutOrdername(mlfrontPayInfoOne.getPayinfoUname());
			moneyEmailcheckout.setEmailcheckoutSendtype(IfSuccessPayOrderStr);
			moneyEmailcheckoutService.insertSelective(moneyEmailcheckout);
		}
		
		/**
		 * 4.1.1更改成功订单的状态IfEmail--3
		 * */
		private void updateSuccessOnePay(MlfrontPayInfo mlfrontPayInfoInto) {
			Integer payinfoId = mlfrontPayInfoInto.getPayinfoId();
			MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
			mlfrontPayInfoOne.setPayinfoId(payinfoId);
			mlfrontPayInfoOne.setPayinfoIfEmail(3);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			System.out.println("SMS本条是成功记录-无需发送,标记为成功状态3");
		}
		
		//4.2
		private Integer checkIfAlreadyHaveByOid(Integer smscheckoutOrderid) {
			
			MoneyEmailcheckout moneyEmailcheckoutReq = new MoneyEmailcheckout();
			moneyEmailcheckoutReq.setEmailcheckoutOrderid(smscheckoutOrderid);
			
			List<MoneyEmailcheckout> moneyEmailcheckoutList = moneyEmailcheckoutService.selectMoneyEmailcheckoutByPayOId(moneyEmailcheckoutReq);
			if(moneyEmailcheckoutList.size()>0){
				return 1;
			}else{
				return 0;
			}
			
		}
		
		//4.2.1把相同的单子表集成2-重复单子
		private void updateSameUnpayOne(MlfrontPayInfo mlfrontPayInfoOneInto) {
			MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
			mlfrontPayInfoOne.setPayinfoId(mlfrontPayInfoOneInto.getPayinfoId());
			mlfrontPayInfoOne.setPayinfoIfEmail(2);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			System.out.println("SMS本条是重复单-无需发送,标记为无需发送的状态2");
		}
		
		//4.3
		private Integer checkIfAlreadyHaveByOname(String orderName, Integer orderIdLast) {

			MoneyEmailcheckout moneyEmailcheckoutReq = new MoneyEmailcheckout();
			moneyEmailcheckoutReq.setEmailcheckoutOrdername(orderName);
			moneyEmailcheckoutReq.setEmailcheckoutId(orderIdLast);
			
			List<MoneyEmailcheckout> moneyEmailcheckoutList = moneyEmailcheckoutService.selectMoneyEmailcheckoutByPayOnameAndOid(moneyEmailcheckoutReq);
			if(moneyEmailcheckoutList.size()>0){
				return 1;
			}else{
				return 0;
			}
		}
		
		//4.0准备发邮件
	    //先发邮件,把发完的邮件变成状态1
		private void sendEmailToUnpayList(String webSiteUrl,List<MlfrontPayInfo> mlfrontPayInfoList) {

			//查询发邮件的模板,封装参数
			MlbackHtmlEmail MlbackHtmlEmailReq = new MlbackHtmlEmail();
			MlbackHtmlEmailReq.setHtmlemailName("checkEmail");
			MlbackHtmlEmailReq.setHtmlemailStatus(1);
			//查询本条-模板数据
			List<MlbackHtmlEmail> mlbackHtmlEmailResList =mlbackHtmlEmailService.selectMlbackHtmlEmailListByName(MlbackHtmlEmailReq);
			MlbackHtmlEmail mlbackHtmlEmailOne = new MlbackHtmlEmail();
			if(mlbackHtmlEmailResList.size()>0){
				mlbackHtmlEmailOne =mlbackHtmlEmailResList.get(0);
				//准备发送消息
				readyToSendEmail(webSiteUrl, mlbackHtmlEmailOne,mlfrontPayInfoList);
			}else{
				mlbackHtmlEmailOne = null;
			}
		}
		
		private void readyToSendEmail(String webSiteUrl,MlbackHtmlEmail mlbackHtmlEmailOne, List<MlfrontPayInfo> mlfrontPayInfoList) {
			//把发完的邮件变成状态1
			for(MlfrontPayInfo mlfrontPayInfoA:mlfrontPayInfoList){
				
				Integer payinfoIda = mlfrontPayInfoA.getPayinfoId();
				Integer resifSuccessed = SendOneAndUpdateOne(webSiteUrl,mlbackHtmlEmailOne,mlfrontPayInfoA);
				if(resifSuccessed>0){
					//发送成功
					MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
					mlfrontPayInfoOne.setPayinfoId(payinfoIda);
					mlfrontPayInfoOne.setPayinfoIfEmail(1);
					mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
				}else{
					System.out.println("EmailTask-本条筛选发送单,标记为已经发送的状态1");
				}
			}
		}
		
		private Integer SendOneAndUpdateOne(String webSiteUrl,MlbackHtmlEmail mlbackHtmlEmailOne, MlfrontPayInfo mlfrontPayInfoInto) {
			
			//查询本条的orderid,email,生成信息
			//这一单存在
			MlfrontPayInfo mlfrontPayInfoRes = mlfrontPayInfoInto;
			
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
					//产品文案
					String htmlProductStr = getOrderProductInfo(mlfrontOrderOne);
					//获取链接信息
					String recoverLink = getRecoverLink(webSiteUrl,poid);//点击链接
					//获取最终模板信息
					String emailContent = getEmailTemplateIfno(recoverLink,mlbackHtmlEmailOne,htmlProductStr);
					EmailHtmlUtil.sendHtmlUnCheckoutEmil(userEmail,emailContent) ;
					return 1;
				}else{
					System.out.println("没有查到Orderid下的地址id--异常信息");
					return 0;
				}
			}else{
				//该orderid不存在本条没了,没了就不操作
				System.out.println("没有查到payId下的Orderid--异常信息");
				return 0;
			}
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
