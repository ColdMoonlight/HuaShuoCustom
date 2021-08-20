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
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.UrlCount;
import com.atguigu.common.Msg;
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
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlfrontOrder")
public class MlfrontOrderController {
		
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
	
	/**
	 * 1.0	onuse	20191225	检查
	 * 前台移动端获取详情页面mfront/tomOrderDetailPage
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/getNowOrderDetail",method=RequestMethod.GET)
	@ResponseBody
	public Msg getNowOrderDetail(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		//接收传递进来的参数
		MlfrontUser loginUser =(MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		if(loginUser==null){
			usertype = 0;
		}else{
			usertype = 1;
		}
		Integer orderId =  (Integer) session.getAttribute("orderId");
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		mlfrontOrderItemReq.setOrderId(orderId);
		List<MlfrontOrderItem> mlfrontOrderItemList = mlfrontOrderItemService.selectMlfrontOrderItemByOrderId(mlfrontOrderItemReq);
		//返回视图
		return Msg.success().add("resMsg", "更新成功").add("mlfrontOrderItemList", mlfrontOrderItemList).add("orderId", orderId).add("usertype", usertype);
	}
	
	/**2.0	useOn	200323
	 * Cheekout页面,加减更新order表中的OrderItem数量。
	 * @param MlfrontOrder
	 */
	@RequestMapping(value="/updateOrderItemNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateOrderItemNum(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrderItem mlfrontOrderItem){
		
		Integer orderitemId =  mlfrontOrderItem.getOrderitemId();
		Integer orderitemPskuNumber = mlfrontOrderItem.getOrderitemPskuNumber();
		
		MlfrontOrderItem mlfrontOrderItemReq = new MlfrontOrderItem();
		mlfrontOrderItemReq.setOrderitemId(orderitemId);
		mlfrontOrderItemReq.setOrderitemPskuNumber(orderitemPskuNumber);
		
		mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemReq);
		
