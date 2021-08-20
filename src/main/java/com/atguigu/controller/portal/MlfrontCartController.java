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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.bean.MlbackAddCartViewDetail;
import com.atguigu.bean.MlbackAddCheakoutViewDetail;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlfrontCart;
import com.atguigu.bean.MlfrontCartItem;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAddCartViewDetailService;
import com.atguigu.service.MlbackAddCheakoutViewDetailService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.service.MlfrontCartItemService;
import com.atguigu.service.MlfrontCartService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EncryptUtil;

@Controller
@RequestMapping("/MlbackCart")
public class MlfrontCartController {
	
	@Autowired
	MlfrontCartService mlfrontCartService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlfrontCartItemService mlfrontCartItemService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlbackAddCartViewDetailService mlbackAddCartViewDetailService;
	
	@Autowired
	MlbackAddCheakoutViewDetailService mlbackAddCheakoutViewDetailService;
	
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	/**
	 * 1.0	zsh200729
	 * 前台购物车列表页面toCartList
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toCartList")
	public String toCartList(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "portal/CartOrderPay/cartList";
	}
	
	/**
	 * 2.0	zsh200729
	 * 前台移动端解算页面front/cheakOut
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toCheakOut")
	public String toCheakOut(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "portal/CartOrderPay/checkOut";
	}
	
	//这里加方法
	@RequestMapping(value="/toCheakOutMoreBuyByhtml",method=RequestMethod.GET)
	public ModelAndView toCheakOutMoreBuyByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "orderIdIntoStr") String orderIdIntoStr) throws Exception{

		//先解码
		String orginalOrderIdStr = EncryptUtil.XORdecode(orderIdIntoStr,"megalook");
		System.out.println("本条弃购链接的orderid为orderIdStr:"+orginalOrderIdStr);
		Integer orderId = Integer.parseInt(orginalOrderIdStr);
	
		ModelAndView modelAndView = new ModelAndView();
		//带着id进来了
		MlfrontOrder mlfrontOrderInto = new MlfrontOrder();
		mlfrontOrderInto.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderInto);
		if(mlfrontOrderList.size()>0){
			MlfrontOrder mlfrontOrderOne = mlfrontOrderList.get(0);
			Integer orderStatus = mlfrontOrderOne.getOrderStatus();
			Integer addressId = mlfrontOrderOne.getOrderAddressinfoId();
			if(orderStatus>0){
				//此订单不是弃购订单
				//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
				modelAndView.setViewName("redirect:/");
				return modelAndView;
			}else{
				
				//有客户点击,把这一单的payinfo状态弄成付款状态
				updatePayInfo(orderId);
				
				//这是弃购订单,开始操作；
				//要判断这一单是不是登陆客户
				if(mlfrontOrderOne.getOrderUid()==null){
					//是游客
					session.setAttribute("orderId", orderId);
					session.setAttribute("addressId", addressId);
				}else{
					//取出客户id,查询账号密码
					MlfrontUser mlfrontUserReq =new MlfrontUser();
					Integer userId = mlfrontOrderOne.getOrderUid();
					mlfrontUserReq.setUserId(userId);
					List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserByConditionS(mlfrontUserReq);
					if(mlfrontUserList.size()>0){
						MlfrontUser mlfrontUserRes = mlfrontUserList.get(0);
						session.setAttribute("loginUser", mlfrontUserRes);
						session.setAttribute("orderId", orderId);
						session.setAttribute("addressId", addressId);
					}else{
						//没查到数据,数据错误,直接回页面吧
						modelAndView.setViewName("redirect:/");
						return modelAndView;
					}
				}
				modelAndView.setViewName("portal/CartOrderPay/checkOut");
				return modelAndView;
			}
		}else{
			//先判断这个orderid在不在,不在直接跳转该订单已经结束;
			modelAndView.setViewName("redirect:/");
			return modelAndView;
		}
	 }
	
	private void updatePayInfo(Integer orderId) {
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		
		mlfrontPayInfoReq.setPayinfoOid(orderId);
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectHighPayInfoListBySearch(mlfrontPayInfoReq);
		
		MlfrontPayInfo mlfrontPayInfoRes = new MlfrontPayInfo();
		
		MlfrontPayInfo mlfrontPayInfoUpdate = new MlfrontPayInfo();
		
		if(mlfrontPayInfoList.size()>0){
			mlfrontPayInfoRes = mlfrontPayInfoList.get(0);
			Integer payInfoId = mlfrontPayInfoRes.getPayinfoId();
			mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
			mlfrontPayInfoUpdate.setPayinfoIfEmail(9);//客户点击成功返回
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
		}
	}
	
	//这里加方法
	@RequestMapping(value="/toCheakOutSMSMoreBuyByhtml",method=RequestMethod.GET)
	public ModelAndView toCheakOutSMSMoreBuyByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "orderIdIntoStr") String orderIdIntoStr) throws Exception{

		//先解码
		String orginalOrderIdStr = EncryptUtil.XORdecode(orderIdIntoStr,"megalook");
		System.out.println("本条弃购链接的orderid为orderIdStr:"+orginalOrderIdStr);
		Integer orderId = Integer.parseInt(orginalOrderIdStr);
	
		ModelAndView modelAndView = new ModelAndView();
		//带着id进来了
		MlfrontOrder mlfrontOrderInto = new MlfrontOrder();
		mlfrontOrderInto.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderInto);
		if(mlfrontOrderList.size()>0){
			MlfrontOrder mlfrontOrderOne = mlfrontOrderList.get(0);
			Integer orderStatus = mlfrontOrderOne.getOrderStatus();
			Integer addressId = mlfrontOrderOne.getOrderAddressinfoId();
			if(orderStatus>0){
				//此订单不是弃购订单
				//0未支付//1支付成功//2支付失败//3审单完毕 //4发货完毕//5已退款//6发送弃购//7重复单关闭
				modelAndView.setViewName("redirect:/");
				return modelAndView;
			}else{
				
				//有客户点击,把这一单的payinfo状态弄成付款状态
				updatePayInfoStatusBySMS(orderId);
				
				//这是弃购订单,开始操作；
				//要判断这一单是不是登陆客户
				if(mlfrontOrderOne.getOrderUid()==null){
					//是游客
					session.setAttribute("orderId", orderId);
					session.setAttribute("addressId", addressId);
				}else{
					//取出客户id,查询账号密码
					MlfrontUser mlfrontUserReq =new MlfrontUser();
					Integer userId = mlfrontOrderOne.getOrderUid();
					mlfrontUserReq.setUserId(userId);
					List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserByConditionS(mlfrontUserReq);
					if(mlfrontUserList.size()>0){
						MlfrontUser mlfrontUserRes = mlfrontUserList.get(0);
						session.setAttribute("loginUser", mlfrontUserRes);
						session.setAttribute("orderId", orderId);
						session.setAttribute("addressId", addressId);
					}else{
						//没查到数据,数据错误,直接回页面吧
						modelAndView.setViewName("redirect:/");
						return modelAndView;
					}
				}
				modelAndView.setViewName("portal/CartOrderPay/checkOut");
				return modelAndView;
			}
		}else{
			//先判断这个orderid在不在,不在直接跳转该订单已经结束;
			modelAndView.setViewName("redirect:/");
			return modelAndView;
		}
	 }
	
	private void updatePayInfoStatusBySMS(Integer orderId) {
		
		MlfrontPayInfo mlfrontPayInfoReq = new MlfrontPayInfo();
		
		mlfrontPayInfoReq.setPayinfoOid(orderId);
		
		List<MlfrontPayInfo> mlfrontPayInfoList = mlfrontPayInfoService.selectHighPayInfoListBySearch(mlfrontPayInfoReq);
		
		MlfrontPayInfo mlfrontPayInfoRes = new MlfrontPayInfo();
		
		MlfrontPayInfo mlfrontPayInfoUpdate = new MlfrontPayInfo();
		
		if(mlfrontPayInfoList.size()>0){
			mlfrontPayInfoRes = mlfrontPayInfoList.get(0);
			Integer payInfoId = mlfrontPayInfoRes.getPayinfoId();
			mlfrontPayInfoUpdate.setPayinfoId(payInfoId);
			mlfrontPayInfoUpdate.setPayinfoIfSMS(9);//客户点击成功返回
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
		}
	}

	/**
	 * 3.0	200612
	 * getMlfrontCartByDate控制面板根据时间统计数据所需
	 * @param mlfrontCart
	 * @return 
	 * */
	@RequestMapping(value="/getMlfrontCartByDate",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontCartByDate(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody MlfrontCart mlfrontCart){
	
		String beginTime = mlfrontCart.getCartCreatetime();
		String endTime = mlfrontCart.getCartMotifytime();
		MlfrontCart mlfrontCartReq = new MlfrontCart();
		mlfrontCartReq.setCartCreatetime(beginTime);
		mlfrontCartReq.setCartMotifytime(endTime);
		List<MlfrontCart> mlfrontCartReqList = mlfrontCartService.selectMlfrontCartByDate(mlfrontCartReq);
		Integer countNumber =mlfrontCartReqList.size();
		return Msg.success().add("countNumber", countNumber).add("resMsg", "加购数查询成功");
	}
	
	/**
	 * 4.0	zsh 200617
	 * 添加产品项进购物车toAddToCart
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/toAddToCart",method=RequestMethod.POST)
	@ResponseBody
	public Msg toAddToCart(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//4.1
		insertAddCartView(mlfrontCartItem,session);
		String sessionId = session.getId();
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		//判断session中是否有登录信息 ,if 有,新增进入cart中,没有新增进入
		if(loginUser==null){
			//获取ip信息,去cart中用ip查询,是否有该ip的购物车
			MlfrontCart MlfrontCartReq = new MlfrontCart();
			MlfrontCartReq.setCartIp(Userip);
			MlfrontCartReq.setCartStatus(0);
			List<MlfrontCart> MlfrontCartResList = mlfrontCartService.selectMlfrontCartByIp(MlfrontCartReq);
			Integer ifHave = 1;
			if(MlfrontCartResList.size()>0){
				String cartitemIdstrUser = MlfrontCartResList.get(0).getCartitemIdstr();
				if(cartitemIdstrUser.length()>0){
					ifHave = 1;
				}else{
					//名下有购物车,但是为空
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//此时要删掉这个为空的购物车
				}
			}else{
				//如果名下没购物车
				ifHave = 0;
			}
			if(ifHave==1){
				//如果有,获取该购物车的itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//判断是否有本次添加的产品,如果没有itemStr中加入本次的productId,
				Integer pid = mlfrontCartItem.getCartitemProductId();
				String skuIdstr = mlfrontCartItem.getCartitemProductskuIdstr();
				String skuIdnamestr = mlfrontCartItem.getCartitemProductskuIdnamestr();
				Integer pskuId = mlfrontCartItem.getCartitemProductskuId();
				String pskuName =mlfrontCartItem.getCartitemProductskuName();
				String pskuCode = mlfrontCartItem.getCartitemProductskuCode();
				int proNumberNew =mlfrontCartItem.getCartitemProductNumber();
				String[] aa = cartitemIdstrUser.split(",");
				int number = 0;
				for(int i=0;i<aa.length;i++){
					number++;
					String CartItemId = aa[i];
					Integer CartItemIdInt =Integer.parseInt(CartItemId);
					MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
					mlfrontCartItemFor.setCartitemId(CartItemIdInt);
					mlfrontCartItemFor.setCartitemProductId(pid);
					mlfrontCartItemFor.setCartitemProductskuIdstr(skuIdstr);
					mlfrontCartItemFor.setCartitemProductskuIdnamestr(skuIdnamestr);
					mlfrontCartItemFor.setCartitemProductskuId(pskuId);
					mlfrontCartItemFor.setCartitemProductskuName(pskuName);
					mlfrontCartItemFor.setCartitemProductskuCode(pskuCode);
					//查看该id+该pid+skuIdstr+skuIdnamestr+skuId+skuName+skuCode时候已经有了
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//如果存在
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//该sku的Cartitem的number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//更新
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//没有该sku的Cartitem	新增该条Cartitem sku
							//将该id写入cart对象的cartItemStr中
							System.out.println("操作说明：当前客户添加购物车,此前购物车中不包含本次添加的产品"); 
							//将本id加入原来的idstr中
							//更新一下该user的购物车中的cartitemIdstr字段
							mlfrontCartItem.setCartitemStatus(0);
							mlfrontCartItem.setCartitemCreatetime(nowTime);
							mlfrontCartItem.setCartitemMotifytime(nowTime);
							mlfrontCartItemService.insertSelective(mlfrontCartItem);
							Integer cartItemId = mlfrontCartItem.getCartitemId();
							String cartItemIdStrnew = cartItemId+"";
							cartitemIdstrUser=cartitemIdstrUser+","+cartItemIdStrnew;
							MlfrontCart mlfrontCartAfter = new MlfrontCart();
							mlfrontCartAfter.setCartId(cartId);
							mlfrontCartAfter.setCartUid(cartUid);
							mlfrontCartAfter.setCartitemIdstr(cartitemIdstrUser);
							mlfrontCartAfter.setCartMotifytime(nowTime);
							mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartAfter);
							//将本mlfrontCartItem插入mlfrontCartItem表中
							//更新本条cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//这个ip没来过,直接新增一条即可mlfrontCartItem,将主键放入cart对象中,之后在新增该对象cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				Integer cartItemId = mlfrontCartItem.getCartitemId();
				//拿回来主键ID		mlfrontCartItem
				MlfrontCart MlfrontCartItemReturn = new MlfrontCart();
				String cartItemIdStr = cartItemId+"";
				MlfrontCartItemReturn.setCartitemIdstr(cartItemIdStr);
				MlfrontCartItemReturn.setCartIp(Userip);
				MlfrontCartItemReturn.setCartStatus(0);
				MlfrontCartItemReturn.setCartCreatetime(nowTime);
				MlfrontCartItemReturn.setCartMotifytime(nowTime);
				mlfrontCartService.insertSelective(MlfrontCartItemReturn);
				
				Integer cartAfterId = MlfrontCartItemReturn.getCartId();
				mlfrontCartItem.setCartitemId(cartItemId);
				mlfrontCartItem.setCartitemCartId(cartAfterId);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
			}
		}else{
			//获取uid信息,去cart中用uid查询,是否有该uid的购物车
			MlfrontCart MlfrontCartReq = new MlfrontCart();
			Integer uid =loginUser.getUserId();
			MlfrontCartReq.setCartUid(uid);
			MlfrontCartReq.setCartStatus(0);
			List<MlfrontCart> MlfrontCartResList = mlfrontCartService.selectMlfrontCartByUidAndStatus(MlfrontCartReq);
			Integer ifHave = 1;
			if(MlfrontCartResList.size()>0){
				String cartitemIdstrUser = MlfrontCartResList.get(0).getCartitemIdstr();
				if(cartitemIdstrUser.length()>0){
					ifHave = 1;
				}else{
					//名下有购物车,但是为空
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//此时要删掉这个为空的购物车
				}
			}else{
				//如果名下没购物车
				ifHave = 0;
			}
			if(ifHave==1){
				//如果有,获取该购物车的itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//判断是否有本次添加的产品,如果没有itemStr中加入本次的productId,
				Integer pid = mlfrontCartItem.getCartitemProductId();
				String skuIdstr = mlfrontCartItem.getCartitemProductskuIdstr();
				String skuIdnamestr = mlfrontCartItem.getCartitemProductskuIdnamestr();
				Integer pskuId = mlfrontCartItem.getCartitemProductskuId();
				String pskuName =mlfrontCartItem.getCartitemProductskuName();
				String pskuCode = mlfrontCartItem.getCartitemProductskuCode();
				int proNumberNew =mlfrontCartItem.getCartitemProductNumber();
				String[] aa = cartitemIdstrUser.split(",");
				int number = 0;
				for(int i=0;i<aa.length;i++){
					number++;
					String CartItemId = aa[i];
					Integer CartItemIdInt =Integer.parseInt(CartItemId);
					MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
					mlfrontCartItemFor.setCartitemId(CartItemIdInt);
					mlfrontCartItemFor.setCartitemProductId(pid);
					mlfrontCartItemFor.setCartitemProductskuIdstr(skuIdstr);
					mlfrontCartItemFor.setCartitemProductskuIdnamestr(skuIdnamestr);
					mlfrontCartItemFor.setCartitemProductskuId(pskuId);
					mlfrontCartItemFor.setCartitemProductskuName(pskuName);
					mlfrontCartItemFor.setCartitemProductskuCode(pskuCode);
					//查看该id+该pid+skuIdstr+skuIdnamestr+skuNamestr时候已经有了
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//如果存在
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//该sku的Cartitem的number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//更新
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//没有该sku的Cartitem
							//新增该条Cartitem sku
							//将该id写入cart对象的cartItemStr中
							System.out.println("操作说明：当前客户添加购物车,此前购物车中不包含本次添加的产品"); 
							//将本id加入原来的idstr中
							//更新一下该user的购物车中的cartitemIdstr字段
							mlfrontCartItem.setCartitemStatus(0);
							mlfrontCartItem.setCartitemCreatetime(nowTime);
							mlfrontCartItem.setCartitemMotifytime(nowTime);
							mlfrontCartItemService.insertSelective(mlfrontCartItem);
							
							Integer cartItemId = mlfrontCartItem.getCartitemId();

							String cartItemIdStrnew = cartItemId+"";
							cartitemIdstrUser=cartitemIdstrUser+","+cartItemIdStrnew;
							MlfrontCart mlfrontCartAfter = new MlfrontCart();
							mlfrontCartAfter.setCartId(cartId);
							mlfrontCartAfter.setCartUid(cartUid);
							mlfrontCartAfter.setCartitemIdstr(cartitemIdstrUser);
							mlfrontCartAfter.setCartMotifytime(nowTime);
							mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartAfter);
							//将本mlfrontCartItem插入mlfrontCartItem表中
							//更新本条cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//这个ip没来过,直接新增一条即可mlfrontCartItem,将主键放入cart对象中,之后在新增该对象cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				
				Integer cartItemId = mlfrontCartItem.getCartitemId();

				//拿回来主键ID		mlfrontCartItem
				MlfrontCart MlfrontCartItemReturn = new MlfrontCart();
				String cartItemIdStr = cartItemId+"";
				MlfrontCartItemReturn.setCartitemIdstr(cartItemIdStr);
				MlfrontCartItemReturn.setCartUid(uid);
				MlfrontCartItemReturn.setCartStatus(0);
				MlfrontCartItemReturn.setCartCreatetime(nowTime);
				MlfrontCartItemReturn.setCartMotifytime(nowTime);
				mlfrontCartService.insertSelective(MlfrontCartItemReturn);
				
				Integer cartAfterId = MlfrontCartItemReturn.getCartId();

				mlfrontCartItem.setCartitemId(cartItemId);
				mlfrontCartItem.setCartitemCartId(cartAfterId);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
			}
		}
		return Msg.success().add("resMsg", "添加成功");
	}
	
	/**
	 * 4.1	zsh 201221
	 * 添加产品项进购物车toAddMoreProToCart
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/toAddMoreProToCart",method=RequestMethod.POST)
	@ResponseBody
	public Msg toAddMoreProToCart(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam("addMoreProToCartTeams") String addMoreProToCartTeams) throws Exception{
			
		//1.收到productskuPid,查询该id下,所有的产品sku
		//收到list集合,先对该产品下,所有的生效中的进行状态为0,
		//遍历集合
		//取出一条,查询这一条,如果存在,将状态改成1,生效中。如果有,更改属性值即可,如果没,新增本条即可。
		
		JSONArray jsonArray= JSON.parseArray(addMoreProToCartTeams);
	    List<MlfrontCartItem> mlfrontCartItemList = jsonArray.toJavaList(MlfrontCartItem.class);
	    
	    for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
	    	
			insertAddCartView(mlfrontCartItem,session);
			//4.1
			String sessionId = session.getId();
			String Userip =sessionId;
			session.setAttribute("Userip", Userip);
			String nowTime = DateUtil.strTime14s();
			MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
			//判断session中是否有登录信息 ,if 有,新增进入cart中,没有新增进入
			if(loginUser==null){
				//获取ip信息,去cart中用ip查询,是否有该ip的购物车
				MlfrontCart MlfrontCartReq = new MlfrontCart();
				MlfrontCartReq.setCartIp(Userip);
				MlfrontCartReq.setCartStatus(0);
				List<MlfrontCart> MlfrontCartResList = mlfrontCartService.selectMlfrontCartByIp(MlfrontCartReq);
				Integer ifHave = 1;
				if(MlfrontCartResList.size()>0){
					String cartitemIdstrUser = MlfrontCartResList.get(0).getCartitemIdstr();
					if(cartitemIdstrUser.length()>0){
						ifHave = 1;
					}else{
						//名下有购物车,但是为空
						Integer cartId = MlfrontCartResList.get(0).getCartId();
						MlfrontCart MlfrontCartdelRep = new MlfrontCart();
						MlfrontCartdelRep.setCartId(cartId);
						mlfrontCartService.deleteByPrimaryKey(cartId);
						ifHave = 0;
						//此时要删掉这个为空的购物车
					}
				}else{
					//如果名下没购物车
					ifHave = 0;
				}
				if(ifHave==1){
					//如果有,获取该购物车的itemStr,
					MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
					String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
					Integer cartId = mlfrontCartUser.getCartId();
					Integer cartUid = mlfrontCartUser.getCartUid();
					//判断是否有本次添加的产品,如果没有itemStr中加入本次的productId,
					Integer pid = mlfrontCartItem.getCartitemProductId();
					String skuIdstr = mlfrontCartItem.getCartitemProductskuIdstr();
					String skuIdnamestr = mlfrontCartItem.getCartitemProductskuIdnamestr();
					Integer pskuId = mlfrontCartItem.getCartitemProductskuId();
					String pskuName =mlfrontCartItem.getCartitemProductskuName();
					String pskuCode = mlfrontCartItem.getCartitemProductskuCode();
					int proNumberNew =mlfrontCartItem.getCartitemProductNumber();
					String[] aa = cartitemIdstrUser.split(",");
					int number = 0;
					for(int i=0;i<aa.length;i++){
						number++;
						String CartItemId = aa[i];
						Integer CartItemIdInt =Integer.parseInt(CartItemId);
						MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
						mlfrontCartItemFor.setCartitemId(CartItemIdInt);
						mlfrontCartItemFor.setCartitemProductId(pid);
						mlfrontCartItemFor.setCartitemProductskuIdstr(skuIdstr);
						mlfrontCartItemFor.setCartitemProductskuIdnamestr(skuIdnamestr);
						mlfrontCartItemFor.setCartitemProductskuId(pskuId);
						mlfrontCartItemFor.setCartitemProductskuName(pskuName);
						mlfrontCartItemFor.setCartitemProductskuCode(pskuCode);
						//查看该id+该pid+skuIdstr+skuIdnamestr+skuId+skuName+skuCode时候已经有了
						List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
						if(mlfrontCartItemListFor.size()>0){
							//如果存在
							MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
							int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
							//该sku的Cartitem的number+1,
							ProductNumber= ProductNumber+proNumberNew;
							mlfrontCartItemOne.setCartitemId(CartItemIdInt);
							mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
							mlfrontCartItemOne.setCartitemCreatetime(nowTime);
							mlfrontCartItemOne.setCartitemMotifytime(nowTime);
							//更新
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
							break;
						}else{
							if(number <aa.length){
								continue;
							}else{
								//没有该sku的Cartitem	新增该条Cartitem sku
								//将该id写入cart对象的cartItemStr中
								System.out.println("操作说明：当前客户添加购物车,此前购物车中不包含本次添加的产品"); 
								//将本id加入原来的idstr中
								//更新一下该user的购物车中的cartitemIdstr字段
								mlfrontCartItem.setCartitemStatus(0);
								mlfrontCartItem.setCartitemCreatetime(nowTime);
								mlfrontCartItem.setCartitemMotifytime(nowTime);
								mlfrontCartItemService.insertSelective(mlfrontCartItem);
								Integer cartItemId = mlfrontCartItem.getCartitemId();
								String cartItemIdStrnew = cartItemId+"";
								cartitemIdstrUser=cartitemIdstrUser+","+cartItemIdStrnew;
								MlfrontCart mlfrontCartAfter = new MlfrontCart();
								mlfrontCartAfter.setCartId(cartId);
								mlfrontCartAfter.setCartUid(cartUid);
								mlfrontCartAfter.setCartitemIdstr(cartitemIdstrUser);
								mlfrontCartAfter.setCartMotifytime(nowTime);
								mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartAfter);
								//将本mlfrontCartItem插入mlfrontCartItem表中
								//更新本条cartitem,
								mlfrontCartItem.setCartitemId(cartItemId);
								mlfrontCartItem.setCartitemCartId(cartId);
								mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
								break;
							}
						}
					}
				}else{
					//这个ip没来过,直接新增一条即可mlfrontCartItem,将主键放入cart对象中,之后在新增该对象cart
					mlfrontCartItem.setCartitemCreatetime(nowTime);
					mlfrontCartItem.setCartitemMotifytime(nowTime);
					mlfrontCartItem.setCartitemStatus(0);
					mlfrontCartItemService.insertSelective(mlfrontCartItem);
					Integer cartItemId = mlfrontCartItem.getCartitemId();
					//拿回来主键ID		mlfrontCartItem
					MlfrontCart MlfrontCartItemReturn = new MlfrontCart();
					String cartItemIdStr = cartItemId+"";
					MlfrontCartItemReturn.setCartitemIdstr(cartItemIdStr);
					MlfrontCartItemReturn.setCartIp(Userip);
					MlfrontCartItemReturn.setCartStatus(0);
					MlfrontCartItemReturn.setCartCreatetime(nowTime);
					MlfrontCartItemReturn.setCartMotifytime(nowTime);
					mlfrontCartService.insertSelective(MlfrontCartItemReturn);
					
					Integer cartAfterId = MlfrontCartItemReturn.getCartId();
					mlfrontCartItem.setCartitemId(cartItemId);
					mlfrontCartItem.setCartitemCartId(cartAfterId);
					mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
				}
			}else{
				//获取uid信息,去cart中用uid查询,是否有该uid的购物车
				MlfrontCart MlfrontCartReq = new MlfrontCart();
				Integer uid =loginUser.getUserId();
				MlfrontCartReq.setCartUid(uid);
				MlfrontCartReq.setCartStatus(0);
				List<MlfrontCart> MlfrontCartResList = mlfrontCartService.selectMlfrontCartByUidAndStatus(MlfrontCartReq);
				Integer ifHave = 1;
				if(MlfrontCartResList.size()>0){
					String cartitemIdstrUser = MlfrontCartResList.get(0).getCartitemIdstr();
					if(cartitemIdstrUser.length()>0){
						ifHave = 1;
					}else{
						//名下有购物车,但是为空
						Integer cartId = MlfrontCartResList.get(0).getCartId();
						MlfrontCart MlfrontCartdelRep = new MlfrontCart();
						MlfrontCartdelRep.setCartId(cartId);
						mlfrontCartService.deleteByPrimaryKey(cartId);
						ifHave = 0;
						//此时要删掉这个为空的购物车
					}
				}else{
					//如果名下没购物车
					ifHave = 0;
				}
				if(ifHave==1){
					//如果有,获取该购物车的itemStr,
					MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
					String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
					Integer cartId = mlfrontCartUser.getCartId();
					Integer cartUid = mlfrontCartUser.getCartUid();
					//判断是否有本次添加的产品,如果没有itemStr中加入本次的productId,
					Integer pid = mlfrontCartItem.getCartitemProductId();
					String skuIdstr = mlfrontCartItem.getCartitemProductskuIdstr();
					String skuIdnamestr = mlfrontCartItem.getCartitemProductskuIdnamestr();
					Integer pskuId = mlfrontCartItem.getCartitemProductskuId();
					String pskuName =mlfrontCartItem.getCartitemProductskuName();
					String pskuCode = mlfrontCartItem.getCartitemProductskuCode();
					int proNumberNew =mlfrontCartItem.getCartitemProductNumber();
					String[] aa = cartitemIdstrUser.split(",");
					int number = 0;
					for(int i=0;i<aa.length;i++){
						number++;
						String CartItemId = aa[i];
						Integer CartItemIdInt =Integer.parseInt(CartItemId);
						MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
						mlfrontCartItemFor.setCartitemId(CartItemIdInt);
						mlfrontCartItemFor.setCartitemProductId(pid);
						mlfrontCartItemFor.setCartitemProductskuIdstr(skuIdstr);
						mlfrontCartItemFor.setCartitemProductskuIdnamestr(skuIdnamestr);
						mlfrontCartItemFor.setCartitemProductskuId(pskuId);
						mlfrontCartItemFor.setCartitemProductskuName(pskuName);
						mlfrontCartItemFor.setCartitemProductskuCode(pskuCode);
						//查看该id+该pid+skuIdstr+skuIdnamestr+skuNamestr时候已经有了
						List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
						if(mlfrontCartItemListFor.size()>0){
							//如果存在
							MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
							int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
							//该sku的Cartitem的number+1,
							ProductNumber= ProductNumber+proNumberNew;
							mlfrontCartItemOne.setCartitemId(CartItemIdInt);
							mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
							mlfrontCartItemOne.setCartitemCreatetime(nowTime);
							mlfrontCartItemOne.setCartitemMotifytime(nowTime);
							//更新
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
							break;
						}else{
							if(number <aa.length){
								continue;
							}else{
								//没有该sku的Cartitem
								//新增该条Cartitem sku
								//将该id写入cart对象的cartItemStr中
								System.out.println("操作说明：当前客户添加购物车,此前购物车中不包含本次添加的产品"); 
								//将本id加入原来的idstr中
								//更新一下该user的购物车中的cartitemIdstr字段
								mlfrontCartItem.setCartitemStatus(0);
								mlfrontCartItem.setCartitemCreatetime(nowTime);
								mlfrontCartItem.setCartitemMotifytime(nowTime);
								mlfrontCartItemService.insertSelective(mlfrontCartItem);
								
								Integer cartItemId = mlfrontCartItem.getCartitemId();
	
								String cartItemIdStrnew = cartItemId+"";
								cartitemIdstrUser=cartitemIdstrUser+","+cartItemIdStrnew;
								MlfrontCart mlfrontCartAfter = new MlfrontCart();
								mlfrontCartAfter.setCartId(cartId);
								mlfrontCartAfter.setCartUid(cartUid);
								mlfrontCartAfter.setCartitemIdstr(cartitemIdstrUser);
								mlfrontCartAfter.setCartMotifytime(nowTime);
								mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartAfter);
								//将本mlfrontCartItem插入mlfrontCartItem表中
								//更新本条cartitem,
								mlfrontCartItem.setCartitemId(cartItemId);
								mlfrontCartItem.setCartitemCartId(cartId);
								mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
								break;
							}
						}
					}
				}else{
					//这个ip没来过,直接新增一条即可mlfrontCartItem,将主键放入cart对象中,之后在新增该对象cart
					mlfrontCartItem.setCartitemCreatetime(nowTime);
					mlfrontCartItem.setCartitemMotifytime(nowTime);
					mlfrontCartItem.setCartitemStatus(0);
					mlfrontCartItemService.insertSelective(mlfrontCartItem);
					
					Integer cartItemId = mlfrontCartItem.getCartitemId();
	
					//拿回来主键ID		mlfrontCartItem
					MlfrontCart MlfrontCartItemReturn = new MlfrontCart();
					String cartItemIdStr = cartItemId+"";
					MlfrontCartItemReturn.setCartitemIdstr(cartItemIdStr);
					MlfrontCartItemReturn.setCartUid(uid);
					MlfrontCartItemReturn.setCartStatus(0);
					MlfrontCartItemReturn.setCartCreatetime(nowTime);
					MlfrontCartItemReturn.setCartMotifytime(nowTime);
					mlfrontCartService.insertSelective(MlfrontCartItemReturn);
					
					Integer cartAfterId = MlfrontCartItemReturn.getCartId();
	
					mlfrontCartItem.setCartitemId(cartItemId);
					mlfrontCartItem.setCartitemCartId(cartAfterId);
					mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
				}
			}
		}
		return Msg.success().add("resMsg", "添加成功");
	}
	
	
	/**5.0	zsh 200617
	 * getCartProductNumber	POST
	 * @param 
	 */
	@RequestMapping(value="/getCartProductNumber",method=RequestMethod.POST)
	@ResponseBody
	public Msg getCartProductNumber(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		String sessionId = session.getId();
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		//判断session中是否有登录信息 ,if 有,新增进入cart中,没有新增进入
		int number = 0;
		String Userip =sessionId;
		MlfrontCart MlfrontCartReq = new MlfrontCart();
		MlfrontCartReq.setCartStatus(0);
		if(loginUser==null){
			MlfrontCartReq.setCartIp(Userip);
			//查询是否有生效中的购物车
			List<MlfrontCart> mlfrontCartResList = mlfrontCartService.selectMlfrontCartByIp(MlfrontCartReq);
			if(mlfrontCartResList.size()>0){
				String cartitemIdStr = mlfrontCartResList.get(0).getCartitemIdstr();
				if(cartitemIdStr.length()>0){
					String[] aa = cartitemIdStr.split(",");
					number=aa.length;
				}
				//System.out.println(number);
			}else{
				number = 0;
			}
		}else{
			Integer uid = loginUser.getUserId();
			MlfrontCartReq.setCartUid(uid);
			//查询是否有生效中的购物车
			List<MlfrontCart> mlfrontCartResList = mlfrontCartService.selectMlfrontCartByUidAndStatus(MlfrontCartReq);
			if(mlfrontCartResList.size()>0){
				String cartitemIdStr = mlfrontCartResList.get(0).getCartitemIdstr();
				if(cartitemIdStr.length()>0){
					String[] aa = cartitemIdStr.split(",");
					number=aa.length;
				}
				//System.out.println(number);
			}else{
				number = 0;
			}
		}
		return Msg.success().add("resMsg", "更新成功").add("number", number);
	}

	/**
	 * 6.0	0530
	 * 选择购物车中的产品项,提交生成订单cartToOrder
	 * 1接住传入的List<cartItem>,遍历 insert订单orderItem。插入order表,返回orderId
	 * 2遍历,更新orderItem中的orderId归属字段；
	 * 3从cart中,把cartItemIdstr字段,更新。  这一步骤,不该出现在这,要放在生成订单那里。就没事了
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/cartToOrder",method=RequestMethod.POST)
	@ResponseBody
	public Msg cartToOrder(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody List<MlfrontCartItem> mlfrontCartItemList) throws Exception{
		//计算cart到boynow的加购次数
		calcFormCartListToCheakoutPage(mlfrontCartItemList,session);
		//从其中一个中获取cartId
		Integer cartId = 0;
		String sessionId = session.getId();
		Integer orderIdFinally = 0;
		//获取ip地址
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<String> cartItemIdStr = new ArrayList<String>();
		List<String> orderItemIdList = new ArrayList<String>();
		String orderItemIdStr = "";
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		MlfrontCartItem mlfrontCartItemGet = new MlfrontCartItem();
		if(loginUser==null){
			//loginUser为null
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项,改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象,准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
				mlfrontOrderItemNew.setOrderitemPseo(mlfrontCartItemreturn.getCartitemProductSeoName());
				mlfrontOrderItemNew.setOrderitemProductMainimgurl(mlfrontCartItemreturn.getCartitemProductMainimgurl());
				mlfrontOrderItemNew.setOrderitemProductOriginalprice(mlfrontCartItemreturn.getCartitemProductOriginalprice());
				mlfrontOrderItemNew.setOrderitemProductAccoff(mlfrontCartItemreturn.getCartitemProductActoff());
				mlfrontOrderItemNew.setOrderitemPskuIdstr(mlfrontCartItemreturn.getCartitemProductskuIdstr());
				mlfrontOrderItemNew.setOrderitemPskuIdnamestr(mlfrontCartItemreturn.getCartitemProductskuIdnamestr());
				mlfrontOrderItemNew.setOrderitemPskuId(mlfrontCartItemreturn.getCartitemProductskuId());
				mlfrontOrderItemNew.setOrderitemPskuName(mlfrontCartItemreturn.getCartitemProductskuName());
				mlfrontOrderItemNew.setOrderitemPskuCode(mlfrontCartItemreturn.getCartitemProductskuCode());
				mlfrontOrderItemNew.setOrderitemPskuMoneystr(mlfrontCartItemreturn.getCartitemProductskuMoneystr());
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("这个值是需要计算的");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//获取新生成的id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//存档便于以后用
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			//插入订单对象
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			mlfrontOrderNew.setOrderIp(Userip);
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//获取新生成的id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//后更新每个orderItem对象
			Integer orderId = mlfrontOrdergetAllOne.getOrderId();
			orderIdFinally = orderId;
			MlfrontOrderItem mlfrontOrderItemOldone = new MlfrontOrderItem();
			for(String orderItemIdOld:orderItemIdList){
				Integer orderItemIdOldInt =  Integer.parseInt(orderItemIdOld);
				mlfrontOrderItemOldone.setOrderitemId(orderItemIdOldInt);
				mlfrontOrderItemOldone.setOrderId(orderId);
				mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemOldone);
			}
		}else{
			//loginUser不为null
			Integer Uid = loginUser.getUserId();
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				//cartId = mlfrontCartItem.getCartitemCartId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项,改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象,准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
				mlfrontOrderItemNew.setOrderitemPseo(mlfrontCartItemreturn.getCartitemProductSeoName());
				mlfrontOrderItemNew.setOrderitemProductMainimgurl(mlfrontCartItemreturn.getCartitemProductMainimgurl());
				mlfrontOrderItemNew.setOrderitemProductOriginalprice(mlfrontCartItemreturn.getCartitemProductOriginalprice());
				mlfrontOrderItemNew.setOrderitemProductAccoff(mlfrontCartItemreturn.getCartitemProductActoff());
				mlfrontOrderItemNew.setOrderitemPskuIdstr(mlfrontCartItemreturn.getCartitemProductskuIdstr());
				mlfrontOrderItemNew.setOrderitemPskuIdnamestr(mlfrontCartItemreturn.getCartitemProductskuIdnamestr());
				mlfrontOrderItemNew.setOrderitemPskuId(mlfrontCartItemreturn.getCartitemProductskuId());
				mlfrontOrderItemNew.setOrderitemPskuName(mlfrontCartItemreturn.getCartitemProductskuName());
				mlfrontOrderItemNew.setOrderitemPskuCode(mlfrontCartItemreturn.getCartitemProductskuCode());
				mlfrontOrderItemNew.setOrderitemPskuMoneystr(mlfrontCartItemreturn.getCartitemProductskuMoneystr());
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("这个值是需要计算的");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//获取新生成的id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//存档便于以后用
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			
			//插入订单对象
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			mlfrontOrderNew.setOrderUid(Uid);//这是登录用户,存错登录状态
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//获取新生成的id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//后更新每个orderItem对象
			Integer orderId = mlfrontOrdergetAllOne.getOrderId();
			orderIdFinally = orderId;
			MlfrontOrderItem mlfrontOrderItemOldone = new MlfrontOrderItem();
			for(String orderItemIdOld:orderItemIdList){
				Integer orderItemIdOldInt =  Integer.parseInt(orderItemIdOld);
				mlfrontOrderItemOldone.setOrderitemId(orderItemIdOldInt);
				mlfrontOrderItemOldone.setOrderId(orderId);
				mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemOldone);
			}
		}
		session.setAttribute("orderId", orderIdFinally);
		//返回视图
		return Msg.success().add("resMsg", "cartToOrder订单提交成功").add("orderId", orderIdFinally);
	}
	
	/**7.0	zsh 200617
	 * getCartitemIdDetails
	 */
	@RequestMapping(value="/getCartitemIdDetails",method=RequestMethod.POST)
	@ResponseBody
	public Msg getCartitemIdDetails(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		String sessionId = session.getId();
		String Userip =sessionId;
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		MlfrontCart MlfrontCartReq = new MlfrontCart();
		MlfrontCartReq.setCartStatus(0);
		List<MlfrontCart> mlfrontCartResList = new ArrayList<MlfrontCart>();
		if(loginUser==null){
			MlfrontCartReq.setCartIp(Userip);
			mlfrontCartResList = mlfrontCartService.selectMlfrontCartByIp(MlfrontCartReq);
		}else{
			Integer Uid = loginUser.getUserId();
			MlfrontCartReq.setCartUid(Uid);
			mlfrontCartResList = mlfrontCartService.selectMlfrontCartByUidAndStatus(MlfrontCartReq);
		}
		List<MlfrontCartItem> mlfrontCartItemListRes = new ArrayList<MlfrontCartItem>();
		if(mlfrontCartResList.size()>0){
			String cartitemIdStr = mlfrontCartResList.get(0).getCartitemIdstr();
			String[] aa = cartitemIdStr.split(",");
			for(int i=0;i<aa.length;i++){
				String CartItemIdStr = aa[i];
				//String CartItemId = CartItemIdStr;
				System.out.println("这一行报错For input string: '',此时CartItemIdStr为"+CartItemIdStr+".");
				if(CartItemIdStr != null && CartItemIdStr.length() != 0){
					
					String cartItemIdStrend = CartItemIdStr.trim();
					if(cartItemIdStrend.length()>0){
						Integer CartItemIdInt =Integer.parseInt(cartItemIdStrend);//这一行报错For input string: ""
						MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
						mlfrontCartItemFor.setCartitemId(CartItemIdInt);
						//查看该id+该pid时候已经有了
						List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemFor);
						if(mlfrontCartItemListFor.size()>0){
							mlfrontCartItemListRes.add(mlfrontCartItemListFor.get(0));
						}
					}
				}
			}
		}
		return Msg.success().add("resMsg", "更新成功").add("mlfrontCartItemListRes", mlfrontCartItemListRes);
	}
	
