package com.atguigu.controller.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.atguigu.bean.CouponAnalysisDate;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.bean.MlbackSearch;
import com.atguigu.bean.MlfrontCouponLuckyDraw;
import com.atguigu.bean.MlfrontUser;
import com.atguigu.bean.UrlCount;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.CouponAnalysisDateService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackCouponService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlfrontCouponLuckyDrawService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.service.UrlCountService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.LuckDrawUtils;
import com.atguigu.vo.LuckDrawDate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/MlbackCoupon")
public class MlbackCouponController {
		
	@Autowired
	MlbackCouponService mlbackCouponService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	UrlCountService urlCountService;
	
	@Autowired
	CouponAnalysisDateService couponAnalysisDateService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlfrontCouponLuckyDrawService mlfrontCouponLuckyDrawService;
	
	/**
	 * 1.0	zsh	1225
	 * to??????MlbackCoupon????????????
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackCouponPage")
	public String toMlbackCouponPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers????????????
			return "back/mlbackAdminLogin";
		}else{
			return "back/operate/mlbackCouponPage";
		}
	}
	
	/**2.0	useOn	0505
	 * ??????MlbackCoupon????????????list??????
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackCouponByPage")
	@ResponseBody
	public Msg getMlbackCouponByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers????????????
//			return Msg.fail().add("resMsg", "session???adminuser????????????");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackCoupon> mlbackCouponList = mlbackCouponService.selectMlbackCouponGetAll();
			PageInfo page = new PageInfo(mlbackCouponList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**2.0	useOn	0505
	 * ??????MlbackCoupon????????????list?????? ??????????????????
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackCouponByPageOrderByStatus")
	@ResponseBody
	public Msg getMlbackCouponByPageOrderByStatus(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers????????????
//			return Msg.fail().add("resMsg", "session???adminuser????????????");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackCoupon> mlbackCouponList = mlbackCouponService.selectMlbackCouponOrderByStatus();
			PageInfo page = new PageInfo(mlbackCouponList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	20200608
	 * MlbackProduct	initializaCategory
	 * @param MlbackProduct
	 * @return
	 */
	@RequestMapping(value="/initializaCoupon",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaCoupon(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackCoupon mlbackCoupon = new MlbackCoupon();
		//??????id
		String nowTime = DateUtil.strTime14s();
		mlbackCoupon.setCouponCreatetime(nowTime);
		mlbackCoupon.setCouponProductonlyType(0);
		mlbackCoupon.setCouponProductonlyPidstr("");
		//???idinsert
		System.out.println("?????????"+mlbackCoupon.toString());
		mlbackCouponService.insertSelective(mlbackCoupon);
		System.out.println("?????????"+mlbackCoupon.toString());
		return Msg.success().add("resMsg", "Coupon???????????????").add("mlbackCoupon", mlbackCoupon);
	}
	
	/**3.1	zsh	201226
	 * MlbackCoupon	insert
	 * @param MlbackCoupon
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCoupon mlbackCoupon){
		//??????????????????
		//System.out.println("mlbackCoupon:"+mlbackCoupon);
		String nowTime = DateUtil.strTime14s();
		mlbackCoupon.setCouponMotifytime(nowTime);
		//??????????????????????????????
		Integer couponProductOnlyType = mlbackCoupon.getCouponProductonlyType();
		
		if(couponProductOnlyType==null){
			mlbackCoupon.setCouponProductonlyType(0);
		}
		if(couponProductOnlyType==0){
			mlbackCoupon.setCouponProductonlyPidstr("");
		}
		if(couponProductOnlyType==1){
			System.out.println("????????????,????????????");
		}
		if(couponProductOnlyType==2){
			String cateIdsStr = mlbackCoupon.getCouponApplyCateidstr();
			String prosStrFromApplyCateidstr  = getProIdStr(cateIdsStr);
	        mlbackCoupon.setCouponProsFromApplyCateidstr(prosStrFromApplyCateidstr);
		}
		if(couponProductOnlyType==3){
			//????????????????????????-???????????????
			String allExceptPidstr = mlbackCoupon.getCouponAllExceptPidstr();
			System.out.println("allExceptPidstr:"+allExceptPidstr.toString());
			String allExceptPseostr = mlbackCoupon.getCouponAllExceptPseostr();
			System.out.println("allExceptPseostr:"+allExceptPseostr.toString());
		}
		//???id,update
		mlbackCouponService.updateByPrimaryKeySelective(mlbackCoupon);
		System.out.println("????????????:CouponService.updateByPrimaryKeySelective:"+mlbackCoupon.toString());
		//1??????????????????/??????????????????/???????????????/
		//2???????????????????????????,???1???????????????????????????-??????????????????
		//3???????????????/????????????-???2??????????????????????????????
		//4??????????????????
		return Msg.success().add("resMsg", "????????????");
	}
	//save???????????????
	private String getProIdStr(String cateIdsStr) {
		//1.x??????cate???id???,?????????????????????proid
		String cateIdsStrArr [] =cateIdsStr.split(",");
		String cateidStr ="";
		Integer cateidInt =0;
		List<String> cateProIdStrList = new ArrayList<String>(); 
		for(int i=0;i<cateIdsStrArr.length;i++){
			//2.x????????????????????????cateid
			cateidStr = cateIdsStrArr[i];
			cateidInt = Integer.parseInt(cateidStr);
			MlbackCategory mlbackCategoryReq = new MlbackCategory();
			mlbackCategoryReq.setCategoryId(cateidInt);
			List<MlbackCategory> mlbackCategoryResList =mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
			MlbackCategory mlbackCategoryOne =mlbackCategoryResList.get(0);
			//2.1.x?????????????????????seo?????????prostr??????
			String cateProidStr = mlbackCategoryOne.getCategoryProductIds();
			cateProIdStrList.add(cateProidStr);
		}
		//????????????seo??????,??????proStr??????,???????????????
		Map<Integer, Integer> proIdStrMap = new HashMap<>();
		for(int j=0;j<cateProIdStrList.size();j++){
			String proIdsStr = cateProIdStrList.get(j);
			String proIdsStrArr [] =proIdsStr.split(",");
			
			String pidStr ="";
			Integer pidInt=0;
			for(int k=0;k<proIdsStrArr.length;k++){
				//2.x????????????????????????cateid
				pidStr = proIdsStrArr[k];
				if("".equals(pidStr)){
					//???
				}else if(pidStr==null){
					//???
				}else{
					pidInt = Integer.parseInt(pidStr);
					//??????????????????
					proIdStrMap.put(pidInt, pidInt);
				}
			}
		}
		List<Integer> proIdStrList = new ArrayList(proIdStrMap.values());
		
		Integer[] proIdStrListArr = new Integer[proIdStrList.size()];
		//??????
		proIdStrList.toArray(proIdStrListArr);
		int temp;//????????????????????????
        for(int m=0;m<proIdStrListArr.length-1;m++){//????????????
            for(int n=0;n<proIdStrListArr.length-m-1;n++){
                if(proIdStrListArr[n+1]<proIdStrListArr[n]){
                    temp = proIdStrListArr[n];
                    proIdStrListArr[n] = proIdStrListArr[n+1];
                    proIdStrListArr[n+1] = temp;
                }
            }
        }
        String prosStrFromApplyCateidstr = "";
        String proidStrFinal ="";
        for(int x = 0;x<proIdStrListArr.length;x++){
        	Integer proidInt = proIdStrListArr[x];
        	String proidStr = proidInt+",";
        	proidStrFinal+=proidStr;
        }
        
        if(proidStrFinal.length()>0){
        	prosStrFromApplyCateidstr=proidStrFinal.substring(0,proidStrFinal.length()-1);
		}
		//2.2.x?????????pidstr?????????pid?????????,????????????,??????
		//2.3.x????????????pid????????????list,??????,?????????????????????
        return prosStrFromApplyCateidstr;
	}

	/**4.0	useOn	0505
	 * MlbackCoupon	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackCoupon mlbackCoupon){
		//??????id??????
		int couponIdInt = mlbackCoupon.getCouponId();
		int intResult = mlbackCouponService.deleteByPrimaryKey(couponIdInt);
		System.out.println("????????????:MlbackCoupon,delete success+intResult:"+intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	useOn	0505
	 * ?????????Coupon??????
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDetailById(@RequestParam(value = "couponId") Integer couponId){
		
		//????????????
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponId(couponId);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCoupon(mlbackCouponReq);
		MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
		return Msg.success().add("resMsg", "?????????Coupon??????")
					.add("mlbackCouponOne", mlbackCouponOne);
	}
	
	/**
	 * 7.0	20200608
	 * ????????????backSearchByProduct??????list
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByCoupon",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByCoupon(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "couponName") String couponName) throws Exception{
		
		//???????????????????????????
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponName(couponName);
		List<MlbackCoupon> mlbackCouponResList = mlbackCouponService.selectMlbackCouponBackSearch(mlbackCouponReq);
		PageInfo page = new PageInfo(mlbackCouponResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 6.0	zsh	201225
	 * ???????????????Code-?????????Coupon??????
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDetailByCode",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDetailByCode(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCoupon mlbackCoupon){
	    
	    String couponCode = mlbackCoupon.getCouponCode();
	    //????????????
	    MlbackCoupon mlbackCouponReq = new MlbackCoupon();
	    mlbackCouponReq.setCouponCode(couponCode);
	    List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponBYCode(mlbackCouponReq);
	    MlbackCoupon mlbackCouponOne = null;
	    /**
	     * ?????????-?????????-??????????????????
	     * 		1.x?????????,????????????
	     *  	2.x????????????,??????
	     * 		3.x?????????,??????
	     * 		4.x??????????????????,??????
	     * 		5.0??????-??????
	     * */
	    if(mlbackCouponResList.size()>0){
	      //1??????????????????????????????	
	      mlbackCouponOne =mlbackCouponResList.get(0);
	      //2????????????????????????????????????
	      Integer couponStatus = mlbackCouponOne.getCouponStatus();
	      if(couponStatus==0){
	    	  //????????????????????????
	    	  //2.x????????????,??????,
	    	  return Msg.success().add("resMsg", "The coupon is no longer valid.").add("mlbackCouponOne", null);
	      }
	      //3??????????????????????????????
	      String endtime = mlbackCouponOne.getCouponEndtime();
	      String nowtime = DateUtil.strTime14s();
	      Integer IfUse=endtime.compareTo(nowtime);
	      if(!(IfUse>0)){
	    	  //3.x?????????,??????
	    	  return Msg.success().add("resMsg", "The coupon has expired.").add("mlbackCouponOne", null);
	      }
	      //4????????????????????????????????????
	      Integer couponAlltimes = mlbackCouponOne.getCouponAlltimes();
	      Integer couponTimes = mlbackCouponOne.getCouponTimes();
	      if(!(couponTimes<couponAlltimes)){
	    	  //4.x??????????????????,??????
	    	  return Msg.success().add("resMsg", "The number of times the coupon has been used is full.").add("mlbackCouponOne", null);
	      }
	      return Msg.success().add("resMsg", "getOneMCouponDetailByCode??????").add("mlbackCouponOne", mlbackCouponOne);
	    }else{
	    	//1.x?????????,????????????
	    	return Msg.success().add("resMsg", "This coupon does not exist, please check.").add("mlbackCouponOne", null);
	    }
	  }
	
