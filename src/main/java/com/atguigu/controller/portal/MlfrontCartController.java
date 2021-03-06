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
	 * ???????????????????????????toCartList
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toCartList")
	public String toCartList(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "portal/CartOrderPay/cartList";
	}
	
	/**
	 * 2.0	zsh200729
	 * ???????????????????????????front/cheakOut
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toCheakOut")
	public String toCheakOut(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "portal/CartOrderPay/checkOut";
	}
	
	//???????????????
	@RequestMapping(value="/toCheakOutMoreBuyByhtml",method=RequestMethod.GET)
	public ModelAndView toCheakOutMoreBuyByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "orderIdIntoStr") String orderIdIntoStr) throws Exception{

		//?????????
		String orginalOrderIdStr = EncryptUtil.XORdecode(orderIdIntoStr,"megalook");
		System.out.println("?????????????????????orderid???orderIdStr:"+orginalOrderIdStr);
		Integer orderId = Integer.parseInt(orginalOrderIdStr);
	
		ModelAndView modelAndView = new ModelAndView();
		//??????id?????????
		MlfrontOrder mlfrontOrderInto = new MlfrontOrder();
		mlfrontOrderInto.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderInto);
		if(mlfrontOrderList.size()>0){
			MlfrontOrder mlfrontOrderOne = mlfrontOrderList.get(0);
			Integer orderStatus = mlfrontOrderOne.getOrderStatus();
			Integer addressId = mlfrontOrderOne.getOrderAddressinfoId();
			if(orderStatus>0){
				//???????????????????????????
				//0?????????//1????????????//2????????????//3???????????? //4????????????//5?????????//6????????????//7???????????????
				modelAndView.setViewName("redirect:/");
				return modelAndView;
			}else{
				
				//???????????????,???????????????payinfo????????????????????????
				updatePayInfo(orderId);
				
				//??????????????????,???????????????
				//???????????????????????????????????????
				if(mlfrontOrderOne.getOrderUid()==null){
					//?????????
					session.setAttribute("orderId", orderId);
					session.setAttribute("addressId", addressId);
				}else{
					//????????????id,??????????????????
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
						//???????????????,????????????,??????????????????
						modelAndView.setViewName("redirect:/");
						return modelAndView;
					}
				}
				modelAndView.setViewName("portal/CartOrderPay/checkOut");
				return modelAndView;
			}
		}else{
			//???????????????orderid?????????,???????????????????????????????????????;
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
			mlfrontPayInfoUpdate.setPayinfoIfEmail(9);//????????????????????????
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
		}
	}
	
	//???????????????
	@RequestMapping(value="/toCheakOutSMSMoreBuyByhtml",method=RequestMethod.GET)
	public ModelAndView toCheakOutSMSMoreBuyByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "orderIdIntoStr") String orderIdIntoStr) throws Exception{

		//?????????
		String orginalOrderIdStr = EncryptUtil.XORdecode(orderIdIntoStr,"megalook");
		System.out.println("?????????????????????orderid???orderIdStr:"+orginalOrderIdStr);
		Integer orderId = Integer.parseInt(orginalOrderIdStr);
	
		ModelAndView modelAndView = new ModelAndView();
		//??????id?????????
		MlfrontOrder mlfrontOrderInto = new MlfrontOrder();
		mlfrontOrderInto.setOrderId(orderId);
		List<MlfrontOrder> mlfrontOrderList = mlfrontOrderService.selectMlfrontOrderById(mlfrontOrderInto);
		if(mlfrontOrderList.size()>0){
			MlfrontOrder mlfrontOrderOne = mlfrontOrderList.get(0);
			Integer orderStatus = mlfrontOrderOne.getOrderStatus();
			Integer addressId = mlfrontOrderOne.getOrderAddressinfoId();
			if(orderStatus>0){
				//???????????????????????????
				//0?????????//1????????????//2????????????//3???????????? //4????????????//5?????????//6????????????//7???????????????
				modelAndView.setViewName("redirect:/");
				return modelAndView;
			}else{
				
				//???????????????,???????????????payinfo????????????????????????
				updatePayInfoStatusBySMS(orderId);
				
				//??????????????????,???????????????
				//???????????????????????????????????????
				if(mlfrontOrderOne.getOrderUid()==null){
					//?????????
					session.setAttribute("orderId", orderId);
					session.setAttribute("addressId", addressId);
				}else{
					//????????????id,??????????????????
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
						//???????????????,????????????,??????????????????
						modelAndView.setViewName("redirect:/");
						return modelAndView;
					}
				}
				modelAndView.setViewName("portal/CartOrderPay/checkOut");
				return modelAndView;
			}
		}else{
			//???????????????orderid?????????,???????????????????????????????????????;
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
			mlfrontPayInfoUpdate.setPayinfoIfSMS(9);//????????????????????????
			mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoUpdate);
		}
	}

	/**
	 * 3.0	200612
	 * getMlfrontCartByDate??????????????????????????????????????????
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
		return Msg.success().add("countNumber", countNumber).add("resMsg", "?????????????????????");
	}
	
	/**
	 * 4.0	zsh 200617
	 * ???????????????????????????toAddToCart
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
		//??????session???????????????????????? ,if ???,????????????cart???,??????????????????
		if(loginUser==null){
			//??????ip??????,???cart??????ip??????,????????????ip????????????
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
					//??????????????????,????????????
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//???????????????????????????????????????
				}
			}else{
				//????????????????????????
				ifHave = 0;
			}
			if(ifHave==1){
				//?????????,?????????????????????itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//????????????????????????????????????,????????????itemStr??????????????????productId,
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
					//?????????id+???pid+skuIdstr+skuIdnamestr+skuId+skuName+skuCode??????????????????
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//????????????
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//???sku???Cartitem???number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//??????
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//?????????sku???Cartitem	????????????Cartitem sku
							//??????id??????cart?????????cartItemStr???
							System.out.println("??????????????????????????????????????????,????????????????????????????????????????????????"); 
							//??????id???????????????idstr???
							//???????????????user??????????????????cartitemIdstr??????
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
							//??????mlfrontCartItem??????mlfrontCartItem??????
							//????????????cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//??????ip?????????,????????????????????????mlfrontCartItem,???????????????cart?????????,????????????????????????cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				Integer cartItemId = mlfrontCartItem.getCartitemId();
				//???????????????ID		mlfrontCartItem
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
			//??????uid??????,???cart??????uid??????,????????????uid????????????
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
					//??????????????????,????????????
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//???????????????????????????????????????
				}
			}else{
				//????????????????????????
				ifHave = 0;
			}
			if(ifHave==1){
				//?????????,?????????????????????itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//????????????????????????????????????,????????????itemStr??????????????????productId,
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
					//?????????id+???pid+skuIdstr+skuIdnamestr+skuNamestr??????????????????
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//????????????
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//???sku???Cartitem???number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//??????
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//?????????sku???Cartitem
							//????????????Cartitem sku
							//??????id??????cart?????????cartItemStr???
							System.out.println("??????????????????????????????????????????,????????????????????????????????????????????????"); 
							//??????id???????????????idstr???
							//???????????????user??????????????????cartitemIdstr??????
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
							//??????mlfrontCartItem??????mlfrontCartItem??????
							//????????????cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//??????ip?????????,????????????????????????mlfrontCartItem,???????????????cart?????????,????????????????????????cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				
				Integer cartItemId = mlfrontCartItem.getCartitemId();

				//???????????????ID		mlfrontCartItem
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
		return Msg.success().add("resMsg", "????????????");
	}
	
	/**
	 * 4.1	zsh 201221
	 * ???????????????????????????toAddMoreProToCart
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/toAddMoreProToCart",method=RequestMethod.POST)
	@ResponseBody
	public Msg toAddMoreProToCart(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam("addMoreProToCartTeams") String addMoreProToCartTeams) throws Exception{
			
		//1.??????productskuPid,?????????id???,???????????????sku
		//??????list??????,??????????????????,????????????????????????????????????0,
		//????????????
		//????????????,???????????????,????????????,???????????????1,?????????????????????,?????????????????????,?????????,?????????????????????
		
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
			//??????session???????????????????????? ,if ???,????????????cart???,??????????????????
			if(loginUser==null){
				//??????ip??????,???cart??????ip??????,????????????ip????????????
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
						//??????????????????,????????????
						Integer cartId = MlfrontCartResList.get(0).getCartId();
						MlfrontCart MlfrontCartdelRep = new MlfrontCart();
						MlfrontCartdelRep.setCartId(cartId);
						mlfrontCartService.deleteByPrimaryKey(cartId);
						ifHave = 0;
						//???????????????????????????????????????
					}
				}else{
					//????????????????????????
					ifHave = 0;
				}
				if(ifHave==1){
					//?????????,?????????????????????itemStr,
					MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
					String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
					Integer cartId = mlfrontCartUser.getCartId();
					Integer cartUid = mlfrontCartUser.getCartUid();
					//????????????????????????????????????,????????????itemStr??????????????????productId,
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
						//?????????id+???pid+skuIdstr+skuIdnamestr+skuId+skuName+skuCode??????????????????
						List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
						if(mlfrontCartItemListFor.size()>0){
							//????????????
							MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
							int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
							//???sku???Cartitem???number+1,
							ProductNumber= ProductNumber+proNumberNew;
							mlfrontCartItemOne.setCartitemId(CartItemIdInt);
							mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
							mlfrontCartItemOne.setCartitemCreatetime(nowTime);
							mlfrontCartItemOne.setCartitemMotifytime(nowTime);
							//??????
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
							break;
						}else{
							if(number <aa.length){
								continue;
							}else{
								//?????????sku???Cartitem	????????????Cartitem sku
								//??????id??????cart?????????cartItemStr???
								System.out.println("??????????????????????????????????????????,????????????????????????????????????????????????"); 
								//??????id???????????????idstr???
								//???????????????user??????????????????cartitemIdstr??????
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
								//??????mlfrontCartItem??????mlfrontCartItem??????
								//????????????cartitem,
								mlfrontCartItem.setCartitemId(cartItemId);
								mlfrontCartItem.setCartitemCartId(cartId);
								mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
								break;
							}
						}
					}
				}else{
					//??????ip?????????,????????????????????????mlfrontCartItem,???????????????cart?????????,????????????????????????cart
					mlfrontCartItem.setCartitemCreatetime(nowTime);
					mlfrontCartItem.setCartitemMotifytime(nowTime);
					mlfrontCartItem.setCartitemStatus(0);
					mlfrontCartItemService.insertSelective(mlfrontCartItem);
					Integer cartItemId = mlfrontCartItem.getCartitemId();
					//???????????????ID		mlfrontCartItem
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
				//??????uid??????,???cart??????uid??????,????????????uid????????????
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
						//??????????????????,????????????
						Integer cartId = MlfrontCartResList.get(0).getCartId();
						MlfrontCart MlfrontCartdelRep = new MlfrontCart();
						MlfrontCartdelRep.setCartId(cartId);
						mlfrontCartService.deleteByPrimaryKey(cartId);
						ifHave = 0;
						//???????????????????????????????????????
					}
				}else{
					//????????????????????????
					ifHave = 0;
				}
				if(ifHave==1){
					//?????????,?????????????????????itemStr,
					MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
					String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
					Integer cartId = mlfrontCartUser.getCartId();
					Integer cartUid = mlfrontCartUser.getCartUid();
					//????????????????????????????????????,????????????itemStr??????????????????productId,
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
						//?????????id+???pid+skuIdstr+skuIdnamestr+skuNamestr??????????????????
						List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
						if(mlfrontCartItemListFor.size()>0){
							//????????????
							MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
							int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
							//???sku???Cartitem???number+1,
							ProductNumber= ProductNumber+proNumberNew;
							mlfrontCartItemOne.setCartitemId(CartItemIdInt);
							mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
							mlfrontCartItemOne.setCartitemCreatetime(nowTime);
							mlfrontCartItemOne.setCartitemMotifytime(nowTime);
							//??????
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
							break;
						}else{
							if(number <aa.length){
								continue;
							}else{
								//?????????sku???Cartitem
								//????????????Cartitem sku
								//??????id??????cart?????????cartItemStr???
								System.out.println("??????????????????????????????????????????,????????????????????????????????????????????????"); 
								//??????id???????????????idstr???
								//???????????????user??????????????????cartitemIdstr??????
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
								//??????mlfrontCartItem??????mlfrontCartItem??????
								//????????????cartitem,
								mlfrontCartItem.setCartitemId(cartItemId);
								mlfrontCartItem.setCartitemCartId(cartId);
								mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
								break;
							}
						}
					}
				}else{
					//??????ip?????????,????????????????????????mlfrontCartItem,???????????????cart?????????,????????????????????????cart
					mlfrontCartItem.setCartitemCreatetime(nowTime);
					mlfrontCartItem.setCartitemMotifytime(nowTime);
					mlfrontCartItem.setCartitemStatus(0);
					mlfrontCartItemService.insertSelective(mlfrontCartItem);
					
					Integer cartItemId = mlfrontCartItem.getCartitemId();
	
					//???????????????ID		mlfrontCartItem
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
		return Msg.success().add("resMsg", "????????????");
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
		//??????session???????????????????????? ,if ???,????????????cart???,??????????????????
		int number = 0;
		String Userip =sessionId;
		MlfrontCart MlfrontCartReq = new MlfrontCart();
		MlfrontCartReq.setCartStatus(0);
		if(loginUser==null){
			MlfrontCartReq.setCartIp(Userip);
			//????????????????????????????????????
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
			//????????????????????????????????????
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
		return Msg.success().add("resMsg", "????????????").add("number", number);
	}

	/**
	 * 6.0	0530
	 * ??????????????????????????????,??????????????????cartToOrder
	 * 1???????????????List<cartItem>,?????? insert??????orderItem?????????order???,??????orderId
	 * 2??????,??????orderItem??????orderId???????????????
	 * 3???cart???,???cartItemIdstr??????,?????????  ????????????,??????????????????,??????????????????????????????????????????
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/cartToOrder",method=RequestMethod.POST)
	@ResponseBody
	public Msg cartToOrder(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody List<MlfrontCartItem> mlfrontCartItemList) throws Exception{
		//??????cart???boynow???????????????
		calcFormCartListToCheakoutPage(mlfrontCartItemList,session);
		//????????????????????????cartId
		Integer cartId = 0;
		String sessionId = session.getId();
		Integer orderIdFinally = 0;
		//??????ip??????
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<String> cartItemIdStr = new ArrayList<String>();
		List<String> orderItemIdList = new ArrayList<String>();
		String orderItemIdStr = "";
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		MlfrontCartItem mlfrontCartItemGet = new MlfrontCartItem();
		if(loginUser==null){
			//loginUser???null
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//????????????cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//???????????????,????????????
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//??????mlfrontOrderItem??????,??????insert??????mlfrontOrderItem???
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
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("???????????????????????????");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//??????????????????id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//?????????????????????
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			//??????????????????
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			mlfrontOrderNew.setOrderIp(Userip);
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//??????????????????id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//???????????????orderItem??????
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
			//loginUser??????null
			Integer Uid = loginUser.getUserId();
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//????????????cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				//cartId = mlfrontCartItem.getCartitemCartId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//???????????????,????????????
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//??????mlfrontOrderItem??????,??????insert??????mlfrontOrderItem???
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
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("???????????????????????????");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//??????????????????id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//?????????????????????
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			
			//??????????????????
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			mlfrontOrderNew.setOrderUid(Uid);//??????????????????,??????????????????
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//??????????????????id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//???????????????orderItem??????
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
		//????????????
		return Msg.success().add("resMsg", "cartToOrder??????????????????").add("orderId", orderIdFinally);
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
				System.out.println("???????????????For input string: '',??????CartItemIdStr???"+CartItemIdStr+".");
				if(CartItemIdStr != null && CartItemIdStr.length() != 0){
					
					String cartItemIdStrend = CartItemIdStr.trim();
					if(cartItemIdStrend.length()>0){
						Integer CartItemIdInt =Integer.parseInt(cartItemIdStrend);//???????????????For input string: ""
						MlfrontCartItem mlfrontCartItemFor = new MlfrontCartItem();
						mlfrontCartItemFor.setCartitemId(CartItemIdInt);
						//?????????id+???pid??????????????????
						List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemFor);
						if(mlfrontCartItemListFor.size()>0){
							mlfrontCartItemListRes.add(mlfrontCartItemListFor.get(0));
						}
					}
				}
			}
		}
		return Msg.success().add("resMsg", "????????????").add("mlfrontCartItemListRes", mlfrontCartItemListRes);
	}
	
	/**
	 * 8.0	zsh 190615
	 * ????????????????????????delCartItem
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/delCartItem",method=RequestMethod.POST)
	@ResponseBody
	public Msg delCartItem(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//???????????????????????????
		Integer cartItemIdOriginal = mlfrontCartItem.getCartitemId();
		int isDelSuccess = 0;
		if(cartItemIdOriginal==null){
			return Msg.success().add("resMsg", "????????????,?????????null,??????????????????").add("isDelSuccess", isDelSuccess);//isDelSuccess0????????????,alert	resMsg/1,????????????
		}else{
			//??????cartitem???????????????????????????,????????????cartId
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
				//1??????cart????????????????????????cartItemStr????????????
				String cartitemIdstr = mlfrontCartRes.getCartitemIdstr();
				String cartItemArr[] =cartitemIdstr.split(",");
				String cartitemStrEnd = "";
				if(cartItemArr.length>1){
					//1.1???????????????????????????????????????
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
					//??????cart??????cartitemStr??????
					MlfrontCart mlfrontCartreqE = new MlfrontCart();
					mlfrontCartreqE.setCartId(cartId);
					mlfrontCartreqE.setCartitemIdstr(cartitemStrEnd);
					mlfrontCartreqE.setCartMotifytime(nowTime);
					mlfrontCartService.updateByPrimaryKeySelective(mlfrontCartreqE);
				}else{
					//1.2??????????????????,???????????????
					mlfrontCartService.deleteByPrimaryKey(cartId);
				}
				//2????????????CartItem??????
				mlfrontCartItemService.deleteByPrimaryKey(cartItemIdOriginal);
				isDelSuccess = 1;
				return Msg.success().add("resMsg", "????????????").add("isDelSuccess", isDelSuccess);//isDelSuccess0????????????,alert	resMsg/1,????????????
			}else{
				return Msg.success().add("resMsg", "????????????,???cartitem????????????,??????????????????").add("isDelSuccess", isDelSuccess);//isDelSuccess0????????????,alert	resMsg/1,????????????
			}
		}			
	}
	
	/**
	 * 9.0	zsh 190624
	 * ???????????????????????????updateCartItemSkuNum
	 * @param
	 * @return Msg
	 * */
	@RequestMapping(value="/updateCartItemSkuNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateCartItemSkuNum(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//???????????????????????????
		Integer cartItemIdOriginal = mlfrontCartItem.getCartitemId();
		Integer number = mlfrontCartItem.getCartitemProductNumber();
		
		MlfrontCartItem MlfrontCartItemUpdate = new MlfrontCartItem();
		MlfrontCartItemUpdate.setCartitemId(cartItemIdOriginal);
		MlfrontCartItemUpdate.setCartitemProductNumber(number);
		String nowTime = DateUtil.strTime14s();
		MlfrontCartItemUpdate.setCartitemMotifytime(nowTime);
		//?????????CartItem??????
		mlfrontCartItemService.updateByPrimaryKeySelective(MlfrontCartItemUpdate);
		
		return Msg.success().add("resMsg", "mlfrontCartItemUpdate successful");
	}
	
	/**
	 * 10.0	zsh 200729???????????????
	 * ?????????????????????????????????
	 * @param mlfrontOrderOne
	 * @return 
	 */
