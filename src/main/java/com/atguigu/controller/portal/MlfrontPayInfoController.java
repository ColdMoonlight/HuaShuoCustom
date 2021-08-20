package com.atguigu.controller.portal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlPaypalShipAddress;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlbackCaclPay;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackOrderStateEmail;
import com.atguigu.bean.MlbackProductSku;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontCheckoutView;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.UrlCount;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlPaypalShipAddressService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlbackCaclPayService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlbackOrderStateEmailService;
import com.atguigu.service.MlbackProductSkuService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontCheckoutViewService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.service.UrlCountService;
import com.atguigu.ship.Classes.ConnectionAPI;
import com.atguigu.ship.Classes.Tracking;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EcppUpdateWebStatusUtil;
import com.atguigu.utils.EmailNewUtilshtml;
import com.atguigu.utils.EmailNewUtilshtmlCustomer;
import com.atguigu.utils.FaceBookServerSideApiUtil;
import com.atguigu.utils.PropertiesUtil;
import com.atguigu.vo.AfterShipReturn;
import com.atguigu.vo.EcppTrackItem;

@Controller
@RequestMapping("/MlfrontPayInfo")
public class MlfrontPayInfoController {
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlPaypalShipAddressService mlPaypalShipAddressService;
	
	@Autowired
	MlbackOrderStateEmailService mlbackOrderStateEmailService;
	
	@Autowired
	MlbackCaclPayService mlbackCaclPayService;
	
	@Autowired
	MlbackCouponService mlbackCouponService;
	
	@Autowired
	UrlCountService UrlCountService;
	
	@Autowired
	MlfrontCheckoutViewService mlfrontCheckoutViewService;
	
	@Autowired
	MlbackProductSkuService mlbackProductSkuService;
	
//	afterShip的真实物流url环境
	private final static String ConnectionAPIid = "7b04f01f-4f04-4b37-bbb9-5b159af73ee1";
	
