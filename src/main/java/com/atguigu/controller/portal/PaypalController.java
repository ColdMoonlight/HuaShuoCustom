package com.atguigu.controller.portal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlPaypalShipAddress;
import com.atguigu.bean.MlPaypalStateprovince;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.portal.ToPaypalInfo;
import com.atguigu.common.Msg;
import com.atguigu.enumC.PaypalPaymentIntent;
import com.atguigu.enumC.PaypalPaymentMethod;
import com.atguigu.service.MlPaypalShipAddressService;
import com.atguigu.service.MlPaypalStateprovinceService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.service.PaypalService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.PropertiesUtil;
import com.atguigu.utils.URLUtils;
import com.atguigu.utils.EcppIntoUtil;
import com.atguigu.utils.EmailNewUtilshtml;
import com.atguigu.utils.EmailNewUtilshtmlCustomer;
import com.atguigu.vo.order;
import com.paypal.api.payments.ErrorDetails;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/paypal")
public class PaypalController {
	//wap???????????????
	public static final String PAYPAL_SUCCESS_M_URL = "paypal/msuccess";
    public static final String PAYPAL_CANCEL_M_URL = "paypal/mcancel";
    public static final String PAYPAL_SUCCESS_M_URLIn = "msuccess";
    public static final String PAYPAL_CANCEL_M_URLIn = "mcancel";

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
    
    @Autowired
	MlfrontOrderService mlfrontOrderService;
    
    @Autowired
    MlfrontOrderItemService mlfrontOrderItemService;
    
    @Autowired
    MlfrontAddressService mlfrontAddressService;
    
    @Autowired
    MlPaypalShipAddressService mlPaypalShipAddressService;
    
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackOrderStateEmailService mlbackOrderStateEmailService;
	
	@Autowired
	MlPaypalStateprovinceService mlPaypalStateprovinceService;

