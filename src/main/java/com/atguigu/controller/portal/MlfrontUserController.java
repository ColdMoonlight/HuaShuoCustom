package com.atguigu.controller.portal;

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
import com.atguigu.bean.MlbackEmailRichText;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackEmailRichTextService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.EmailNewUtilshtml;
import com.atguigu.utils.EmailNewUtilshtmlCustomer;
import com.atguigu.utils.EmailRichTextUtilshtml;
import com.atguigu.utils.EmailRichTextUtilshtmlCustomer;
import com.atguigu.utils.IfMobileUtils;
import com.atguigu.utils.PropertiesUtil;

@Controller
@RequestMapping("/MlfrontUser")
public class MlfrontUserController {
		
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackEmailRichTextService mlbackEmailRichTextService;
	
	/**1.0	zsh 200619
	 * ifSession	初始化验证是否登录
	 * @param ifSession
	 */
	@RequestMapping(value="/ifLogin",method=RequestMethod.POST)
	@ResponseBody
	public Msg ifLogin(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//获取session中是否已经登陆
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer ifLogin = 0;
		if(loginUser==null){
			ifLogin = 0;//无登录状态
		}else{
			ifLogin = 1;//有登录状态
		}
		return Msg.success().add("resMsg", "初始化验证是否登录").add("ifLogin", ifLogin);
	}
	
	/**2	zsh 200619
	 * MlfrontUser	update
	 * @param id MlfrontUser
	 * @return
	 */
	@RequestMapping(value="/reSetPwd",method=RequestMethod.POST)
	@ResponseBody
	public Msg reSetPwd(@RequestBody MlfrontUser mlfrontUser){
		//接受信息
		String nowtime = DateUtil.strTime14s();
		mlfrontUser.setUserMotifytime(nowtime);
		//更新本条状态
		mlfrontUserService.updatePwdByEmail(mlfrontUser);
		return Msg.success().add("resMsg", "Update Successful").add("mlfrontUser", mlfrontUser);
	}
	