	/**
	 * 8.0	zsh 190615
	 * 删除购物车中的项delCartItem
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/delCartItem",method=RequestMethod.POST)
	@ResponseBody
	public Msg delCartItem(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//接收传递进来的参数
		Integer cartItemIdOriginal = mlfrontCartItem.getCartitemId();
		int isDelSuccess = 0;
		if(cartItemIdOriginal==null){
			return Msg.success().add("resMsg", "删除失败,主建为null,请联系管理员").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败,alert	resMsg/1,删除成功
		}else{
			//通过cartitem的之间查回该条信息,从中取出cartId
			MlfrontCartItem mlfrontCartItemReqq  =new MlfrontCartItem();
			mlfrontCartItemReqq.setCartitemId(cartItemIdOriginal);
			List<MlfrontCartItem> mlfrontCartItemRessList = mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemReqq);
			if(mlfrontCartItemRessList.size()>0){
				Integer cartId = mlfrontCartItemRessList.get(0).getCartitemCartId();
				String cartItemIdOriginalStr = cartItemIdOriginal+"";
				String nowTime = DateUtil.strTime14s();
				MlfrontCart MlfrontCartReq = new MlfrontCart();
				MlfrontCartReq.setCartId(cartId);
				MlfrontCart mlfrontCartRes  = mlfrontCartService.selectMlfrontCartByCartId(cartId);
				//1根据cart该购物车中的本条cartItemStr中的字段
				String cartitemIdstr = mlfrontCartRes.getCartitemIdstr();
				String cartItemArr[] =cartitemIdstr.split(",");
				String cartitemStrEnd = "";
				if(cartItemArr.length>1){
					//1.1如果有多个删除字段中的该项
					for(int i =0;i<cartItemArr.length;i++){
						String tem = cartItemArr[i];
						if(tem.equals(cartItemIdOriginalStr)){
							continue;
						}else{
							cartitemStrEnd=cartitemStrEnd+tem+",";
						}
					}
					int lastIN = cartitemStrEnd.length();
					cartitemStrEnd = cartitemStrEnd.substring(0, (lastIN-1));
					//更新cart中的cartitemStr字段
					MlfrontCart mlfrontCartreqE = new MlfrontCart();
					mlfrontCartreqE.setCartId(cartId);
					mlfrontCartreqE.setCartitemIdstr(cartitemStrEnd);
					mlfrontCartreqE.setCartMotifytime(nowTime);
					mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartreqE);
				}else{
					//1.2如果只有一个,删掉购物车
					mlfrontCartService.deleteByPrimaryKey(cartId);
				}
				//2删除该条CartItem信息
				mlfrontCartItemService.deleteByPrimaryKey(cartItemIdOriginal);
				isDelSuccess = 1;
				return Msg.success().add("resMsg", "删除成功").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败,alert	resMsg/1,删除成功
			}else{
				return Msg.success().add("resMsg", "删除失败,该cartitem项不存在,请联系管理员").add("isDelSuccess", isDelSuccess);//isDelSuccess0删除失败,alert	resMsg/1,删除成功
			}
		}			
	}
	
	/**
	 * 9.0	zsh 190624
	 * 添加产品项进购物车updateCartItemSkuNum
	 * @param
	 * @return Msg
	 * */
	@RequestMapping(value="/updateCartItemSkuNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateCartItemSkuNum(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//接收传递进来的参数
		Integer cartItemIdOriginal = mlfrontCartItem.getCartitemId();
		Integer number = mlfrontCartItem.getCartitemProductNumber();
		
		MlfrontCartItem MlfrontCartItemUpdate = new MlfrontCartItem();
		MlfrontCartItemUpdate.setCartitemId(cartItemIdOriginal);
		MlfrontCartItemUpdate.setCartitemProductNumber(number);
		String nowTime = DateUtil.strTime14s();
		MlfrontCartItemUpdate.setCartitemMotifytime(nowTime);
		//更新本CartItem信息
		mlfrontCartItemService.updateByPrimaryKeySelective(MlfrontCartItemUpdate);
		
		return Msg.success().add("resMsg", "mlfrontCartItemUpdate successful");
	}
	