		return Msg.success().add("resMsg", "更新成功"); 
	}
	
	/**
	 * 3.0	zsh 0615
	 * 删除购物车中的项delCartItem
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/delOrderItem",method=RequestMethod.POST)
	@ResponseBody
	public Msg delOrderItem(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontOrderItem mlfrontOrderItem) throws Exception{
		//接收传递进来的参数
		Integer orderitemIdOriginal = mlfrontOrderItem.getOrderitemId();
		int isDelSuccess = 0;
		if(orderitemIdOriginal==null){
			//前台传递异常,没有把订单项的id值传进来
			return Msg.success().add("resMsg", "删除失败,主建为null,请联系管理员").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败,alert	resMsg/1,删除成功
		}else{
			//通过cartitem的之间查回该条信息,从中取出cartId
			MlfrontOrderItem mlfrontOrderItemReq  =new MlfrontOrderItem();
			mlfrontOrderItemReq.setOrderitemId(orderitemIdOriginal);
			List<MlfrontOrderItem> mlfrontOrderItemResList = mlfrontOrderItemService.selectMlfrontOrderItemById(mlfrontOrderItemReq);
			if(mlfrontOrderItemResList.size()>0){
				Integer orderId = mlfrontOrderItemResList.get(0).getOrderId();
				String orderitemIdOriginalStr = orderitemIdOriginal+"";
				String nowTime = DateUtil.strTime14s();
				MlfrontOrder MlfrontOrderReq = new MlfrontOrder();
				MlfrontOrderReq.setOrderId(orderId);
				List<MlfrontOrder> mlfrontOrderListRes  = mlfrontOrderService.selectMlfrontOrderById(MlfrontOrderReq);
				MlfrontOrder mlfrontOrderRes = mlfrontOrderListRes.get(0);
				//1根据cart该购物车中的本条cartItemStr中的字段
				String orderitemidstr = mlfrontOrderRes.getOrderOrderitemidstr();
				String orderItemArr[] =orderitemidstr.split(",");
				String orderitemStrEnd = "";
				int orginalItemNum = orderItemArr.length;
				if(orderItemArr.length>1){
					//1.1如果有多个删除字段中的该项
					for(int i =0;i<orderItemArr.length;i++){
						String tem = orderItemArr[i];
						if(tem.equals(orderitemIdOriginalStr)){
							continue;
						}else{
							orderitemStrEnd=orderitemStrEnd+tem+",";
						}
					}
					int lastIN = orderitemStrEnd.length();
					orderitemStrEnd = orderitemStrEnd.substring(0, (lastIN-1));
					//更新cart中的cartitemStr字段
					MlfrontOrder mlfrontOrderreqE = new MlfrontOrder();
					mlfrontOrderreqE.setOrderId(orderId);
					mlfrontOrderreqE.setOrderOrderitemidstr(orderitemStrEnd);
					mlfrontOrderreqE.setOrderMotifytime(nowTime);
					mlfrontOrderService.updateByPrimaryKeySelective(mlfrontOrderreqE);
				}else{
					//1.2如果只有一个,删掉购物车
					mlfrontOrderService.deleteByPrimaryKey(orderId);
				}
				//2删除该条CartItem信息
				mlfrontOrderItemService.deleteByPrimaryKey(orderitemIdOriginal);
				orginalItemNum=orginalItemNum-1;
				isDelSuccess = 1;
				return Msg.success().add("resMsg", "删除成功").add("isDelSuccess", isDelSuccess).add("orginalItemNum", orginalItemNum);//isDelSuccess0删除失败,alert	resMsg/1,删除成功
			}else{
				return Msg.success().add("resMsg", "删除失败,该cartitem项不存在,请联系管理员").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败,alert	resMsg/1,删除成功
			}
		}			
	}
	
	/**4.0	useOn	200323
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
		session.setAttribute("CouponCodeMoney", CouponCodeMoneyStr);
		//减去优惠券优惠的钱
		totalprice = totalprice.subtract(CouponCodeMoney);
		
		//2.0.2计算地址价格,计算优惠价格,插入order项目价格
		//2.0.2.1拿到地址ID,
		Integer AddressId = mlfrontOrder.getOrderAddressinfoId();
		if(AddressId==null){
			AddressId= (Integer) session.getAttribute("realAddressId");
		}
		//2.0.2.2查询英文名,查询该英文名的价格运费价格
		Integer addressMoney = getAddressMoney(AddressId);
		String addressMoneyStr= df1.format(addressMoney);
		session.setAttribute("addressMoney", addressMoneyStr);
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
		session.setAttribute("mlfrontAddressToPay", mlfrontAddressToPay);
		session.setAttribute("totalprice", totalprice);
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
		
		session.setAttribute("payinfoId", payinfoId);
		
		session.setAttribute("sendAddressinfoId", payAddressinfoId);
		
		//取出sessionid,再次放入
		Integer orderIdFinally = (Integer) session.getAttribute("orderId");
		session.setAttribute("orderId", orderIdFinally);
		//4.0传入orderid,查询其中的orderItemID,找到cartID 找到cartid,移除购物车中的
		updateCart(mlfrontOrder);
		//5.0发起支付
		Integer isSuccess = 0;//返回0,跳支付成功页面;返回1,跳支付失败页面
		return Msg.success().add("resMsg", "更新成功").add("isSuccess", isSuccess).add("payinfoId", payinfoId).add("orderId", orderIdFinally);
	}
	
	/**
	 * 3.1查询英文名,查询该英文名的价格运费价格
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
	 * 3.2查询该优惠码的优惠价格
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
	 * 3.2.1上面方法子方法
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
		    //System.out.println("a大于等于b");
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
	 * 3.3更新表字段
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
	 * 3.4处理一分钱问题
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
	 * 6.1计算折后的筹款价格
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
	 * 7.0	useOn	0505
	 * 查看单条类目的详情细节
	 * @param mlfrontOrderOne
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontOrderDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontOrderDetail(@RequestBody MlfrontOrder mlfrontOrder){
		
		Integer orderId = mlfrontOrder.getOrderId();
		MlfrontOrder mlfrontOrderReq = new MlfrontOrder();
		mlfrontOrderReq.setOrderId(orderId);
		//查询本条
		List<MlfrontOrder> mlfrontOrderResList =mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderReq);
		MlfrontOrder mlfrontOrderOne = new MlfrontOrder();
		List<MlfrontOrderItem>  mlfrontOrderItemList = new ArrayList<MlfrontOrderItem>();
		Integer addressinfoId =0;
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		MlfrontAddress mlfrontAddressRes = new MlfrontAddress();
		List<MlfrontAddress> mlfrontAddressList = new ArrayList<MlfrontAddress>();
		Integer areafreightMoney = 0;
		if(mlfrontOrderResList.size()>0){
			mlfrontOrderOne =mlfrontOrderResList.get(0);
			addressinfoId = mlfrontOrderOne.getOrderAddressinfoId();
			mlfrontAddressReq.setAddressId(addressinfoId);
//			mlfrontAddressList =mlfrontAddressService.selectMlfrontAddressById(mlfrontAddressReq);
			mlfrontAddressList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
			if(mlfrontAddressList.size()>0){
				mlfrontAddressRes = mlfrontAddressList.get(0);
				//从中取出国家字段
				String areafreightCountryCode = mlfrontAddressRes.getAddressCountryCode();
				MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
//				mlbackAreafreightReq.setAreafreightCountryEnglish(areafreightCountryEnglish);//
				mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
				//查询本条
//				List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByEng(mlbackAreafreightReq);
				List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
				if(mlbackAreafreightResList.size()>0){
					areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
				}
			}
			//2.3从详情中拿到orderItemIDStr;
			String orderItemIdsStr = mlfrontOrderOne.getOrderOrderitemidstr();
			//取出mlfrontOrderOne的数量对象
			//如果对象那个中的详情为空
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
		}else{
			mlfrontOrderOne = null;
		}
		
		
		List<MlfrontPayInfo> mlfrontPayInfoOneList = new ArrayList<MlfrontPayInfo>();
		
		MlfrontPayInfo mlfrontPayInfoOne = new MlfrontPayInfo();
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		
		Integer payinfoOid = mlfrontOrderOne.getOrderId();
		
		mlfrontPayInfoReq.setPayinfoOid(payinfoOid);
		
		mlfrontPayInfoOneList = mlfrontPayInfoService.selectHighPayInfoListBySearch(mlfrontPayInfoReq);
		
		if(mlfrontPayInfoOneList.size()>0){
			mlfrontPayInfoOne = mlfrontPayInfoOneList.get(0);
		}else{
			mlfrontPayInfoOne = null;
		}
		
		return Msg.success().add("resMsg", "查看单条mlfrontOrderOne的详情细节完毕")
					.add("mlfrontOrderOne", mlfrontOrderOne).add("mlfrontOrderItemList", mlfrontOrderItemList)
					.add("addressInfo", mlfrontAddressRes).add("areafreightMoney", areafreightMoney).add("mlfrontPayInfoOne", mlfrontPayInfoOne);
	}

}