	/**3.0	zsh 200619
	 * MlfrontUser	登录login
	 * @param MlfrontUser
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Msg frontUserLogin(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody MlfrontUser mlfrontUser){
		//接受账号密码
		String userEmail = mlfrontUser.getUserEmail();
		String userPassword = mlfrontUser.getUserPassword();
		//查询拿着账号去查询
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		Integer loginYes = 0;
		
		List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		if(mlfrontUserList.size()>0){
			//此账号邮箱存在，返回即可
			MlfrontUser mlfrontUserres = mlfrontUserList.get(0);
			String alreadyPwd= mlfrontUserres.getUserPassword();
			Integer UserId = mlfrontUserres.getUserId();
			//3.1修改登录时间
			updateLoginTime(UserId);
			if(alreadyPwd.equals(userPassword)){
				System.out.println("密码正确,登陆成功");
				session.setAttribute("loginUser", mlfrontUserres);
				loginYes = 1;
				return Msg.success().add("resMsg", "Successfully").add("loginYes", loginYes);
			}else{
				System.out.println("密码错误,登陆失败");
				return Msg.success().add("resMsg", "PassWord Error").add("loginYes", loginYes);
			}
		}else{
			return Msg.success().add("resMsg", "Account does not exist").add("loginYes", loginYes);
		}
	}
	
	//3.1修改登录时间
	private void updateLoginTime(Integer userId) {
		
		String nowtime = DateUtil.strTime14s();
		MlfrontUser mlfrontUser = new MlfrontUser();
		mlfrontUser.setUserId(userId);
		mlfrontUser.setUserMotifytime(nowtime);
		mlfrontUser.setUserLastonlinetime(nowtime);
		mlfrontUserService.updateByPrimaryKeySelective(mlfrontUser);
	}

	/**4.0	zsh 200619
	 * MlfrontUser	注册register
	 * @param MlfrontUser
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Msg frontUserRegister(HttpServletResponse rep,HttpServletRequest res,HttpSession session ,@RequestBody MlfrontUser mlfrontUser){
		//接受账号密码
		String userEmail = mlfrontUser.getUserEmail();
		String userPassword = mlfrontUser.getUserPassword();
		//查询拿着账号去查询
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		Integer registerYes = 0;
		List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		String nowtime = DateUtil.strTime14s();
		if(mlfrontUserList.size()>0){
			//此账号邮箱已经注册,返回即可
			return Msg.success().add("resMsg", "Registration failed, the email has been registered").add("registerYes", registerYes);//0的话取出resMsg
		}else{
			//此账号邮箱未注册,继续封装参数,插入即可
			mlfrontUserreq.setUserPassword(userPassword);
			//给user的Coupon字段加入优惠券的idStr
			mlfrontUserreq.setUserCouponidstr("1,2,3");
			mlfrontUserreq.setUserCreatetime(nowtime);
			mlfrontUserreq.setUserLastonlinetime(nowtime);
			mlfrontUserreq.setUserViplevel(0);
			mlfrontUserreq.setUserTimes(0);
			mlfrontUserService.insertSelective(mlfrontUserreq);
			//用账号把它查回来
			MlfrontUser mlfrontUserInAfterreq = new MlfrontUser();
			mlfrontUserInAfterreq.setUserEmail(userEmail);
			List<MlfrontUser> mlfrontUserInafterList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserInAfterreq);
			MlfrontUser mlfrontUserafterIn =mlfrontUserInafterList.get(0);
			session.setAttribute("loginUser", mlfrontUserafterIn);
			registerYes = 1;
			try {
				//测试方法
				String getToEmail = userEmail;
				String MegaLook = (String) PropertiesUtil.getProperty("megalook.properties", "MegaLook");
				String Message = "Welcome to Register In "+MegaLook+".";
				//String Message = "Welcome to Register In Megalook.";
//				EmailUtilshtml.readyEmailRegister(getToEmail, Message,mlfrontUserafterIn);
//				EmailUtilshtmlCustomer.readyEmailRegisterCustomer(getToEmail, Message,mlfrontUserafterIn);
//				EmailNewUtilshtml.readyEmailRegister(getToEmail, Message,mlfrontUserafterIn);
//				EmailNewUtilshtmlCustomer.readyEmailRegisterCustomer(getToEmail, Message,mlfrontUserafterIn);
				//
				MlbackEmailRichText mlbackEmailRichTextReq= new MlbackEmailRichText();
				MlbackEmailRichText mlbackEmailRichTextRes= new MlbackEmailRichText();
				String EmailrichtextSeo= "userRegister";
				mlbackEmailRichTextReq.setEmailrichtextSeoname(EmailrichtextSeo);
				List<MlbackEmailRichText> mlbackEmailRichTextList = mlbackEmailRichTextService.selectMlbackEmailRichTextListByName(mlbackEmailRichTextReq);
				if(mlbackEmailRichTextList.size()>0){
					mlbackEmailRichTextRes = mlbackEmailRichTextList.get(0);
					EmailRichTextUtilshtml.readyEmailRichTextRegister(getToEmail, mlbackEmailRichTextRes,mlfrontUserafterIn);
					EmailRichTextUtilshtmlCustomer.readyEmailRichTextRegisterCustomerAll(getToEmail, mlbackEmailRichTextRes,mlfrontUserafterIn);
				}else{
					EmailNewUtilshtml.readyEmailRegister(getToEmail, Message,mlfrontUserafterIn);
					EmailNewUtilshtmlCustomer.readyEmailRegisterCustomer(getToEmail, Message,mlfrontUserafterIn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Msg.success().add("resMsg", "Registration Success").add("registerYes", registerYes);//1的话,成功
		}
	}
	
	/**5.0	zsh 200619
	 * MlfrontUser	注销exit
	 * @param MlfrontUser
	 */
	@RequestMapping(value="/exit",method=RequestMethod.POST)
	@ResponseBody
	public Msg mfrontUserexit(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//接受账号密码
		Integer exitInt= 0;
		MlfrontUser mlfrontUserLogin  = (MlfrontUser) session.getAttribute("loginUser");
		if(mlfrontUserLogin==null){
			exitInt = 0; 
		}else{
			session.removeAttribute("loginUser");
			exitInt = 0; 
		}
		return Msg.success().add("resMsg", "Sign Out").add("exitInt", exitInt);//1的话,成功
	}
	
