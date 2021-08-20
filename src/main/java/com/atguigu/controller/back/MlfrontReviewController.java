package com.atguigu.controller.back;

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
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackReviewImg;
import com.atguigu.bean.MlfrontReview;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.common.StartNum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackReviewImgService;
import com.atguigu.service.MlfrontReviewService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.URLLocationUtils;

@Controller
@RequestMapping("/MlfrontReview")
public class MlfrontReviewController {
		
	@Autowired
	MlfrontReviewService mlfrontReviewService;
	
	@Autowired
	MlbackReviewImgService mlbackReviewImgService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	useOn	0505
	 * 后台MlfrontReview列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlfrontReviewPage")
	public String toMlfrontReviewPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/product/mlbackReviewPage";
		}
	}
	
	/**2.0	useOn	0505
	 * 分类MlbackCoupon列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontReviewByPage")
	@ResponseBody
	public Msg getMlfrontReviewByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 30;
			PageHelper.startPage(pn, PagNum);
			List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectMlfrontReviewAll();
			PageInfo page = new PageInfo(mlfrontReviewList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	zsh	20200701
	 * MlfrontReview	initializaReview
	 * @param MlfrontReview
	 * @return
	 */
	@RequestMapping(value="/initializaReview",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaReview(HttpServletResponse rep,HttpServletRequest res){
		
		MlfrontReview mlfrontReview = new MlfrontReview();
		String nowTime = DateUtil.strTime14s();
		mlfrontReview.setReviewCreatetime(nowTime);
		mlfrontReview.setReviewSupercateidstr("1");
		mlfrontReviewService.insertSelective(mlfrontReview);
		return Msg.success().add("resMsg", "Review初始化成功").add("mlfrontReview", mlfrontReview);
	}

	/**3.1	zsh	20200701
	 * MlfrontReview	insert
	 * @param MlfrontReview
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontReview mlfrontReview){
		//接受参数信息
		String nowTime = DateUtil.strTime14s();
		mlfrontReview.setReviewMotifytime(nowTime);
		//获取用户名,判断头像图片
		String reviewUname = mlfrontReview.getReviewUname();
		if((("").equals(reviewUname))||reviewUname==null){
			mlfrontReview.setReviewUname("Megelook Customer");
			reviewUname =mlfrontReview.getReviewUname();
		}
		//3.1.1
		String uimgurl = GetUImgUrlByUname(rep,res,reviewUname);
		mlfrontReview.setReviewUimgurl(uimgurl);
		System.out.println("uimgurl:"+uimgurl);
		String ReviewCreatetime = mlfrontReview.getReviewCreatetime();
		if(ReviewCreatetime ==""){
			mlfrontReview.setReviewCreatetime(nowTime);
		}
		String ReviewConfirmtime = mlfrontReview.getReviewConfirmtime();
		if(ReviewConfirmtime==""){
			mlfrontReview.setReviewConfirmtime(nowTime);
		}
		
		//屏蔽
		String intoReviewMessage = mlfrontReview.getReviewDetailstr();
        String afterMessage = intoReviewMessage.replaceAll("[^\\u0000-\\uFFFF]", "");
        mlfrontReview.setReviewDetailstr(afterMessage);
		//有id,update
		mlfrontReviewService.updateByPrimaryKeySelective(mlfrontReview);
		return Msg.success().add("resMsg", "更新成功");
	}

	/**3.1.1	zsh	20200701
	 * MlfrontReview	GetUImgUrlByUname
	 * @param MlfrontReview
	 */
	private String GetUImgUrlByUname(HttpServletResponse rep,HttpServletRequest res,String reviewUname) {
		
		String contextPathStr = res.getContextPath();
        
        String realPathStr = res.getSession().getServletContext().getRealPath("/");    
        System.out.println("realPathStr:"+realPathStr);
        
        String basePathStr = res.getScheme()+"://"+res.getServerName()+":"+res.getServerPort()+contextPathStr+"/";
        System.out.println("basePathStr:"+basePathStr);
		
//		String pathBig = basePathStr;
		String pathBig = URLLocationUtils.getbasePathStr(rep,res);
		String path="static/upload/img/ReviewUImg/";
		String returnReaUrl = "";
		
		String firstName = reviewUname.substring(0, 1);
		String firstNameUp = firstName.toUpperCase();
		if(firstNameUp.equals("A")){
			returnReaUrl="A.png";
		}else if(firstNameUp.equals("B")){
			returnReaUrl="B.png";
		}else if(firstNameUp.equals("C")){
			returnReaUrl="C.png";
		}else if(firstNameUp.equals("D")){
			returnReaUrl="D.png";
		}else if(firstNameUp.equals("E")){
			returnReaUrl="E.png";
		}else if(firstNameUp.equals("F")){
			returnReaUrl="F.png";
		}else if(firstNameUp.equals("G")){
			returnReaUrl="G.png";
		}else if(firstNameUp.equals("H")){
			returnReaUrl="H.png";
		}else if(firstNameUp.equals("I")){
			returnReaUrl="I.png";
		}else if(firstNameUp.equals("J")){
			returnReaUrl="J.png";
		}else if(firstNameUp.equals("K")){
			returnReaUrl="K.png";
		}else if(firstNameUp.equals("L")){
			returnReaUrl="L.png";
		}else if(firstNameUp.equals("M")){
			returnReaUrl="M.png";
		}else if(firstNameUp.equals("N")){
			returnReaUrl="N.png";
		}else if(firstNameUp.equals("O")){
			returnReaUrl="O.png";
		}else if(firstNameUp.equals("P")){
			returnReaUrl="P.png";
		}else if(firstNameUp.equals("Q")){
			returnReaUrl="Q.png";
		}else if(firstNameUp.equals("R")){
			returnReaUrl="R.png";
		}else if(firstNameUp.equals("S")){
			returnReaUrl="S.png";
		}else if(firstNameUp.equals("T")){
			returnReaUrl="T.png";
		}else if(firstNameUp.equals("U")){
			returnReaUrl="U.png";
		}else if(firstNameUp.equals("V")){
			returnReaUrl="V.png";
		}else if(firstNameUp.equals("W")){
			returnReaUrl="W.png";
		}else if(firstNameUp.equals("X")){
			returnReaUrl="X.png";
		}else if(firstNameUp.equals("Y")){
			returnReaUrl="Y.png";
		}else if(firstNameUp.equals("Z")){
			returnReaUrl="Z.png";
		}
		String returnReaUrlAll = pathBig+path+returnReaUrl;
		return returnReaUrlAll;
	}

	/**4.0	useOn	0505
	 * MlfrontReview	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontReview mlfrontReview){
		//接收id信息
		Integer reviewId = mlfrontReview.getReviewId();
		mlfrontReviewService.deleteByPrimaryKey(reviewId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	useOn	0505
	 * 查看单条MlfrontReview的详情细节
	 * @param MlfrontReview
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontReviewDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontReviewDetailById(@RequestBody MlfrontReview mlfrontReview){
		
		Integer reviewId = mlfrontReview.getReviewId();
		//接受信息
		MlfrontReview mlfrontReviewReq = new MlfrontReview();
		mlfrontReviewReq.setReviewId(reviewId);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewById(mlfrontReviewReq);
		MlfrontReview mlfrontReviewOne =mlfrontReviewResList.get(0);
		return Msg.success().add("resMsg", "查看单条优惠券的详情细节完毕")
					.add("mlfrontReviewOne", mlfrontReviewOne);
	}
	
	/**
	 * 6.0	useOn	0505
	 * 查看单条MlfrontReview的详情细节
	 * @param MlfrontReview
	 * @return 
	 */
	@RequestMapping(value="/getMlfrontReviewByProductIdAndPage",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontReviewByProductIdAndPage(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontReview mlfrontReviewReq,HttpSession session) {
		
		//接受信息
		Integer pn = mlfrontReviewReq.getReviewUid();
		if(pn>=1){
			System.out.println("getMlfrontReviewByProductIdAndPage:pn:"+pn);
		}else{
			pn = 1;
		}
		Integer productId = mlfrontReviewReq.getReviewPid();
		//查询该ID下的评论
		MlfrontReview mlfrontReview = new MlfrontReview();
		mlfrontReview.setReviewPid(productId);
		mlfrontReview.setReviewStatus(1);
		//条件查询
		if(mlfrontReviewReq.getSearchType() != null){
			//searchType 1:是否有照片
			if(1 == mlfrontReviewReq.getSearchType()){
				mlfrontReview.setReviewIsPics(1);
			}
		}
		
		List<MlfrontReview> mlfrontReviewResListPage = new ArrayList<MlfrontReview>();
		
		List<MlfrontReview> mlfrontReviewResreturn = new ArrayList<MlfrontReview>();
		
		//分页数据获取完毕
		int PagNum = 5;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(mlfrontReview);
		PageInfo page = new PageInfo(mlfrontReviewResList, PagNum);
		
		//从中获取本次展示的5条信息
		mlfrontReviewResListPage = page.getList();
		MlbackReviewImg mlbackReviewImg = new MlbackReviewImg();
		List<MlbackReviewImg>  mlbackReviewImgList = new ArrayList<MlbackReviewImg>();
		List<List<String>> imgUrlStrListst = new ArrayList<List<String>>();
		//开始对5条信息进行遍历，获取每条的详情照片
		Integer StartIndex = (pn-1)*PagNum;
		Integer lengthAll = mlfrontReviewResListPage.size();
		Integer EndIndex = 0;
		if(StartIndex+5>lengthAll){
			EndIndex = lengthAll;
		}else{
			EndIndex = StartIndex+5;
		}
		
		for(int i=0;i<EndIndex;i++){
			MlfrontReview mlfrontReviewOne = mlfrontReviewResListPage.get(i);
			mlfrontReviewResreturn.add(mlfrontReviewOne);
			Integer reviewId = mlfrontReviewOne.getReviewId();
			mlbackReviewImg.setReviewId(reviewId);
			mlbackReviewImgList =  mlbackReviewImgService.selectMlbackReviewImgByReviewId(reviewId);
			
			String reviewimgUrl ="";
			List<String> imgUrlOneList = new ArrayList<String>();
			for(MlbackReviewImg mlbackReviewImgOne:mlbackReviewImgList){
				reviewimgUrl = mlbackReviewImgOne.getReviewimgUrl();
				imgUrlOneList.add(reviewimgUrl);
			}
			imgUrlStrListst.add(imgUrlOneList);
		}
		//return 分页信息pageInfo,5条评论内容，5条评论中的图片
		return Msg.success().add("pageInfo", page).add("imgUrlStrListst", imgUrlStrListst);
	}
	
	/**7.0	useOn	0505
	 * review的汇总接口
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlfrontReviewCount",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlfrontReviewCount(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontReview mlfrontReview){
		//封装参数
		mlfrontReview.setReviewStatus(1);
		//封装完毕
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(mlfrontReview);
		//评论总数
		Integer allReviewNum = mlfrontReviewResList.size();
		//各个星级评论数	
		List<StartNum> StartNumList =  staryStartDetail(mlfrontReviewResList);
		return Msg.success().add("StartNumList", StartNumList).add("allReviewNum", allReviewNum);
	}
	
	/**7.1	useOn	0505
	 * 分类StartNum
	 * @param pn
	 * @return
	 */
	private List<StartNum> staryStartDetail(List<MlfrontReview> mlfrontReviewList) {
		List<StartNum> startNumList = new ArrayList<StartNum>();
		StartNum StartNumOne = new StartNum(1,0);
		StartNum StartNumTwo = new StartNum(2,0);
		StartNum StartNumThree = new StartNum(3,0);
		StartNum StartNumFour = new StartNum(4,0);
		StartNum StartNumFive = new StartNum(5,0);
		for(MlfrontReview mlfrontReview :mlfrontReviewList){
			Integer starnum = mlfrontReview.getReviewProstarnum();//这个是产品评分
			Integer temp = 0;
			if(starnum==1){
				temp = StartNumOne.getStartCount();
				StartNumOne.setStartCount(temp+1);
			}else if(starnum==2){
				temp = StartNumTwo.getStartCount();
				StartNumTwo.setStartCount(temp+1);
			}else if(starnum==3){
				temp = StartNumThree.getStartCount();
				StartNumThree.setStartCount(temp+1);
			}else if(starnum==4){
				temp = StartNumFour.getStartCount();
				StartNumFour.setStartCount(temp+1);
			}else if(starnum==5){
				temp = StartNumFive.getStartCount();
				StartNumFive.setStartCount(temp+1);
			}
		}
		startNumList.add(StartNumOne);
		startNumList.add(StartNumTwo);
		startNumList.add(StartNumThree);
		startNumList.add(StartNumFour);
		startNumList.add(StartNumFive);
		return startNumList;
	}
	
	/**8.0	useOn	0505
	 * MlfrontReview	insert
	 * @param MlfrontReview
	 */
	@RequestMapping(value="/saveNew",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveNewSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlfrontReview mlfrontReview){
		
		Integer Pid = mlfrontReview.getReviewPid();
		//接受参数信息
		System.out.println("mlfrontReview:"+mlfrontReview);
		String nowTime = DateUtil.strTime14s();
		mlfrontReview.setReviewMotifytime(nowTime);
		mlfrontReview.setReviewCreatetime(nowTime);
		mlfrontReview.setReviewConfirmtime(nowTime);
		mlfrontReview.setReviewStatus(0);//0待审核状态1审核完毕
		//插入本条null的reviewid
		mlfrontReviewService.insertSelective(mlfrontReview);
		//查回来插入的这一条
		MlfrontReview MlfrontReviewReq = new MlfrontReview();
		MlfrontReviewReq.setReviewPid(Pid);
		MlfrontReviewReq.setReviewStatus(0);
		List<MlfrontReview> mlfrontReviewResList =mlfrontReviewService.selectMlfrontReviewListByPId(MlfrontReviewReq);
		MlfrontReview mlfrontReviewOne = mlfrontReviewResList.get(0);
		//查回来插入的这一条，返回前台
		return Msg.success().add("resMsg", "插入成功").add("mlfrontReviewOne", mlfrontReviewOne);
		
	}
	
	/**9.0	useOn	0505
	 * MlfrontReview	delete
	 * @param id
	 */
	@RequestMapping(value="/deleteNew",method=RequestMethod.POST)
	@ResponseBody
	public Msg deleteNew(@RequestBody MlfrontReview mlfrontReview){
		//接收id信息,删除本条主评论
		Integer reviewId = mlfrontReview.getReviewId();
		mlfrontReviewService.deleteByPrimaryKey(reviewId);
		mlbackReviewImgService.deleteByreviewId(reviewId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	
	/**10.0	useOn	0505
	 * 分类MlfrontReview列表分页list数据
	 * @param pn,
	 * Integer reviewPid;
	 * Integer reviewStatus;
	 * Integer reviewProstarnum;
	 * Integer reviewFrom;
	 * String reviewStarttime;
	 * String reviewEndtime;
	 * @return
	 */
	@RequestMapping(value="/selectMlblackReviewListBySearch",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectMlfrontReviewListBySearch(@RequestBody MlfrontReview mlfrontReviewInfo,HttpSession session) {
		
		//传进来		页码,行业,归属pid,状态,几级,来源,开始时间,结束时间,
		Integer pn = mlfrontReviewInfo.getReviewUid();
		String reviewSupercateidstr = mlfrontReviewInfo.getReviewSupercateidstr();
		Integer reviewPid = mlfrontReviewInfo.getReviewPid();
		Integer reviewStatus = mlfrontReviewInfo.getReviewStatus();
		Integer reviewProstarnum = mlfrontReviewInfo.getReviewProstarnum();
		Integer reviewFrom = mlfrontReviewInfo.getReviewFrom();
		String reviewStarttime = mlfrontReviewInfo.getReviewCreatetime();
		String reviewEndtime = mlfrontReviewInfo.getReviewMotifytime();
		
		String nowTime = DateUtil.strTime14s();
		MlfrontReview mlfrontReviewReq = new MlfrontReview();
		//写入时间
		if(reviewStarttime.length()>0){
			mlfrontReviewReq.setReviewCreatetime(reviewStarttime);
		}else{
			mlfrontReviewReq.setReviewCreatetime(nowTime);
		}
		if(reviewEndtime.length()>0){
			mlfrontReviewReq.setReviewMotifytime(reviewEndtime);
		}else{
			mlfrontReviewReq.setReviewMotifytime(nowTime);
		}
		//如果没传,默认0
		if(("".equals(reviewSupercateidstr))||("0".equals(reviewSupercateidstr))||reviewSupercateidstr==null){
			mlfrontReviewReq.setReviewSupercateidstr("1");
		}else{
			mlfrontReviewReq.setReviewSupercateidstr(reviewSupercateidstr);
		}
		//如果没传,默认0
		if(reviewPid==0){
			System.out.println("reviewPid==0");
		}else{
			mlfrontReviewReq.setReviewPid(reviewPid);
		}
		//如果没传,默认0
		if(reviewProstarnum==0){
			System.out.println("reviewProstarnum==0");
		}else{
			mlfrontReviewReq.setReviewProstarnum(reviewProstarnum);
		}
		//如果没传,默认999
		if(reviewStatus==999){
			System.out.println("reviewStatus==999");
		}else{
			mlfrontReviewReq.setReviewStatus(reviewStatus);
		}
		//如果没传,默认999
		if(reviewFrom==999){
			System.out.println("reviewFrom==999");
		}else{
			mlfrontReviewReq.setReviewFrom(reviewFrom);
		}
		//如果没传,默认999
		if(pn>=1){
			System.out.println("getMlfrontReviewByProductIdAndPage:pn:"+pn);
		}else{
			pn = 1;
		}
		int PagNum = Const.PAGE_NUM_CATEGORY;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectMlbackReviewListBySearch(mlfrontReviewReq);
		
		PageInfo page = new PageInfo(mlfrontReviewList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**11.0	onuse	200104
	 * search review From Ins
	 */
	@RequestMapping(value="/selectReviewListFrom",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectReviewListFrom(HttpSession session,@RequestBody MlfrontReview mlfrontReview) {
		
		Integer reviewFrom = mlfrontReview.getReviewFrom();
		
		MlfrontReview mlfrontReviewReq = new MlfrontReview();
		mlfrontReviewReq.setReviewStatus(1);
		mlfrontReviewReq.setReviewFrom(reviewFrom);
		List<MlfrontReview> mlfrontReviewList = mlfrontReviewService.selectReviewListFrom(mlfrontReviewReq);
		
		MlbackReviewImg mlbackReviewImgOneReq = new MlbackReviewImg();
		List<List<MlbackReviewImg>> mlfrontReviewImgList = new ArrayList<List<MlbackReviewImg>>();
		
		List<MlbackReviewImg> mlfrontReviewImgFirstList = new ArrayList<MlbackReviewImg>();
		for(int i=0;i<mlfrontReviewList.size();i++){
			Integer reviewId = mlfrontReviewList.get(i).getReviewId();
			mlbackReviewImgOneReq.setReviewId(reviewId);
			mlbackReviewImgOneReq.setReviewimgSortOrder(1);
			mlfrontReviewImgFirstList = mlbackReviewImgService.selectMlbackReviewImgByRIdAndImgSort(mlbackReviewImgOneReq);
			mlfrontReviewImgList.add(mlfrontReviewImgFirstList);
		}
		return Msg.success().add("mlfrontReviewList", mlfrontReviewList).add("mlfrontReviewImgList", mlfrontReviewImgList);
	}
	
	/**
	 * 12.0	onuse	200108
	 * Customer Review page
	 * @param jsp
	 * @return
	 * */
	@RequestMapping("/toReviewCustomer")
	public String toReviewCustomer(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		
		return "portal/review/reviewCustomerListPage";
	}
	
	/**
	 * 13.0	onuse	200104
	 * Ins Review page
	 * @param jsp
	 * @return
	 * */
	@RequestMapping("/toReviewInsPage")
	public String toReviewInsPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session) throws Exception{
		  
		return "portal/review/reviewInsListPage";
	}
	
}