    /**1.0
     * ????????????,WAP????????????????????????
     * paypal/mpay
     * */
    @RequestMapping(method = RequestMethod.POST, value = "mpay")
    @ResponseBody
    public Msg pay(HttpServletRequest request,HttpSession session,@RequestParam(value = "paypalPlatFrom", defaultValue = "0") String paypalPlatFrom){
    	    	
    	System.out.println("into**********/paypal/mpay**********");
    	//1.1,???????????????,???session?????????getPayInfo??????
    	ToPaypalInfo toPaypalInfo = getPayInfo(session);
    	//1.2,???????????????,???session??????????????????????????????
    	String Shopdiscount = getCouponMoney(session);
    	//1.3,???????????????,???session?????????????????????
    	String addressMoney = getAddressMoney(session);
    	//1.4,???????????????,???session?????????????????????
    	MlfrontAddress mlfrontAddress = getMlfrontAddress(session);
    	//1.5,???????????????,???session?????????orderList??????
    	List<MlfrontOrderItem> mlfrontOrderItemList = getMlfrontOrderItemList(session);
    	
    	BigDecimal money = toPaypalInfo.getMoneyNum();
    	String moneyStr = money.toString();
    	Double moneyDouble = Double.parseDouble(moneyStr);
    	String moneyTypeStr = toPaypalInfo.getMoneyType();
    	String payDes = toPaypalInfo.getPaymentDescription();

    	//??????paypal??????
        String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_M_URL;
        String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_M_URL;
        
        Payment payment = new Payment();
        PaypalService paypalService = new PaypalService();
        
        String PaypalErrorName="";
        List<ErrorDetails> paypalErrorList= null;
        try {
            payment = paypalService.createPayment(
            		moneyDouble,// 888.00, 
            		moneyTypeStr, //"USD",  
            		mlfrontAddress,//mlfrontAddress
            		mlfrontOrderItemList,
                    PaypalPaymentMethod.paypal, //paypal
                    PaypalPaymentIntent.sale,//paypal
                    payDes,//"payment description", 
                    Shopdiscount,//"CouponMoney", 
                    addressMoney,//shopping
                    cancelUrl, 
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                	System.out.println("links.getHref:"+links.getHref());
                    //return "redirect:" + links.getHref();
                	if(("0").equals(paypalPlatFrom)){
                		//0paypal
                		return Msg.success().add("ifPaypalCheckSuccess", 1).add("redirectUrl", links.getHref());
                	}else{
                		String str = links.getHref();
                        String str1=str.substring(0, str.indexOf("token="));
                        System.out.println("str:"+str);
                        System.out.println("str1:"+str1);
                        String str2=str.substring(str1.length(), str.length());
                        System.out.println("str2:"+str2);
                        //1?????????????????????
                        //String payCartUrl = "https://www.paypal.com/checkoutweb/signup?"+str2;
                        //2?????????????????????
                        String payCartUrl = "https://www.sandbox.paypal.com/checkoutweb/signup?"+str2;
                        System.out.println("payCartUrl:  "+payCartUrl);
                		return Msg.success().add("ifPaypalCheckSuccess", 1).add("redirectUrl", payCartUrl);
                	}
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
            System.out.println("----------/paypal/mpay/Exception----------");
            System.out.println("---------e.getMessage()-----begin------");
            System.out.println(e.getMessage());
            System.out.println("---------e.getMessage()------end-------");
            System.out.println("---------e.getDetails()-----begin------");
            System.out.println(e.getDetails());
            if(e.getDetails()==null){
            	//PaypalErrorName = "retry fails..  check log for more information";
            	PaypalErrorName = "network error,"+"pls try once again";
            	//return Msg.success().add("ifPaypalCheckSuccess", 2).add("errorDetail", PaypalErrorName);
            }else{
            	PaypalErrorName = e.getDetails().getName();
            	paypalErrorList = e.getDetails().getDetails();
            	if(paypalErrorList.size()>1){
            		//city,state,zip?????????
            		PaypalErrorName = "pls check your information, make sure that city,state,zip code  is  match your address";
            	}else{
            		//???????????????
            		String errStr = paypalErrorList.get(0).getField();
            		String errStrll = errStr.replace(".", ",");
            		System.out.println("errStrll:"+errStrll);
            		
            		String errStrArr[] = errStrll.split(",");
            		Integer errLen = errStrArr.length;
            		System.out.println("errLen:"+errLen);
            		String lastStr = errStrArr[errLen-1];
            		
            		if(lastStr.equals("phone")){
            			PaypalErrorName = "Pls fill right phone number with Digital 0-9,which shouldn't included Any Alphabet and Symbol .";
            		}else{
            			PaypalErrorName ="Please check the "+lastStr+" . and Pls try again";
            		}
            	}
            }
            System.out.println("??????????????????????????????PaypalErrorName:"+PaypalErrorName);
            System.out.println("---------e.getDetails()------end------");
            return Msg.success().add("ifPaypalCheckSuccess", 2).add("errorDetail", PaypalErrorName);
        }
        return Msg.success().add("ifPaypalCheckSuccess", 0).add("redirectUrl", "MlbackCart/toCheakOut");
    }
    
    /**2.0
     * wap?????????????????????
     * mfront/paySuccess
     * */
    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_M_URLIn)
    public String successPay(HttpSession session,@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){

    	try {
    		PaypalService paypalService = new PaypalService();
            Payment payment = paypalService.executePayment(paymentId, payerId);
            
            session.setAttribute("successpaymentId", paymentId);
            session.setAttribute("successpayerId", payerId);
            session.setAttribute("successpayment", payment);
            //2.1wap+pc?????????toUpdatePayInfoStateSuccess
            //1???????????????,2??????payinfo?????????,????????????payment?????????VIPId=payinfoId
            MlPaypalShipAddress mlPaypalShipAddressReturn = toUpdatePayInfoStateSuccess(session,payerId,paymentId,payment);
        	//2.2??????order?????????
        	toUpdateOrderInfoSuccess(session,payment,mlPaypalShipAddressReturn);
        	//????????????payment??????
        	System.out.println("---------------------payment.toJSON()---------------------");
            System.out.println(payment.toJSON());
            System.out.println("---------------------payment.toJSON()---------------------");
            if(payment.getState().equals("approved")){
            	return "redirect:/Success.html";
            }else{
            	return "portal/CartOrderPay/payFail";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
            System.out.println("----wap????????????????????????Exception-----e.getMessage()-----begin------");
            System.out.println(e.getMessage());
            System.out.println("----wap????????????????????????Exception-----e.getMessage()-----end------");
        }
    	return "redirect:/MlbackCart/toCheakOut";
    }

    /**
	 * 1.1??????getPayInfo??????
     * ???????????????,???session?????????getPayInfo??????
     * */
    private ToPaypalInfo getPayInfo(HttpSession session) {
    	//???session???????????????
    	MlfrontAddress mlfrontAddressToPay = (MlfrontAddress) session.getAttribute("mlfrontAddressToPay");
    	//???session?????????payinfoId,????????????Desc???,??????paypal?????????,???????????????
    	Integer payinfoId = (Integer) session.getAttribute("payinfoId");
    	String payinfoIdStr = payinfoId+"";
    	BigDecimal totalprice = (BigDecimal) session.getAttribute("totalprice");
    	ToPaypalInfo toPaypalInfo = new ToPaypalInfo();
		//????????????????????????
		String toPayTelephone = mlfrontAddressToPay.getAddressTelephone();
		String toPayCountry = mlfrontAddressToPay.getAddressCountry();
		String toPayProvince = mlfrontAddressToPay.getAddressProvince();
		String toPayCity = mlfrontAddressToPay.getAddressCity();
		String toPayDetail = mlfrontAddressToPay.getAddressDetail();
		String toPayUserfirstname = mlfrontAddressToPay.getAddressUserfirstname();
		String toPayUserlastname = mlfrontAddressToPay.getAddressUserlastname();
		//????????????
		String toPayDesc = "";
		toPayDesc+="VIP";
		toPayDesc+=payinfoIdStr+",";
		toPayDesc+=toPayTelephone+",";
		toPayDesc+=toPayCountry+",";
		toPayDesc+=toPayProvince+",";
		toPayDesc+=toPayCity+",";
		toPayDesc+=toPayDetail+",";
		toPayDesc+=toPayUserfirstname+",";
		toPayDesc+=toPayUserlastname;
		toPaypalInfo.setMoneyNum(totalprice);
		toPaypalInfo.setMoneyType("USD");
		toPaypalInfo.setPaymentDescription(toPayDesc);
		return toPaypalInfo;
	}
    /**
	 * 1.2??????????????????CouponMoney
     * ???????????????,???session??????????????????????????????
     * */
	private String getCouponMoney(HttpSession session) {
    	String Shopdiscount = (String) session.getAttribute("CouponCodeMoney");
    	System.out.println("???session??????????????????????????????-Shopdiscount:"+Shopdiscount);
		return Shopdiscount;
	}
	/**
	 * 1.3session???????????????AddressMoney
     * ???????????????,???session???????????????AddressMoney
     * */
    private String getAddressMoney(HttpSession session) {
    	String addressMoney = (String) session.getAttribute("addressMoney");
    	System.out.println("???session?????????????????????-addressMoney:"+addressMoney);
		return addressMoney;
	}
    /**
     * 1.4???session?????????????????????
     * ???????????????,???session?????????????????????
     * */
    private MlfrontAddress getMlfrontAddress(HttpSession session) {
    	MlfrontAddress mlfrontAddressToPay = (MlfrontAddress) session.getAttribute("mlfrontAddressToPay");
		return mlfrontAddressToPay;
	}
    /**
     * 1.5???session?????????orderList??????
     * ???????????????,???session?????????orderList??????
     * */
    private List<MlfrontOrderItem> getMlfrontOrderItemList(HttpSession session) {
    	Integer orderId = (Integer) session.getAttribute("orderId");
    	
    	MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
    	mlfrontOrderReq.setOrderId(orderId);
    	List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
    	//??????????????????,????????????
    	MlfrontOrder mlfrontOrderRes = mlfrontOrderList.get(0);//???????????????
    	//??????????????????,????????????
    	String orderitemidstr = mlfrontOrderRes.getOrderOrderitemidstr();
    	String orderitemidArr[] = orderitemidstr.split(",");
    	
    	MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
    	MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
    	
    	List<MlfrontOrderItem> mlfrontOrderItemsList = new ArrayList<MlfrontOrderItem>();
    	for(int i=0;i<orderitemidArr.length;i++){
			Integer orderItemId = Integer.parseInt(orderitemidArr[i]);
			mlfrontOrderItemReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			mlfrontOrderItemsList.add(mlfrontOrderItemRes);
		}
		return mlfrontOrderItemsList;
	}
    
    /**20200611
     * 2.1wap+pc???
     * ??????toUpdatePayInfoStateSuccess
     * ???????????????,?????????????????????payinfo??????
     * @param payment 
     * */
    private MlPaypalShipAddress toUpdatePayInfoStateSuccess(HttpSession session, String payerId, String paymentId, Payment payment) {
    	//????????????????????????Transactions,
    	Transaction TransactionReturn = payment.getTransactions().get(0);
    	//????????????id,????????????,????????????,
    	String transactionId = TransactionReturn.getRelatedResources().get(0).getSale().getId();
    	String paypalDescription =  TransactionReturn.getDescription();
    	String transactionState = TransactionReturn.getRelatedResources().get(0).getSale().getState();   	
    	String shippingTelPhone = TransactionReturn.getItemList().getShippingPhoneNumber();
    	//????????????????????????PayerInfo,
    	PayerInfo payerInfoReturn = payment.getPayer().getPayerInfo();
		//?????????,name,????????????
    	String paypayEmail = payerInfoReturn.getEmail();
    	String paypayFirstName = payerInfoReturn.getFirstName();
    	String paypayLastName = payerInfoReturn.getLastName();
    	
    	//????????????????????????Transactions
    	String paypalDescriptionArr[] = paypalDescription.split(",");
    	String DescVipIdStr = "";
    	String DescIdStr = "";
    	if(paypalDescriptionArr.length>1){
			//1.1???????????????????????????????????????
    		DescVipIdStr = paypalDescriptionArr[0];
		}
    	DescIdStr = DescVipIdStr.replace("VIP", "");
    	Integer payinfoId =  Integer.parseInt(DescIdStr);
    	session.setAttribute("payinfoId", payinfoId);
    	//2.1.1paypal???????????????????????????????????????--????????????????????????,????????????payment???toString
    	//?????????????????????paypal?????????????????????
        String paymentStr = payment.toString();
        System.out.println("payment.toString().length()"+paymentStr.length());
        MlPaypalShipAddress mlPaypalShipAddressReturn = new MlPaypalShipAddress();
        mlPaypalShipAddressReturn = insertMlPaypalShipAddressInfo(paymentId,DescIdStr,payerInfoReturn,paymentStr,shippingTelPhone);
    	//?????????????????????
    	MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoId(payinfoId);
		List<MlfrontPayInfo> MlfrontPayInfoList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoNew);
		MlfrontPayInfo mlfrontPayInfoIOne = MlfrontPayInfoList.get(0);
		//????????????payOid
		Integer PayOid = mlfrontPayInfoIOne.getPayinfoOid();
		mlfrontPayInfoIOne.setPayinfoStatus(1);//????????????
		mlfrontPayInfoIOne.setPayinfoPlatformserialcode(payerId);
		String nowTime = DateUtil.strTime14s();
		mlfrontPayInfoIOne.setPayinfoMotifytime(nowTime);
		mlfrontPayInfoIOne.setPayinfoReturntime(nowTime);
		//????????????????????????
		String payinfoIdStr = payinfoId+"";
		Integer orderlen = payinfoIdStr.length();
		if(orderlen==6){
			payinfoIdStr = "0"+payinfoIdStr;
		}else if(orderlen==5){
			payinfoIdStr = "00"+payinfoIdStr;
		}else if(orderlen==4){
			payinfoIdStr = "000"+payinfoIdStr;
		}else if(orderlen==3){
			payinfoIdStr = "0000"+payinfoIdStr;
		}else if(orderlen==2){
			payinfoIdStr = "00000"+payinfoIdStr;
		}else if(orderlen==1){
			payinfoIdStr = "000000"+payinfoIdStr;
		}
		String payInfoTime = DateUtil.getDateTime();
		String teamLogo = (String) PropertiesUtil.getProperty("megalook.properties", "teamLogo");
		//  ML(megalook)	HSH(huashuohair)
		String payinfoPlateNum = teamLogo+payInfoTime+payinfoIdStr;
		mlfrontPayInfoIOne.setPayinfoPlatenum(payinfoPlateNum);
		mlfrontPayInfoIOne.setPayinfoTransidnum(transactionId);
		mlfrontPayInfoIOne.setPayinfoTransStatus(transactionState);	
		mlfrontPayInfoIOne.setPayinfoUemail(paypayEmail);
		mlfrontPayInfoIOne.setPayinfoUname(paypayFirstName+" "+paypayLastName);
		
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoIOne);
		