	/**
	 * 1.0	UseNow	0505
	 * toPaySuccessPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toPaySuccessPage")
	public String toPaySuccessPage() throws Exception{
	
		return "portal/CartOrderPay/paySuccess";
	}
	
	/**
	 * 2.0	UseNow	0505
	 * toMlbackPayInfoList列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackPayInfoList")
	public String toMlbackPayInfoList(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//mlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/order/mlbackPayInfoPage";
		}
	}
	
	/**3.0	useOn	0505
	 * 分类MlfrontPayInfo列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontPayInfoByPage")
	@ResponseBody
	public Msg getMlfrontPayInfoByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = Const.PAGE_NUM_PAYINFO;
			PageHelper.startPage(pn, PagNum);
//			List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoAll();
			List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoSuccessAll();
			PageInfo page = new PageInfo(mlfrontPayInfoList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**
	 * 4.0	UseNow	0505
	 * toMlbackPayInfoDetailPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toMlbackPayInfoDetailPage",method=RequestMethod.GET)
	public String toMlbackPayInfoDetailPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "payinfoId") Integer payinfoId) throws Exception{
	
		//接收传递进来的参数
		Integer payinfoIdReq = payinfoId;
		//放回响应域中
		res.setAttribute("payinfoId", payinfoIdReq);
		//放回session域中
		session.setAttribute("payinfoId", payinfoId);
		return "back/order/mlbackPayInfoDetailPage";
	}
	
	/**
	 * 5.0	zsh200716
	 * 支付成功页面获取相关展示信息
	 * @param MlfrontPayInfo
	 * @return 
	 */
	@RequestMapping(value="/successPageGetPaymentInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg successPageGetPaymentInfo(HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
		Integer payinfoId = mlfrontPayInfo.getPayinfoId();
		//接受payinfoId
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payinfoId);
		//1.查询本条pay支付单
		List<MlfrontPayInfo> mlfrontPayInfoResList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoReq);
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		if(mlfrontPayInfoResList.size()>0){
			mlfrontPayInfoOne =mlfrontPayInfoResList.get(0);
			//前面成功页面,是否首次调用支付信息明细,
			Integer ifFirst = checkPayIdIfcale(mlfrontPayInfoOne);
			
			//2.获取本条单子的orderId,2.1查询本条的order详情;2.2从详情中拿到addressid;2.3orderItemIDStr;2.4uid信息,历史购买次数;
			//2.1查询本条的order详情;
			Integer payinfoOid = mlfrontPayInfoOne.getPayinfoOid();
			MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
			mlfrontOrderPay.setOrderId(payinfoOid);
			List<MlfrontOrder> mlfrontOrderPayResList= mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPay);
			MlfrontOrder mlfrontOrderPayOneRes = mlfrontOrderPayResList.get(0);
			//2.2从详情中拿到addressid;
			Integer addressinfoId = mlfrontOrderPayOneRes.getOrderAddressinfoId();
			MlfrontAddress MlfrontAddressReq = new MlfrontAddress();
			MlfrontAddressReq.setAddressId(addressinfoId);
			List<MlfrontAddress> MlfrontAddressList = mlfrontAddressService.selectMlfrontAddressByParam(MlfrontAddressReq);
			MlfrontAddress mlfrontAddressOne = MlfrontAddressList.get(0);
			//2.2.1从地址中取出国家字段	addressCountry: "US"	addressCountryAll: "United States"
			//从地址中拿到国家的code
			String areafreightCountryCode = mlfrontAddressOne.getAddressCountryCode();
			//封装国家code
			MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
			mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
			//查询该国家的运费
			List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
			Integer areafreightMoney = 0;
			if(mlbackAreafreightResList.size()>0){
				areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();	//拿到国家运费
			}
			//2.3从详情中拿到orderItemIDStr;
			String orderItemIdsStr = mlfrontOrderPayOneRes.getOrderOrderitemidstr();
			List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
			MlfrontOrderItem mlfrontOrderItemOne = new MlfrontOrderItem();
			MlfrontOrderItem mlfrontOrderItemOneReq = new MlfrontOrderItem();
			String orderItemIdStrArr [] = orderItemIdsStr.split(",");
			String orderItemIdStr = "";
			Integer orderItemIdInt = 0;
			for(int i =0;i<orderItemIdStrArr.length;i++){
				orderItemIdStr = orderItemIdStrArr[i];
				orderItemIdInt = Integer.parseInt(orderItemIdStr);
				mlfrontOrderItemOneReq.setOrderitemId(orderItemIdInt);
				List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemOneReq);
				mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
				mlfrontOrderItemList.add(mlfrontOrderItemOne);
				
				//减库存
				//proSkunumFromOrderItem(mlfrontOrderItemList);
			}
			//2.4uid信息
			Integer uid = mlfrontOrderPayOneRes.getOrderUid();
			MlfrontUser mlfrontUserOne = new MlfrontUser();
			if(uid==null){
				mlfrontUserOne = null;
			}else{
				MlfrontUser mlfrontUserReq = new MlfrontUser();
				mlfrontUserReq.setUserId(uid);
				List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserByConditionS(mlfrontUserReq);
				mlfrontUserOne = mlfrontUserList.get(0);
			}
			//3.获取本payinfoid的paypal_shippingaddress地址;
			MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
			String shippingaddressPayinfoid=payinfoId+"";
			mlPaypalShipAddressReq.setShippingaddressPayinfoid(shippingaddressPayinfoid);
			List<MlPaypalShipAddress> mlPaypalShipAddressResList =mlPaypalShipAddressService.selectMlPaypalShipAddressByPayinfoid(mlPaypalShipAddressReq);
			MlPaypalShipAddress mlPaypalShipAddressRes = new MlPaypalShipAddress();
			if(mlPaypalShipAddressResList.size()==0){
				System.out.println("xx调用了getOneMlfrontPayInfoDetail接口,paypal那边返回的地址为null,没有payment的返回地址");
			}else{
				mlPaypalShipAddressRes = mlPaypalShipAddressResList.get(0);
			}
			
			//完毕回传
			return Msg.success().add("resMsg", "查看单条mlfrontPayInfoOne的详情细节完毕").add("ifFirst", ifFirst)
						.add("mlfrontPayInfoOne", mlfrontPayInfoOne).add("mlfrontOrderPayOneRes", mlfrontOrderPayOneRes)
						.add("mlfrontAddressOne", mlfrontAddressOne).add("mlfrontOrderItemList", mlfrontOrderItemList)
						.add("mlfrontUserOne", mlfrontUserOne).add("mlPaypalShipAddressOne", mlPaypalShipAddressRes).add("areafreightMoney", areafreightMoney);
		}else{
			mlfrontPayInfoOne = null;
			//账户异常
			return Msg.success().add("resMsg", "查询单条mlfrontPayInfoOne的详情细节完毕").add("mlfrontPayInfoOne", mlfrontPayInfoOne);
		}
	}
	
	
	
	/**
	 * 6.0	zsh200716
	 * 支付页面的最后接口,向FB发送服务端信息
	 * @param MlfrontPayInfo
	 * @return 
	 */
	@RequestMapping(value="/successPageThenSendDataToFacebook",method=RequestMethod.POST)
	@ResponseBody
	public Msg successPageThenSendDataToFacebook(HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfoInto){
		
		Integer payinfoId = mlfrontPayInfoInto.getPayinfoId();
		
		//2.获取本条单子的orderId,2.1查询本条的order详情;2.2从详情中拿到addressid;2.3orderItemIDStr;2.4uid信息,历史购买次数;
		//2.1查询本条的order详情;
		Integer payinfoOid = mlfrontPayInfoInto.getPayinfoOid();
		MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
		mlfrontOrderPay.setOrderId(payinfoOid);
		List<MlfrontOrder> mlfrontOrderPayResList= mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPay);
		MlfrontOrder mlfrontOrderPayOneRes = mlfrontOrderPayResList.get(0);
		//2.3从详情中拿到orderItemIDStr;
		String orderItemIdsStr = mlfrontOrderPayOneRes.getOrderOrderitemidstr();
		List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		MlfrontOrderItem mlfrontOrderItemOne = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemOneReq = new MlfrontOrderItem();
		String orderItemIdStrArr [] = orderItemIdsStr.split(",");
		String orderItemIdStr = "";
		Integer orderItemIdInt = 0;
		for(int i =0;i<orderItemIdStrArr.length;i++){
			orderItemIdStr = orderItemIdStrArr[i];
			orderItemIdInt = Integer.parseInt(orderItemIdStr);
			mlfrontOrderItemOneReq.setOrderitemId(orderItemIdInt);
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemOneReq);
			mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
			mlfrontOrderItemList.add(mlfrontOrderItemOne);
		}
		//3.获取本payinfoid的paypal_shippingaddress地址;
		MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
		String shippingaddressPayinfoid=payinfoId+"";
		mlPaypalShipAddressReq.setShippingaddressPayinfoid(shippingaddressPayinfoid);
		List<MlPaypalShipAddress> mlPaypalShipAddressResList =mlPaypalShipAddressService.selectMlPaypalShipAddressByPayinfoid(mlPaypalShipAddressReq);
		MlPaypalShipAddress mlPaypalShipAddressRes = new MlPaypalShipAddress();
		if(mlPaypalShipAddressResList.size()==0){
			System.out.println("xx调用了getOneMlfrontPayInfoDetail接口,paypal那边返回的地址为null,没有payment的返回地址");
		}else{
			mlPaypalShipAddressRes = mlPaypalShipAddressResList.get(0);
		}
		//向facebook那边传递服务器加载数据
		
		
		//获取查询的秘钥
		
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
	    mlbackOrderStateEmailReq.setOrderstateemailName("FBServiceAPI");
	        
	    List<MlbackOrderStateEmail> mlbackOrderStateEmailList = mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
	    
	    String FBTOKEN = "";
	    
	    String FBPIXELID = "";
	    
	    if(mlbackOrderStateEmailList.size()>0){
	    	
	    	MlbackOrderStateEmail mlbackOrderStateEmailOne = mlbackOrderStateEmailList.get(0);
	    	
	    	FBTOKEN = mlbackOrderStateEmailOne.getOrderstateemailOne();
	    	
	    	FBPIXELID = mlbackOrderStateEmailOne.getOrderstateemailTwo();
	    }else{
	    	FBTOKEN="EAALqAx5gd40BALlNPxpfAAYGY7gMZAyXWivriYgfZATE9ZAn9WUhKo7al9P8GJ1TlBNYeTRJQUb8TS3xZAtsu0edh6VPgZB9Ft0336BIpIrgaZBPGDqSDAZBY9txCnwidE9TepgffqUZAFMzS5q9cUqqVWdyEfd3wFzZBQWtUCuZB2dhkZBeAbaf6540kVnStkaRr8ZD";
	    	
	    	FBPIXELID="246433859565492";
	    	
	    	//FBTOKEN="EAALqAx5gd40BAPV1KA216p6vR2AouIFRWpzHxjBfxKQDJzF8ySF41nULUJ5qbRyu5RKJeAW0XNLZAEzQNf9fEWGDT394VOr81Ve89S6qkJu3Bc95XxTTiAwZAq1ZCnHlSfJXwDOQPGaaDM3qg5P6Q5sLDIGCZAhBovLozLk9xrZCASZBCud3s171RMniKNKFAZD";
	    	
	    	//FBPIXELID="667403967094866";
	    }
	    
		//订单信息
		FaceBookServerSideApiUtil.toFbServiceApi(mlfrontPayInfoInto,mlfrontOrderPayOneRes,mlfrontOrderItemList,mlPaypalShipAddressRes,FBTOKEN,FBPIXELID);
		
		return Msg.success().add("resMsg", "yes");
	}
	
	
	private void proSkunumFromOrderItem(List<MlfrontOrderItem> mlfrontOrderItemListInto) {
		
		for(MlfrontOrderItem mlfrontOrderItemOne:mlfrontOrderItemListInto){
			
			String proSkuBuy = mlfrontOrderItemOne.getOrderitemPskuCode();//orderItem_pSku_code
			
			Integer buyNum = mlfrontOrderItemOne.getOrderitemPskuNumber();
			String proSeo = mlfrontOrderItemOne.getOrderitemPseo();
			
			MlbackProductSku mlbackProductSkuReq = new MlbackProductSku();
			mlbackProductSkuReq.setProductskuCode(proSkuBuy);
			mlbackProductSkuReq.setProductskuPSeoname(proSeo);
			List<MlbackProductSku> mlbackProductSkuList =  mlbackProductSkuService.selectMlProductSkuListByPskuCode(mlbackProductSkuReq);
			if(mlbackProductSkuList.size()>0){
				MlbackProductSku mlbackProductSkuOne = mlbackProductSkuList.get(0);
				Integer nowNum = mlbackProductSkuOne.getProductskuStock();
				if(nowNum>0){
					nowNum = nowNum - buyNum;
					//买后减库存
					MlbackProductSku mlbackProductSkuNow = new MlbackProductSku();
					mlbackProductSkuNow.setProductskuId(mlbackProductSkuOne.getProductskuId());
					mlbackProductSkuNow.setProductskuStock(nowNum);
					//执行更新,将减后的数据-执行更新
					mlbackProductSkuService.updateByPrimaryKeySelective(mlbackProductSkuNow);
				}
			}
		}
	}

	/**
	 * 5.1	zsh201208
	 * update 是否复购信息
	 * @param MlfrontPayInfo payinfo_id,shippingEmail,
	 * @return 
	 */
	@RequestMapping(value="/updateSuccessInfoIfMoreTimesBuy",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateSuccessInfoIfMoreTimesBuy(HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
		Integer payinfoId = mlfrontPayInfo.getPayinfoId();
		//接受payinfoId
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payinfoId);
    	
		MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
		String shippingaddressPayinfoid=payinfoId+"";
		mlPaypalShipAddressReq.setShippingaddressPayinfoid(shippingaddressPayinfoid);
		List<MlPaypalShipAddress> mlPaypalShipAddressResList =mlPaypalShipAddressService.selectMlPaypalShipAddressByPayinfoid(mlPaypalShipAddressReq);
		//查到了本条结算的返回信息
		MlPaypalShipAddress mlPaypalShipAddressRes = new MlPaypalShipAddress();
		if(mlPaypalShipAddressResList.size()==0){
			System.out.println("xx调用了getOneMlfrontPayInfoDetail接口,paypal那边返回的地址为null,没有payment的返回地址");
		}else{
			//返回信息
			mlPaypalShipAddressRes = mlPaypalShipAddressResList.get(0);
			//取出id,email准备使用
			String email =mlPaypalShipAddressRes.getShippingaddressEmail();
			Integer shippingaddressId = mlPaypalShipAddressRes.getShippingaddressId();
			
			//先查一下这一单是不是非首次出现了
			Integer ifPaypalShipAddOldCustomer = checkifOldCustomer(email);
			
			if(ifPaypalShipAddOldCustomer>0){
				//这一单是老客户,需要更新成1
				MlPaypalShipAddress mlPaypalShipAddressUpdateReq = new MlPaypalShipAddress();
				mlPaypalShipAddressUpdateReq.setShippingaddressId(shippingaddressId);
				mlPaypalShipAddressUpdateReq.setShippingaddressIfFirstBuy(ifPaypalShipAddOldCustomer);
				mlPaypalShipAddressService.updateByPrimaryKeySelective(mlPaypalShipAddressUpdateReq);
			}else{
				//这一单是新单,再去成交单里面查
				Integer ifOrderAddressOldCustomer = checkifOldCustomerFromAdress(email);
				//如果查到是多次来,是复购单,走更新,
				if(ifOrderAddressOldCustomer>0){
					MlPaypalShipAddress mlPaypalShipAddressUpdateReq = new MlPaypalShipAddress();
					mlPaypalShipAddressUpdateReq.setShippingaddressId(shippingaddressId);
					mlPaypalShipAddressUpdateReq.setShippingaddressIfFirstBuy(ifPaypalShipAddOldCustomer);
					mlPaypalShipAddressService.updateByPrimaryKeySelective(mlPaypalShipAddressUpdateReq);
				}else{
					//如果不是多次来,是新单跳过
					System.out.println("payinfoId:"+payinfoId+""+"这一单是新客户,跳过");
				}
			}
		}
		//完毕回传
		return Msg.success().add("resMsg", "检测复购完毕");
	}
	
	private Integer checkifOldCustomerFromAdress(String addressEmail) {
		
		Integer ifOldCustomer = 0;
    	
		UrlCount UrlCountifOldReq = new UrlCount();
		UrlCountifOldReq.setUrlString(addressEmail);
    	
    	List<UrlCount> ifOldOrderAddressEmailList = UrlCountService.selectMoreBuyCountFromAddressByTime(UrlCountifOldReq);
    	
    	if(ifOldOrderAddressEmailList.size()>0){
    		UrlCount urlCountRes = ifOldOrderAddressEmailList.get(0);
    		String buyNumStr = urlCountRes.getUrlStringNum();
    		//存在2条以上该邮箱订单,属于多次购买。这是老客户
    		if("0".equals(buyNumStr)){
        		ifOldCustomer=0;
    		}else if("1".equals(buyNumStr)){
        		ifOldCustomer=0;
    		}else{
    			ifOldCustomer=1;
    		}
    	}else{
    		//新客户
    		ifOldCustomer=0;
    	}
    	
		return ifOldCustomer;
	}

	//存储数据之前,查一下
    private Integer checkifOldCustomer(String CustomerEmail) {

    	Integer ifOldCustomer = 0;
    	
    	MlPaypalShipAddress mlPaypalShipAddressifOldReq = new MlPaypalShipAddress();
    	mlPaypalShipAddressifOldReq.setShippingaddressEmail(CustomerEmail);
    	
    	List<MlPaypalShipAddress> ifOldMlPaypalShipAddressList = mlPaypalShipAddressService.selectMlPaypalShipAddressByEmail(mlPaypalShipAddressifOldReq);
    	
    	if(ifOldMlPaypalShipAddressList.size()>1){
    		//存在2条以上该邮箱订单,属于多次购买。这是老客户
    		ifOldCustomer=1;
    	}else{
    		//新客户
    		ifOldCustomer=0;
    	}
    	
		return ifOldCustomer;
	}
	
	
	//前面成功页面,是否首次调用支付信息明细
	private Integer checkPayIdIfcale(MlfrontPayInfo mlfrontPayInfoOne) {

		String payinfoPlatenum = mlfrontPayInfoOne.getPayinfoPlatenum();
		Integer poid = mlfrontPayInfoOne.getPayinfoOid();
		MlbackCaclPay mlbackCaclPayReq = new MlbackCaclPay();
		mlbackCaclPayReq.setTbMlbackCaclpayPaltenum(payinfoPlatenum);
		List<MlbackCaclPay> mlbackCaclPayList = mlbackCaclPayService.selectMlbackCaclPayByParams(mlbackCaclPayReq);
		if(mlbackCaclPayList.size()>0){
			return 1;//本条已经在数据库中,未被计算过
		}else{
			String nowtime = DateUtil.strTime14s();
			mlbackCaclPayReq.setTbMlbackCaclpayCreatetime(nowtime);
			mlbackCaclPayReq.setTbMlbackCaclpayMotifytime(nowtime);
			mlbackCaclPayReq.setTbMlbackCaclpayOrderid(poid);
			mlbackCaclPayService.insertSelective(mlbackCaclPayReq);
			//System.out.println("mlbackCaclPayReq:"+mlbackCaclPayReq.toString());
			/******************计算优惠券次数************************/
			Integer payinfoOid = mlfrontPayInfoOne.getPayinfoOid();
			MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
			mlfrontOrderPay.setOrderId(payinfoOid);
			List<MlfrontOrder> mlfrontOrderPayResList= mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPay);
			MlfrontOrder mlfrontOrderPayOneRes = mlfrontOrderPayResList.get(0);
			String orderCouponCode = mlfrontOrderPayOneRes.getOrderCouponCode();
			if(orderCouponCode==null){
				System.out.println("往google,单号为:"+payinfoPlatenum+",此单没有用优惠券");
			}else if("".equals(orderCouponCode)){
				System.out.println("往google,单号为:"+payinfoPlatenum+"此单没有用优惠券");
			}else{
				//查询该优惠券的次数,并+1;
				MlbackCoupon mlbackCouponReq = new MlbackCoupon();
				mlbackCouponReq.setCouponCode(orderCouponCode);
				List<MlbackCoupon> mlbackCouponList =  mlbackCouponService.selectMlbackCouponBYCode(mlbackCouponReq);
				if(!(mlbackCouponList.size()>0)){
					//优惠券不存在
					System.out.println("往google,此优惠券不存在");
				}else{
					//取出次数,id,次数加一
					MlbackCoupon mlbackCouponRes = mlbackCouponList.get(0);
					Integer usedTimes = mlbackCouponRes.getCouponTimes();
					usedTimes++;
					Integer couponId = mlbackCouponRes.getCouponId();
					
					//更新操作结果
					MlbackCoupon mlbackCouponEndReq = new MlbackCoupon();
					mlbackCouponEndReq.setCouponId(couponId);
					mlbackCouponEndReq.setCouponTimes(usedTimes);
					mlbackCouponService.updateByPrimaryKeySelective(mlbackCouponEndReq);
				}
			}
			/******************计算优惠券次数************************/
			return 0;//本条已经在数据库中,未被计算过
		}
	}

	/**
	 * 6.0	zsh200815
	 * 后台单条明细页面获取展示信息
	 * @param MlfrontPayInfo
	 * @return 
	 */
	@RequestMapping(value="/backGetOnePayInfoDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg backGetOnePayInfoDetail(HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		//接受payinfoId
		Integer payinfoId = mlfrontPayInfo.getPayinfoId();
		//准备req
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(payinfoId);
		//1.查询本条pay支付单
		List<MlfrontPayInfo> mlfrontPayInfoResList =mlfrontPayInfoService.selectMlfrontPayInfoById(mlfrontPayInfoReq);
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		if(mlfrontPayInfoResList.size()>0){
			mlfrontPayInfoOne =mlfrontPayInfoResList.get(0);
		}else{
			mlfrontPayInfoOne = null;
		}
		//2.获取本条单子的orderId,2.1查询本条的order详情;2.2从详情中拿到addressid;2.3orderItemIDStr;2.4uid信息,历史购买次数;
		//2.1查询本条的order详情;
		Integer payinfoOid = mlfrontPayInfoOne.getPayinfoOid();
		MlfrontOrder mlfrontOrderPay = new MlfrontOrder();
		mlfrontOrderPay.setOrderId(payinfoOid);
		List<MlfrontOrder> mlfrontOrderPayResList= mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderPay);
		MlfrontOrder mlfrontOrderPayOneRes = mlfrontOrderPayResList.get(0);
		//2.2从详情中拿到addressid;
		Integer addressinfoId = mlfrontOrderPayOneRes.getOrderAddressinfoId();
		MlfrontAddress MlfrontAddressReq = new MlfrontAddress();
		MlfrontAddressReq.setAddressId(addressinfoId);
