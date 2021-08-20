package com.atguigu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlbackSmstype;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackHtmlEmailService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackSmstypeService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;

@Component
public class sendSMSTask {
	
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
	MlbackAreafreightService mlbackAreafreightService;
	
	/**
	 * 1.0	zsh210304
	 * 点击按钮发送短信
	 * @param String PayInfoNumStr
	 * @return 
	 * */
	//@Scheduled(cron = "0 0/20 * * * ?")
	//@Scheduled(cron = "0 0 0 5 * ?")
    public void doTask()  throws InterruptedException{
		
		String nowtime = DateUtil.strTime14s();//当前时间
		System.out.println("SMS定时启动nowtime:"+nowtime);
     
		//从邮件模板里面
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
        mlbackOrderStateEmailReq.setOrderstateemailName("AbondonSMS");
        
        List<MlbackOrderStateEmail> mlbackOrderStateEmailList = mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
        
        MlbackOrderStateEmail mlbackOrderStateEmailOne = mlbackOrderStateEmailList.get(0);
        String webSiteUrl = mlbackOrderStateEmailOne.getOrderstateemailOne();
        String lasMinute = mlbackOrderStateEmailOne.getOrderstateemailTwo();//超过N分钟就开始发邮件
        String intervalTime = mlbackOrderStateEmailOne.getOrderstateemailThree();//间隔N分钟
        
        Integer lasMinuteInt = Integer.parseInt(lasMinute);
        Integer longTime = Integer.parseInt(intervalTime);
        
//      String endTime = DateUtil.dateRoll(lastHourInt);//当前时间N分钟
//		String startTime = DateUtil.dateRoll(lastHourInt+longTime);
		
        String endTime = DateUtil.dateRollMinus(lasMinuteInt);//当前时间N分钟
		String startTime = DateUtil.dateRollMinus(lasMinuteInt+longTime);
        
		MlfrontPayInfo mlfrontPayInfoRe = new MlfrontPayInfo();
		
		mlfrontPayInfoRe.setPayinfoCreatetime(startTime);
		mlfrontPayInfoRe.setPayinfoMotifytime(endTime);
		mlfrontPayInfoRe.setPayinfoIfEmail(0);
		
		mlfrontPayInfoRe.setPayinfoCreatetime(startTime);
		System.out.println("SMS-startTime"+startTime);
		mlfrontPayInfoRe.setPayinfoMotifytime(endTime);
		System.out.println("SMS-endTime"+endTime);
		
		//查询接口,发送时间定时的N分钟,间隔几小时,发送文案
		MlbackSmstype mlbackSmstype = new MlbackSmstype();
		mlbackSmstype.setSmstypeName("sms");
		List<MlbackSmstype> mlbackSmstypeList = mlbackSmstypeService.selectMlbackSmstypeByName(mlbackSmstype);
		if(mlbackSmstypeList.size()>0){
			MlbackSmstype mlbackSmstypeOne = mlbackSmstypeList.get(0);
			Integer SmstypeStatus =  mlbackSmstypeOne.getSmstypeStatus();
			if(SmstypeStatus>0){
				String Content = mlbackSmstypeOne.getSmstypeContent();
				System.out.println("SMS-本短信的挽回语为:Content"+Content);
				//查询一下这个时间段的orderid没有结算的并且有结算地址的信息
				MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
				mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
				mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
				mlfrontPayInfoReq.setPayinfoIfSMS(0);
				
				//拿到筛选后的操作数据
				List<MlfrontPayInfo> mlfrontPayInfoList = getPayInfoList(mlfrontPayInfoReq);
				if(mlfrontPayInfoList.size()>0){
					
					String orderIdLastOneStr = "";
					for(MlfrontPayInfo mlfrontPayInfoOne:mlfrontPayInfoList){
						
						//本payinfoId是多少,更新完了的话,需要把这一单的payinfo_SMS更新过来
						Integer payId = mlfrontPayInfoOne.getPayinfoId();
						System.out.println("SMS-实际电话号码realTel的payId:"+payId);
						Integer checkRecoverOrderId = mlfrontPayInfoOne.getPayinfoOid();
						String checkRecoverOrderIdNowStr = checkRecoverOrderId+"";
						if(checkRecoverOrderIdNowStr.equals(orderIdLastOneStr)){
							//当前orderid=上一个orderid,逃过,循环下一个.
							//当前单子有毛病
							continue;
						}else{
							//读出order对应的order明细;
							//---------------拿到orderId,去地址表中查询addressId,再从地址信息中查询邮箱手机号-------begin----------
							MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
							mlfrontOrderPay.setOrderId(checkRecoverOrderId);
							List<MlfrontOrder> mlfrontOrderPayResList= mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPay);
							MlfrontOrder mlfrontOrderPayOneRes = mlfrontOrderPayResList.get(0);
							//2.2从详情中拿到addressid;
							Integer addressinfoId = mlfrontOrderPayOneRes.getOrderAddressinfoId();
							MlfrontAddress MlfrontAddressReq = new MlfrontAddress();
							MlfrontAddressReq.setAddressId(addressinfoId);
							List<MlfrontAddress> MlfrontAddressList = mlfrontAddressService.selectMlfrontAddressByParam(MlfrontAddressReq);
							MlfrontAddress mlfrontAddressOne = MlfrontAddressList.get(0);
							String telephone = mlfrontAddressOne.getAddressFormatTelephone();
							String firstName = mlfrontAddressOne.getAddressUserfirstname();
							String countryCode = mlfrontAddressOne.getAddressCountryCode();
							
							String realTel = getRealTel(countryCode,telephone);
							System.out.println("SMS-实际电话号码realTel:"+realTel);
							
							String userName = firstName+" ";
							//---------------拿到orderId,去地址表中查询addressId,再从地址信息中查询邮箱手机号-------end--------
							String checkRecoverOrderIdStr = checkRecoverOrderId+"";
							
							String websiteStr = webSiteUrl;
							
							String secretOrderIdStr = EncryptUtil.XORencode(checkRecoverOrderIdStr,"megalook");
							
							String SendStr = "【MegaLook】Dear "+userName+","+Content+"."+websiteStr+"SMS/"+checkRecoverOrderIdStr+".html";
//							System.out.println("本单号位checkRecoverOrderIdStr："+checkRecoverOrderIdStr+",本条弃购链接为SendStr:"+SendStr);
							String SendSecretStr = "【MegaLook】Dear "+userName+","+Content+"."+websiteStr+"SMS/"+secretOrderIdStr+".html";
//							System.out.println("本单号位checkRecoverOrderIdStr："+checkRecoverOrderIdStr+",本条弃购链接为SendSecretStr:"+SendSecretStr);
							
							//这里要解密
//							String unSecretOrderIdStr = EncryptUtil.XORdecode(secretOrderIdStr,"megalook");
//							String SendUnSecretStr = Content+"."+websiteStr+"recover/"+unSecretOrderIdStr+".html";
//							System.out.println("本单号位checkRecoverOrderIdStr："+checkRecoverOrderIdStr+",本条弃购链接为SendUnSecretStr:"+SendUnSecretStr);
							
							try {
								//这个是真实发送
								//String SMSreturnData = SMSUtilshtml.sendSMS(SendStr,telephone);//未加密串
								if(realTel.length()>0){
									//String SMSreturnData = SMSUtilshtml.sendSMS(SendSecretStr,realTel);//加密串
									System.out.println(SendSecretStr+",这一单发送成功");
								}else{
									System.out.println("SMS-当前手机号为："+realTel+",这一单无法发送");
								}
								//这个是真实发送
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println(SendStr+",这一单系统异常,报错了");
							}
							//1-本payinfoId是多少,更新完了的话;
							MlfrontPayInfo mlfrontPayInfoSMSed = new MlfrontPayInfo();
							mlfrontPayInfoSMSed.setPayinfoId(payId);
							mlfrontPayInfoSMSed.setPayinfoIfSMS(1);
							//2-需要把这一单的payinfo_SMS更新过来
							mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoSMSed);
							
							//操作完毕,把当前orderid存住;
							orderIdLastOneStr = checkRecoverOrderIdNowStr;
						}
						
					}
					//该状态不生效,没有生效的
					//return Msg.success().add("resMsg", "本时间段的弃购挽回信息操作完毕");
				}else{
					//该状态不生效,没有生效的
					//return Msg.fail().add("resMsg", "本时间段没有可以弃购挽回的订单信息");
				}
			}else{
				//该状态不生效,没有生效的
				//return Msg.fail().add("resMsg", "没有生效的smstype命令");
			}
		}else{
			//没查到这个,返回
			//return Msg.fail().add("resMsg", "没配置smstypeName对象");
		}
	}

	//通过国家拿手机号码的前缀
	private String getRealTel(String countryCodeInto, String telephone) {
		
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		
		mlbackAreafreightReq.setAreafreightCountryCode(countryCodeInto);
		
		List<MlbackAreafreight> mlbackAreafreightList = mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		
		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
		
		String realTelephone ="";
		if(mlbackAreafreightList.size()>0){
			mlbackAreafreightOne = mlbackAreafreightList.get(0);
			
			Integer telPrefix = mlbackAreafreightOne.getAreafreightTelPrefix();
			
			realTelephone = checkTel(countryCodeInto,telPrefix,telephone);
			
			return realTelephone;
		}else{
			mlbackAreafreightOne = null;
			return "";
		}
	}