	/**
	 * 10.0	zsh 200729发现未使用
	 * 查看单条类目的详情细节
	 * @param mlfrontOrderOne
	 * @return 
	 */
//	@RequestMapping(value="/getOneMlfrontCartDetail",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg getOneMlfrontCartDetail(@RequestParam(value = "cartId") Integer cartId){
//		
//		//接受categoryId
//		MlfrontCart mlfrontCartReq = new MlfrontCart();
//		mlfrontCartReq.setCartId(cartId);
//		//查询本条
//		MlfrontCart mlfrontOrderResList =mlfrontCartService.selectMlfrontCartByCartId(cartId);
//		MlfrontCart mlfrontCartOne = new MlfrontCart();
//		if(mlfrontOrderResList!=null){
//			mlfrontCartOne =mlfrontOrderResList;
//		}
//		return Msg.success().add("resMsg", "查看单条mlfrontOrderOne的详情细节完毕").add("mlfrontCartOne", mlfrontCartOne);
//	}
	
	/**
	 * 10.0	useOn	0530
	 * 加购+提交订单,全部一步生成to	BuyNow
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/toBuyNow",method=RequestMethod.POST)
	@ResponseBody
	public Msg toBuyNow(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//记录加购数量
		insertAddCartViewBuyNow(mlfrontCartItem,session);
		
		insertAddCheckOutViewBuyNow(mlfrontCartItem,session);
		
		//放回响应域中
		session.setAttribute("mlfrontCartItem", mlfrontCartItem);
		String sessionId = session.getId();
		//获取ip地址
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<MlfrontCartItem> mlfrontCartItemListBuyNow = new ArrayList<MlfrontCartItem>();
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		//判断session中是否有登录信息 ,if 有,新增进入cart中,没有新增进入
		if(loginUser==null){
			//获取ip信息,去cart中用ip查询,是否有该ip的购物车
			MlfrontCart MlfrontCartReq = new MlfrontCart();
			MlfrontCartReq.setCartIp(Userip);
			MlfrontCartReq.setCartStatus(0);
			List<MlfrontCart> MlfrontCartResList = mlfrontCartService.selectMlfrontCartByIp(MlfrontCartReq);
			Integer ifHave = 1;
			if(MlfrontCartResList.size()>0){
				String cartitemIdstrUser = MlfrontCartResList.get(0).getCartitemIdstr();
				if(cartitemIdstrUser.length()>0){
					ifHave = 1;
				}else{
					//名下有购物车,但是为空
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//此时要删掉这个为空的购物车
				}
			}else{
				//如果名下没购物车
				ifHave = 0;
			}
			if(ifHave==1){
				//如果有,获取该购物车的itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//判断是否有本次添加的产品,如果没有itemStr中加入本次的productId,
				Integer pid = mlfrontCartItem.getCartitemProductId();
				String skuIdstr = mlfrontCartItem.getCartitemProductskuIdstr();
				String skuIdnamestr = mlfrontCartItem.getCartitemProductskuIdnamestr();
				Integer pskuId = mlfrontCartItem.getCartitemProductskuId();
				String pskuName =mlfrontCartItem.getCartitemProductskuName();
				String pskuCode = mlfrontCartItem.getCartitemProductskuCode();
				int proNumberNew =mlfrontCartItem.getCartitemProductNumber();
				String[] aa = cartitemIdstrUser.split(",");
				int number = 0;
				for(int i=0;i<aa.length;i++){
					number++;
					String CartItemId = aa[i];
					Integer CartItemIdInt =Integer.parseInt(CartItemId);
					MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
					mlfrontCartItemFor.setCartitemId(CartItemIdInt);
					mlfrontCartItemFor.setCartitemProductId(pid);
					mlfrontCartItemFor.setCartitemProductskuIdstr(skuIdstr);
					mlfrontCartItemFor.setCartitemProductskuIdnamestr(skuIdnamestr);
					mlfrontCartItemFor.setCartitemProductskuId(pskuId);
					mlfrontCartItemFor.setCartitemProductskuName(pskuName);
					mlfrontCartItemFor.setCartitemProductskuCode(pskuCode);
					//查看该id+该pid+skuIdstr+skuIdnamestr+skuNamestr时候已经有了
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//如果存在
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//该sku的Cartitem的number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//更新
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						mlfrontCartItem.setCartitemId(CartItemIdInt);
						//System.out.println(mlfrontCartItem);
						mlfrontCartItemListBuyNow.add(mlfrontCartItem);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//没有该sku的Cartitem	新增该条Cartitem sku
							//将该id写入cart对象的cartItemStr中
							System.out.println("操作说明：当前客户添加购物车,此前购物车中不包含本次添加的产品");  
							//将本id加入原来的idstr中
							//更新一下该user的购物车中的cartitemIdstr字段
							mlfrontCartItem.setCartitemStatus(0);
							mlfrontCartItem.setCartitemCreatetime(nowTime);
							mlfrontCartItem.setCartitemMotifytime(nowTime);
							mlfrontCartItemService.insertSelective(mlfrontCartItem);
							List<MlfrontCartItem> mlfrontCartItemList = mlfrontCartItemService.selectMlfrontCartItemGetAllHundred();
							Integer cartItemId = mlfrontCartItemList.get(0).getCartitemId();
							String cartItemIdStrnew = cartItemId+"";
							cartitemIdstrUser=cartitemIdstrUser+","+cartItemIdStrnew;
							MlfrontCart mlfrontCartAfter = new MlfrontCart();
							mlfrontCartAfter.setCartId(cartId);
							mlfrontCartAfter.setCartUid(cartUid);
							mlfrontCartAfter.setCartitemIdstr(cartitemIdstrUser);
							mlfrontCartAfter.setCartMotifytime(nowTime);
							mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartAfter);
							//将本mlfrontCartItem插入mlfrontCartItem表中
							//更新本条cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							//System.out.println(mlfrontCartItem);
							mlfrontCartItemListBuyNow.add(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//这个ip没来过,直接新增一条即可mlfrontCartItem,将主键放入cart对象中,之后在新增该对象cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				List<MlfrontCartItem> mlfrontCartItemList = mlfrontCartItemService.selectMlfrontCartItemGetAllHundred();
				Integer cartItemId = mlfrontCartItemList.get(0).getCartitemId();
				//拿回来主键ID		mlfrontCartItem
				MlfrontCart MlfrontCartItemReturn = new MlfrontCart();
				String cartItemIdStr = cartItemId+"";
				MlfrontCartItemReturn.setCartitemIdstr(cartItemIdStr);
				MlfrontCartItemReturn.setCartIp(Userip);
				MlfrontCartItemReturn.setCartStatus(0);
				MlfrontCartItemReturn.setCartCreatetime(nowTime);
				MlfrontCartItemReturn.setCartMotifytime(nowTime);
				mlfrontCartService.insertSelective(MlfrontCartItemReturn);
				List<MlfrontCart> mlfrontCartInsertAfterList= mlfrontCartService.selectMlfrontCartGetAllHundred();
				Integer cartAfterId = mlfrontCartInsertAfterList.get(0).getCartId();
				mlfrontCartItem.setCartitemId(cartItemId);
				mlfrontCartItem.setCartitemCartId(cartAfterId);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
				//System.out.println(mlfrontCartItem);
				mlfrontCartItemListBuyNow.add(mlfrontCartItem);
			}
			//游客加购已完毕,准备生成订单
			BuyNowAndcartToOrder(rep,res,session, mlfrontCartItemListBuyNow);
		}else{
			//获取uid信息,去cart中用uid查询,是否有该uid的购物车
			MlfrontCart MlfrontCartReq = new MlfrontCart();
			Integer uid =loginUser.getUserId();
			MlfrontCartReq.setCartUid(uid);
			MlfrontCartReq.setCartStatus(0);
			List<MlfrontCart> MlfrontCartResList = mlfrontCartService.selectMlfrontCartByUidAndStatus(MlfrontCartReq);
			Integer ifHave = 1;
			if(MlfrontCartResList.size()>0){
				String cartitemIdstrUser = MlfrontCartResList.get(0).getCartitemIdstr();
				if(cartitemIdstrUser.length()>0){
					ifHave = 1;
				}else{
					//名下有购物车,但是为空
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//此时要删掉这个为空的购物车
				}
			}else{
				//如果名下没购物车
				ifHave = 0;
			}
			if(ifHave==1){
				//如果有,获取该购物车的itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//判断是否有本次添加的产品,如果没有itemStr中加入本次的productId,
				Integer pid = mlfrontCartItem.getCartitemProductId();
				String skuIdstr = mlfrontCartItem.getCartitemProductskuIdstr();
				String skuIdnamestr = mlfrontCartItem.getCartitemProductskuIdnamestr();
				Integer pskuId = mlfrontCartItem.getCartitemProductskuId();
				String pskuName =mlfrontCartItem.getCartitemProductskuName();
				String pskuCode = mlfrontCartItem.getCartitemProductskuCode();
				int proNumberNew =mlfrontCartItem.getCartitemProductNumber();
				String[] aa = cartitemIdstrUser.split(",");
				int number = 0;
				for(int i=0;i<aa.length;i++){
					number++;
					String CartItemId = aa[i];
					Integer CartItemIdInt =Integer.parseInt(CartItemId);
					MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
					mlfrontCartItemFor.setCartitemId(CartItemIdInt);
					mlfrontCartItemFor.setCartitemProductId(pid);
					mlfrontCartItemFor.setCartitemProductskuIdstr(skuIdstr);
					mlfrontCartItemFor.setCartitemProductskuIdnamestr(skuIdnamestr);
					mlfrontCartItemFor.setCartitemProductskuId(pskuId);
					mlfrontCartItemFor.setCartitemProductskuName(pskuName);
					mlfrontCartItemFor.setCartitemProductskuCode(pskuCode);
					//查看该id+该pid+skuIdstr+skuIdnamestr+skuNamestr时候已经有了
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//如果存在
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//该sku的Cartitem的number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//更新
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						mlfrontCartItem.setCartitemId(CartItemIdInt);
						mlfrontCartItemListBuyNow.add(mlfrontCartItem);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//没有该sku的Cartitem
							//新增该条Cartitem sku
							//将该id写入cart对象的cartItemStr中
							System.out.println("操作说明：当前客户添加购物车,此前购物车中不包含本次添加的产品"); 
							//将本id加入原来的idstr中
							//更新一下该user的购物车中的cartitemIdstr字段
							mlfrontCartItem.setCartitemStatus(0);
							mlfrontCartItem.setCartitemCreatetime(nowTime);
							mlfrontCartItem.setCartitemMotifytime(nowTime);
							mlfrontCartItemService.insertSelective(mlfrontCartItem);
							List<MlfrontCartItem> mlfrontCartItemList = mlfrontCartItemService.selectMlfrontCartItemGetAllHundred();
							Integer cartItemId = mlfrontCartItemList.get(0).getCartitemId();
							String cartItemIdStrnew = cartItemId+"";
							cartitemIdstrUser=cartitemIdstrUser+","+cartItemIdStrnew;
							MlfrontCart mlfrontCartAfter = new MlfrontCart();
							mlfrontCartAfter.setCartId(cartId);
							mlfrontCartAfter.setCartUid(cartUid);
							mlfrontCartAfter.setCartitemIdstr(cartitemIdstrUser);
							mlfrontCartAfter.setCartMotifytime(nowTime);
							mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartAfter);
							//将本mlfrontCartItem插入mlfrontCartItem表中
							//更新本条cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							mlfrontCartItemListBuyNow.add(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//这个ip没来过,直接新增一条即可mlfrontCartItem,将主键放入cart对象中,之后在新增该对象cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				List<MlfrontCartItem> mlfrontCartItemList = mlfrontCartItemService.selectMlfrontCartItemGetAllHundred();
				Integer cartItemId = mlfrontCartItemList.get(0).getCartitemId();
				//拿回来主键ID		mlfrontCartItem
				MlfrontCart MlfrontCartItemReturn = new MlfrontCart();
				String cartItemIdStr = cartItemId+"";
				MlfrontCartItemReturn.setCartitemIdstr(cartItemIdStr);
				MlfrontCartItemReturn.setCartUid(uid);
				MlfrontCartItemReturn.setCartStatus(0);
				MlfrontCartItemReturn.setCartCreatetime(nowTime);
				MlfrontCartItemReturn.setCartMotifytime(nowTime);
				mlfrontCartService.insertSelective(MlfrontCartItemReturn);
				List<MlfrontCart> mlfrontCartInsertAfterList= mlfrontCartService.selectMlfrontCartGetAllHundred();
				Integer cartAfterId = mlfrontCartInsertAfterList.get(0).getCartId();
				mlfrontCartItem.setCartitemId(cartItemId);
				mlfrontCartItem.setCartitemCartId(cartAfterId);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
				//System.out.println(mlfrontCartItem);
				mlfrontCartItemListBuyNow.add(mlfrontCartItem);
			}
			//loginUser加购已完毕,准备生成订单	
			BuyNowAndcartToOrder(rep,res,session, mlfrontCartItemListBuyNow);
		}
		return Msg.success().add("resMsg", "添加成功");
	}
	
	/**
	 * 方法
	 * 勿碰
	 * 购买中的任务
	 * */
	public void BuyNowAndcartToOrder(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody List<MlfrontCartItem> mlfrontCartItemList) throws Exception{
		//接收传递进来的参数
		System.out.println(mlfrontCartItemList);
		//从其中一个中获取cartId
		Integer cartId = 0;
		String sessionId = session.getId();
		Integer orderIdFinally = 0;
		//获取ip地址
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<String> cartItemIdStr = new ArrayList<String>();
		List<String> orderItemIdList = new ArrayList<String>();
		String orderItemIdStr = "";
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		MlfrontCartItem mlfrontCartItemGet = new MlfrontCartItem();
		if(loginUser==null){
			//loginUser为null
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项,改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象,准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
				mlfrontOrderItemNew.setOrderitemPseo(mlfrontCartItemreturn.getCartitemProductSeoName());
				mlfrontOrderItemNew.setOrderitemProductMainimgurl(mlfrontCartItemreturn.getCartitemProductMainimgurl());
				mlfrontOrderItemNew.setOrderitemProductOriginalprice(mlfrontCartItemreturn.getCartitemProductOriginalprice());
				mlfrontOrderItemNew.setOrderitemProductAccoff(mlfrontCartItemreturn.getCartitemProductActoff());
				mlfrontOrderItemNew.setOrderitemPskuIdstr(mlfrontCartItemreturn.getCartitemProductskuIdstr());
				mlfrontOrderItemNew.setOrderitemPskuIdnamestr(mlfrontCartItemreturn.getCartitemProductskuIdnamestr());
				mlfrontOrderItemNew.setOrderitemPskuId(mlfrontCartItemreturn.getCartitemProductskuId());
				mlfrontOrderItemNew.setOrderitemPskuName(mlfrontCartItemreturn.getCartitemProductskuName());
				mlfrontOrderItemNew.setOrderitemPskuCode(mlfrontCartItemreturn.getCartitemProductskuCode());
				mlfrontOrderItemNew.setOrderitemPskuMoneystr(mlfrontCartItemreturn.getCartitemProductskuMoneystr());
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("这个值是需要计算的");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//获取新生成的id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//存档便于以后用
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			//插入订单对象
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			mlfrontOrderNew.setOrderIp(Userip);
			
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			
			//直接进入的时候,没有order表中没有数量字段
			MlfrontCartItem mlfrontCartItemBuyNow = mlfrontCartItemList.get(0);
			Integer ProductNumber = mlfrontCartItemBuyNow.getCartitemProductNumber();
			String ProductNumberStr = ProductNumber+"";
			mlfrontOrderNew.setOrderPronumStr(ProductNumberStr);
			
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//获取新生成的id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//后更新每个orderItem对象
			Integer orderId = mlfrontOrdergetAllOne.getOrderId();
			orderIdFinally = orderId;
			MlfrontOrderItem mlfrontOrderItemOldone = new MlfrontOrderItem();
			for(String orderItemIdOld:orderItemIdList){
				Integer orderItemIdOldInt =  Integer.parseInt(orderItemIdOld);
				mlfrontOrderItemOldone.setOrderitemId(orderItemIdOldInt);
				mlfrontOrderItemOldone.setOrderId(orderId);
				mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemOldone);
			}
		}else{
			//loginUser不为null
			Integer Uid = loginUser.getUserId();
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项,改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象,准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
				mlfrontOrderItemNew.setOrderitemPseo(mlfrontCartItemreturn.getCartitemProductSeoName());
				mlfrontOrderItemNew.setOrderitemProductMainimgurl(mlfrontCartItemreturn.getCartitemProductMainimgurl());
				mlfrontOrderItemNew.setOrderitemProductOriginalprice(mlfrontCartItemreturn.getCartitemProductOriginalprice());
				mlfrontOrderItemNew.setOrderitemProductAccoff(mlfrontCartItemreturn.getCartitemProductActoff());
				mlfrontOrderItemNew.setOrderitemPskuIdstr(mlfrontCartItemreturn.getCartitemProductskuIdstr());
				mlfrontOrderItemNew.setOrderitemPskuIdnamestr(mlfrontCartItemreturn.getCartitemProductskuIdnamestr());
				mlfrontOrderItemNew.setOrderitemPskuId(mlfrontCartItemreturn.getCartitemProductskuId());
				mlfrontOrderItemNew.setOrderitemPskuName(mlfrontCartItemreturn.getCartitemProductskuName());
				mlfrontOrderItemNew.setOrderitemPskuCode(mlfrontCartItemreturn.getCartitemProductskuCode());
				mlfrontOrderItemNew.setOrderitemPskuMoneystr(mlfrontCartItemreturn.getCartitemProductskuMoneystr());
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("这个值是需要计算的");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//获取新生成的id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//存档便于以后用
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			//插入订单对象
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			//mlfrontOrderNew.setOrderIp(Userip);
			mlfrontOrderNew.setOrderUid(Uid);//这是登录用户,存错登录状态
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			
			//直接进入的时候,没有order表中没有数量字段
			MlfrontCartItem mlfrontCartItemBuyNow = mlfrontCartItemList.get(0);
			Integer ProductNumber = mlfrontCartItemBuyNow.getCartitemProductNumber();
			String ProductNumberStr = ProductNumber+"";
			mlfrontOrderNew.setOrderPronumStr(ProductNumberStr);
			
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//获取新生成的id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//后更新每个orderItem对象
			Integer orderId = mlfrontOrdergetAllOne.getOrderId();
			orderIdFinally = orderId;
			MlfrontOrderItem mlfrontOrderItemOldone = new MlfrontOrderItem();
			for(String orderItemIdOld:orderItemIdList){
				Integer orderItemIdOldInt =  Integer.parseInt(orderItemIdOld);
				mlfrontOrderItemOldone.setOrderitemId(orderItemIdOldInt);
				mlfrontOrderItemOldone.setOrderId(orderId);
				mlfrontOrderItemService.updateByPrimaryKeySelective(mlfrontOrderItemOldone);
			}
		}
		session.setAttribute("orderId", orderIdFinally);
		//返回视图
	}
	
	/**
	 * 11.0	zsh 190624
	 * cart位置更新sku的细节的接口
	 * @param updateCartitemPku
	 * @return 
	 */
	@RequestMapping(value="/updateCartitemPku",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateCartitemPku(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem){
		
		System.out.println("更新购物车中的mlfrontCartItem:"+mlfrontCartItem.toString());
		mlfrontCartItem.getCartitemId();
		mlfrontCartItem.getCartitemProductskuId();
		mlfrontCartItem.getCartitemProductskuName();
		mlfrontCartItem.getCartitemProductskuCode();
		mlfrontCartItem.getCartitemProductskuMoneystr();
		mlfrontCartItem.getCartitemProductNumber();
		mlfrontCartItem.getCartitemProductskuIdstr();
		mlfrontCartItem.getCartitemProductskuIdnamestr();
		mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
		return Msg.success().add("resMsg", "更新成功").add("mlfrontCartItem", mlfrontCartItem);
	}
	
	//4.1记录加购数量
	private void insertAddCartView(MlfrontCartItem mlfrontCartItem, HttpSession session) {
		String nowViewTime = DateUtil.strTime14s();
		//获取该产品id
		Integer productId = mlfrontCartItem.getCartitemProductId();
		//通过pid查回该产品
		MlbackProduct mlbackProductrep = new MlbackProduct();
		mlbackProductrep.setProductId(productId);
		List<MlbackProduct> mlbackProductresList = mlbackProductService.selectMlbackProductByParam(mlbackProductrep);
		MlbackProduct mlbackProductres = mlbackProductresList.get(0);
		//拿到seo名字+产品名
		String addcartviewdetailSeoname = mlbackProductres.getProductSeo();
		String addcartviewdetailProname = mlbackProductres.getProductName();
		System.out.println("操作说明:nowViewTime,"+nowViewTime+",toAddToCart	:"+addcartviewdetailSeoname);
		//准备参数信息
		MlbackAddCartViewDetail mlbackAddCartViewDetailreq = new MlbackAddCartViewDetail();
		//浏览对象
		mlbackAddCartViewDetailreq.setAddcartviewdetailProseo(addcartviewdetailSeoname);
		mlbackAddCartViewDetailreq.setAddcartviewdetailProname(addcartviewdetailProname);
		//sessionID
		String sessionId =  session.getId();
		mlbackAddCartViewDetailreq.setAddcartviewdetailSessionid(sessionId);
		//时间信息
		String nowTime = DateUtil.strTime14s();
		mlbackAddCartViewDetailreq.setAddcartviewdetailCreatetime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailMotifytime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailActnum(0); //计数用户行为,0纯加购	,1点buyNow附带的加购
		mlbackAddCartViewDetailService.insertSelective(mlbackAddCartViewDetailreq);
	}
	
	/**
	 * 计算从detail--buyNow的加购数
	 * */
	private void insertAddCartViewBuyNow(MlfrontCartItem mlfrontCartItem, HttpSession session) {
		Integer productId = mlfrontCartItem.getCartitemProductId();
		
		MlbackProduct mlbackProductrep = new MlbackProduct();
		mlbackProductrep.setProductId(productId);
		
		List<MlbackProduct> mlbackProductresList = mlbackProductService.selectMlbackProductByParam(mlbackProductrep);
		MlbackProduct mlbackProductres = mlbackProductresList.get(0);
		
		String addcartviewdetailSeoname = mlbackProductres.getProductSeo();
		String addcartviewdetailProname = mlbackProductres.getProductName();
		
		//准备参数信息
		MlbackAddCartViewDetail mlbackAddCartViewDetailreq = new MlbackAddCartViewDetail();
		//浏览对象
		mlbackAddCartViewDetailreq.setAddcartviewdetailProseo(addcartviewdetailSeoname);
		mlbackAddCartViewDetailreq.setAddcartviewdetailProname(addcartviewdetailProname);
		//sessionID
		String sessionId =  session.getId();
		mlbackAddCartViewDetailreq.setAddcartviewdetailSessionid(sessionId);
		//时间信息
		String nowTime = DateUtil.strTime14s();
		mlbackAddCartViewDetailreq.setAddcartviewdetailCreatetime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailMotifytime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailActnum(1); //计数用户行为,0纯加购	,1点buyNow附带的加购
		mlbackAddCartViewDetailService.insertSelective(mlbackAddCartViewDetailreq);
		
	}
	
	
	/**
	 * 计算从detail--buyNow--Cheakout的结算数
	 * 
	 * */
	private void insertAddCheckOutViewBuyNow(MlfrontCartItem mlfrontCartItem, HttpSession session) {

		Integer productId = mlfrontCartItem.getCartitemProductId();
		
		MlbackProduct mlbackProductrep = new MlbackProduct();
		mlbackProductrep.setProductId(productId);
		
		List<MlbackProduct> mlbackProductresList = mlbackProductService.selectMlbackProductByParam(mlbackProductrep);
		MlbackProduct mlbackProductres = mlbackProductresList.get(0);
		
		String addcartviewdetailSeoname = mlbackProductres.getProductSeo();
		String addcartviewdetailProname = mlbackProductres.getProductName();
		
		//准备参数信息
		MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetailreq = new MlbackAddCheakoutViewDetail();
		//浏览对象
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProseo(addcartviewdetailSeoname);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProname(addcartviewdetailProname);
		//sessionID
		String sessionId =  session.getId();
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailSessionid(sessionId);
		//时间信息
		String nowTime = DateUtil.strTime14s();
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailCreatetime(nowTime);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailMotifytime(nowTime);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailActnum(1); //计数用户行为,0纯加购	,1点buyNow附带的加购
		mlbackAddCheakoutViewDetailService.insertSelective(mlbackAddCheakoutViewDetailreq);
	}
	
	/**
	 * 计算从CartList--Cheakout的结算数
	 * 
	 * */
	private void calcFormCartListToCheakoutPage(List<MlfrontCartItem> mlfrontCartItemList, HttpSession session) {
		
		for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
			
			Integer productId = mlfrontCartItem.getCartitemProductId();
			
			MlbackProduct mlbackProductrep = new MlbackProduct();
			mlbackProductrep.setProductId(productId);
			List<MlbackProduct> mlbackProductresList = mlbackProductService.selectMlbackProductByParam(mlbackProductrep);
			MlbackProduct mlbackProductres = mlbackProductresList.get(0);
			
			String addcartviewdetailSeoname = mlbackProductres.getProductSeo();
			String addcartviewdetailProname = mlbackProductres.getProductName();
			
			//准备参数信息
			MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetailreq = new MlbackAddCheakoutViewDetail();
			//浏览对象
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProseo(addcartviewdetailSeoname);
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProname(addcartviewdetailProname);
			//sessionID
			String sessionId =  session.getId();
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailSessionid(sessionId);
			//时间信息
			String nowTime = DateUtil.strTime14s();
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailCreatetime(nowTime);
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailMotifytime(nowTime);
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailActnum(0); //计数用户行为,0(从CartList--Cheakout)的结算数	,1(从buyNow--Cheakout)的结算数
			mlbackAddCheakoutViewDetailService.insertSelective(mlbackAddCheakoutViewDetailreq);
		}
	}
	
}
