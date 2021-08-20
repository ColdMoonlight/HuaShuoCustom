package com.atguigu.controller.portal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontCart;
import com.atguigu.bean.MlfrontCartItem;
import com.atguigu.bean.MlfrontCheckoutView;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.UrlCount;
import com.atguigu.bean.portal.ToPaypalInfo;
import com.atguigu.common.Msg;
import com.atguigu.enumC.PaypalPaymentIntent;
import com.atguigu.enumC.PaypalPaymentMethod;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontCartItemService;
import com.atguigu.service.MlfrontCartService;
import com.atguigu.service.MlfrontCheckoutViewService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.PaypalService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.URLUtils;
import com.paypal.api.payments.ErrorDetails;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/MlfrontOrderSuper")
public class MlfrontOrderSuperController {
	
	public static final String PAYPAL_SUCCESS_M_URL = "paypal/msuccess";
    public static final String PAYPAL_CANCEL_M_URL = "paypal/mcancel";
    public static final String PAYPAL_SUCCESS_M_URLIn = "msuccess";
    public static final String PAYPAL_CANCEL_M_URLIn = "mcancel";
		
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontCartItemService mlfrontCartItemService;
	
	@Autowired
	MlfrontCartService mlfrontCartService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackCouponService mlbackCouponService;
	
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontCheckoutViewService mlfrontCheckoutViewService;
	
	
	/**1.0	zsh210408
	 * 更新order表中的:地址字段,优惠券字段,优惠券折扣.
	 * @param MlfrontOrder
	 */
	@RequestMapping(value="/orderToPayInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg orderToPayInfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrder mlfrontOrder){
		
		String checkoutviewIdStr = mlfrontOrder.getOrderMotifytime();
		
		String cusTomerPidStr = mlfrontOrder.getOrderCreatetime();
		String pidItemAndMoneyStr ="";
		
		//0.0接受参数信息,替换留言中的表情字符
		String intoOrderMessage = mlfrontOrder.getOrderBuyMess();
		System.out.println("intoOrderMessage:"+intoOrderMessage);
		String afterMessage = intoOrderMessage.replaceAll("[^\\u0000-\\uFFFF]", "");
		System.out.println("afterOrderMessage:"+afterMessage);
		mlfrontOrder.setOrderBuyMess(afterMessage);
		Integer originalOrderId = mlfrontOrder.getOrderId();
		String filnanyNumber = mlfrontOrder.getOrderPronumStr();
		Integer CouponId =mlfrontOrder.getOrderCouponId();
		//System.out.println("点结算按钮的时候,接收到的CouponId:"+CouponId);
		Integer orderPayPlateInt = mlfrontOrder.getOrderPayPlate();//客户选择的支付方式,0paypal,1待定
		String buyMessStr = mlfrontOrder.getOrderBuyMess();
		//1.0用order查orderItem,遍历orderItem,计算每个Item的价格,再加在一起；
		String Orderitemidstr = mlfrontOrder.getOrderOrderitemidstr();
		String orderitemidArr[] = Orderitemidstr.split(",");
		BigDecimal totalprice = new BigDecimal(0);	//初始化最终价格参数
		DecimalFormat df1 = new DecimalFormat("0.00");
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemRes = new MlfrontOrderItem();
		String orderitemidArri="";
		for(int i=0;i<orderitemidArr.length;i++){
			BigDecimal oneAllprice = new BigDecimal(0);//01初始化字段,用来存本sku的钱
			System.out.println("orderitemidArr[i]:"+orderitemidArr[i]);
			orderitemidArri = orderitemidArr[i].trim();
			Integer orderItemId = Integer.parseInt(orderitemidArri);
			mlfrontOrderItemReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			BigDecimal ItemProductOriginalprice =mlfrontOrderItemRes.getOrderitemProductOriginalprice();
			String OrderitemPskuMoneystr = mlfrontOrderItemRes.getOrderitemPskuMoneystr();
			Integer number = mlfrontOrderItemRes.getOrderitemPskuNumber();
			Integer accoff = mlfrontOrderItemRes.getOrderitemProductAccoff();
			String PskuMoneystr[] = OrderitemPskuMoneystr.split(",");
			BigDecimal pskuMoneyOne = new BigDecimal(0);
			String pskuTrimStr="";
			for(int j =0;j<PskuMoneystr.length;j++){
				pskuTrimStr = PskuMoneystr[j].trim();
				pskuMoneyOne = new BigDecimal(pskuTrimStr);
				oneAllprice = oneAllprice.add(pskuMoneyOne);//02计算本orderItem下的所有sku项的钱
			}
			oneAllprice=oneAllprice.add(ItemProductOriginalprice);//03叠加本品基础价的钱(now：该条的allPskumoney+该条Pro的基础价)
			//计算这一项的价格,(基础价格+每个的sku价格的和)*折扣*数量,存入orderitemPskuReamoney字段中;
			oneAllprice = oneAllprice.multiply(new BigDecimal(number));//04乘本品的个数得到总价
			oneAllprice = oneAllprice.multiply(new BigDecimal(accoff));//05乘本品的折扣
			oneAllprice = oneAllprice.multiply(new BigDecimal(0.01));//06还原本品+sku集合的最终价
			String str = df1.format(oneAllprice);
			//System.out.println("OrderitemPskuReamoney原始值:"+oneAllprice);
			System.out.println("存进去的OrderitemPskuReamoney:"+str); //13.15
			//存储每一项的Money
			pidItemAndMoneyStr =pidItemAndMoneyStr+","+str;
			
			MlfrontOrderItem mlfrontOrderItemMoneyBlack = new MlfrontOrderItem();
			mlfrontOrderItemMoneyBlack.setOrderitemId(orderItemId);
			mlfrontOrderItemMoneyBlack.setOrderitemPskuReamoney(str);
			//更新本条,存入orderitemPskuReamoney字段
			mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemMoneyBlack);
			//一个字段存储总价格
			totalprice = totalprice.add(oneAllprice);//07总价字段累加该条的全部价格
		}
		if(pidItemAndMoneyStr.length()>0){
			pidItemAndMoneyStr=pidItemAndMoneyStr.substring(1,pidItemAndMoneyStr.length());
		}
		System.out.println("pidItemAndMoneyStr:"+pidItemAndMoneyStr);
		/*2.0
		 * 		加		单个的	(基础价格+每个的sku价格的和)*折扣*数量,
		 * 		减		2.0.1	优惠价格
		 * 		加		2.0.2	地址运费
		 * */
		//2.0.1.1拿到优惠码Code,
		String CouponCode = mlfrontOrder.getOrderCouponCode();
		//2.0.1.2查询该优惠码的优惠价格(优惠券,总价,客户选好的产品id串,计算好的对应产品item价格串)
		BigDecimal CouponCodeMoney = getCouponCodeMoney(CouponCode, totalprice,cusTomerPidStr,pidItemAndMoneyStr);
		String CouponCodeMoneyStr= df1.format(CouponCodeMoney);
		session.setAttribute("CouponCodeMoney", CouponCodeMoneyStr);//111--CouponCodeMoney;
		//减去优惠券优惠的钱
		totalprice = totalprice.subtract(CouponCodeMoney);
		