//		查询当前国际code,查出对应的号码区号;
//		查看当前手机号码开头是否包含这段区号;
//		如果包含,就返回首页.如果不包含,就加上;
	private String checkTel(String countryCodeInto, Integer telPrefix, String telephone) {
		
		String telPrefixStr = telPrefix+"";
		
		String finalTel = telephone;
		if("US".equals(countryCodeInto)){
			//United States
			if(telephone.startsWith("1")){
				finalTel = telephone;
				System.out.println("us-1");
			}else{
				finalTel=telPrefixStr+telephone;
				System.out.println("us-2");
			}
			//finalTel="86"+telephone;
		}else if("CA".equals(countryCodeInto)){
			//Canada
			if(telephone.startsWith("1")){
				finalTel = telephone;
				System.out.println("ca-1");
			}else{
				finalTel=telPrefixStr+telephone;
				System.out.println("ca-2");
			}
		}else if("DE".equals(countryCodeInto)){
			//Germany
			if(telephone.startsWith("49")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("ZA".equals(countryCodeInto)){
			//South Africa
			if(telephone.startsWith("27")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("AU".equals(countryCodeInto)){
			//Australia
			if(telephone.startsWith("61")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("FR".equals(countryCodeInto)){
			//France
			if(telephone.startsWith("33")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("IE".equals(countryCodeInto)){
			//Ireland
			if(telephone.startsWith("353")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("VI".equals(countryCodeInto)){
			//U.S.Virgin Islands
			if(telephone.startsWith("1340")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("GB".equals(countryCodeInto)){
			//United Kingdom
			if(telephone.startsWith("44")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else{
			
		}
		
		return finalTel;
	}
	
	
	private List<MlfrontPayInfo> getPayInfoList(MlfrontPayInfo mlfrontPayInfoReq) {
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndIfEmail(mlfrontPayInfoReq);
		System.out.println("SMS-mlfrontPayInfoList.size():"+mlfrontPayInfoList.size());
		
		List<MlfrontPayInfo> mlfrontPayInfoSuccessList = new ArrayList<MlfrontPayInfo>();
		List<MlfrontPayInfo> mlfrontPayInfoUnPayList = new ArrayList<MlfrontPayInfo>();
		List<MlfrontPayInfo> mlfrontPayInfotrueUnPayList = new ArrayList<MlfrontPayInfo>();

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
				mlfrontPayInfotrueUnPayList.add(mlfrontPayInfoE);
				lastUnpOrderUname = unpOrderUname;
			}
		}
		
		//重复单标记状态2
		updateSameUnpay(sameUnPayIdList);
		//成功单标记状态3
		updateSuccessPay(mlfrontPayInfoSuccessList);
		
		return mlfrontPayInfotrueUnPayList;
	}
	
	//把相同的单子表集成2-重复单子
	private void updateSameUnpay(List<Integer> sameUnPayIdList) {
		for(Integer payinfoId:sameUnPayIdList){
			MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
			mlfrontPayInfoOne.setPayinfoId(payinfoId);
			mlfrontPayInfoOne.setPayinfoIfSMS(2);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			System.out.println("SMS本条是重复单-无需发送,标记为无需发送的状态2");
		}
	}
	
	//成功单标记成功单,无需发送
	private void updateSuccessPay(List<MlfrontPayInfo> mlfrontPayInfoSuccessList) {
		for(MlfrontPayInfo mlfrontPayInfoInto:mlfrontPayInfoSuccessList){
			Integer payinfoId = mlfrontPayInfoInto.getPayinfoId();
			MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
			mlfrontPayInfoOne.setPayinfoId(payinfoId);
			mlfrontPayInfoOne.setPayinfoIfSMS(3);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
			System.out.println("SMS本条是成功记录-无需发送,标记为成功状态3");
		}
	}

}
