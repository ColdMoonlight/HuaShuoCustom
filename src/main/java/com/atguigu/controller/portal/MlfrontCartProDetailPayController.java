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
import com.atguigu.bean.MlfrontCart;
import com.atguigu.bean.MlfrontCartItem;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlfrontCartItemService;
import com.atguigu.service.MlfrontCartService;
import com.atguigu.service.MlfrontOrderItemService;
import com.atguigu.service.MlfrontOrderService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/ProPay")
public class MlfrontCartProDetailPayController {
	
	@Autowired
	MlfrontCartService mlfrontCartService;
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	@Autowired
	MlfrontOrderItemService mlfrontOrderItemService;
	
	@Autowired
	MlfrontCartItemService mlfrontCartItemService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	/**
	 * 1.0	useOn	0530
	 * 加购+提交订单，全部一步生成to	BuyNow
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/toBuyNowPay",method=RequestMethod.POST)
	@ResponseBody
	public Msg toBuyNowPay(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCartItem mlfrontCartItem) throws Exception{
		
		Integer OrderIdBuyNowPay = 0;
		
		//放回响应域中
		session.setAttribute("mlfrontCartItem", mlfrontCartItem);
		String sessionId = session.getId();
		//获取ip地址
		String Userip =sessionId;
		session.setAttribute("Userip", Userip);
		String nowTime = DateUtil.strTime14s();
		List<MlfrontCartItem> mlfrontCartItemListBuyNow = new ArrayList<MlfrontCartItem>();
		MlfrontUser loginUser = (MlfrontUser) session.getAttribute("loginUser");
		//判断session中是否有登录信息 ,if 有，新增进入cart中，没有新增进入
		if(loginUser==null){
			//获取ip信息，去cart中用ip查询，是否有该ip的购物车
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
				//如果有，获取该购物车的itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//判断是否有本次添加的产品，如果没有itemStr中加入本次的productId,
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
							mlfrontCartItemListBuyNow.add(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//这个ip没来过，直接新增一条即可mlfrontCartItem，将主键放入cart对象中，之后在新增该对象cart
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
				System.out.println(mlfrontCartItem);
				mlfrontCartItemListBuyNow.add(mlfrontCartItem);
			}
			//游客加购已完毕,准备生成订单
			OrderIdBuyNowPay = BuyNowAndcartToOrder(rep,res,session, mlfrontCartItemListBuyNow);
		}else{
			//获取uid信息，去cart中用uid查询，是否有该uid的购物车
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
				//如果有，获取该购物车的itemStr,
				MlfrontCart mlfrontCartUser = MlfrontCartResList.get(0);
				String cartitemIdstrUser = mlfrontCartUser.getCartitemIdstr();
				Integer cartId = mlfrontCartUser.getCartId();
				Integer cartUid = mlfrontCartUser.getCartUid();
				//判断是否有本次添加的产品，如果没有itemStr中加入本次的productId,
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
							mlfrontCartItemListBuyNow.add(mlfrontCartItem);
							break;
						}
					}
				}
			}else{
				//这个ip没来过，直接新增一条即可mlfrontCartItem，将主键放入cart对象中，之后在新增该对象cart
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
				mlfrontCartItemListBuyNow.add(mlfrontCartItem);
			}
			//loginUser加购已完毕,准备生成订单	
			OrderIdBuyNowPay = BuyNowAndcartToOrder(rep,res,session, mlfrontCartItemListBuyNow);
		}
		return Msg.success().add("resMsg", "添加成功").add("OrderIdBuyNowPay", OrderIdBuyNowPay);
	}
	
	/**
	 * 方法
	 * 勿碰
	 * 购买中的任务
	 * */
	public Integer BuyNowAndcartToOrder(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody List<MlfrontCartItem> mlfrontCartItemList) throws Exception{
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
				System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项，改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象，准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
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
			
			//直接进入的时候，没有order表中没有数量字段
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
				System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项，改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象，准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
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
			mlfrontOrderNew.setOrderUid(Uid);//这是登录用户，存错登录状态
			mlfrontOrderNew.setOrderStatus(0);//cart To Order
			mlfrontOrderNew.setOrderCreatetime(nowTime);
			mlfrontOrderNew.setOrderMotifytime(nowTime);
			
			//直接进入的时候，没有order表中没有数量字段
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
		return orderIdFinally;
		//返回视图
	}
	
	/**
	 * 6.0	0530
	 * 选择购物车中的产品项，提交生成订单cartToOrder
	 * 1接住传入的List《cartItem》，遍历 insert订单orderItem。插入order表，返回orderId
	 * 2遍历，更新orderItem中的orderId归属字段；
	 * 3从cart中，把cartItemIdstr字段，更新。  这一步骤，不该出现在这，要放在生成订单那里。就没事了
	 * @param Msg
	 * @return 
	 * */
	@RequestMapping(value="/cartToOrderPay",method=RequestMethod.POST)
	@ResponseBody
	public Msg cartToOrder(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody List<MlfrontCartItem> mlfrontCartItemList) throws Exception{
		//接收传递进来的参数
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
				System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项，改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象，准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
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
				System.out.println(mlfrontCartItem);
				//取出本条cartItem
				Integer cartitemIdInt = mlfrontCartItem.getCartitemId();
				mlfrontCartItemGet.setCartitemId(cartitemIdInt);
				List<MlfrontCartItem> mlfrontCartItemGetRes =mlfrontCartItemService.selectMlfrontCartItemById(mlfrontCartItemGet);
				//将该购物项，改成已用
				mlfrontCartItemGet.setCartitemStatus(1);
				mlfrontCartItemService.updateByPrimaryKeySelective(mlfrontCartItemGet);
				MlfrontCartItem mlfrontCartItemreturn = mlfrontCartItemGetRes.get(0);
				cartId = mlfrontCartItemreturn.getCartitemCartId();
				String cartitemIdIntStrOne = cartitemIdInt+"";
				cartItemIdStr.add(cartitemIdIntStrOne);
				//封装mlfrontOrderItem对象，准备insert进入mlfrontOrderItem表
				mlfrontOrderItemNew.setOrderCartitemid(cartitemIdInt);
				mlfrontOrderItemNew.setOrderitemPid(mlfrontCartItem.getCartitemProductId());
				mlfrontOrderItemNew.setOrderitemPname(mlfrontCartItemreturn.getCartitemProductName());
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
			mlfrontOrderNew.setOrderUid(Uid);//这是登录用户，存错登录状态
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
		//返回视图
		session.setAttribute("orderId", orderIdFinally);
		return Msg.success().add("resMsg", "订单提交成功").add("OrderIdcartToOrderPay", orderIdFinally);
	}
	
}