	/**
	 * 7.0	useOn	0505
	 * ????????????????????????????????????
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDetailByUId",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDetailByUId(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		Integer userType= 0;
		MlfrontUser mlfrontUserLogin  = (MlfrontUser) session.getAttribute("loginUser");
		List<MlbackCoupon> mlbackCouponReturnList = new ArrayList<MlbackCoupon>();
		if(mlfrontUserLogin==null){
			//???????????????
			userType =0;
			return Msg.success().add("resMsg", "??????????????????????????????????????????").add("userType", userType);
		}else{
			//????????????
			userType =1;
			MlfrontUser mlfrontUserreq =new MlfrontUser();
			String userEmail = mlfrontUserLogin.getUserEmail();
			mlfrontUserreq.setUserEmail(userEmail);
			List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
			if(mlfrontUserList.size()>0){
				//???????????????????????????,????????????
				//0????????????resMsg
				MlfrontUser mlfrontUserres =mlfrontUserList.get(0);
				String couponidstr = mlfrontUserres.getUserCouponidstr();
				
				String couponidstrArr[] = couponidstr.split(",");
				String couponIdStr =""; 
				Integer couponIdInt =0;
				
				for(int i=0;i<couponidstrArr.length;i++){
					couponIdStr = couponidstrArr[i];
					couponIdInt = Integer.parseInt(couponIdStr);
					//??????couponId,????????????,??????????????????????????????
					MlbackCoupon mlbackCouponReq = new MlbackCoupon();
					mlbackCouponReq.setCouponId(couponIdInt);
					List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCoupon(mlbackCouponReq);
					MlbackCoupon mlbackCouponOne =mlbackCouponResList.get(0);
					mlbackCouponReturnList.add(mlbackCouponOne);
				}
				return Msg.success().add("resMsg", "??????????????????????????????????????????").add("mlbackCouponReturnList", mlbackCouponReturnList).add("userType", userType);
			}else{
				userType =0;
				return Msg.success().add("resMsg", "??????????????????????????????????????????").add("userType", userType);
			}
		}
	}

	/**
	 * 8.0	zsh200728
	 * ?????????????????????????????????
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/getMlbackCouponShowByLuckDrawType",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackCouponShowByLuckDrawType(HttpServletResponse rep,HttpServletRequest res){
		
		//????????????
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponLuckdrawType(1);
		List<MlbackCoupon> mlbackCouponResList =mlbackCouponService.selectMlbackCouponByLuckDrawType(mlbackCouponReq);
		
		LuckDrawDate luckDrawDate =new LuckDrawDate();
		try {
			luckDrawDate = LuckDrawUtils.getLuckDrawDate(mlbackCouponResList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Msg.success().add("resMsg", "????????????????????????????????????").add("mlbackCouponResList", mlbackCouponResList).add("luckDrawDate", luckDrawDate);
	}
	
	/**
	 * 9.0	zsh200728
	 * ???????????????,getMlbackCouponShowByLuckDrawType,??????8???????????????????????????,
	 * ????????????,???????????????????????????.
	 * ????????????,checkCouponLuckDrawResultAndUserEmail.
	 * ??????????????????.getCouponLuckDrawResultAndUserEmail.
	 * ?????????????????????????????????????????????,????????????????????????????????????.???????????????.
	 * ???????????????XXXXX,3s???????????????.????????????????????????????????????.--????????????email+couponId????????????.
	 * ?????????????????????,??????????????????.
	 * @param MlbackCoupon
	 * @return 
	 */
	@RequestMapping(value="/checkCouponLuckDrawResultAndUserEmail",method=RequestMethod.POST)
	@ResponseBody
	public Msg cheakCouponLuckDrawResultAndUserEmail(@RequestParam(value = "couponId") String couponId,
			@RequestParam(value = "userEmail") String userEmail,HttpSession session){
		
		//????????????,??????email,?????????????????????
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		Integer emailIsCan = 0;//???????????????????????????,?????????????????????

		if(mlfrontUserList.size()>0){
			//?????????????????????,???????????????????????????????????????
			MlfrontUser mlfrontUserres = mlfrontUserList.get(0);
			String couponidstr= mlfrontUserres.getUserCouponidstr();
			
			String[] couponidArr = couponidstr.split(",");
			
			for(int i=0;i<couponidArr.length;i++){
				String couponOne = couponidArr[i];
				if(couponOne.equals(couponId)){
					emailIsCan = 0;
					break;
				}
			}
			if(emailIsCan==1){
				//???????????????????????????????????????
				return Msg.success().add("resMsg", "????????????????????????????????????").add("emailIsCan", emailIsCan);
			}else{
				//????????????????????????,?????????
				return Msg.success().add("resMsg", "????????????????????????,?????????").add("emailIsCan", emailIsCan);
			}
		}else{
			//??????????????????,????????????
			return Msg.success().add("resMsg", "??????????????????????????????").add("emailIsCan", emailIsCan);
		}
	}
	