	/**6.0	zsh 200728
	 * ifSession	去个人中心
	 * @param ifSession
	 */
	@RequestMapping(value="/toUserCenter")
	public String toUserCenter(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		 if(ifMobile.equals("1")){
			  return "portal/user/userCenter";//手机端
		  }else{
			  return "portal/user/userPcCenter";//Pc端
		  }
	}
	
	/**7.0	zsh 200630
	 * MlfrontUser	update
	 * @param id MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Msg update(@RequestBody MlfrontUser mlfrontUser){
		//接受信息
		String nowtime = DateUtil.strTime14s();
		mlfrontUser.setUserMotifytime(nowtime);
		mlfrontUser.setUserLastonlinetime(nowtime);
		//更新本条状态
		mlfrontUserService.updateByPrimaryKeySelective(mlfrontUser);
		return Msg.success().add("resMsg", "Update Successful").add("mlfrontUser", mlfrontUser);
	}
	
	/**
	 * 8.0	zsh 200630
	 * 查看单条MlfrontUser的详情细节
	 * @param MlfrontUser
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontUserDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontUserDetail(@RequestBody MlfrontUser mlfrontUser){
		
		//接受信息
		Integer userId = mlfrontUser.getUserId();
		MlfrontUser mlfrontUserReq = new MlfrontUser();
		mlfrontUserReq.setUserId(userId);
		List<MlfrontUser> mlfrontUserResList =mlfrontUserService.selectMlfrontUserByConditionS(mlfrontUserReq);
		MlfrontUser mlfrontUserOne =mlfrontUserResList.get(0);
		return Msg.success().add("resMsg", "查看单条UserOne详情细节完毕").add("mlfrontUserOne", mlfrontUserOne);
	}	
	
	/**
	 * 9.0	zsh 200630
	 * 查看单条MlfrontUser的详情细节
	 * @param MlfrontUser
	 * @return 
	 */
	@RequestMapping(value="/getLoginUserDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getLoginUserDetail(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//获取session中是否已经登陆
		MlfrontUser loginUser =  (MlfrontUser) session.getAttribute("loginUser");
		Integer userId = loginUser.getUserId();
		//接受信息
		MlfrontUser mlfrontUserReq = new MlfrontUser();
		mlfrontUserReq.setUserId(userId);
		List<MlfrontUser> mlfrontUserResList =mlfrontUserService.selectMlfrontUserByConditionS(mlfrontUserReq);
		
		if(mlfrontUserResList.size()>0){
			MlfrontUser mlfrontUserOne =mlfrontUserResList.get(0);
			return Msg.success().add("resMsg", "查看单条类目的详情细节完毕").add("mlfrontUserOne", mlfrontUserOne);
		}else{
			return Msg.fail().add("resMsg", "查看单条类目的详情细节完毕").add("mlfrontUserOne", null);
		}
	}	
	
	/**
	 * 10.0	zsh 200630
	 * to	toPersonInfoPage列表页面
	 * @return  
	 * */
	@RequestMapping("/toPersonInfoPage")
	public String toPersonInfoPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
	
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		if(ifMobile.equals("1")){
			  return "portal/user/PersonInfo";//手机端
		}else{
			  return "portal/user/PcPersonInfo";//Pc端
		}
	}
	
	/**
	 * 11.0	useOn	0505
	 * to	toCouponPage列表页面
	 * @return jsp 
	 * */
	@RequestMapping("/toCouponPage")
	public String toCouponPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{

		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		if(ifMobile.equals("1")){
			  return "portal/user/myCoupon";//手机端
		}else{
			  return "portal/user/myPcCoupon";//Pc端
		}
	}

	/**
	 * 12.0	useOn	0505
	 * to	tomyOrderPage列表页面
	 * @return jsp
	 * */
	@RequestMapping("/tomyOrderPage")
	public String tommyOrderPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
	
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		if(ifMobile.equals("1")){
			  return "portal/user/myOrderList";//手机端
		}else{
			  return "portal/user/myPcOrderList";//Pc端
		}
	}
	
	/**
	 * 13.0	zsh200722
	 * to	tomyOrderDetailPage
	 * @return  jsp
	 * */
	@RequestMapping("/tomyOrderDetailPage")
	public String tomyOrderDetailPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
	
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		if(ifMobile.equals("1")){
			  return "portal/user/myOrderDetail";//手机端
		}else{
			  return "portal/user/myPcOrderDetail";//Pc端
		}
	}

}