//	@RequestMapping(value="/getOneMlfrontCartDetail",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg getOneMlfrontCartDetail(@RequestParam(value = "cartId") Integer cartId){
//		
//		//??????categoryId
//		MlfrontCart mlfrontCartReq = new MlfrontCart();
//		mlfrontCartReq.setCartId(cartId);
//		//????????????
//		MlfrontCart mlfrontOrderResList =mlfrontCartService.selectMlfrontCartByCartId(cartId);
//		MlfrontCart mlfrontCartOne = new MlfrontCart();
//		if(mlfrontOrderResList!=null){
//			mlfrontCartOne =mlfrontOrderResList;
//		}
//		return Msg.success().add("resMsg", "????????????mlfrontOrderOne?????????????????????").add("mlfrontCartOne", mlfrontCartOne);
//	}
	
	/**
	 * 10.0	useOn	0530
	 * ??????+????????????,??????????????????to	BuyNow
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/toBuyNow",method=RequestMethod.POST)
	@ResponseBody
	public Msg toBuyNow(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		//??????????????????
		insertAddCartViewBuyNow(mlfrontCartItem,session);
		
		insertAddCheckOutViewBuyNow(mlfrontCartItem,session);
		
		//??????????????????
		session.setAttribute("mlfrontCartItem", mlfrontCartItem);
		String sessionId = session.getId();
		//??????ip??????
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<MlfrontCartItem> mlfrontCartItemListBuyNow = new ArrayList<MlfrontCartItem>();
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		//??????session???????????????????????? ,if ???,????????????cart???,??????????????????
		if(loginUser==null){
			//??????ip??????,???cart??????ip??????,????????????ip????????????
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
					//??????????????????,????????????
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//???????????????????????????????????????
				}
			}else{
				//????????????????????????
				ifHave = 0;
			}
			if(ifHave==1){
				//?????????,?????????????????????itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//????????????????????????????????????,????????????itemStr??????????????????productId,
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
					//?????????id+???pid+skuIdstr+skuIdnamestr+skuNamestr??????????????????
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//????????????
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//???sku???Cartitem???number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//??????
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						mlfrontCartItem.setCartitemId(CartItemIdInt);
						//System.out.println(mlfrontCartItem);
						mlfrontCartItemListBuyNow.add(mlfrontCartItem);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//?????????sku???Cartitem	????????????Cartitem sku
							//??????id??????cart?????????cartItemStr???
							System.out.println("??????????????????????????????????????????,????????????????????????????????????????????????");  
							//??????id???????????????idstr???
							//???????????????user??????????????????cartitemIdstr??????
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
							//??????mlfrontCartItem??????mlfrontCartItem??????
							//????????????cartitem,
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
				//??????ip?????????,????????????????????????mlfrontCartItem,???????????????cart?????????,????????????????????????cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				List<MlfrontCartItem> mlfrontCartItemList = mlfrontCartItemService.selectMlfrontCartItemGetAllHundred();
				Integer cartItemId = mlfrontCartItemList.get(0).getCartitemId();
				//???????????????ID		mlfrontCartItem
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
			//?????????????????????,??????????????????
			BuyNowAndcartToOrder(rep,res,session, mlfrontCartItemListBuyNow);
		}else{
			//??????uid??????,???cart??????uid??????,????????????uid????????????
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
					//??????????????????,????????????
					Integer cartId = MlfrontCartResList.get(0).getCartId();
					MlfrontCart MlfrontCartdelRep = new MlfrontCart();
					MlfrontCartdelRep.setCartId(cartId);
					mlfrontCartService.deleteByPrimaryKey(cartId);
					ifHave = 0;
					//???????????????????????????????????????
				}
			}else{
				//????????????????????????
				ifHave = 0;
			}
			if(ifHave==1){
				//?????????,?????????????????????itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//????????????????????????????????????,????????????itemStr??????????????????productId,
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
					//?????????id+???pid+skuIdstr+skuIdnamestr+skuNamestr??????????????????
					List<MlfrontCartItem> mlfrontCartItemListFor= mlfrontCartItemService.selectMlfrontCartItemOne(mlfrontCartItemFor);
					if(mlfrontCartItemListFor.size()>0){
						//????????????
						MlfrontCartItem mlfrontCartItemOne = mlfrontCartItemListFor.get(0);
						int ProductNumber = mlfrontCartItemOne.getCartitemProductNumber();
						//???sku???Cartitem???number+1,
						ProductNumber= ProductNumber+proNumberNew;
						mlfrontCartItemOne.setCartitemId(CartItemIdInt);
						mlfrontCartItemOne.setCartitemProductNumber(ProductNumber);
						mlfrontCartItemOne.setCartitemCreatetime(nowTime);
						mlfrontCartItemOne.setCartitemMotifytime(nowTime);
						//??????
						mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemOne);
						mlfrontCartItem.setCartitemId(CartItemIdInt);
						mlfrontCartItemListBuyNow.add(mlfrontCartItem);
						break;
					}else{
						if(number <aa.length){
							continue;
						}else{
							//?????????sku???Cartitem
							//????????????Cartitem sku
							//??????id??????cart?????????cartItemStr???
							System.out.println("??????????????????????????????????????????,????????????????????????????????????????????????"); 
							//??????id???????????????idstr???
							//???????????????user??????????????????cartitemIdstr??????
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
							//??????mlfrontCartItem??????mlfrontCartItem??????
							//????????????cartitem,
							mlfrontCartItem.setCartitemId(cartItemId);
							mlfrontCartItem.setCartitemCartId(cartId);
							mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
							mlfrontCartItemListBuyNow.add(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//??????ip?????????,????????????????????????mlfrontCartItem,???????????????cart?????????,????????????????????????cart
				mlfrontCartItem.setCartitemCreatetime(nowTime);
				mlfrontCartItem.setCartitemMotifytime(nowTime);
				mlfrontCartItem.setCartitemStatus(0);
				mlfrontCartItemService.insertSelective(mlfrontCartItem);
				List<MlfrontCartItem> mlfrontCartItemList = mlfrontCartItemService.selectMlfrontCartItemGetAllHundred();
				Integer cartItemId = mlfrontCartItemList.get(0).getCartitemId();
				//???????????????ID		mlfrontCartItem
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
			//loginUser???????????????,??????????????????	
			BuyNowAndcartToOrder(rep,res,session, mlfrontCartItemListBuyNow);
		}
		return Msg.success().add("resMsg", "????????????");
	}
	
	/**
	 * ??????
	 * ??????
	 * ??????????????????
	 * */
	public void BuyNowAndcartToOrder(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody List<MlfrontCartItem> mlfrontCartItemList) throws Exception{
		//???????????????????????????
		System.out.println(mlfrontCartItemList);
		//????????????????????????cartId
		Integer cartId = 0;
		String sessionId = session.getId();
		Integer orderIdFinally = 0;
		//??????ip??????
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<String> cartItemIdStr = new ArrayList<String>();
		List<String> orderItemIdList = new ArrayList<String>();
		String orderItemIdStr = "";
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		MlfrontCartItem mlfrontCartItemGet = new MlfrontCartItem();
		if(loginUser==null){
			//loginUser???null
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//????????????cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//???????????????,????????????
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//??????mlfrontOrderItem??????,??????insert??????mlfrontOrderItem???
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
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("???????????????????????????");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//??????????????????id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//?????????????????????
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			//??????????????????
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			mlfrontOrderNew.setOrderIp(Userip);
			
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			
			//?????????????????????,??????order????????????????????????
			MlfrontCartItem mlfrontCartItemBuyNow = mlfrontCartItemList.get(0);
			Integer ProductNumber = mlfrontCartItemBuyNow.getCartitemProductNumber();
			String ProductNumberStr = ProductNumber+"";
			mlfrontOrderNew.setOrderPronumStr(ProductNumberStr);
			
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//??????????????????id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//???????????????orderItem??????
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
			//loginUser??????null
			Integer Uid = loginUser.getUserId();
			MlfrontOrderItem mlfrontOrderItemNew = new MlfrontOrderItem();
			for(MlfrontCartItem mlfrontCartItem:mlfrontCartItemList){
				//System.out.println(mlfrontCartItem);
				//????????????cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//???????????????,????????????
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//??????mlfrontOrderItem??????,??????insert??????mlfrontOrderItem???
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
				//mlfrontOrderItemNew.setOrderitemPskuReamoney("???????????????????????????");
				mlfrontOrderItemNew.setOrderitemPskuNumber(mlfrontCartItem.getCartitemProductNumber());
				mlfrontOrderItemNew.setOrderitemCreatetime(nowTime);
				mlfrontOrderItemNew.setOrderitemMotifytime(nowTime);
				mlfrontOrderItemService.insertSelective(mlfrontOrderItemNew);
				//??????????????????id
				MlfrontOrderItem MlfrontOrderItemReallOne =mlfrontOrderItemService.selectMlfrontOrderItemAllHundred().get(0);
				Integer orderItemNewId = MlfrontOrderItemReallOne.getOrderitemId();
				String orderItemNewIdStr = orderItemNewId+"";
				//?????????????????????
				orderItemIdList.add(orderItemNewIdStr);
				orderItemIdStr=orderItemIdStr+","+orderItemNewIdStr;
			}
			//??????????????????
			orderItemIdStr = orderItemIdStr.substring(1);
			MlfrontOrder mlfrontOrderNew  = new MlfrontOrder();
			mlfrontOrderNew.setOrderOrderitemidstr(orderItemIdStr);
			//mlfrontOrderNew.setOrderIp(Userip);
			mlfrontOrderNew.setOrderUid(Uid);//??????????????????,??????????????????
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			
			//?????????????????????,??????order????????????????????????
			MlfrontCartItem mlfrontCartItemBuyNow = mlfrontCartItemList.get(0);
			Integer ProductNumber = mlfrontCartItemBuyNow.getCartitemProductNumber();
			String ProductNumberStr = ProductNumber+"";
			mlfrontOrderNew.setOrderPronumStr(ProductNumberStr);
			
			mlfrontOrderService.insertSelective(mlfrontOrderNew);
			//??????????????????id
			MlfrontOrder mlfrontOrdergetAllOne = mlfrontOrderService.selectMlfrontOrderAllHundred().get(0);
			//???????????????orderItem??????
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
		//????????????
	}
	
	/**
	 * 11.0	zsh 190624
	 * cart????????????sku??????????????????
	 * @param updateCartitemPku
	 * @return 
	 */
	@RequestMapping(value="/updateCartitemPku",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateCartitemPku(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem){
		
		System.out.println("?????????????????????mlfrontCartItem:"+mlfrontCartItem.toString());
		mlfrontCartItem.getCartitemId();
		mlfrontCartItem.getCartitemProductskuId();
		mlfrontCartItem.getCartitemProductskuName();
		mlfrontCartItem.getCartitemProductskuCode();
		mlfrontCartItem.getCartitemProductskuMoneystr();
		mlfrontCartItem.getCartitemProductNumber();
		mlfrontCartItem.getCartitemProductskuIdstr();
		mlfrontCartItem.getCartitemProductskuIdnamestr();
		mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItem);
		return Msg.success().add("resMsg", "????????????").add("mlfrontCartItem", mlfrontCartItem);
	}
	
	//4.1??????????????????
	private void insertAddCartView(MlfrontCartItem mlfrontCartItem, HttpSession session) {
		String nowViewTime = DateUtil.strTime14s();
		//???????????????id
		Integer productId = mlfrontCartItem.getCartitemProductId();
		//??????pid???????????????
		MlbackProduct mlbackProductrep = new MlbackProduct();
		mlbackProductrep.setProductId(productId);
		List<MlbackProduct> mlbackProductresList = mlbackProductService.selectMlbackProductByParam(mlbackProductrep);
		MlbackProduct mlbackProductres = mlbackProductresList.get(0);
		//??????seo??????+?????????
		String addcartviewdetailSeoname = mlbackProductres.getProductSeo();
		String addcartviewdetailProname = mlbackProductres.getProductName();
		System.out.println("????????????:nowViewTime,"+nowViewTime+",toAddToCart	:"+addcartviewdetailSeoname);
		//??????????????????
		MlbackAddCartViewDetail mlbackAddCartViewDetailreq = new MlbackAddCartViewDetail();
		//????????????
		mlbackAddCartViewDetailreq.setAddcartviewdetailProseo(addcartviewdetailSeoname);
		mlbackAddCartViewDetailreq.setAddcartviewdetailProname(addcartviewdetailProname);
		//sessionID
		String sessionId =  session.getId();
		mlbackAddCartViewDetailreq.setAddcartviewdetailSessionid(sessionId);
		//????????????
		String nowTime = DateUtil.strTime14s();
		mlbackAddCartViewDetailreq.setAddcartviewdetailCreatetime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailMotifytime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailActnum(0); //??????????????????,0?????????	,1???buyNow???????????????
		mlbackAddCartViewDetailService.insertSelective(mlbackAddCartViewDetailreq);
	}
	
	/**
	 * ?????????detail--buyNow????????????
	 * */
	private void insertAddCartViewBuyNow(MlfrontCartItem mlfrontCartItem, HttpSession session) {
		Integer productId = mlfrontCartItem.getCartitemProductId();
		
		MlbackProduct mlbackProductrep = new MlbackProduct();
		mlbackProductrep.setProductId(productId);
		
		List<MlbackProduct> mlbackProductresList = mlbackProductService.selectMlbackProductByParam(mlbackProductrep);
		MlbackProduct mlbackProductres = mlbackProductresList.get(0);
		
		String addcartviewdetailSeoname = mlbackProductres.getProductSeo();
		String addcartviewdetailProname = mlbackProductres.getProductName();
		
		//??????????????????
		MlbackAddCartViewDetail mlbackAddCartViewDetailreq = new MlbackAddCartViewDetail();
		//????????????
		mlbackAddCartViewDetailreq.setAddcartviewdetailProseo(addcartviewdetailSeoname);
		mlbackAddCartViewDetailreq.setAddcartviewdetailProname(addcartviewdetailProname);
		//sessionID
		String sessionId =  session.getId();
		mlbackAddCartViewDetailreq.setAddcartviewdetailSessionid(sessionId);
		//????????????
		String nowTime = DateUtil.strTime14s();
		mlbackAddCartViewDetailreq.setAddcartviewdetailCreatetime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailMotifytime(nowTime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailActnum(1); //??????????????????,0?????????	,1???buyNow???????????????
		mlbackAddCartViewDetailService.insertSelective(mlbackAddCartViewDetailreq);
		
	}
	
	
	/**
	 * ?????????detail--buyNow--Cheakout????????????
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
		
		//??????????????????
		MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetailreq = new MlbackAddCheakoutViewDetail();
		//????????????
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProseo(addcartviewdetailSeoname);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProname(addcartviewdetailProname);
		//sessionID
		String sessionId =  session.getId();
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailSessionid(sessionId);
		//????????????
		String nowTime = DateUtil.strTime14s();
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailCreatetime(nowTime);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailMotifytime(nowTime);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailActnum(1); //??????????????????,0?????????	,1???buyNow???????????????
		mlbackAddCheakoutViewDetailService.insertSelective(mlbackAddCheakoutViewDetailreq);
	}
	
	/**
	 * ?????????CartList--Cheakout????????????
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
			
			//??????????????????
			MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetailreq = new MlbackAddCheakoutViewDetail();
			//????????????
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProseo(addcartviewdetailSeoname);
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailProname(addcartviewdetailProname);
			//sessionID
			String sessionId =  session.getId();
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailSessionid(sessionId);
			//????????????
			String nowTime = DateUtil.strTime14s();
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailCreatetime(nowTime);
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailMotifytime(nowTime);
			mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailActnum(0); //??????????????????,0(???CartList--Cheakout)????????????	,1(???buyNow--Cheakout)????????????
			mlbackAddCheakoutViewDetailService.insertSelective(mlbackAddCheakoutViewDetailreq);
		}
	}
	
}