		//2.1.2????????????ecpp??????,????????????????????????,??????ecpp???
		payInfoIntoEcpp(mlfrontPayInfoIOne,mlPaypalShipAddressReturn);
		session.setAttribute("mlfrontPayInfoIOne", mlfrontPayInfoIOne);
		session.setAttribute("payinfoIdStr", payinfoIdStr);//???????????????MLXXXXXXXXX0001;
		session.setAttribute("orderId", PayOid);
		
		return mlPaypalShipAddressReturn;
	}

	/**
     * 2.1.1paypal???????????????????????????????????????
     * insertMlPaypalShipAddressInfo
     * ??????????????????Paypal??????
     * @param payment 
     * */
    private MlPaypalShipAddress insertMlPaypalShipAddressInfo(String paymentId, String payinfoIdStr, PayerInfo payerInfoReturn,String paymentStr,String shippingTelPhone) {
    	
    	//???????????????code,??????Code
    	MlPaypalStateprovince mlPaypalStateprovinceReq = new MlPaypalStateprovince();
    	String CountryCode= payerInfoReturn.getShippingAddress().getCountryCode();
    	String provinceCode= payerInfoReturn.getShippingAddress().getState();
    	
    	if(provinceCode.length()>0){
    		//??????????????????,???????????????
    		mlPaypalStateprovinceReq.setStateprovinceCountryCode(CountryCode);
    		mlPaypalStateprovinceReq.setStateprovinceNameCode(provinceCode);
    		String provinceName = "";
    		String countryName = "";
    		
    		List<MlPaypalStateprovince> mlPaypalStateprovinceList =  mlPaypalStateprovinceService.selectMlPaypalStateprovinceByCountryCodeAndProvinceCode(mlPaypalStateprovinceReq);
    		if(mlPaypalStateprovinceList.size()>0){
    			provinceName =  mlPaypalStateprovinceList.get(0).getStateprovinceName();
    			countryName =  mlPaypalStateprovinceList.get(0).getStateprovinceCountry();
    		}
    		
    		MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
    		mlPaypalShipAddressReq.setShippingaddressCountryCode(payerInfoReturn.getShippingAddress().getCountryCode());
    		mlPaypalShipAddressReq.setShippingaddressCountryName(countryName);
    		mlPaypalShipAddressReq.setShippingaddressTelNumber(shippingTelPhone);
    		mlPaypalShipAddressReq.setShippingaddressState(payerInfoReturn.getShippingAddress().getState());
    		mlPaypalShipAddressReq.setShippingaddressStateProvinceName(provinceName);
    		mlPaypalShipAddressReq.setShippingaddressCity(payerInfoReturn.getShippingAddress().getCity());
    		mlPaypalShipAddressReq.setShippingaddressPostalCode(payerInfoReturn.getShippingAddress().getPostalCode());
    		mlPaypalShipAddressReq.setShippingaddressLine1(payerInfoReturn.getShippingAddress().getLine1());
    		mlPaypalShipAddressReq.setShippingaddressLine2(payerInfoReturn.getShippingAddress().getLine2());
    		mlPaypalShipAddressReq.setShippingaddressRecipientName(payerInfoReturn.getShippingAddress().getRecipientName());
    		mlPaypalShipAddressReq.setShippingaddressEmail(payerInfoReturn.getEmail());
    		mlPaypalShipAddressReq.setShippingaddressPayinfoid(payinfoIdStr);
    		mlPaypalShipAddressReq.setShippingaddressPaymentid(paymentId);
    		mlPaypalShipAddressReq.setShippingaddressPaymentStr(paymentStr);
    		mlPaypalShipAddressService.insertSelective(mlPaypalShipAddressReq);
    		
    		return mlPaypalShipAddressReq;
    	}else{
    		//???????????????,
    		mlPaypalStateprovinceReq.setStateprovinceCountryCode(CountryCode);
    		String provinceName = "";
    		String countryName = "";
    		
    		List<MlPaypalStateprovince> mlPaypalStateprovinceList =  mlPaypalStateprovinceService.selectMlPaypalStateprovinceByCountryCode(mlPaypalStateprovinceReq);
    		if(mlPaypalStateprovinceList.size()>0){
    			countryName =  mlPaypalStateprovinceList.get(0).getStateprovinceCountry();
    		}
    		
    		MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
    		mlPaypalShipAddressReq.setShippingaddressCountryCode(payerInfoReturn.getShippingAddress().getCountryCode());
    		mlPaypalShipAddressReq.setShippingaddressCountryName(countryName);
    		mlPaypalShipAddressReq.setShippingaddressTelNumber(shippingTelPhone);
    		mlPaypalShipAddressReq.setShippingaddressState(payerInfoReturn.getShippingAddress().getState());
    		mlPaypalShipAddressReq.setShippingaddressStateProvinceName(provinceName);
    		mlPaypalShipAddressReq.setShippingaddressCity(payerInfoReturn.getShippingAddress().getCity());
    		mlPaypalShipAddressReq.setShippingaddressPostalCode(payerInfoReturn.getShippingAddress().getPostalCode());
    		mlPaypalShipAddressReq.setShippingaddressLine1(payerInfoReturn.getShippingAddress().getLine1());
    		mlPaypalShipAddressReq.setShippingaddressLine2(payerInfoReturn.getShippingAddress().getLine2());
    		mlPaypalShipAddressReq.setShippingaddressRecipientName(payerInfoReturn.getShippingAddress().getRecipientName());
    		mlPaypalShipAddressReq.setShippingaddressEmail(payerInfoReturn.getEmail());
    		mlPaypalShipAddressReq.setShippingaddressPayinfoid(payinfoIdStr);
    		mlPaypalShipAddressReq.setShippingaddressPaymentid(paymentId);
    		mlPaypalShipAddressReq.setShippingaddressPaymentStr(paymentStr);
    		mlPaypalShipAddressService.insertSelective(mlPaypalShipAddressReq);
    		
    		return mlPaypalShipAddressReq;
    	}
	}
    
    /**
     * 2.1.2????????????ecpp??????,????????????????????????,??????ecpp???
     * @param mlPaypalShipAddressReturn
     * */
    private void payInfoIntoEcpp(MlfrontPayInfo mlfrontPayInfoIn, MlPaypalShipAddress mlPaypalShipAddressReturn) {
    	
    	Integer payinfoId = mlfrontPayInfoIn.getPayinfoId();
    	
    	MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoId(payinfoId);
		List<MlfrontPayInfo> MlfrontPayInfoList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoNew);
		MlfrontPayInfo mlfrontPayInfoIOne = MlfrontPayInfoList.get(0);
		System.out.println(mlfrontPayInfoIOne.toString());
		
		//??????orderId
		Integer orderId = mlfrontPayInfoIOne.getPayinfoOid();
		String paypalIdStr = mlfrontPayInfoIOne.getPayinfoTransidnum();
		
		//??????MlfrontOrderReq
		MlfrontOrder mlfrontOrderPayReq = new MlfrontOrder();
		mlfrontOrderPayReq.setOrderId(orderId);
		//????????????
		List<MlfrontOrder> mlfrontOrderList =  mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPayReq);
		System.out.println("mlfrontOrderList:"+mlfrontOrderList.toString());
		MlfrontOrder mlfrontOrderResOne = mlfrontOrderList.get(0);
		
		String orderitemidstr = mlfrontOrderResOne.getOrderOrderitemidstr();
		String orderitemidArr[] = orderitemidstr.split(",");
		List<MlfrontOrderItem> mlfrontOrderItemEcppNeedList =new ArrayList<MlfrontOrderItem>();
		for(int x=0;x<orderitemidArr.length;x++){
			MlfrontOrderItem mlfrontOrderItem = new MlfrontOrderItem();
			int orderItemId = Integer .parseInt(orderitemidArr[x]);
			mlfrontOrderItem.setOrderitemId(orderItemId);
			List<MlfrontOrderItem>  mlfrontOrderItemList= mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItem);
			MlfrontOrderItem mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			mlfrontOrderItemEcppNeedList.add(mlfrontOrderItemRes);
		}
		
		//????????????????????????id
		Integer payAddressinfoId = mlfrontOrderResOne.getOrderAddressinfoId();
		MlfrontAddress mlfrontAddress =new MlfrontAddress();
		mlfrontAddress.setAddressId(payAddressinfoId);
		List<MlfrontAddress> mlfrontAddressToPayList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddress);
		
		MlfrontAddress mlfrontAddressToPay = mlfrontAddressToPayList.get(0);
		
		order ecppOrderResult = EcppIntoUtil.getEcppNeedOrder(mlfrontPayInfoIOne,mlfrontOrderResOne,mlfrontOrderItemEcppNeedList,mlfrontAddressToPay,mlPaypalShipAddressReturn);
		
		String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
