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
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackSlide;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackSlideService;
import com.atguigu.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/MlbackSlides")
public class MlbackSlidesController {
	
	@Autowired
	MlbackSlideService mlbackSlideService;
		
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	/**
	 * 1.0	20200707
	 * toMlbackSlidePage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackSlidePage")
	public String toMlbackSlidePage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/marketing/mlbackSlidePage";
		}
	}
	
	/**2.0	20200707
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackSlideByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = Const.PAGE_NUM_CATEGORY;
			PageHelper.startPage(pn, PagNum);
			List<MlbackSlide> mlbackSlideList = mlbackSlideService.selectMlbackSlideGetAll();
			System.out.println("mlbackSlideList.size:"+mlbackSlideList.size());
			PageInfo page = new PageInfo(mlbackSlideList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**2.1	20200707
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackSlideDownlist")
	@ResponseBody
	public Msg getMlbackSlideDownlist(HttpSession session) {
		List<MlbackSlide> mlbackSlideList = mlbackSlideService.selectMlbackSlideGetAll();
		System.out.println("mlbackSlideList.size:"+mlbackSlideList.size());
		return Msg.success().add("mlbackSlideList", mlbackSlideList);
	}
	
	/**3.0	20200703
	 * MlbackSlide	initializaSlide
	 * @param MlbackSlide
	 * @return
	 */
	@RequestMapping(value="/initializaSlide",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaSlide(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackSlide mlbackSlide = new MlbackSlide();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackSlide.setSlideCreatetime(nowTime);
		mlbackSlide.setSlideWapstatus(0);//0未上架1上架中
		mlbackSlide.setSlidePcstatus(0);//0未上架1上架中
		//无id,insert
		System.out.println("插入前"+mlbackSlide.toString());
		mlbackSlideService.insertSelective(mlbackSlide);
		System.out.println("插入后"+mlbackSlide.toString());
		return Msg.success().add("resMsg", "Catalog初始化成功").add("mlbackSlide", mlbackSlide);
	}
	
	/**3.0	20200707
	 * MlbackActShowPro	save
	 * @param MlbackActShowPro
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受参数信息
		
		//取出slideIfproORcateORpage;	//0pro	1cate	2page
		Integer ifproORcateORpage = mlbackSlide.getSlideIfproorcateorpage();
		if(ifproORcateORpage==0){
			//0pro
			Integer proId = mlbackSlide.getSlideProid();
			
			MlbackProduct mlbackProductReq = new MlbackProduct();
			MlbackProduct mlbackProductRes = new MlbackProduct();
			mlbackProductReq.setProductId(proId);
			List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProductByParam(mlbackProductReq);
			mlbackProductRes = mlbackProductResList.get(0);
			String Pname = mlbackProductRes.getProductName();
			String Pseoname = mlbackProductRes.getProductSeo();
			mlbackSlide.setSlideProname(Pname);//Pseoname
			mlbackSlide.setSlideSeoname(Pseoname);
		}else if(ifproORcateORpage==1){
			//1cate
			Integer cateid = mlbackSlide.getSlideCateid();
			MlbackCategory mlbackCategoryReq = new MlbackCategory();
			MlbackCategory mlbackCategoryRes = new MlbackCategory();
			mlbackCategoryReq.setCategoryId(cateid);
			List<MlbackCategory> mlbackCategoryResList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
			mlbackCategoryRes = mlbackCategoryResList.get(0);
			String cName = mlbackCategoryRes.getCategoryName();
			String cateSeo = mlbackCategoryRes.getCategorySeo();
			mlbackSlide.setSlideCatename(cName);//Pseoname
			mlbackSlide.setSlideCateseoname(cateSeo);
		}else{
			//2page
			String pageSeoname = mlbackSlide.getSlidePageseoname();
			mlbackSlide.setSlidePageseoname(pageSeoname);
		}
		
		//mlbackProductService;
		String nowtime = DateUtil.strTime14s();
		mlbackSlide.setSlideMotifytime(nowtime);
		
		//有id,update
		mlbackSlideService.updateByPrimaryKeySelective(mlbackSlide);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**4.0	20200707
	 * MlbackSlide	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackSlide mlbackSlide){
		//接收SlideId
		Integer slideId = mlbackSlide.getSlideId();
		mlbackSlideService.deleteByPrimaryKey(slideId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	20200707
	 * 查看单条Slide的详情细节
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackSlideDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackSlideDetail(@RequestBody MlbackSlide mlbackSlide){
		
		Integer slideId = mlbackSlide.getSlideId();
		//接受actshowproId
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideId(slideId);
		//查询本条
		MlbackSlide mlbackSlideOne =mlbackSlideService.selectMlbackSlideById(mlbackSlideReq);
		return Msg.success().add("resMsg", "查看单条Slide的详情完毕")
					.add("mlbackSlideOne", mlbackSlideOne);
	}
	
	/**
	 * 6.0	20200707
	 * 查询不同轮播位置的Slide位置的图片
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getMlbackSlidListByArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackSlidListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受slideArea
		Integer slideArea = mlbackSlide.getSlideArea();
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideArea(slideArea);
		//查询wap端这个位置的信息
		mlbackSlideReq.setSlideWapstatus(1);
		List<MlbackSlide> mlbackSlideList =mlbackSlideService.selectMlbackSlidewapListByArea(mlbackSlideReq);
		return Msg.success().add("resMsg", "查看该位置的轮播完毕").add("mlbackSlideList", mlbackSlideList);
	}
	
	/**
	 * 6.0	UseNow	190905
	 * 查询wap不同位置的图片
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getMlbackSlidewapListByArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackSlidewapListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受slideArea
		Integer slideArea = mlbackSlide.getSlideArea();
		
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideArea(slideArea);
		mlbackSlideReq.setSlideWapstatus(1);
		//查询本条
		List<MlbackSlide> mlbackSlideList =mlbackSlideService.selectMlbackSlidewapListByArea(mlbackSlideReq);
		MlbackSlide mlbackSlideOne = new MlbackSlide();
		MlbackProduct mlbackProductOne = new MlbackProduct();
		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
		if(mlbackSlideList.size()>0){
			for(int i=0;i<mlbackSlideList.size();i++){
				mlbackSlideOne = mlbackSlideList.get(i);
				Integer proId = mlbackSlideOne.getSlideProid();
				mlbackProductOne.setProductId(proId);
				mlbackProductOne.setProductStatus(1);
				//List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProductByParam(mlbackProductOne);
//				List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProductSimpleByParam(mlbackProductOne);
				List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProductSimpleByParamOnlyProDateilUsed(mlbackProductOne);
				if(mlbackProductList.size()>0){
					mlbackProductResList.add(mlbackProductList.get(0));
				}
			}
		}
		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
					.add("mlbackSlideList", mlbackSlideList).add("mlbackProductResList", mlbackProductResList);
	}
	
	/**
	 * 7.0	UseNow	190905
	 * 查询pc不同位置的图片
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getMlbackSlidepcListByArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackSlidepcListByArea(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSlide mlbackSlide){
		//接受slideArea
		Integer slideArea = mlbackSlide.getSlideArea();
		MlbackSlide mlbackSlideReq = new MlbackSlide();
		mlbackSlideReq.setSlideArea(slideArea);
		mlbackSlideReq.setSlidePcstatus(1);
		//查询本条
		List<MlbackSlide> mlbackSlideList =mlbackSlideService.selectMlbackSlidepcListByArea(mlbackSlideReq);
		MlbackSlide mlbackSlideOne = new MlbackSlide();
		MlbackProduct mlbackProductOne = new MlbackProduct();
		List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
		if(mlbackSlideList.size()>0){
			for(int i=0;i<mlbackSlideList.size();i++){
				mlbackSlideOne = mlbackSlideList.get(i);
				Integer proId = mlbackSlideOne.getSlideProid();
				mlbackProductOne.setProductId(proId);
				mlbackProductOne.setProductStatus(1);
				//List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProductByParam(mlbackProductOne);
				List<MlbackProduct> mlbackProductList  = mlbackProductService.selectMlbackProductSimpleByParam(mlbackProductOne);
				if(mlbackProductList.size()>0){
					mlbackProductResList.add(mlbackProductList.get(0));
				}
			}
		}
		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
					.add("mlbackSlideList", mlbackSlideList).add("mlbackProductResList", mlbackProductResList);
	}

}