//		List<MlfrontAddress> MlfrontAddressList = mlfrontAddressService.selectMlfrontAddressById(MlfrontAddressReq);
		List<MlfrontAddress> MlfrontAddressList = mlfrontAddressService.selectMlfrontAddressByParam(MlfrontAddressReq);
		MlfrontAddress mlfrontAddressOne = MlfrontAddressList.get(0);
		//2.2.1从地址中取出国家字段	addressCountry: "US"	addressCountryAll: "United States"
		//拿到国家的code
		String areafreightCountryCode = mlfrontAddressOne.getAddressCountryCode();
		//封装国家code
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
		//查询该国家的全称
//		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		Integer areafreightMoney = 0;
		if(mlbackAreafreightResList.size()>0){
			areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();	//拿到国家运费
		}
		//2.3从详情中拿到orderItemIDStr;
		String orderItemIdsStr = mlfrontOrderPayOneRes.getOrderOrderitemidstr();
		List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		MlfrontOrderItem mlfrontOrderItemOne = new MlfrontOrderItem();
		MlfrontOrderItem mlfrontOrderItemOneReq = new MlfrontOrderItem();
		String orderItemIdStrArr [] = orderItemIdsStr.split(",");
		String orderItemIdStr = "";
		Integer orderItemIdInt = 0;
		for(int i =0;i<orderItemIdStrArr.length;i++){
			orderItemIdStr = orderItemIdStrArr[i];
			orderItemIdInt = Integer.parseInt(orderItemIdStr);
			mlfrontOrderItemOneReq.setOrderitemId(orderItemIdInt);
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemOneReq);
			mlfrontOrderItemOne = mlfrontOrderItemResList.get(0);
			mlfrontOrderItemList.add(mlfrontOrderItemOne);
		}
		//2.4uid信息
		Integer uid = mlfrontOrderPayOneRes.getOrderUid();
		MlfrontUser mlfrontUserOne = new MlfrontUser();
		if(uid==null){
			mlfrontUserOne = null;
		}else{
			MlfrontUser mlfrontUserReq = new MlfrontUser();
			mlfrontUserReq.setUserId(uid);
			List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserByConditionS(mlfrontUserReq);
			mlfrontUserOne = mlfrontUserList.get(0);
		}
		//3.获取本payinfoid的paypal_shippingaddress地址;
		MlPaypalShipAddress mlPaypalShipAddressReq = new MlPaypalShipAddress();
		String shippingaddressPayinfoid=payinfoId+"";
		mlPaypalShipAddressReq.setShippingaddressPayinfoid(shippingaddressPayinfoid);
		List<MlPaypalShipAddress> mlPaypalShipAddressResList =mlPaypalShipAddressService.selectMlPaypalShipAddressByPayinfoid(mlPaypalShipAddressReq);
		MlPaypalShipAddress mlPaypalShipAddressRes = new MlPaypalShipAddress();
		if(mlPaypalShipAddressResList.size()==0){
			System.out.println("后台点进单条查看支付单子明细backGetOnePayInfoDetail接口,paypal那边返回的地址为null,没有payment的返回地址");
		}else{
			mlPaypalShipAddressRes = mlPaypalShipAddressResList.get(0);
		}
		
		//通过payinfoId查询CheckoutView
		MlfrontCheckoutView mlfrontCheckoutViewReq = new MlfrontCheckoutView();
		mlfrontCheckoutViewReq.setCheckoutviewPayinfoid(payinfoId+"");
		List<MlfrontCheckoutView> mlfrontCheckoutViewList = mlfrontCheckoutViewService.selectMlfrontCheckoutViewByPayinfoid(mlfrontCheckoutViewReq);
		MlfrontCheckoutView mlfrontCheckoutViewOne = new MlfrontCheckoutView();
		if(mlfrontCheckoutViewList.size()>0){
			mlfrontCheckoutViewOne = mlfrontCheckoutViewList.get(0);
		}
		
		//完毕回传
		return Msg.success().add("resMsg", "查看单条mlfrontPayInfoOne的详情细节完毕")
					.add("mlfrontPayInfoOne", mlfrontPayInfoOne).add("mlfrontOrderPayOneRes", mlfrontOrderPayOneRes)
					.add("mlfrontAddressOne", mlfrontAddressOne).add("mlfrontOrderItemList", mlfrontOrderItemList)
					.add("mlfrontUserOne", mlfrontUserOne).add("mlPaypalShipAddressOne", mlPaypalShipAddressRes)
					.add("areafreightMoney", areafreightMoney).add("mlfrontCheckoutViewOne", mlfrontCheckoutViewOne);
	}
	
	/**7.0	zsh200720
	 * 检查已支付的单子,是否在ecpp上已经审核
	 * 1,收到前台的查询时间范围,后台查询这些时间内的已支付订单,
	 * 2,便利这些单子,用token+H号,去查询本条的状态,
	 * 3.1如果是审核完毕,就在我方系统中,将本条改成已经审核的状态
	 * 3.2如果是已发货,就在我方系统中,将本单改成已经已发货的状态;并且把哪家物流的名字+track_no,同步回我方后台
	 * @param "payinfoCreatetime": "2020-07-21 08:20:06",
     * 		  "payinfoMotifytime": "2020-07-21 08:50:06"
	 */
	@RequestMapping(value="/checkEcppIfVerify",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkEcppIfVerify(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
			String startTime = mlfrontPayInfo.getPayinfoCreatetime();
			String endTime = mlfrontPayInfo.getPayinfoMotifytime();
			
			MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
			mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
			mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
			mlfrontPayInfoReq.setPayinfoStatus(1);//只查询已支付的状态
			
			List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndStatus(mlfrontPayInfoReq);
			
			if(mlfrontPayInfoList.size()>0){
				//如果有已支付状态的文件
				for(MlfrontPayInfo mlfrontPayInfoOne:mlfrontPayInfoList){
					Integer payInfoId = mlfrontPayInfoOne.getPayinfoId();
					Integer orderId = mlfrontPayInfoOne.getPayinfoOid();
					
					//取出mlfrontPayInfoOne中的Ecpphsnum中的信息
					String ecppHSnum = mlfrontPayInfoOne.getPayinfoEcpphsnum();
					String order_sn = ecppHSnum;
					String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
					
					String soapXML = EcppUpdateWebStatusUtil.getXML(token,order_sn);
					
					MlfrontPayInfo mlfrontPayInfoUpdate = new MlfrontPayInfo();
					
					try {
						EcppTrackItem ecppTrackItem = EcppUpdateWebStatusUtil.send(token,soapXML);
						String ecppOrderStatusCode = ecppTrackItem.getEcppOrderStatusCode();
						MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
	            		mlfrontOrderReq.setOrderId(orderId);
	            		
						if("OOO".equals(ecppOrderStatusCode)){
							//131-OOO-订单核对完成(已发货)
							//更新成-发货-状态
							mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
	                		mlfrontPayInfoUpdate.setPayinfoStatus(3);//payinfo状态为3,已发货
	                		mlfrontPayInfoUpdate.setPayinfoSendnum(ecppTrackItem.getEcppOrderTrackNo());
	                		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
	                		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
	                		
	                		//查询本条的order信息,更新物流单号+物流名称
	                		mlfrontOrderReq.setOrderLogisticsname(ecppTrackItem.getShippingName());
	                		mlfrontOrderReq.setOrderLogisticsnumber(ecppTrackItem.getEcppOrderTrackNo());
	                		mlfrontOrderReq.setOrderStatus(4);//orderStatus == 4已发货,待接收
	                		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
	                		
	                		String orderLogisticsname = ecppTrackItem.getShippingName();
	                		String orderLogisticsnumber = ecppTrackItem.getEcppOrderTrackNo();
	                		String payinfoPlateNum = mlfrontPayInfoOne.getPayinfoPlatenum();
	                		
	                		//10.1向afterShip官方发送物流添加按钮
	                		try {
	                			//向物流中插入物流单号,订单号(Item,价格),传递orderId,即可全部走查询
	                			AfterShipReturn afterShipReturn = new AfterShipReturn();
	                			afterShipReturn = addTrackingNumberIntoAfterShip(orderId,payinfoPlateNum,orderLogisticsnumber);
	                			String afterOperateStatus = afterShipReturn.getAfterOperateStatus();
	                			
	                			if("1".equals(afterOperateStatus)){
	                				//往aftership中插入成功
	                				System.out.println("平台号为"+payinfoPlateNum+"的成交单,物流号插入AfterShip成功,返回的物流名为:"+afterShipReturn.getAfterShipSlugName());
	                				orderLogisticsname  = afterShipReturn.getAfterShipSlugName();
	                				//更新order表中本条成最终的物流信息的物流名称
	                				MlfrontOrder mlfrontOrderAfterReq = new MlfrontOrder();
	                				mlfrontOrderAfterReq.setOrderId(orderId);
	                				mlfrontOrderAfterReq.setOrderLogisticsname(orderLogisticsname);
	                				mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderAfterReq);
	                			}else{
	                				//往aftership没有插入成功
	                				System.out.println("平台号为"+payinfoPlateNum+"的成交单,物流号插入AfterShip失败----------0201");
	                				//需要往pay表,更新出失败插入失败的提示
	                				MlfrontPayInfo mlfrontPayInfoFail = new MlfrontPayInfo();
	                				mlfrontPayInfoFail.setPayinfoId(payInfoId);
	                				mlfrontPayInfoFail.setPayinfoSendnum(ecppTrackItem.getEcppOrderTrackNo()+"intofail");
	                        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoFail);
	                			}
	                		} catch (Exception e) {
	                			e.printStackTrace();
	                			System.out.println("aftership中插入物流单号--有异常----------0202");
	                			System.out.println(e.getMessage());
	                		}
						}else if("UOO".equals(ecppOrderStatusCode)){
							//113-UOO-客服审核完成的状态,就更新成审核完毕的状态
							mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
	                		mlfrontPayInfoUpdate.setPayinfoStatus(2);//payinfo状态为2,已审单//orderStatus == 3已审单,待发货
	                		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
	                		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
	                		
	                		mlfrontOrderReq.setOrderStatus(3);//orderStatus == 3已审单,待发货//
	                		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
						}else if("DEL".equals(ecppOrderStatusCode)){
							//124-DEL-客户退件了,这是ecpp取消发货后的状态,就更新成取消发货的状态
							mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
	                		mlfrontPayInfoUpdate.setPayinfoStatus(4);//payinfoStatus=2已审单//payinfoStatus=3已发货//4已退款
	                		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
	                		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
	                		
	                		mlfrontOrderReq.setOrderStatus(5);//orderStatus == 5已退款//
	                		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
						}else{
							mlfrontPayInfoUpdate.setPayinfoId(payInfoId);//不改状态,只赋值
							mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
							mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				return Msg.success().add("resMsg", "本次刷新有修改,请注意观察payInfo列表变化");
			}else{
				//查询结果为空
				//当前没有状态为已支付的数据
				return Msg.success().add("resMsg", "本次刷新没有状态是已支付的单子,无改变");
			}
	}
	
	/**7.0	zsh200720
	 * 检查已支付的单子,是否在ecpp上已经审核
	 * 1,收到前台的查询时间范围,后台查询这些时间内的已支付订单,
	 * 2,便利这些单子,用token+H号,去查询本条的状态,
	 * 3.1如果是审核完毕,就在我方系统中,将本条改成已经审核的状态
	 * 3.2如果是已发货,就在我方系统中,将本单改成已经已发货的状态;并且把哪家物流的名字+track_no,同步回我方后台
	 * @param Integer payInfoId,Integer orderId,String ecppHSnum,Integer orderId
	 */
	@RequestMapping(value="/checkEcppOneIfVerify",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkEcppOneIfVerify(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
		Integer payInfoId = mlfrontPayInfo.getPayinfoId();
		Integer orderId = mlfrontPayInfo.getPayinfoOid();
		String payinfoPlateNumInto = mlfrontPayInfo.getPayinfoPlatenum();
		String payinfoUemailInto = mlfrontPayInfo.getPayinfoUemail();
		
		String payinfoAddressEmail = mlfrontPayInfo.getPayinfoReturntime();
		
		//取出mlfrontPayInfoOne中的Ecpphsnum中的信息
		String ecppHSnum = mlfrontPayInfo.getPayinfoEcpphsnum();
		String order_sn = ecppHSnum;
		String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
		
		String soapXML = EcppUpdateWebStatusUtil.getXML(token,order_sn);
		
		MlfrontPayInfo mlfrontPayInfoUpdate = new MlfrontPayInfo();
		
		try {
			EcppTrackItem ecppTrackItem = EcppUpdateWebStatusUtil.send(token,soapXML);
			String ecppOrderStatusCode = ecppTrackItem.getEcppOrderStatusCode();
			MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
    		mlfrontOrderReq.setOrderId(orderId);
    		
			if("OOO".equals(ecppOrderStatusCode)){
				//131-OOO-订单核对完成(已发货)
				//更新成-发货-状态
				mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
        		mlfrontPayInfoUpdate.setPayinfoStatus(3);//payinfo状态为3,已发货
        		mlfrontPayInfoUpdate.setPayinfoSendnum(ecppTrackItem.getEcppOrderTrackNo());
        		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
        		
        		//查询本条的order信息,更新物流单号+物流名称
        		mlfrontOrderReq.setOrderLogisticsname(ecppTrackItem.getShippingName());
        		mlfrontOrderReq.setOrderLogisticsnumber(ecppTrackItem.getEcppOrderTrackNo());
        		mlfrontOrderReq.setOrderStatus(4);//orderStatus == 4已发货,待接收
        		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
        		
        		String orderLogisticsname = ecppTrackItem.getShippingName();
        		String orderLogisticsnumber = ecppTrackItem.getEcppOrderTrackNo();
        		String payinfoPlateNum = mlfrontPayInfo.getPayinfoPlatenum();
        		
        		//10.1向afterShip官方发送物流添加按钮
        		try {
        			//向物流中插入物流单号,订单号(Item,价格),传递orderId,即可全部走查询
        			AfterShipReturn afterShipReturn = new AfterShipReturn();
        			afterShipReturn = addTrackingNumberIntoAfterShip(orderId,payinfoPlateNum,orderLogisticsnumber);
        			String afterOperateStatus = afterShipReturn.getAfterOperateStatus();
        			
        			if("1".equals(afterOperateStatus)){
        				//往aftership中插入成功
        				System.out.println("平台号为"+payinfoPlateNum+"的成交单,物流号插入AfterShip成功,返回的物流名为:"+afterShipReturn.getAfterShipSlugName());
        				orderLogisticsname  = afterShipReturn.getAfterShipSlugName();
        				//更新order表中本条成最终的物流信息的物流名称
        				MlfrontOrder mlfrontOrderAfterReq = new MlfrontOrder();
        				mlfrontOrderAfterReq.setOrderId(orderId);
        				mlfrontOrderAfterReq.setOrderLogisticsname(orderLogisticsname);
        				mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderAfterReq);
        			}else{
        				//往aftership没有插入成功
        				System.out.println("平台号为"+payinfoPlateNum+"的成交单,物流号插入AfterShip失败");
        				
        				//需要往pay表,更新出失败插入失败的提示
        				MlfrontPayInfo mlfrontPayInfoFail = new MlfrontPayInfo();
        				mlfrontPayInfoFail.setPayinfoId(payInfoId);
        				mlfrontPayInfoFail.setPayinfoSendnum(ecppTrackItem.getEcppOrderTrackNo()+"intofail");
                		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoFail);
        				
        			}
        		} catch (Exception e) {
        			e.printStackTrace();
        			System.out.println("aftership中插入物流单号--有异常");
        			System.out.println(e.getMessage());
        		}
			}else if("UOO".equals(ecppOrderStatusCode)){
				//113-UOO-客服审核完成的状态,就更新成审核完毕的状态
				mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
        		mlfrontPayInfoUpdate.setPayinfoStatus(2);//payinfo状态为2,已审单//orderStatus == 3已审单,待发货
        		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
        		mlfrontPayInfoUpdate.setPayinfoTransStatus("completed");//人工审核后必须账户已收款
        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
        		
        		mlfrontOrderReq.setOrderStatus(3);//orderStatus == 3已审单,待发货//
        		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
        		//发审核完毕的邮件通知客户
        		sendVerfirtEmail(payinfoPlateNumInto,payinfoUemailInto,payinfoAddressEmail);
			}else if("POO".equals(ecppOrderStatusCode)){
				//114-POO-客服审核完成的状态,就更新成审核完毕的状态
				mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
        		mlfrontPayInfoUpdate.setPayinfoStatus(2);//payinfo状态为2,已审单//orderStatus == 3已审单,待发货
        		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
        		
        		mlfrontOrderReq.setOrderStatus(3);//orderStatus == 3已审单,待发货//
        		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
        		//发审核完毕的邮件通知客户
        		sendVerfirtEmail(payinfoPlateNumInto,payinfoUemailInto,payinfoAddressEmail);
			}else if("PPP".equals(ecppOrderStatusCode)){
				//129-PPP-客服审核完成的状态,就更新成审核完毕的状态
				mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
        		mlfrontPayInfoUpdate.setPayinfoStatus(2);//payinfo状态为2,已审单//orderStatus == 3已审单,待发货
        		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
        		
        		mlfrontOrderReq.setOrderStatus(3);//orderStatus == 3已审单,待发货//
        		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
        		//发审核完毕的邮件通知客户
        		sendVerfirtEmail(payinfoPlateNumInto,payinfoUemailInto,payinfoAddressEmail);
			}else if("DEL".equals(ecppOrderStatusCode)){
				//124-DEL-客户退件了,这是ecpp取消发货后的状态,就更新成取消发货的状态
				mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
        		mlfrontPayInfoUpdate.setPayinfoStatus(4);//payinfoStatus=2已审单//payinfoStatus=3已发货//4已退款
        		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
        		
        		mlfrontOrderReq.setOrderStatus(5);//orderStatus == 5已退款//
        		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
			}else{
				mlfrontPayInfoUpdate.setPayinfoId(payInfoId);//不改状态,只赋值
				mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
				mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Msg.success().add("resMsg", "本次刷新有修改,请注意观察payInfo列表变化");
	}

	private void sendVerfirtEmail(String payinfoPlateNum,String userEmail,String payinfoAddressEmail) {
		String toCustomerVerifyInfoStr = "";
		//查询
		MlbackOrderStateEmail mlbackOrderStateEmailReq = new MlbackOrderStateEmail();
		mlbackOrderStateEmailReq.setOrderstateemailName("Verifyed");
		//查询本条
		List<MlbackOrderStateEmail> mlbackOrderStateEmailList =mlbackOrderStateEmailService.selectMlbackOrderStateEmailByName(mlbackOrderStateEmailReq);
		if(mlbackOrderStateEmailList.size()>0){
			
			MlbackOrderStateEmail mlbackOrderStateEmailOne =mlbackOrderStateEmailList.get(0);
			
			toCustomerVerifyInfoStr = getToCustomerVerifyEmailManage(mlbackOrderStateEmailOne,payinfoPlateNum);
		}else{
			
			toCustomerVerifyInfoStr = getToCustomerVerifyEmail(payinfoPlateNum);
		}
		//提醒客户准备发货
		try {
			String getToEmail = userEmail;
//			EmailUtilshtml.readyEmailVerifySuccess(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum);
//			EmailUtilshtmlCustomer.readyEmailVerifyCustomer(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum);
			EmailNewUtilshtml.readyEmailVerifySuccess(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum);
			EmailNewUtilshtmlCustomer.readyEmailVerifyCustomer(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum,payinfoAddressEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getToCustomerVerifyEmailManage(MlbackOrderStateEmail mlbackOrderStateEmailOne,String payinfoPlateNum) {
		
		String emailOneStr =  mlbackOrderStateEmailOne.getOrderstateemailOne();
		String emailTwoStr =  mlbackOrderStateEmailOne.getOrderstateemailTwo();
		String emailThreeStr =  mlbackOrderStateEmailOne.getOrderstateemailThree();
		String emailFourStr =  mlbackOrderStateEmailOne.getOrderstateemailFour();
		String emailFiveStr =  mlbackOrderStateEmailOne.getOrderstateemailFive();
		String Message ="";
		Message =Message+"Hi gorgeous girl ,"+"<br><br>";
		Message=Message+emailOneStr+" # ("+payinfoPlateNum+") "+emailTwoStr+"<br><br>";
		Message=Message+emailThreeStr+"<br><br>";
		Message=Message+emailFourStr+"<br><br>";
		if("".equals(emailFiveStr)){
			System.out.println("emailFiveStr:"+emailFiveStr+"这句话为空");
		}else{
			Message=Message+emailFiveStr+"<br><br>";
		}
		Message=Message+"Best Regards,<br>";
		Message=Message+"-----------------------------------<br>";
		String team = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.team");
		String email = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.email");
		String whatsapp = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.whatsapp");
		String Telephone = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.Telephone");
		//读取配置文件
		Message=Message+team+"<br>";
		Message=Message+"Email:"+email+"<br>";
		Message=Message+"Whatsapp:"+whatsapp+"<br>";
		Message=Message+"Telephone/SMS:"+Telephone+"<br>";
		return Message;
	}
	
	//ToCustomerVerifyEmail
	private String getToCustomerVerifyEmail(String payinfoPlateNum) {
		String Message ="";
		Message =Message+"Hi gorgeous girl ,"+"<br><br>";
		Message=Message+"This is Megalook Hair  <br>. ";
		Message=Message+"We have received your order # ("+payinfoPlateNum+")  and confirmed your payment. <br><br><br>";
		Message=Message+"The hair you ordered is in stock and is expected to be shipped within 24-48 hours .<br><br>";
		Message=Message+"We will send the parcel tracking number to you through email & SMS after delivery, and you can also view it on the PayPal bill.<br><br><br>";
		Message=Message+"Please don't hesitate to call me if you need help. We still here behind Megalook Hair.<br><br>";
		Message=Message+"Best Regards,<br>";
		Message=Message+"-----------------------------------<br>";
		String team = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.team");
		String email = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.email");
		String whatsapp = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.whatsapp");
		String Telephone = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.Telephone");
		//读取配置文件
		Message=Message+team+"<br>";
		Message=Message+"Email:"+email+"<br>";
		Message=Message+"Whatsapp:"+whatsapp+"<br>";
		Message=Message+"Telephone/SMS:"+Telephone+"<br>";
		return Message;
	}

	/**8.0	zsh200722
	 * 检查已审核的单子,是否在ecpp上已经发货
	 * 1,收到前台的查询时间范围,后台查询这些时间内的已审核订单,
	 * 2,便利这些单子,用token+H号,去查询本条的状态,
	 * 3,如果是已发货,就在我方系统中,将本单改成已经已发货的状态;并且把哪家物流的名字+track_no,同步回我方后台
	 * @param "payinfoCreatetime": "2020-07-21 08:20:06",
     * 		  "payinfoMotifytime": "2020-07-21 08:50:06"
	 */
	@RequestMapping(value="/checkEcppIfSend",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkEcppIfSend(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfo){
		
		String startTime = mlfrontPayInfo.getPayinfoCreatetime();
		String endTime = mlfrontPayInfo.getPayinfoMotifytime();
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoCreatetime(startTime);
		mlfrontPayInfoReq.setPayinfoMotifytime(endTime);
		mlfrontPayInfoReq.setPayinfoStatus(2);//只查询已审单的状态
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectMlfrontPayInfoByDateAndStatus(mlfrontPayInfoReq);
		
		if(mlfrontPayInfoList.size()>0){
			//如果有已支付状态的文件
			for(MlfrontPayInfo mlfrontPayInfoOne:mlfrontPayInfoList){
				Integer payInfoId = mlfrontPayInfoOne.getPayinfoId();
				Integer orderId = mlfrontPayInfoOne.getPayinfoOid();
				
				//取出mlfrontPayInfoOne中的Ecpphsnum中的信息
				String ecppHSnum = mlfrontPayInfoOne.getPayinfoEcpphsnum();
				String order_sn = ecppHSnum;
				String token = (String) PropertiesUtil.getProperty("megalook.properties", "ecppToken");
				
				String soapXML = EcppUpdateWebStatusUtil.getXML(token,order_sn);
				
				MlfrontPayInfo mlfrontPayInfoUpdate = new MlfrontPayInfo();
				
				try {
					EcppTrackItem ecppTrackItem = EcppUpdateWebStatusUtil.send(token,soapXML);
					String ecppOrderStatusCode = ecppTrackItem.getEcppOrderStatusCode();
					MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
            		mlfrontOrderReq.setOrderId(orderId);
					if("OOO".equals(ecppOrderStatusCode)){
						//131-OOO-订单核对完成(已发货)
						//更新成-发货-状态
						mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
                		mlfrontPayInfoUpdate.setPayinfoStatus(3);//payinfo状态为3,已发货
                		mlfrontPayInfoUpdate.setPayinfoSendnum(ecppTrackItem.getEcppOrderTrackNo());
                		mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);
                		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
                		
                		//查询本条的order信息,更新物流单号+物流名称
                		mlfrontOrderReq.setOrderLogisticsname(ecppTrackItem.getShippingName());
                		mlfrontOrderReq.setOrderLogisticsnumber(ecppTrackItem.getEcppOrderTrackNo());
                		mlfrontOrderReq.setOrderStatus(4);//orderStatus == 4已发货,待接收
                		mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderReq);
                		
                		String orderLogisticsname = ecppTrackItem.getShippingName();
                		String orderLogisticsnumber = ecppTrackItem.getEcppOrderTrackNo();
                		String payinfoPlateNum = mlfrontPayInfoOne.getPayinfoPlatenum();
                		
                		//10.1向afterShip官方发送物流添加按钮
                		try {
                			//向物流中插入物流单号,订单号,orderId,即可全部走查询(Item,价格)
                			AfterShipReturn afterShipReturn = new AfterShipReturn();
                			afterShipReturn = addTrackingNumberIntoAfterShip(orderId,payinfoPlateNum,orderLogisticsnumber);
                			String afterOperateStatus = afterShipReturn.getAfterOperateStatus();
                			
                			if("1".equals(afterOperateStatus)){
                				//插入成功
                				System.out.println("平台号为"+payinfoPlateNum+"的成交单,物流号插入AfterShip成功,返回的物流名为:"+afterShipReturn.getAfterShipSlugName());
                				orderLogisticsname  = afterShipReturn.getAfterShipSlugName();
                				//更新order表中本条成最终的物流信息的物流名称
                				MlfrontOrder mlfrontOrderAfterReq = new MlfrontOrder();
                				mlfrontOrderAfterReq.setOrderId(orderId);
                				mlfrontOrderAfterReq.setOrderLogisticsname(orderLogisticsname);
                				mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderAfterReq);
                			}else{
                				//没有插入成功
                				System.out.println("平台号为"+payinfoPlateNum+"的成交单,物流号插入AfterShip失败");
                				MlfrontPayInfo mlfrontPayInfoFail = new MlfrontPayInfo();
                				mlfrontPayInfoFail.setPayinfoId(payInfoId);
                				mlfrontPayInfoFail.setPayinfoSendnum(ecppTrackItem.getEcppOrderTrackNo()+"intofail");
                        		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoFail);
                			}
                		} catch (Exception e) {
                			e.printStackTrace();
                			System.out.println("物流中插入物流单号--有异常");
                			System.out.println(e.getMessage());
                		}
					}else{
						mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
						mlfrontPayInfoUpdate.setPayinfoEcpphsnumStatus(ecppOrderStatusCode);//不改状态,只赋值
						mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return Msg.success().add("resMsg", "本次刷新有修改,请注意观察payInfo列表变化");
		}else{
			//查询结果为空
			//当前没有状态为已支付的数据
			return Msg.success().add("resMsg", "本次刷新没有状态是已支付的单子,无改变");
		}
	}
	
	/**
	 * 向afterShip官方发送物流单号
	 * */
	private AfterShipReturn addTrackingNumberIntoAfterShip(Integer orderId,String payinfoPlateNum,String trackingNumber) {
		
		AfterShipReturn afterShipReturn = new AfterShipReturn();
		afterShipReturn.setAfterOperateStatus("0");
		
		String connectionAPIStr = ConnectionAPIid;
		ConnectionAPI connection = new ConnectionAPI(connectionAPIStr);
		
		//First we have to create a Tracking
		Tracking tracking = new Tracking(trackingNumber);
		//Then we can add information;
		tracking.setTitle(payinfoPlateNum);//订单号
		tracking.setOrderID(payinfoPlateNum);//Ecpp的订单号
		
		//公共方法
		MlfrontOrder orderOne = getOrderDetailByOrderId(orderId);
		
		Integer addressId = orderOne.getOrderAddressinfoId();
		
		MlfrontAddress addressOne = getAddressDetailByAddressId(addressId);
		
		String userfirstname = addressOne.getAddressUserfirstname();
		String userlastname = addressOne.getAddressUserlastname();		
		String addressEmail = addressOne.getAddressEmail();
		String addressTelephone = addressOne.getAddressTelephone();
		
		String uname = userfirstname+" "+userlastname;
//		System.out.println("uname:"+uname);
//		System.out.println("addressEmail:"+addressEmail);
//		System.out.println("addressTelephone:"+addressTelephone);
		tracking.setCustomerName(uname);
		tracking.addEmails(addressEmail);
		tracking.addSmses(addressTelephone);

		String orderitemidstr = orderOne.getOrderOrderitemidstr();
		String orderItemArr[] =orderitemidstr.split(",");
		String proStr = "";
		String proOneDetailStr = "";
		//1.1如果有多个删除字段中的该项
		for(int i =0;i<orderItemArr.length;i++){
			String orderItemId = orderItemArr[i];
			
			MlfrontOrderItem orderItemOne = getOrderItemDetailByOrderItemId(orderItemId);
			String proName = orderItemOne.getOrderitemPseo();
			Integer orderItemNum = orderItemOne.getOrderitemPskuNumber();
			String orderItemNumStr = orderItemNum+"";
			String proSkuName = orderItemOne.getOrderitemPskuName();
			
			proOneDetailStr= ""+orderItemNumStr+" x "+proName+"("+proSkuName+")"+".";
			proStr+=proOneDetailStr;
		}
		
		//Even add customer fields
		tracking.addCustomFields("product_Items",proStr);

		//Finally we add the tracking to our account
		Tracking trackingPosted;
		String afterShipNameSlug = "0";//初始化填入afterShip后运单号,返回的物流名称,异常话是1,无异常的话,是真实的ShipName
		try {
			trackingPosted = connection.postTracking(tracking);

			System.out.println("trackingPosted.getTrackingNumber()------------------------");
			System.out.println(trackingPosted.getTrackingNumber());
			
			trackingPosted.getExpectedDelivery();
			trackingPosted.generateJSON();
			afterShipNameSlug = trackingPosted.getSlug();
			
			afterShipReturn.setAfterOperateStatus("1");
			afterShipReturn.setAfterShipSlugName(afterShipNameSlug);
			
			System.out.println("trackingPosted.getSlug()------------------------");
			System.out.println(trackingPosted.getSlug());
			System.out.println("向aftership中插入成功:"+afterShipReturn.toString()+"----------0101");
			return afterShipReturn;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("向aftership中插入失败:"+afterShipReturn.toString()+"----------010");
			return afterShipReturn;
		}
	}
	
	//获取orderItem的明细
	private MlfrontOrderItem getOrderItemDetailByOrderItemId(String orderItemIdStr) {
		
		Integer orderItemId = Integer.parseInt(orderItemIdStr);
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		mlfrontOrderItemReq.setOrderitemId(orderItemId);
		List<MlfrontOrderItem> mlfrontOrderItemList  = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
		MlfrontOrderItem mlfrontOrderItemRes = mlfrontOrderItemList.get(0);
		return mlfrontOrderItemRes;
	}

	//公共方法,用orderId查询OrderDetail
	private MlfrontOrder getOrderDetailByOrderId(Integer orderId) {
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderListRes  = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
		MlfrontOrder mlfrontOrderRes = mlfrontOrderListRes.get(0);
		return mlfrontOrderRes;
	}
	//公共方法,用addressId查询AddressDetail
	private MlfrontAddress getAddressDetailByAddressId(Integer addressId) {
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		mlfrontAddressReq.setAddressId(addressId);
		List<MlfrontAddress> mlfrontAddressListRes  = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
		MlfrontAddress mlfrontAddressRes = mlfrontAddressListRes.get(0);
		return mlfrontAddressRes;
	}

	/**
	 * 9.0	zsh	200720
	 * to	全部支付单的状态-分状态查询
	 * @param pn,Integer payinfoStatus,String payinfoPlateNum;
	 * @return
	 */
	@RequestMapping(value="/selectHighPayInfoListBySearch",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectHighPayInfoListBySearch(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "payinfoStatus") Integer payinfoStatus,
			@RequestParam(value = "payinfoPlateNum") String payinfoPlateNum,
			HttpSession session) {
		
		//初始化请求参数
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		List<MlfrontPayInfo> mlfrontPayInfoList = new ArrayList<MlfrontPayInfo>();
		int PagNum = 50;
		PageHelper.startPage(pn, PagNum);
		PageInfo page = new PageInfo();
		
		if("".equals(payinfoPlateNum)){
			mlfrontPayInfoReq.setPayinfoPlatenum(null);
		}else{
			mlfrontPayInfoReq.setPayinfoPlatenum(payinfoPlateNum);
		}
		
		if(payinfoStatus==999){
			mlfrontPayInfoReq.setPayinfoStatus(null);
		}else{
			mlfrontPayInfoReq.setPayinfoStatus(payinfoStatus);
		}
		mlfrontPayInfoList = mlfrontPayInfoService.selectHighPayInfoListBySearch(mlfrontPayInfoReq);
		page = new PageInfo(mlfrontPayInfoList, PagNum);
		
		return Msg.success().add("pageInfo", page).add("mlfrontPayInfoList", mlfrontPayInfoList);
		
	}
	
	//PayinfoStatus(5);//0未支付//1支付成功//2审单完毕//3发货完毕 //4已退款//5发送弃购//6重复单关
	//OrderStatus(6);//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
	
	/**
	 * 9.0	zsh	200720
	 * to	手动发送物流邮件后的通知manualUpdateSendSucceed
	 * @param PayinfoId,PayinfoSendnum
	 * @return
	 */
	@RequestMapping(value="/manualUpdateSendSucceed",method=RequestMethod.POST)
	@ResponseBody
	public Msg manualUpdateSendSucceed(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontPayInfo mlfrontPayInfoInto) {
		
		//初始化请求参数
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		mlfrontPayInfoReq.setPayinfoId(mlfrontPayInfoInto.getPayinfoId());
		String sendNumStr = mlfrontPayInfoInto.getPayinfoSendnum();
		String sendNumStrNow = sendNumStr.replace("intofail", "succeed");
		System.out.println("manualUpdateSendSucceed,mlfrontPayInfoReq"+mlfrontPayInfoReq.toString());
		mlfrontPayInfoReq.setPayinfoSendnum(sendNumStrNow);
		
		//这一条需要手动发送邮件:
		
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoReq);
		
		return Msg.success().add("resMsg", "本次刷新没有状态是已支付的单子,无改变");
	}
	
}