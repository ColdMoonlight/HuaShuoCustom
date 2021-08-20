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
import com.atguigu.bean.MlPaypalStateprovince;
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.common.Msg;
import com.atguigu.service.MlPaypalStateprovinceService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;
import com.atguigu.service.MlfrontAddressService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlfrontAddress")
public class MlfrontAddressController {
		
	@Autowired
	MlfrontAddressService mlfrontAddressService;
	
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlPaypalStateprovinceService mlPaypalStateprovinceService;
	
	/**1.0	UseNow	1202
	 * MlfrontAddress	insert
	 * @param MlfrontAddress
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontAddress mlfrontAddress){
		//接受参数信息
		System.out.println("客户点击结算按钮的时候,接收到的mlfrontAddress:"+mlfrontAddress);
		//处理掉不存的字符
		String intoAddressDetail = mlfrontAddress.getAddressDetail();
//		System.out.println("intoAddressDetail:"+intoAddressDetail);
		String afterAddressDetail = intoAddressDetail.replaceAll("[^\\u0000-\\uFFFF]", "");
		System.out.println("afterAddressDetail:"+afterAddressDetail);
		mlfrontAddress.setAddressDetail(afterAddressDetail);
		
		
		String intoUserfirstname = mlfrontAddress.getAddressUserfirstname();
//		System.out.println("intoUserfirstname:"+intoUserfirstname);
		String afterUserfirstname = intoUserfirstname.replaceAll("[^\\u0000-\\uFFFF]", "");
		System.out.println("afterUserfirstname:"+afterUserfirstname);
		mlfrontAddress.setAddressUserfirstname(afterUserfirstname);
		
		String intoUserlastname = mlfrontAddress.getAddressUserlastname();
//		System.out.println("intoUserlastname:"+intoUserlastname);
		String afterUserlastname = intoUserlastname.replaceAll("[^\\u0000-\\uFFFF]", "");
		System.out.println("afterUserlastname:"+afterUserlastname);
		mlfrontAddress.setAddressUserlastname(afterUserlastname);
		
		//拿到国家的code
		String areafreightCountryCode = mlfrontAddress.getAddressCountryCode();
		//封装国家code
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
		mlfrontAddress.setAddressCountryCode(areafreightCountryCode);
		//查询该国家的全称
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		Integer areafreightMoney = 0;
		String addressCountryAll ="";
		if(mlbackAreafreightResList.size()>0){
			areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();	//拿到国家运费
			addressCountryAll = mlbackAreafreightResList.get(0).getAreafreightCountry();//拿到国家全称 
		}
		//拿到省份的code
		String addressProvinceName = mlfrontAddress.getAddressProvince();
		
		//封装省份code
		MlPaypalStateprovince mlPaypalStateprovinceReq = new MlPaypalStateprovince();
		mlPaypalStateprovinceReq.setStateprovinceCountryCode(areafreightCountryCode);
		mlPaypalStateprovinceReq.setStateprovinceName(addressProvinceName);
		
		List<MlPaypalStateprovince> mlPaypalStateprovinceList =  mlPaypalStateprovinceService.selectMlPaypalStateprovinceByCountryCodeAndProvince(mlPaypalStateprovinceReq);
		String stateprovinceNameCode ="";
		if(mlPaypalStateprovinceList.size()>0){
			stateprovinceNameCode =mlPaypalStateprovinceList.get(0).getStateprovinceNameCode();//拿到省份Code
		}
		//将省份code放入地址对象中
		mlfrontAddress.setAddressProvincecode(stateprovinceNameCode);
		//这里需要判断省份
		//取出id
		Integer addressId = mlfrontAddress.getAddressId();
		String nowTime = DateUtil.strTime14s();
		mlfrontAddress.setAddressMotifytime(nowTime);
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		if(loginUser==null){
			usertype = 0;//游客
		}else{
			usertype = 1;//注册用户
		}
		//addressCountryAll
		mlfrontAddress.setAddressCountry(addressCountryAll);
		
		//取出addressDetail,用" ",替换掉()
		String addressDetailUserIn = mlfrontAddress.getAddressDetail();
		String addressDetailStrbefore = addressDetailUserIn.replace("(", " ");
		String addressDetailStr = addressDetailStrbefore.replace(")", " ");
		mlfrontAddress.setAddressDetail(addressDetailStr);
		
		//取出邮箱,trim()掉空格
		String addressEmailUserIn = mlfrontAddress.getAddressEmail();
		String addressEmailStr=addressEmailUserIn.replace(" ", "");
		mlfrontAddress.setAddressEmail(addressEmailStr);
		
		int realAddressId = 0;
		if(loginUser==null){
			String sessionId = session.getId();
			//获取ip地址
			String Userip =sessionId;
			
			if(addressId==null){
				//无id,insert
				mlfrontAddress.setAddressIp(Userip);
				mlfrontAddress.setAddressCreatetime(nowTime);
				mlfrontAddressService.insertSelective(mlfrontAddress);
				
				realAddressId = mlfrontAddress.getAddressId();
				
				session.setAttribute("realAddressId", realAddressId);
				System.out.println("游客第一次存的mlfrontAddressService.insertSelective");
				return Msg.success().add("resMsg", "游客地址信息插入成功").add("mlfrontAddress", mlfrontAddress).add("areafreightMoney", areafreightMoney).add("usertype", usertype);//新增以后,返回去的这里,有id,你从这里拿
			}else{
				//有id,update
				mlfrontAddress.setAddressMotifytime(nowTime);
				mlfrontAddressService.updateByPrimaryKeySelective(mlfrontAddress);
				
				session.setAttribute("realAddressId", addressId);
				System.out.println("游客非第一次存的mlfrontAddressService.insertSelective");
				return Msg.success().add("resMsg", "游客地址信息更新成功").add("mlfrontAddress", mlfrontAddress).add("areafreightMoney", areafreightMoney).add("usertype", usertype);
			}		
		}else{
			Integer uid = loginUser.getUserId();
			mlfrontAddress.setAddressUid(uid);
			if(addressId==null){
				//无id,insert
				mlfrontAddress.setAddressCreatetime(nowTime);
				mlfrontAddressService.insertSelective(mlfrontAddress);
				
				realAddressId = mlfrontAddress.getAddressId();
				
				session.setAttribute("realAddressId", realAddressId);
				System.out.println("注册用户第一次存的insertSelective");
				return Msg.success().add("resMsg", "登录用户地址信息插入成功").add("mlfrontAddress", mlfrontAddress).add("areafreightMoney", areafreightMoney).add("usertype", usertype);//新增以后,返回去的这里,有id,你从这里拿
			}else{
				//有id,update
				mlfrontAddress.setAddressMotifytime(nowTime);
				mlfrontAddressService.updateByPrimaryKeySelective(mlfrontAddress);
				
				session.setAttribute("realAddressId", addressId);
				System.out.println("注册用户非第一次存的updateByPrimaryKeySelective");
				return Msg.success().add("resMsg", "登录用户地址信息更新成功").add("mlfrontAddress", mlfrontAddress).add("areafreightMoney", areafreightMoney).add("usertype", usertype);
			}	
		}
	}
	/**1.1	UseNow	0505
	 * MlfrontAddress	insert
	 * @param MlfrontAddress
	 */
	@RequestMapping(value="/getAreafreightMoney",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAreafreightMoney(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontAddress mlfrontAddress){
		//接受参数信息
		//System.out.println("选地址的时候,获取邮费操作:mlfrontAddress:"+mlfrontAddress);
		
		String areafreightCountryCode = mlfrontAddress.getAddressCountry();
		
		//接受categoryId
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
		//查询本条
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		Integer areafreightMoney = 0;
		String areafreightCountry = "";
		if(mlbackAreafreightResList.size()>0){
			areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
			areafreightCountry = mlbackAreafreightResList.get(0).getAreafreightCountry();
		}
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlfrontAddress.setAddressMotifytime(nowTime);
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		if(loginUser==null){
			usertype = 0;//游客
		}else{
			usertype = 1;//注册用户
		}
		//查询该国家的二级省份
		MlPaypalStateprovince mlPaypalStateprovinceReq = new MlPaypalStateprovince();
		mlPaypalStateprovinceReq.setStateprovinceCountryCode(areafreightCountryCode);
		List<MlPaypalStateprovince> mlPaypalStateprovinceList =  mlPaypalStateprovinceService.selectMlPaypalStateprovinceByCountryCode(mlPaypalStateprovinceReq);
		return Msg.success().add("resMsg", "查询运费成功").add("areafreightMoney", areafreightMoney).add("areafreightCountry", areafreightCountry).add("usertype", usertype).add("mlPaypalStateprovinceList", mlPaypalStateprovinceList);//新增以后,返回去的这里,有id,你从这里拿
	}
	
	/**2.0	useOn	0505
	 * MlfrontAddress	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontAddress mlfrontAddress){
		//接收id信息
		int addressId = mlfrontAddress.getAddressId();
		mlfrontAddressService.deleteByPrimaryKey(addressId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**3.0	useOn	0505
	 * MlfrontAddress	update
	 * @param id MlfrontAddress
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Msg update(@RequestBody MlfrontAddress mlfrontAddress){
		//接受信息
		String nowtime = DateUtil.strTime14s();
		mlfrontAddress.setAddressMotifytime(nowtime);
		//更新本条状态
		mlfrontAddressService.updateByPrimaryKeySelective(mlfrontAddress);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**
	 * 4.0	useOn	0505
	 * 查看单条地址信息
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontAddressDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontAddressDetail(@RequestParam(value = "addressId") Integer addressId){
		
		//接受categoryId
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		mlfrontAddressReq.setAddressId(addressId);
		//查询本条
		List<MlfrontAddress> mlfrontAddressResList =mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
		MlfrontAddress mlfrontAddressOne =mlfrontAddressResList.get(0);
		return Msg.success().add("resMsg", "查看单条mlfrontAddressOne的详情细节完毕")
					.add("mlfrontAddressOne", mlfrontAddressOne);
	}
	
	/**
	 * 5.0	useOn	0505
	 * 初始化进入checkout,默认调用 查询此用户是否有曾经留下的地址	无参数,post请求
	 * @param MlfrontAddress
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontAddressDetailByUinfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontAddressDetailByUinfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer usertype = 0;
		Integer uid = 0;
		if(loginUser==null){
			usertype = 0;//游客
		}else{
			usertype = 1;//注册用户
			uid = loginUser.getUserId();
		}
		MlfrontAddress mlfrontAddressReq = new MlfrontAddress();
		List<MlfrontAddress> mlfrontAddressResList = new ArrayList<MlfrontAddress>();
		if(usertype==0){
			String SessionId = session.getId();
			//未登陆状态,用SessionId做Ip查询
			mlfrontAddressReq.setAddressIp(SessionId);
			mlfrontAddressResList =mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
			
			if(mlfrontAddressResList.size()>0){
				
			}else{
				//送session中获取addressid,然后查询
				Integer addressId =  (Integer) session.getAttribute("addressId");
				if(addressId!=null){
					MlfrontAddress mlfrontAddress =new MlfrontAddress();
					mlfrontAddress.setAddressId(addressId);
					mlfrontAddressResList = mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddress);
				}
			}
		}else{
			//登陆状态,用uid做Ip查询
			mlfrontAddressReq.setAddressUid(uid);
			mlfrontAddressResList =mlfrontAddressService.selectMlfrontAddressByParam(mlfrontAddressReq);
		}
		//初始化结果对象,准备接参
		MlfrontAddress mlfrontAddressOne=null;
		Integer areafreightMoney = 0;
		if(mlfrontAddressResList.size()>0){
			 mlfrontAddressOne =mlfrontAddressResList.get(0);
			 String areafreightCountryCode = mlfrontAddressOne.getAddressCountryCode();
			 //接受areafreightCountryCode
			 MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
			 mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
			 //查询本条
			 List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
			 
			 if(mlbackAreafreightResList.size()>0){
				areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
			 }
		}else{
			String areafreightCountryCode = "US";
			//接受areafreightCountryCode
			 MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
			 mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryCode);
			 //查询本条
			 List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
			 
			 if(mlbackAreafreightResList.size()>0){
				areafreightMoney =mlbackAreafreightResList.get(0).getAreafreightPrice();
			 }
		}
		return Msg.success().add("resMsg", "查看单条mlfrontAddressOne的详情细节完毕")
					.add("mlfrontAddressOne", mlfrontAddressOne).add("areafreightMoney", areafreightMoney).add("usertype", usertype);
	}
	
}