//		String token="Lujia2015200708";
		
		String soapXML = EcppIntoUtil.getXML(token,ecppOrderResult);
		
		String EcppHSNum = "";
		try {
			EcppHSNum = EcppIntoUtil.send(token,soapXML,paypalIdStr);
			
			//???ecpp?????????EcppHSNum,???????????????mlfrontPayInfo???
	    	
	    	MlfrontPayInfo mlfrontPayInfoEcppreturnReq = new MlfrontPayInfo();
	    	mlfrontPayInfoEcppreturnReq.setPayinfoId(payinfoId);
	    	mlfrontPayInfoEcppreturnReq.setPayinfoEcpphsnum(EcppHSNum);
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoEcppreturnReq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
     * 2.2wap/pc?????????toUpdateOrderInfoSuccess
	 * @param payment
     * */
	private void toUpdateOrderInfoSuccess(HttpSession session,Payment payment,MlPaypalShipAddress mlPaypalShipAddressReturn) {
    	
		//????????????????????????Transactions,
    	Transaction TransactionReturn = payment.getTransactions().get(0);
    	//???????????????????????????????????????payinfoId,
    	String paypalDescription =  TransactionReturn.getDescription();
    	String paypalDescriptionArr[] = paypalDescription.split(",");
    	String DescVipIdStr = "";
    	String DescIdStr = "";
    	if(paypalDescriptionArr.length>1){
			//1.1???????????????????????????????????????
    		DescVipIdStr = paypalDescriptionArr[0];
		}
    	DescIdStr = DescVipIdStr.replace("VIP", "");
    	Integer payinfoId =  Integer.parseInt(DescIdStr);
    	
    	MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoId(payinfoId);
		List<MlfrontPayInfo> MlfrontPayInfoList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoNew);
		MlfrontPayInfo mlfrontPayInfoIOne = MlfrontPayInfoList.get(0);
		//????????????payOid
		Integer orderId = mlfrontPayInfoIOne.getPayinfoOid();
		//??????order?????????
    	String nowTime = DateUtil.strTime14s();
    	System.out.println("orderId:"+orderId);
		//??????req
		MlfrontOrder mlfrontOrderPayReq = new MlfrontOrder();
		mlfrontOrderPayReq.setOrderId(orderId);
		//????????????
		List<MlfrontOrder> mlfrontOrderList =  mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPayReq);
		System.out.println("mlfrontOrderList:"+mlfrontOrderList.toString());
		MlfrontOrder mlfrontOrderResOne = mlfrontOrderList.get(0);
		//??????????????????
		mlfrontOrderResOne.setOrderStatus(1);
		mlfrontOrderResOne.setOrderMotifytime(nowTime);
		//????????????
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderResOne);
		//????????????????????????successPayinfoId,successOrderId??????session???
		session.setAttribute("successPayinfoId", payinfoId);
		session.setAttribute("successOrderId", orderId);
		
		String ShippingMoney = TransactionReturn.getRelatedResources().get(0).getSale().getAmount().getDetails().getShipping();;
		String addressMoney = ShippingMoney;
		//2.2.1	wap+pc??????????????????(??????????????????)
		sendResultEmail(session,mlfrontPayInfoIOne, mlfrontOrderResOne,addressMoney,mlPaypalShipAddressReturn);
		
		//???????????????vip??????+????????????
		MlfrontUser mlfrontUser= (MlfrontUser) session.getAttribute("loginUser");
		if(mlfrontUser==null){
			System.out.println("???????????????????????????,??????mlfrontUser??????????????????");
		}else{
			//??????session??????mlfrontUser?????????????????????userEmail
			String userEmail = mlfrontUser.getUserEmail();
			//??????userEmail,????????????
			MlfrontUser mlfrontUserByEmail = new MlfrontUser();
			mlfrontUserByEmail.setUserEmail(userEmail);
			List<MlfrontUser> mlfrontUserByEmailListres =  mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserByEmail);
			MlfrontUser mlfrontUserByEmailres = mlfrontUserByEmailListres.get(0);
			Integer uid = mlfrontUserByEmailres.getUserId();
			Integer userTimesOld = mlfrontUserByEmailres.getUserTimes();
			if(userTimesOld==null){
				userTimesOld = 0;
			}
			Integer userTimesafter =userTimesOld+1;
			Integer userVipLevelOld =mlfrontUserByEmailres.getUserViplevel();
			if(userVipLevelOld==null){
				userVipLevelOld = 0;
			}
			Integer userVipLevelafter = userVipLevelOld+1;
			mlfrontUserByEmailres.setUserId(uid);
			mlfrontUserByEmailres.setUserTimes(userTimesafter);
			mlfrontUserByEmailres.setUserViplevel(userVipLevelafter);
			mlfrontUserService.updateByPrimaryKeySelective(mlfrontUserByEmailres);
		}
	}

	/**
     * 2.2.1	wap+pc??????????????????
     * */
    private void sendResultEmail(HttpSession session,MlfrontPayInfo mlfrontPayInfoIOne, MlfrontOrder mlfrontOrderResOne, String addressMoney,MlPaypalShipAddress mlPaypalShipAddress) {
    	try {
    		
    		Integer orderId = mlfrontOrderResOne.getOrderId();
        	List<MlfrontOrderItem> mlfrontOrderItemList = successGetMlfrontOrderItemList(orderId);
        	
        	Integer addressId = mlfrontOrderResOne.getOrderAddressinfoId();
    		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
    		MlfrontAddress mlfrontAddressRes = new MlfrontAddress();
    		mlfrontAddressReq.setAddressId(addressId);
    		
    		List<MlfrontAddress> mlfrontAddressResList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
    		mlfrontAddressRes = mlfrontAddressResList.get(0);
    		String userEmail = mlfrontAddressRes.getAddressEmail();
    		
    		
    		String patSuccessEndLanguage = "";
    		//??????
    		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
    		mlbackOrderStateEmailReq.setOrderstateemailName("Paysuccessed");
    		//????????????
    		List<MlbackOrderStateEmail> mlbackOrderStateEmailList =mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
    		if(mlbackOrderStateEmailList.size()>0){
    			
    			MlbackOrderStateEmail mlbackOrderStateEmailOne =mlbackOrderStateEmailList.get(0);
    			
    			patSuccessEndLanguage = getToCustomerPaySuccessEmailManage(mlbackOrderStateEmailOne);
    		}
    		
			//????????????
			String getToEmail = userEmail;
			String Message = "pay Success</br>,?????????????????????,???????????????????????????,????????????????????????.??????????????????";
			EmailNewUtilshtml.readyEmailPaySuccess(getToEmail, Message,mlfrontOrderItemList,mlfrontPayInfoIOne,mlfrontOrderResOne,addressMoney,patSuccessEndLanguage,mlPaypalShipAddress,mlfrontAddressRes);
			EmailNewUtilshtmlCustomer.readyEmailPaySuccessCustomer(getToEmail, Message,mlfrontOrderItemList,mlfrontPayInfoIOne,mlfrontOrderResOne,addressMoney,patSuccessEndLanguage,mlPaypalShipAddress,mlfrontAddressRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    private String getToCustomerPaySuccessEmailManage(MlbackOrderStateEmail mlbackOrderStateEmailOne) {
		
    	String Message ="";
		String emailOneStr =  mlbackOrderStateEmailOne.getOrderstateemailOne();
		Message = Message + emailOneStr;
		return Message;
	}
    
    /**
     * 1.5.2???session?????????orderList??????
     * ???????????????,???orderId??????orderList??????
     * */
    private List<MlfrontOrderItem> successGetMlfrontOrderItemList(Integer orderId) {
    	
    	MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
    	mlfrontOrderReq.setOrderId(orderId);
    	List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
    	//??????????????????,????????????
    	MlfrontOrder mlfrontOrderRes = mlfrontOrderList.get(0);
    	//??????????????????,????????????
    	String orderitemidstr = mlfrontOrderRes.getOrderOrderitemidstr();
    	String orderitemidArr[] = orderitemidstr.split(",");
    	
    	MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
    	MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
    	List<MlfrontOrderItem> mlfrontOrderItemsList = new ArrayList<MlfrontOrderItem>();
    	for(int i=0;i<orderitemidArr.length;i++){
			Integer orderItemId = Integer.parseInt(orderitemidArr[i]);
			mlfrontOrderItemReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			mlfrontOrderItemsList.add(mlfrontOrderItemRes);
		}
		return mlfrontOrderItemsList;
	}

	/**3.0
     * ????????????page
     * mfront/payFail
     * */
	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_M_URLIn)
    public String cancelPay(HttpSession session){
		
		//??????????????????,????????????
        return "redirect:/MlbackCart/toCheakOut";
    }

}