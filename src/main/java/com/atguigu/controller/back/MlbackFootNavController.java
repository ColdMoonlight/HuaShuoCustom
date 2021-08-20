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
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackFootNav;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackFootNavService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackFootNav")
public class MlbackFootNavController {
		
	@Autowired
	MlbackFootNavService mlbackFootNavService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	UseNow	0505
	 * toMlbackFootNav列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackFootNavPage")
	public String toMlbackFootNavPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/footnav/mlbackFootNavPage";
		}
	}
	
	
	/**2.0	UseNow	0505
	 * 分类MlbackFootNav列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackFootNavByPage")
	@ResponseBody
	public Msg getMlbackFootNavByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackFootNav> mlbackFootNavList = mlbackFootNavService.selectMlbackFootNavAll();
			PageInfo page = new PageInfo(mlbackFootNavList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	onuse	20191225	检查
	 * MlbackFootNav	initializaFootNav
	 * @param MlbackFootNav
	 * @return
	 */
	@RequestMapping(value="/initializaFootNav",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaFootNav(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackFootNav mlbackFootNav = new MlbackFootNav();
		String nowTime = DateUtil.strTime14s();
		mlbackFootNav.setFootnavMotifytime(nowTime);
		mlbackFootNav.setFootnavIfshow(0);//0不展示1展示
		mlbackFootNav.setFootnavSuperCateId(1);
		mlbackFootNav.setFootnavSuperCateName("hair");
		mlbackFootNavService.insertSelective(mlbackFootNav);
		System.out.println("插入后"+mlbackFootNav.toString());
		return Msg.success().add("resMsg", "FootNav初始化成功").add("mlbackFootNav", mlbackFootNav);
	}
	
	/**3.0	UseNow	0505
	 * MlbackFootNav	insert
	 * @param MlbackFootNav
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackFootNav mlbackFootNav){
		//接受参数信息
		//取出id
		Integer footnavId = mlbackFootNav.getFootnavId();
		String nowTime = DateUtil.strTime14s();
		mlbackFootNav.setFootnavMotifytime(nowTime);
		if(footnavId==null){
			//无id，insert
			mlbackFootNav.setFootnavCreatetime(nowTime);
			mlbackFootNavService.insertSelective(mlbackFootNav);
			System.out.println("后台操作:footnavId为null,走add+intResult:"+mlbackFootNav.toString());
			return Msg.success().add("resMsg", "插入成功").add("mlbackFootNav", mlbackFootNav);
		}else{
			//有id，update
			mlbackFootNavService.updateByPrimaryKeySelective(mlbackFootNav);
			System.out.println("后台操作:footnavId不为null,走update+intResult:"+mlbackFootNav.toString());
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	UseNow	0505
	 * MlbackFootNav	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackFootNav mlbackFootNav){
		//接收id信息
		Integer footnavId = mlbackFootNav.getFootnavId();
		int intResult = mlbackFootNavService.deleteByPrimaryKey(footnavId);
		System.out.println("后台操作:MlbackFootNav delete success:"+intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	20200608
	 * 查看单条类目的详情细节
	 * @param MlbackFootNav
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackFootNavOneAllDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackFootNavOneAllDetail(@RequestParam(value = "footnavId") Integer footnavId){
		
		//接受categoryId
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavId(footnavId);
		//查询本条
		List<MlbackFootNav> mlbackFootNavResList =mlbackFootNavService.selectMlbackFootNavOneAllById(mlbackFootNavReq);
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		if(mlbackFootNavResList.size()>0){
			MlbackFootNavOne =mlbackFootNavResList.get(0);
		}else{
			MlbackFootNavOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackFootNavOne的详情细节完毕")
					.add("MlbackFootNavOne", MlbackFootNavOne);
	}
	
	/**
	 * 5.1	20210113
	 * 查看单条类目的详情细节
	 * @param MlbackFootNav
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackFootNavOneAllDetailByFootSeo",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackFootNavOneAllDetailByFootSeo(@RequestParam(value = "footnavSeo") String footnavSeo){
		
		//接受categoryId
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavSeo(footnavSeo);
		//查询本条
		List<MlbackFootNav> mlbackFootNavResList =mlbackFootNavService.selectMlbackFootNavBySeo(mlbackFootNavReq);
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		if(mlbackFootNavResList.size()>0){
			MlbackFootNavOne =mlbackFootNavResList.get(0);
		}else{
			MlbackFootNavOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackFootNavOne的详情细节完毕")
					.add("MlbackFootNavOne", MlbackFootNavOne);
	}
	
	/**
	 * 6.0	20200608
	 * toMlbackFootNav展示页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toFootNavPage",method=RequestMethod.GET)
	public String toFootNavPage(HttpSession session,@RequestParam(value = "footnavId") Integer footnavId,HttpServletResponse rep,HttpServletRequest res) throws Exception{
	
		//接收传递进来的参数
		Integer footnavIdReq = footnavId;
		//放回响应域中
		res.setAttribute("footnavId", footnavIdReq);
		
		session.setAttribute("footnavId", footnavIdReq);

		return "portal/footNavDetailPage";
		
	}
	
	/**
	 * 6.0	20200608
	 * toMlbackFootNav展示页面
	 * @param	JSON
	 * @return 
	 * */
	@RequestMapping(value="/toFootNavDetailByfootSeoPage",method=RequestMethod.GET)
	public ModelAndView toFootNavDetailByfootSeoPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "footnavSeo") String footnavSeo) throws Exception{
		//接收传递进来的参数
		String footnavSeoReq = footnavSeo;
		
		ModelAndView modelAndView = new ModelAndView();
		//放回响应域中
		res.setAttribute("footnavSeo", footnavSeoReq);
		//放回session域中
		session.setAttribute("footnavSeo", footnavSeoReq);
		
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		
		mlbackFootNavReq.setFootnavSeo(footnavSeoReq);
		
		List<MlbackFootNav> mlbackFootNavResList = mlbackFootNavService.selectMlbackFootNavBySeo(mlbackFootNavReq);
		
		if(mlbackFootNavResList.size()>0){
			
			//返回视图
			modelAndView.setViewName("portal/footNavDetailSeoPage");
		}else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	
	/**
	 * 7.0	UseNow	0505
	 * 前台查看list		查看foot全部的接口类目的详情细节
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getMlfrontFootNavAllSimple",method=RequestMethod.POST)
	@ResponseBody
	public Msg selectMlfrontFootNavAllSimpleIfShow(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavIfshow(1);//0不生效	1生效中 
		//查询本条
		List<MlbackFootNav> mlbackFootNavList = mlbackFootNavService.selectMlbackFootNavAllSimpleIfShow(mlbackFootNavReq);
		List<MlbackFootNav> mlbackFootNavOneList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavTwoList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavThreeList = new ArrayList<MlbackFootNav>();
		List<MlbackFootNav> mlbackFootNavFourList = new ArrayList<MlbackFootNav>();
		MlbackFootNav MlbackFootNavOne = new MlbackFootNav();
		
		Integer isNav = 1;
		if(mlbackFootNavList.size()>0){
			isNav = 1;
			MlbackFootNavOne =mlbackFootNavList.get(0);
			for(int i=0;i<mlbackFootNavList.size();i++){
				//获取当前
				MlbackFootNavOne = mlbackFootNavList.get(i);
				if(mlbackFootNavList.get(i).getFootnavLie()==1){
					//第1列
					mlbackFootNavOneList.add(MlbackFootNavOne);
				}else if(mlbackFootNavList.get(i).getFootnavLie()==2){
					//第2列
					mlbackFootNavTwoList.add(MlbackFootNavOne);
				}else if(mlbackFootNavList.get(i).getFootnavLie()==3){
					//第3列
					mlbackFootNavThreeList.add(MlbackFootNavOne);
				}else{
					//第4列
					mlbackFootNavFourList.add(MlbackFootNavOne);
				}
			}
		}else{
			isNav = 0;
		}
		//4footer里请求这个接口,回去的4列
		return Msg.success().add("resMsg", "查看底部导航完毕详情细节完毕").add("isNav", isNav)
				.add("mlbackFootNavOneList", mlbackFootNavOneList).add("mlbackFootNavTwoList", mlbackFootNavTwoList)
				.add("mlbackFootNavThreeList", mlbackFootNavThreeList).add("mlbackFootNavFourList", mlbackFootNavFourList);
	}
	
	/**
	 * 7.0	onuse	20200103	check
	 * 后端获取类下产品list详情页面wap/pc
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByfootNav",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByfootNav(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "footnavName") String footnavName,
			@RequestParam(value = "footnavSuperCateId", defaultValue = "1") Integer footnavSuperCateId) throws Exception{
		
		//接收传递进来的参数
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		MlbackFootNav mlbackFootNavReq = new MlbackFootNav();
		mlbackFootNavReq.setFootnavSuperCateId(footnavSuperCateId);
		mlbackFootNavReq.setFootnavName(footnavName);
		List<MlbackFootNav> mlbackFootNavResList = mlbackFootNavService.selectMlbackFootNavBackSearch(mlbackFootNavReq);
		
		PageInfo page = new PageInfo(mlbackFootNavResList, PagNum);
		
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 1.0	zsh210107
	 * toReturnPolicyPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toReturnPolicyPage")
	public String toReturnPolicyPage(HttpSession session) throws Exception{

		return "portal/policy/returnPolicyPage";
	}
	
	/**
	 * 2.0	zsh210107
	 * toShippingPolicyPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toShippingPolicyPage")
	public String toShippingPolicyPage(HttpSession session) throws Exception{
		
		return "portal/policy/shippingPolicyPage";
	}
	
	/**
	 * 3.0	zsh210107
	 * toPrivacyPolicyPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toPrivacyPolicyPage")
	public String toPrivacyPolicyPage(HttpSession session) throws Exception{

		return "portal/policy/privacyPolicyPage";
	}
	
	/**
	 * 3.0	zsh210406
	 * toTermsConditionsPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toTermsConditionsPage")
	public String toTermsConditionsPage(HttpSession session) throws Exception{

		return "portal/policy/termsConditionsPage";
	}
	
}