	/**
	 * 10.0	zsh200728
	 * ?????????????????????,???????????????,??????????????????
	 * @param MlbackCoupon
	 * @return
	 */
	@RequestMapping(value="/getCouponLuckDrawResultAndUserEmail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getCouponLuckDrawResultAndUserEmail(@RequestParam(value = "couponId") String couponId,
			@RequestParam(value = "userEmail") String userEmail,HttpSession session){
		
		//????????????,??????email,?????????????????????
		
		//??????????????????????????????,
		//1,???????????????,??????????????????,????????????????????????,????????????
		//2,????????????,???????????????????????????,????????????
		
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		List<MlfrontUser> mlfrontUserList= mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		
		String nowtime = DateUtil.strTime14s();
		
		//???????????????????????????,???????????????????????????,
		if(mlfrontUserList.size()>0){
			//2,????????????,???????????????????????????????????????,????????????????????????????????????,????????????
			
			MlfrontUser mlfrontUserres = mlfrontUserList.get(0);
			String couponidstr= mlfrontUserres.getUserCouponidstr();
			
			String[] couponidArr = couponidstr.split(",");
			
			Integer isHave = 0; 
			
			for(int i=0;i<couponidArr.length;i++){
				String couponOne = couponidArr[i];
				if(couponOne.equals(couponId)){
					isHave = 1;
					break;
				}
			}
			if(isHave==1){
				//???????????????????????????
				System.out.println("??????????????????????????????,?????????");
			}else{
				couponidstr = couponidstr+","+couponId;
				
				//??????????????????????????????????????????
				mlfrontUserres.setUserCouponidstr(couponidstr);
				
			}
			mlfrontUserreq.setUserMotifytime(nowtime);
			mlfrontUserreq.setUserLastonlinetime(nowtime);
			mlfrontUserService.updateByPrimaryKeySelective(mlfrontUserres);
			
			session.setAttribute("loginUser", mlfrontUserres);
		}else{
			//1,???????????????,??????????????????,????????????????????????,????????????
			mlfrontUserreq.setUserEmail(userEmail);
			mlfrontUserreq.setUserPassword(userEmail);
			String couponidstr = "1,2,3,"+couponId+"";
			//???????????????????????????
			mlfrontUserreq.setUserCouponidstr(couponidstr);
			
			mlfrontUserreq.setUserCreatetime(nowtime);
			mlfrontUserreq.setUserMotifytime(nowtime);
			mlfrontUserreq.setUserLastonlinetime(nowtime);
			mlfrontUserreq.setUserViplevel(0);
			mlfrontUserreq.setUserTimes(0);
			
			//??????????????????
			mlfrontUserService.insertSelective(mlfrontUserreq);

			session.setAttribute("loginUser", mlfrontUserreq);
		}
		//session???????????????????????????,????????????????????????????????????
		
		//????????????????????????????????????mlfrontCouponLuckyDraw??????????????????????????????session?????????????????????
		String sessionId = session.getId();
		insertMlfrontCouponLuckyDraw(sessionId,couponId,userEmail);
		
		return Msg.success().add("resMsg", "????????????????????????????????????,??????????????????");
		
	}
	/**
	 * ????????????????????????????????????mlfrontCouponLuckyDraw??????
	 * ???????????????????????????session?????????????????????
	 * 
	 * @param sessionId ??????sessionId
	 * @param couponId  ???????????????id
	 * @param userEmail ????????????
	 */
	private void insertMlfrontCouponLuckyDraw(String sessionId, String couponId, String userEmail) {
		
		//??????MlfrontCouponLuckyDraw
		MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw = new MlfrontCouponLuckyDraw();
		mlfrontCouponLuckyDraw.setCouponluckydrawUseremail(userEmail);//??????
		mlfrontCouponLuckyDraw.setCouponluckydrawCouponid(Integer.parseInt(couponId));//?????????id
		String nowtime = DateUtil.strTime14s();
		mlfrontCouponLuckyDraw.setCouponluckydrawCreatetime(nowtime);
		//sessionId
		mlfrontCouponLuckyDraw.setCouponluckydrawSessionid(sessionId);
		
		//????????????
		MlfrontUser mlfrontUserreq = new MlfrontUser();
		mlfrontUserreq.setUserEmail(userEmail);
		List<MlfrontUser> mlfrontUserList = mlfrontUserService.selectMlfrontUserWhenFirst(mlfrontUserreq);
		if(mlfrontUserList.size()>0){
			mlfrontCouponLuckyDraw.setCouponluckydrawUserid(mlfrontUserList.get(0).getUserId());//uId
		}
		
		//???????????????
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponId(Integer.parseInt(couponId));
		List<MlbackCoupon> mlbackCouponResList = mlbackCouponService.selectMlbackCoupon(mlbackCouponReq);
		if(mlbackCouponResList.size()>0){
			MlbackCoupon mlbackCoupon = mlbackCouponResList.get(0);
			mlfrontCouponLuckyDraw.setCouponluckydrawCouponcode(mlbackCoupon.getCouponCode());
			mlfrontCouponLuckyDraw.setCouponluckydrawCouponname(mlbackCoupon.getCouponName());
			mlfrontCouponLuckyDraw.setCouponluckydrawCouponprice(mlbackCoupon.getCouponPrice());
			mlfrontCouponLuckyDraw.setCouponluckydrawCouponpricebaseline(mlbackCoupon.getCouponPriceBaseline());
			mlfrontCouponLuckyDraw.setCouponluckydrawCouponpriceoff(mlbackCoupon.getCouponPriceoff());
			mlfrontCouponLuckyDraw.setCouponluckydrawCoupontype(mlbackCoupon.getCouponType());
		}
		
		//??????????????????
		mlfrontCouponLuckyDrawService.insertSelective(mlfrontCouponLuckyDraw);
		
	}

