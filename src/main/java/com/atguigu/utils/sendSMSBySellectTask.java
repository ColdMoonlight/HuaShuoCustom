package com.atguigu.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlbackSmstype;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MoneySmscheckout;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackHtmlEmailService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackSmstypeService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.MoneySmscheckoutService;

@Component
public class sendSMSBySellectTask {
	
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
	
	@Autowired
	MoneySmscheckoutService moneySmscheckoutService;
	
	/**
	 * 1.0	zsh210304
	 * 点击按钮发送短信
	 * @param String PayInfoNumStr
	 * @return 
	 * */
	@Scheduled(cron = "0 0/20 * * * ?")
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
        //获取弃购短信的定制--内容
        //查询接口,发送时间定时的N分钟,间隔几小时,发送文案
        String SmstypeContent = getSmstypeContent();
        
        //4.0
        //获取当前时间段内的支付-未支付的单子list
        List<MlfrontPayInfo> MlfrontPayInfoNeedSMSList = getNeedSendFromPayInfoList(MlfrontPayInfoList);
        
    	//5.0准备发手机号码
    	readySendPList(MlfrontPayInfoNeedSMSList,webSiteUrl,SmstypeContent);
        
    	
	}

	//这是最终的需要发送短信的数据


	/***************************************子方法**开始********************************************/

	//1.0获取弃购短信的文案;从邮件模板里面
	private MlbackOrderStateEmail getAbondonSMSData() {
		
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
        mlbackOrderStateEmailReq.setOrderstateemailName("AbondonSMS");
        
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
		mlfrontPayInfoReq.setPayinfoIfSMS(0);
		
		mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
		System.out.println("SMS-startTime"+startTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		System.out.println("SMS-endTime"+endTime);
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndIfEmail(mlfrontPayInfoReq);
		System.out.println("EmailTask-mlfrontPayInfoList.size():"+mlfrontPayInfoList.size());
		
		return mlfrontPayInfoList;
	}
	
	//3.0获取弃购短信的文案;从邮件模板里面
	private String getSmstypeContent() {
		
		String Content = "";
		MlbackSmstype mlbackSmstype = new MlbackSmstype();
        mlbackSmstype.setSmstypeName("sms");
        List<MlbackSmstype> mlbackSmstypeList = mlbackSmstypeService.selectMlbackSmstypeByName(mlbackSmstype);
        if(mlbackSmstypeList.size()>0){
			MlbackSmstype mlbackSmstypeOne = mlbackSmstypeList.get(0);
			Integer SmstypeStatus =  mlbackSmstypeOne.getSmstypeStatus();
			if(SmstypeStatus>0){
				Content = mlbackSmstypeOne.getSmstypeContent();
				System.out.println("SMS-本短信的挽回语为:Content"+Content);
			}
        }
		return Content;
	}
	
	/**
	 * 4.0
     * 遍历payinfolist
     * 成功的插入其中,并移除List中当前的数据,将本条数据插入发送表,标记无需发送（成功单标记状态3）
     * 
     * 剩下的遍历,
     * 找到跟成功的相同的orderID 的单子,remove掉。全部标记为重复单（IfSMS==2重复单标记状态2）--
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
				//4.1.1更改成功订单的状态IfSMS--3
				updateSuccessOnePay(mlfrontPayInfoOne);
				
			}
		}
		
		System.out.println("orderIdLast:"+orderIdLast);
		
		/****************************2次****************************/
		List<MlfrontPayInfo> mlfrontPayInfoUnPayRemoveSameOidList = new ArrayList<MlfrontPayInfo>();
		
		for(MlfrontPayInfo mlfrontPayInfoSuccessSameOidUnPayOne:mlfrontPayInfoUnPayList){
			
			Integer orderId = mlfrontPayInfoSuccessSameOidUnPayOne.getPayinfoOid();
			
			//4.2
			Integer ifHave = checkIfAlreadyHaveByOid(orderId);
			
			if(ifHave>0){
				//4.2.1这一单是重复单子,需要标记重复
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
				//4.2.1这一单是重复单子,需要标记重复
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
				//4.2.1这一单是重复单子(name重复),需要标记重复
				updateSameUnpayOne(mlfrontPayInfoUnPayOne);
			}else{
				
				//再查这一条是不是人名相同，如果有，删掉//如果也没查到，记录，准备发消息
				String orderName = mlfrontPayInfoUnPayOne.getPayinfoUname();
				Integer ifHaveName = checkIfAlreadyHaveByOname(orderName,orderIdLast);
				
				if(ifHaveName>0){
					//4.2.1这一单是重复单子(name重复),需要标记重复
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

	//4.1
	private void insertNowPayRecord(MlfrontPayInfo mlfrontPayInfoOne,String IfSuccessPayOrderStr) {
		
		MoneySmscheckout moneySmscheckout = new MoneySmscheckout();
		moneySmscheckout.setSmscheckoutPayid(mlfrontPayInfoOne.getPayinfoId());
		moneySmscheckout.setSmscheckoutOrderid(mlfrontPayInfoOne.getPayinfoOid());
		moneySmscheckout.setSmscheckoutOrdername(mlfrontPayInfoOne.getPayinfoUname());
		moneySmscheckout.setSmscheckoutSendtype(IfSuccessPayOrderStr);
		moneySmscheckoutService.insertSelective(moneySmscheckout);
	}
	
	/**
	 * 4.1.1更改成功订单的状态IfSMS--3
	 * */
	private void updateSuccessOnePay(MlfrontPayInfo mlfrontPayInfoInto) {
		Integer payinfoId = mlfrontPayInfoInto.getPayinfoId();
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		mlfrontPayInfoOne.setPayinfoId(payinfoId);
		mlfrontPayInfoOne.setPayinfoIfSMS(3);
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
		System.out.println("SMS本条是成功记录-无需发送,标记为成功状态3");
		
	}
	
	//4.2
	private Integer checkIfAlreadyHaveByOid(Integer smscheckoutOrderid) {
		
		MoneySmscheckout moneySmscheckoutReq = new MoneySmscheckout();
		moneySmscheckoutReq.setSmscheckoutOrderid(smscheckoutOrderid);
		
		List<MoneySmscheckout> moneySmscheckoutList = moneySmscheckoutService.selectMoneySmscheckoutByPayOId(moneySmscheckoutReq);
		if(moneySmscheckoutList.size()>0){
			return 1;
		}else{
			return 0;
		}
		
	}
	
	//4.2.1把相同的单子表集成2-重复单子
	private void updateSameUnpayOne(MlfrontPayInfo mlfrontPayInfoOneInto) {
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		mlfrontPayInfoOne.setPayinfoId(mlfrontPayInfoOneInto.getPayinfoId());
		mlfrontPayInfoOne.setPayinfoIfSMS(2);
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoOne);
		System.out.println("SMS本条是重复单-无需发送,标记为无需发送的状态2");
	}
	
	//4.3
	private Integer checkIfAlreadyHaveByOname(String orderName, Integer orderIdLast) {

		MoneySmscheckout moneySmscheckoutReq = new MoneySmscheckout();
		moneySmscheckoutReq.setSmscheckoutOrdername(orderName);
		moneySmscheckoutReq.setSmscheckoutId(orderIdLast);
		
		List<MoneySmscheckout> moneySmscheckoutList = moneySmscheckoutService.selectMoneySmscheckoutByPayOnameAndOid(moneySmscheckoutReq);
		if(moneySmscheckoutList.size()>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	//5.0发送邮件
	private void readySendPList(List<MlfrontPayInfo> mlfrontPayInfoNeedList,String webSiteUrl,String Content) {
		
		for(MlfrontPayInfo mlfrontPayInfoOne:mlfrontPayInfoNeedList){
			
			Integer payId = mlfrontPayInfoOne.getPayinfoId();
			System.out.println("SMS-实际电话号码realTel的payId:"+payId);
			Integer checkRecoverOrderId = mlfrontPayInfoOne.getPayinfoOid();
			
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
			
			
			//5.1获取转译后的手机号码
			String realTel = getRealTel(countryCode,telephone);
			System.out.println("SMS-实际电话号码realTel:"+realTel);
			
			String userName = firstName+" ";
			//---------------拿到orderId,去地址表中查询addressId,再从地址信息中查询邮箱手机号-------end--------
			String checkRecoverOrderIdStr = checkRecoverOrderId+"";
			
			String websiteStr = webSiteUrl;
			
			String secretOrderIdStr = EncryptUtil.XORencode(checkRecoverOrderIdStr,"megalook");
			
			String SendStr = "【MegaLook】Dear "+userName+","+Content+"."+websiteStr+"SMS/"+checkRecoverOrderIdStr+".html";
//			System.out.println("本单号位checkRecoverOrderIdStr："+checkRecoverOrderIdStr+",本条弃购链接为SendStr:"+SendStr);
			String SendSecretStr = "【MegaLook】Dear "+userName+","+Content+"."+websiteStr+"SMS/"+secretOrderIdStr+".html";
//			System.out.println("本单号位checkRecoverOrderIdStr："+checkRecoverOrderIdStr+",本条弃购链接为SendSecretStr:"+SendSecretStr);
			
			//这里要解密
//			String unSecretOrderIdStr = EncryptUtil.XORdecode(secretOrderIdStr,"megalook");
//			String SendUnSecretStr = Content+"."+websiteStr+"recover/"+unSecretOrderIdStr+".html";
//			System.out.println("本单号位checkRecoverOrderIdStr："+checkRecoverOrderIdStr+",本条弃购链接为SendUnSecretStr:"+SendUnSecretStr);
			
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
		}
			
			
	}
	
	
	/***************************************子方法*结束*********************************************/

	////5.1获取转译后的手机号
	private String getRealTel(String countryCodeInto, String telephone) {
		
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		
		mlbackAreafreightReq.setAreafreightCountryCode(countryCodeInto);
		
		List<MlbackAreafreight> mlbackAreafreightList = mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		
		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
		
		String realTelephone ="";
		if(mlbackAreafreightList.size()>0){
			mlbackAreafreightOne = mlbackAreafreightList.get(0);
			
			Integer telPrefix = mlbackAreafreightOne.getAreafreightTelPrefix();
			//5.1.1--通过国家拿手机号码的前缀
			realTelephone = checkTel(countryCodeInto,telPrefix,telephone);
			
			return realTelephone;
		}else{
			mlbackAreafreightOne = null;
			return "";
		}
	}

	/**
	 * //5.1.1--通过国家拿手机号码的前缀
	 * 查询当前国际code,查出对应的号码区号;
	 * 查看当前手机号码开头是否包含这段区号;
	 * 如果包含,就返回首页.如果不包含,就加上;
	 * */
	private String checkTel(String countryCodeInto, Integer telPrefix, String telephone) {
		
		String telPrefixStr = telPrefix+"";
		
		String finalTel = telephone;
		if("US".equals(countryCodeInto)){
			//United States
			if(telephone.startsWith("1")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
			//finalTel="86"+telephone;
		}else if("CA".equals(countryCodeInto)){
			//Canada
			if(telephone.startsWith("1")){
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
		}else if("ZA".equals(countryCodeInto)){
			//South Africa
			if(telephone.startsWith("27")){
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
		}else if("AU".equals(countryCodeInto)){
			//Australia
			if(telephone.startsWith("61")){
				finalTel = telephone;
			}else{
				finalTel=telPrefixStr+telephone;
			}
		}else if("DE".equals(countryCodeInto)){
			//Germany
			if(telephone.startsWith("49")){
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
		}else{
			
		}
		
		return finalTel;
	}

}