		//2.0.2计算地址价格,计算优惠价格,插入order项目价格
		//2.0.2.1拿到地址ID,
		Integer AddressId = mlfrontOrder.getOrderAddressinfoId();
		if(AddressId==null){
			AddressId= (Integer) session.getAttribute("realAddressId");//222--AddressId;
		}
		//2.0.2.2查询英文名,查询该英文名的价格运费价格
		Integer addressMoney = getAddressMoney(AddressId);
		String addressMoneyStr= df1.format(addressMoney);
		session.setAttribute("addressMoney", addressMoneyStr);//333--addressMoney;
		//2.0.2.3加上地区快递费
		totalprice = totalprice.add(new BigDecimal(addressMoney));
		//计算该订单的实际价格
		String totalpriceStr = df1.format(totalprice);
		//2.3更新order表(地区字段,优惠券字段,总价的价格,结算方式,留言,总价的价格)
		MlfrontOrder mlfrontOrderEnd = new  MlfrontOrder();
		mlfrontOrderEnd.setOrderId(originalOrderId);
		mlfrontOrderEnd.setOrderAddressinfoId(AddressId);//地区字段
		mlfrontOrderEnd.setOrderCouponId(CouponId);
		mlfrontOrderEnd.setOrderCouponCode(CouponCode);//优惠券字段
		mlfrontOrderEnd.setOrderCouponPrice(CouponCodeMoney);//优惠券money
		mlfrontOrderEnd.setOrderPayPlate(orderPayPlateInt);//结算方式
		mlfrontOrderEnd.setOrderPronumStr(filnanyNumber);//最终各个产品数量
		mlfrontOrderEnd.setOrderBuyMess(buyMessStr);//留言
		BigDecimal bigTotalprice = new BigDecimal(totalpriceStr);//总价的价格
		mlfrontOrderEnd.setOrderMoney(bigTotalprice);
		String nowTime = DateUtil.strTime14s();
		mlfrontOrderEnd.setOrderMotifytime(nowTime);
		//执行更新order表
		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderEnd);
		//3.0将order信息写入payInfo信息中心//uid oid 支付方式,交易订单号,支付信息
		//从orderId中查询uid,地址id,buyMess字段;
		Integer payAddressinfoId = mlfrontOrderEnd.getOrderAddressinfoId();
		MlfrontAddress mlfrontAddress =new MlfrontAddress();
		mlfrontAddress.setAddressId(payAddressinfoId);
		List<MlfrontAddress> mlfrontAddressToPayList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddress);
		MlfrontAddress mlfrontAddressToPay = mlfrontAddressToPayList.get(0);
		String addressName = mlfrontAddressToPay.getAddressUserfirstname()+" "+mlfrontAddressToPay.getAddressUserlastname();
		//把结算地址放进session中
		session.setAttribute("mlfrontAddressToPay", mlfrontAddressToPay);//444--mlfrontAddressToPay;
		session.setAttribute("totalprice", totalprice);//555--totalprice;
		MlfrontPayInfo mlfrontPayInfoNew = new MlfrontPayInfo();
		mlfrontPayInfoNew.setPayinfoOid(originalOrderId);
		mlfrontPayInfoNew.setPayinfoStatus(0);//0未支付1已支付
		if(orderPayPlateInt==null){
			orderPayPlateInt = 0;
		}
		if(orderPayPlateInt==0){
			mlfrontPayInfoNew.setPayinfoPlatform("paypal");
		}else{
			mlfrontPayInfoNew.setPayinfoPlatform("bank_Card");
		}

		//6获取returnmoney
		String orderAllMoney = GetOrderTopayinfoMoney(Orderitemidstr);
		String addressMoneyendStr = addressMoney+"";
		String amTotal = getamountTotal(orderAllMoney,CouponCodeMoneyStr,addressMoneyendStr);
		BigDecimal amTotalBig=new BigDecimal(amTotal);  
		
		mlfrontPayInfoNew.setPayinfoMoney(amTotalBig);//总钱数排除一分钱后的计算结果
		mlfrontPayInfoNew.setPayinfoCreatetime(nowTime);
		mlfrontPayInfoNew.setPayinfoMotifytime(nowTime);
		mlfrontPayInfoNew.setPayinfoReturntime(nowTime);
		mlfrontPayInfoNew.setPayinfoUname(addressName);
		mlfrontPayInfoService.insertSelective(mlfrontPayInfoNew);
		Integer payinfoId = mlfrontPayInfoNew.getPayinfoId();
		
		if(checkoutviewIdStr != null && checkoutviewIdStr.length() != 0){
			
			String checkoutviewIdStrend = checkoutviewIdStr.trim();
			if(checkoutviewIdStrend.length()>0){
				Integer checkoutviewId = Integer.parseInt(checkoutviewIdStr);
				//通过checkoutViewId,更新payinfoId
				String checkoutViewPayinfoIdStr = payinfoId+"";
				MlfrontCheckoutView mlfrontCheckoutViewReq = new MlfrontCheckoutView();
				mlfrontCheckoutViewReq.setCheckoutviewId(checkoutviewId);
				mlfrontCheckoutViewReq.setCheckoutviewPayinfoid(checkoutViewPayinfoIdStr);
				//放入优惠Code
				mlfrontCheckoutViewReq.setCheckoutviewCouponCode(CouponCode);
				//放入优惠券的价格
				mlfrontCheckoutViewReq.setCheckoutviewCouponPrice(CouponCodeMoneyStr);
				//放入邮费
				mlfrontCheckoutViewReq.setCheckoutviewAddressPrice(addressMoneyStr);
				mlfrontCheckoutViewService.updateByPrimaryKeySelective(mlfrontCheckoutViewReq);
			}
		}
		
		session.setAttribute("payinfoId", payinfoId);//666--payinfoId;
		session.setAttribute("sendAddressinfoId", payAddressinfoId);//777--sendAddressinfoId;
		
		//取出sessionid,再次放入
		Integer orderIdFinally = (Integer) session.getAttribute("orderId");//888--orderId;
		session.setAttribute("orderId", orderIdFinally);
		//4.0传入orderid,查询其中的orderItemID,找到cartID 找到cartid,移除购物车中的
		updateCart(mlfrontOrder);
		//5.0发起支付
		Integer isSuccess = 0;//返回0,跳支付成功页面;返回1,跳支付失败页面
		//以下三个参数是都在stripe付款方式的时候使用
		//return Msg.success().add("resMsg", "更新成功").add("isSuccess", isSuccess).add("payinfoId", payinfoId).add("orderId", orderIdFinally);
		
		/*********************************************************************************************************/
		
		System.out.println("into**********/paypal/mpay**********");
    	//1.1,准备支付前,从session中读取getPayInfo参数
    	ToPaypalInfo toPaypalInfo = getPayInfo(mlfrontAddressToPay,payinfoId,totalprice);
    	//1.2,准备支付前,从session中获取优惠券减去额度
    	String Shopdiscount = CouponCodeMoneyStr;
    	//1.3,准备支付前,从session中获取地址运费
    	String addressMoneyNow = addressMoneyStr;
    	//1.4,准备支付前,从session中获取地址信息
    	MlfrontAddress mlfrontAddressNow = mlfrontAddressToPay;
    	//1.5,准备支付前,从session中获取orderList详情
    	List<MlfrontOrderItem> mlfrontOrderItemList = getMlfrontOrderItemList(orderIdFinally);
    	
    	BigDecimal money = toPaypalInfo.getMoneyNum();
    	String moneyStr = money.toString();
    	Double moneyDouble = Double.parseDouble(moneyStr);
    	String moneyTypeStr = toPaypalInfo.getMoneyType();
    	String payDes = toPaypalInfo.getPaymentDescription();

    	//封装paypal所需
    	//HttpServletResponse rep,HttpServletRequest res
    	String cancelUrl = URLUtils.getBaseURl(res) + "/" + PAYPAL_CANCEL_M_URL;
        String successUrl = URLUtils.getBaseURl(res) + "/" + PAYPAL_SUCCESS_M_URL;
    	
        Payment payment = new Payment();
        PaypalService paypalService = new PaypalService();
        
        String PaypalErrorName="";
        List<ErrorDetails> paypalErrorList= null;
        try {
            payment = paypalService.createPayment(
            		moneyDouble,// 888.00, 
            		moneyTypeStr, //"USD",  
            		mlfrontAddressNow,//mlfrontAddress
            		mlfrontOrderItemList,
                    PaypalPaymentMethod.paypal, //paypal
                    PaypalPaymentIntent.sale,//paypal
                    payDes,//"payment description", 
                    Shopdiscount,//"CouponMoney", 
                    addressMoneyNow,//shopping
                    cancelUrl, 
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                	System.out.println("links.getHref:"+links.getHref());
                    //return "redirect:" + links.getHref();
                }
            }
            return Msg.success().add("data", payment.toJSON());
        } catch (PayPalRESTException e) {
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
            	/******************************************第1版本**begin**************************************/
            	PaypalErrorName = e.getDetails().getName();
            	paypalErrorList = e.getDetails().getDetails();
            	if(paypalErrorList.size()>1){
            		//city,state,zip不匹配
            		PaypalErrorName = "pls check your address information, make sure the zip code is matched with your city/state.";
            	}else{
            		//看看是什么
            		String errStr = paypalErrorList.get(0).getField();
            		String errStrll = errStr.replace(".", ",");
            		System.out.println("errStrll:"+errStrll);
            		
            		String errStrArr[] = errStrll.split(",");
            		Integer errLen = errStrArr.length;
            		System.out.println("errLen:"+errLen);
            		String lastStr = errStrArr[errLen-1];
            		
            		if(lastStr.equals("phone")){
            			PaypalErrorName = "Pls fill right phone number with Digital 0-9,which shouldn't included Any Alphabet and Symbol.";
            		}else{
            			String errStrOther = paypalErrorList.get(0).getIssue();
            			PaypalErrorName =""+errStrOther+". Pls check it and try again";
            		}
            	}
            	/******************************************第2版本**begin**************************************/
            }
            System.out.println("后台转换后的错误提示PaypalErrorName:"+PaypalErrorName);
            System.out.println("---------e.getDetails()------end------");
            //return Msg.success().add("ifPaypalCheckSuccess", 2).add("errorDetail", PaypalErrorName);
            return Msg.fail().add("data", PaypalErrorName);
        }
	}
	
	/*********************************************************************************************************/
	
	/**
	 * 1.1查询英文名,查询该英文名的价格运费价格
	 * getAddressMoney
	 * */
	private Integer getAddressMoney(Integer addressId) {
		MlfrontAddress MlfrontAddressReq = new MlfrontAddress();
		MlfrontAddressReq.setAddressId(addressId);
//		List<MlfrontAddress> mlfrontAddressList = mlfrontAddressService.selectMlfrontAddressById(MlfrontAddressReq);
		List<MlfrontAddress> mlfrontAddressList = mlfrontAddressService.selectMlfrontAddressByParam(MlfrontAddressReq);
		String addressCountryCode = mlfrontAddressList.get(0).getAddressCountryCode();
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryCode(addressCountryCode);
//		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
		Integer areafreightPrice = 0;
		if(mlbackAreafreightResList.size()>0){
			mlbackAreafreightOne =mlbackAreafreightResList.get(0);
			areafreightPrice = mlbackAreafreightOne.getAreafreightPrice();
		}
		return areafreightPrice;
	}
	
	/**
	 * 1.2查询该优惠码的优惠价格
	 * getCouponCodeMoney
	 * @param totalprice 
	 * */
	private BigDecimal getCouponCodeMoney(String couponCode, BigDecimal totalprice,String cusTomerPidStr,String pidItemAndMoneyStr) {
		
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponCode(couponCode);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponBYCode(mlbackCouponReq);
		BigDecimal mlbackCouponPrice = new BigDecimal(0.00);
		if(mlbackCouponResList.size()>0){
			MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
			mlbackCouponPrice = getCouponPrice(mlbackCouponOne,cusTomerPidStr,pidItemAndMoneyStr);
		}
		return mlbackCouponPrice;
	}
	/**
	 * 1.2.1上面方法子方法
	 * getCouponPrice
	 * @param totalprice 
	 * */
	private BigDecimal getCouponPrice(MlbackCoupon mlbackCouponOne,String cusTomerPidStr,String pidItemAndMoneyStr) {
		
		BigDecimal mlbackCouponPrice = new BigDecimal(0.00);
		BigDecimal realCouponPrice = new BigDecimal(0.00);
		
		String couponPidStr=null;
		//判断属于哪一类别
		Integer couponProductonlyType = mlbackCouponOne.getCouponProductonlyType();
		if(0==couponProductonlyType){
			couponPidStr=null;
		}else if(1==couponProductonlyType){
			couponPidStr = mlbackCouponOne.getCouponProductonlyPidstr();
		}else if(2==couponProductonlyType){
			couponPidStr = mlbackCouponOne.getCouponProsFromApplyCateidstr();
		}else if(3==couponProductonlyType){
			couponPidStr = mlbackCouponOne.getCouponAllExceptPidstr();
		}
		//
		if(couponPidStr!=null){
			
			String cusTomerPidStrArr[] = cusTomerPidStr.split(",");
			
			String cusTomerPMoneyStrArr[] = pidItemAndMoneyStr.split(",");
			
			List<UrlCount> pidItemAndMoneyList = new ArrayList<UrlCount>();
			for(int x=0;x<cusTomerPidStrArr.length;x++){
				UrlCount pidItemAndMoney = new UrlCount();
				String pidx = cusTomerPidStrArr[x];
				String pMoneyx =cusTomerPMoneyStrArr[x];
				pidItemAndMoney.setUrlString(pidx);
				pidItemAndMoney.setUrlStringNum(pMoneyx);
				pidItemAndMoneyList.add(pidItemAndMoney);
			}
			if(couponProductonlyType==3){
				//不打折的idStr
				System.out.println("特价品不打折,此情况未完成");
				mlbackCouponPrice = getAllProductItemPriceThree(pidItemAndMoneyList,couponPidStr);
				realCouponPrice = getRealCouponPrice(mlbackCouponPrice,mlbackCouponOne);
			}else{
				//特殊产品、特殊类
				mlbackCouponPrice = getAllProductItemPriceOneAndTwo(pidItemAndMoneyList,couponPidStr);
				realCouponPrice = getRealCouponPrice(mlbackCouponPrice,mlbackCouponOne);
			}
		}else{
			//全局折扣,钱数全部累加
			String cusTomerPMoneyStrArr[] = pidItemAndMoneyStr.split(",");
			BigDecimal totalprice = new BigDecimal(0);	//初始化最终价格参数
			BigDecimal oneAllprice = new BigDecimal(0);	//初始化单item的价格参数
			//所有钱数加起来
			for(int y=0;y<cusTomerPMoneyStrArr.length;y++){
				String oneItemAllMoney = cusTomerPMoneyStrArr[y];
				oneAllprice = new BigDecimal(oneItemAllMoney);
				totalprice = totalprice.add(oneAllprice);//07总价字段累加该条的全部价格
			}
			
			mlbackCouponPrice = totalprice;
			
			realCouponPrice = getRealCouponPrice(mlbackCouponPrice,mlbackCouponOne);
		}
		return realCouponPrice;
	}

	private BigDecimal getRealCouponPrice(BigDecimal mlbackCouponPrice, MlbackCoupon mlbackCouponOne) {
		
		BigDecimal realCouponPrice = new BigDecimal(0);
		
		BigDecimal CouponPriceBaseline = mlbackCouponOne.getCouponPriceBaseline();
		
		if(mlbackCouponPrice.compareTo(CouponPriceBaseline) > -1){
		    System.out.println("a大于等于b");
		    //满足可以用
			//判断是满减还是折扣
			String couponType = mlbackCouponOne.getCouponType();
			if("0".equals(couponType)){
				//如果是0类满减券,直接取出;
				//判断起步价
				realCouponPrice = mlbackCouponOne.getCouponPrice();
			}else{
				//如果是1类折扣券,计算完取出取出;
				BigDecimal mlbackCouponPriceOff = mlbackCouponOne.getCouponPriceoff();
				BigDecimal mlbackCouponPricebaifenbi = new BigDecimal(0.01);
				mlbackCouponPrice = mlbackCouponPrice.multiply(mlbackCouponPriceOff);
				realCouponPrice = mlbackCouponPrice.multiply(mlbackCouponPricebaifenbi);
			}
		}else{
			//不足起步价
			realCouponPrice= new BigDecimal(0);;
		}
		return realCouponPrice;
	}

	//特殊产品、特殊类
	private BigDecimal getAllProductItemPriceOneAndTwo(List<UrlCount> pidItemAndMoneyList,String couponPidStr) {
		
		BigDecimal totalprice = new BigDecimal(0);	//初始化最终价格参数
		BigDecimal oneAllprice = new BigDecimal(0);	//初始化单item的价格参数
		
		String couponPidArr[] = couponPidStr.split(",");
		
		for(int i=0;i<pidItemAndMoneyList.size();i++){
			//去除钱数
			UrlCount urlCountOne = pidItemAndMoneyList.get(i);
			String orderItemPidX = urlCountOne.getUrlString();
			
			for(int j=0;j<couponPidArr.length;j++){
				String couponPidX = couponPidArr[j];
				if(couponPidX.equals(orderItemPidX)){
					String OneItemAllMoney = urlCountOne.getUrlStringNum().trim();
					oneAllprice = new BigDecimal(OneItemAllMoney);
					totalprice = totalprice.add(oneAllprice);//07总价字段累加该条的全部价格
					break;
				}
			}
		}
		System.out.println("这是满足优惠券的总钱数totalprice:"+totalprice);
		return totalprice;
	}
	
	//不打折的idStr--特价品不打折,此情况未完成
	private BigDecimal getAllProductItemPriceThree(List<UrlCount> pidItemAndMoneyList,String couponPidStr) {
		
		BigDecimal totalprice = new BigDecimal(0);	//初始化最终价格参数
		BigDecimal oneAllprice = new BigDecimal(0);	//初始化单item的价格参数
		
		String couponPidArr[] = couponPidStr.split(",");
		
		for(int i=0;i<pidItemAndMoneyList.size();i++){
			//统计总钱数
			UrlCount urlCountOne = pidItemAndMoneyList.get(i);
			String orderItemPidX = urlCountOne.getUrlString();
			int ifhave = 0;
			for(int j=0;j<couponPidArr.length;j++){
				String couponPidX = couponPidArr[j];
				if(couponPidX.equals(orderItemPidX)){
					//该itemPid在coupon的不打折范围内
					ifhave=1;
					break;
				}
			}
			if(ifhave>0){
				//该订单中的产品id,在特价品范围
				//特价品,不进行打折计算
			}else{
				//该订单中的产品id,不在特价品范围
				//非特价品,进行打折计算
				String OneItemAllMoney  = urlCountOne.getUrlStringNum().trim();
				oneAllprice = new BigDecimal(OneItemAllMoney);
				totalprice = totalprice.add(oneAllprice);//07总价字段累加该条的全部价格
			}
		}
		System.out.println("这是满足该特价品不打折优惠券的总钱数totalprice:"+totalprice);
		return totalprice;
	}
	
	/**
	 * 1.3更新表字段
	 * updateCart
	 * */
	private void updateCart(MlfrontOrder mlfrontOrder) {
		Integer orderId = mlfrontOrder.getOrderId();
		Integer cartId = 0;
		Integer cartItemtestId= 0;
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder ();
		mlfrontOrderReq.setOrderId(orderId);
		List<MlfrontOrder>  mlfrontOrderResList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
		MlfrontOrder  mlfrontOrderRes = mlfrontOrderResList.get(0);
		//拿到orderItemStr
		String orderitemidstr = mlfrontOrderRes.getOrderOrderitemidstr();
		String orderitemidArr[] = orderitemidstr.split(",");
		//遍历orderitemidArr ,读取每个的orderItemid 取出本条,中的order_cartItemId
		List<String> cartitemIdNew =new ArrayList<String>();
		for(int x=0;x<orderitemidArr.length;x++){
			MlfrontOrderItem mlfrontOrderItem = new MlfrontOrderItem();
			int orderItemId = Integer .parseInt(orderitemidArr[x]);
			mlfrontOrderItem.setOrderitemId(orderItemId);
			List<MlfrontOrderItem>  mlfrontOrderItemList= mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItem);
			MlfrontOrderItem MlfrontOrderItemRes = mlfrontOrderItemList.get(0);
			Integer orderCartItemIdInt = MlfrontOrderItemRes.getOrderCartitemid();
			String orderCartItemIdStr = orderCartItemIdInt+"";
			cartitemIdNew.add(orderCartItemIdStr);
			cartItemtestId = orderCartItemIdInt;
		}
		//通过cartItemID找到cartid,在找到cartID中的cartItemIdStr
		MlfrontCartItem mlfrontCartItemReq = new MlfrontCartItem();
		mlfrontCartItemReq.setCartitemId(cartItemtestId);
		List<MlfrontCartItem> mlfrontCartItemList= mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemReq);
		MlfrontCartItem mlfrontCartItemRes = mlfrontCartItemList.get(0);
		cartId = mlfrontCartItemRes.getCartitemCartId();
		MlfrontCart mlfrontCart = new MlfrontCart();
		mlfrontCart.setCartId(cartId);
		MlfrontCart mlfrontCartRes = mlfrontCartService.selectMlfrontCartByCartId(cartId);
		String cartitemIdstrOld= mlfrontCartRes.getCartitemIdstr();
		String cartitemIdArr[] = cartitemIdstrOld.split(",");
		String pkStr = "";
		String flag = "0";
		String filallyCartitemStr = "";
		for(int i=0;i<cartitemIdArr.length;i++){
			pkStr=cartitemIdArr[i];
			for(int j=0;j<cartitemIdNew.size();j++){
				if(cartitemIdNew.get(j).equals(pkStr)){
					//包含
					flag = "1";
					break;
				}else{
					flag = "0";
					continue;
				}
			}
			if(flag.equals("1")){
				//删掉
				System.out.println("*****************");
			}else{
				//留下
				filallyCartitemStr=filallyCartitemStr+","+pkStr;
			}
		}
		if(filallyCartitemStr.length()>0){
			filallyCartitemStr = filallyCartitemStr.substring(1);
			mlfrontCart.setCartStatus(0);
		}else{
			mlfrontCart.setCartStatus(1);
		}
		mlfrontCart.setCartitemIdstr(filallyCartitemStr);
		mlfrontCartService.updateByPrimaryKeySelective(mlfrontCart);
	}
	
	/**
	 * 1.4处理一分钱问题
	 * */
	private String getamountTotal(String subMoney, String shopdiscount, String addressMoney) {
		
		Double subMoneyDou = Double.parseDouble(subMoney);
		
		Double shopdiscountDou = Double.parseDouble(shopdiscount);
		
		Double addressMoneyDou = Double.parseDouble(addressMoney);
		
		Double amountTotalDou = subMoneyDou - shopdiscountDou + addressMoneyDou;
		
		String amountTotalDouStr = (String.format("%.2f", amountTotalDou));
		
		return amountTotalDouStr;
	}
	
	/**
	 * 1.1计算折后的筹款价格
	 * returnMoney
	 * */
	private String GetOrderTopayinfoMoney(String orderitemidstr) {
		
		String orderitemidArr[] = orderitemidstr.split(",");
		MlfrontOrderItem mlfrontOrderItemMoneyReq = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemMoneyRes = new MlfrontOrderItem();
		List<MlfrontOrderItem> mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		String orderitemidArriReturn="";
		for(int i=0;i<orderitemidArr.length;i++){
			//获取便利orderItemId
			orderitemidArriReturn = orderitemidArr[i].trim();
			Integer orderItemId = Integer.parseInt(orderitemidArriReturn);
			mlfrontOrderItemMoneyReq.setOrderitemId(orderItemId);
			List<MlfrontOrderItem> mlfrontOrderItemMoneyOneList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemMoneyReq);
			mlfrontOrderItemMoneyRes = mlfrontOrderItemMoneyOneList.get(0);
			mlfrontOrderItemList.add(mlfrontOrderItemMoneyRes);
		}
		String subMoney = "";
		if(mlfrontOrderItemList.size()>1){
  			subMoney = getItemListsMoney(mlfrontOrderItemList);
  		}else{
  			MlfrontOrderItem mlfrontOrderItem = mlfrontOrderItemList.get(0);

  			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
  			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
  			String oneMoney = getOnemoney(skuNum,money);
  			money = getOneAllMoney(skuNum,oneMoney);
  			subMoney = money;
  		}
		//此时subMoney为orderItemList的全部价格
		return subMoney;
	}
	/**
	 * 上面方法的子方法
	 * 获取getItemListsMoney的全部价格
	 * */
	private String getItemListsMoney(List<MlfrontOrderItem> mlfrontOrderItemList) {
		Double MoneyDuball=new Double("0.00");
		for(MlfrontOrderItem mlfrontOrderItem:mlfrontOrderItemList){
			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
			String oneMoney =getOnemoney(skuNum,money);
			money = getOneAllMoney(skuNum,oneMoney);
			Double MoneyDub=new Double(money);
			MoneyDuball=MoneyDuball+ MoneyDub;
        }
		String OneOrderAllMoney = String.format("%.2f", MoneyDuball);
		return OneOrderAllMoney;
	}

	/**
	 * 上面方法的子方法
	 * 获取单条OrderItemMoney*num后的总价格
	 * */
	private String getOneAllMoney(Integer skuNum, String oneMoney) {
		Double oneMoneyDou = new Double(oneMoney);
		Double OneAllM = oneMoneyDou*skuNum;
		String OneAllMoney = String.format("%.2f", OneAllM);
		return OneAllMoney;
	}

	/**
	 * 上面方法的子方法
	 * 获取单条OrderItemMoney的总价格
	 * */
	private String getOnemoney(Integer skuNum, String money) {
		Double moneyAll = new Double(money);
		Double oneM = moneyAll/skuNum;
		String Onemoney = String.format("%.2f", oneM);
		return Onemoney;
	}
	
	/**
	 * 1.1读取getPayInfo参数
    * 准备支付前,从session中读取getPayInfo参数
    * */
   private ToPaypalInfo getPayInfo(MlfrontAddress mlfrontAddressToPayInto,Integer payinfoIdPInto,BigDecimal totalpriceInto) {
	   
  	MlfrontAddress mlfrontAddressToPay = mlfrontAddressToPayInto;
  	//从session中获取payinfoId,准备填入Desc中,防止paypal收到钱,却无法查帐
  	Integer payinfoId = payinfoIdPInto;
  	String payinfoIdStr = payinfoId+"";
  	BigDecimal totalprice = totalpriceInto;
   	ToPaypalInfo toPaypalInfo = new ToPaypalInfo();
		//从对象中获取参数
		String toPayTelephone = mlfrontAddressToPay.getAddressTelephone();
		String toPayCountry = mlfrontAddressToPay.getAddressCountry();
		String toPayProvince = mlfrontAddressToPay.getAddressProvince();
		String toPayCity = mlfrontAddressToPay.getAddressCity();
		String toPayDetail = mlfrontAddressToPay.getAddressDetail();
		String toPayUserfirstname = mlfrontAddressToPay.getAddressUserfirstname();
		String toPayUserlastname = mlfrontAddressToPay.getAddressUserlastname();
		//拼接参数
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
    * 1.5从session中获取orderList详情
    * 准备支付前,从session中获取orderList详情
    * */
   	private List<MlfrontOrderItem> getMlfrontOrderItemList(Integer orderIdInto) {
	Integer orderId = orderIdInto;
   	
   	MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
   	mlfrontOrderReq.setOrderId(orderId);
   	List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
   	//这一行报错了,没查到值
   	MlfrontOrder mlfrontOrderRes = mlfrontOrderList.get(0);//这一行报错
   	//这一行报错了,没查到值
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
	
}