	/**
	 * 11.0	zsh	201228
	 * to??????MlbackCoupon????????????
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toCouponAnalysisPage")
	public String toCouponCaclPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers????????????
			return "back/mlbackAdminLogin";
		}else{
			return "back/operate/mlbackCouponAnalysisPage";
		}
	}
	
	/**
	 * 12.0	20201207
	 * ?????????????????????????????????????????????MlbackSearch/getSearchListByTime
	 * @param	MlbackSearch
	 * @return
	 */
	@RequestMapping(value="/getSearchListByTime",method=RequestMethod.POST)
	@ResponseBody
	public Msg getSearchListByTime(HttpSession session,@RequestBody MlbackSearch mlbackSearch) {
		
		String starttime = mlbackSearch.getSearchCreatetime();
		String endtime = mlbackSearch.getSearchMotifytime();
		UrlCount urlCountReq = new UrlCount();
		urlCountReq.setSearchStartTime(starttime);
		urlCountReq.setSearchEndTime(endtime);
		
		List<UrlCount> urlCountList = urlCountService.selectCouponCountByTime(urlCountReq);
		
		return Msg.success().add("urlCountList", urlCountList);
	}
	
	/**
	 * 13.1	zsh	20201207
	 * to??????MlbackCoupon????????????
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toCouponUsedDetailListPage")
	public String toCouponUsedDetailListPage() throws Exception{
	
		return "back/operate/mlbackCouponUsedDetailListPage";
	}
	
	/**
	 * 13.2	20201207
	 * ????????????????????????????????????????????????????????????????????????qingliang
	 * @param	getCouponUsedDetailListByTime
	 * @return
	 */
	@RequestMapping(value="/getCouponUsedDetailListByTime",method=RequestMethod.POST)
	@ResponseBody
	public Msg getCouponUsedDetailListByTime(HttpSession session,@RequestBody MlbackSearch mlbackSearch) {
		
		String starttime = mlbackSearch.getSearchCreatetime();
		String endtime = mlbackSearch.getSearchMotifytime();
		String couponCode = mlbackSearch.getSearchContent();
		CouponAnalysisDate couponAnalysisDateReq = new CouponAnalysisDate();
		couponAnalysisDateReq.setPayinfoCreatetime(starttime);
		couponAnalysisDateReq.setPayinfoMotifytime(endtime);
		couponAnalysisDateReq.setSearchCouponCode(couponCode);
		
		List<CouponAnalysisDate> CouponAnalysisDateList = couponAnalysisDateService.selectCouponAnalysisList(couponAnalysisDateReq);
		
		return Msg.success().add("CouponAnalysisDateList", CouponAnalysisDateList);
	}
	
	/**
	 * 7.0	20200608
	 * ????????????backSearchByProduct??????list
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByCouponCode",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByCouponCode(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "couponName") String couponName) throws Exception{
		
		//???????????????????????????
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackCoupon mlbackCouponReq = new MlbackCoupon();
		mlbackCouponReq.setCouponName(couponName);//??????
		List<MlbackCoupon> mlbackCouponResList = mlbackCouponService.selectMlbackCouponBackSearch(mlbackCouponReq);
		PageInfo page = new PageInfo(mlbackCouponResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
}
