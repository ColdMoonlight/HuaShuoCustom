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
import com.atguigu.bean.MlbackActShowPro;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackPageArea;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlbackSlide;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackActShowProService;
import com.atguigu.service.MlbackCatalogService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackPageAreaService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlbackSlideService;
import com.atguigu.service.MlfrontUserService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.IfMobileUtils;
import com.atguigu.vo.PageAreaDetail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * MlbackPageArea
 * @author
 */
@Controller
@RequestMapping("/MlbackPageArea")
public class MlbackPageAreaController {
	
	@Autowired
	MlfrontUserService mlfrontUserService;
	
	@Autowired
	MlbackPageAreaService mlbackPageAreaService;
	
	@Autowired
	MlbackSlideService mlbackSlideService;
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackActShowProService mlbackActShowProService;
	
	@Autowired
	MlbackCatalogService mlbackCatalogService;
	
	/**
	 * zsh 201014
	 * ?????????????????????
	 * */
	@RequestMapping("/MlbackPageAreaPage")
	public String MlbackPageAreaPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUser????????????
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackPageAreaPage";
		}
	}
	
	/**2.0	zsh 201014
	 * ??????MlbackPageArea????????????list??????
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackPageAreaByPage")
	@ResponseBody
	public Msg getMlbackPageAreaByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		int PagNum = 20;
		PageHelper.startPage(pn, PagNum);
		List<MlbackPageArea> mlbackActShowProList = mlbackPageAreaService.selectMlbackPageAreaGetAll();
		PageInfo page = new PageInfo(mlbackActShowProList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20201014
	 * mlbackPageArea	initializaPageArea
	 * @param mlbackPageArea
	 * @return
	 */
	@RequestMapping(value="/initializaPageArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaPageArea(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackPageArea mlbackPageArea = new MlbackPageArea();
		//??????id
		String nowTime = DateUtil.strTime14s();
		mlbackPageArea.setPageareaCreatetime(nowTime);
		mlbackPageArea.setPageareaStatus(0);//0?????????1?????????
		mlbackPageArea.setPageareaPcstatus(0);//0?????????1?????????
		//???id,insert
		System.out.println("?????????"+mlbackPageArea.toString());
		mlbackPageAreaService.insertSelective(mlbackPageArea);
		System.out.println("?????????"+mlbackPageArea.toString());
		return Msg.success().add("resMsg", "PageArea???????????????").add("mlbackPageArea", mlbackPageArea);
	}
	
	/**4.0	20201014
	 * mlbackPageArea	save
	 * @param mlbackPageArea
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackPageArea mlbackPageArea){
		//??????????????????
		String nowTime = DateUtil.strTime14s();
		mlbackPageArea.setPageareaMotifytime(nowTime);
		//???id,update
		mlbackPageAreaService.updateByPrimaryKeySelective(mlbackPageArea);
		System.out.println("mlbackPageArea-save"+mlbackPageArea.toString());
		return Msg.success().add("resMsg", "????????????");
	}
	
	/**5.0	20201014
	 * mlbackPageArea	delete
	 * @param MlbackPageArea
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackPageArea mlbackPageArea){
		//??????id??????
		Integer pageareaId = mlbackPageArea.getPageareaId();
		mlbackPageAreaService.deleteByPrimaryKey(pageareaId);
		System.out.println("???????????????pageareaId???:"+pageareaId+"?????????");
		return Msg.success().add("resMsg", "delete success");
	}
	/**
	 * 6.0	20201014
	 * ?????????????????????????????????
	 * @param MlbackPageArea
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackPageAreaDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackPageAreaDetail(@RequestBody MlbackPageArea mlbackPageArea){
		
		Integer pageareaId = mlbackPageArea.getPageareaId();
		//??????actshowproId
		MlbackPageArea mlbackPageAreaReq = new MlbackPageArea();
		mlbackPageAreaReq.setPageareaId(pageareaId);
		//????????????
		MlbackPageArea mlbackPageAreaOne =mlbackPageAreaService.selectMlbackPageAreaById(mlbackPageAreaReq);
		return Msg.success().add("resMsg", "???????????????????????????????????????")
					.add("mlbackPageAreaOne", mlbackPageAreaOne);
	}
	
	/**3.0	20200703
	 * MlbackActShowPro	initializaActShowPro
	 * @param MlbackActShowPro
	 * @return
	 */
	@RequestMapping(value="/portalSelectPageArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg portalSelectPageArea(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackPageArea mlbackPageAreaReq = new MlbackPageArea();
		//??????id
		String nowTime = DateUtil.strTime14s();
		mlbackPageAreaReq.setPageareaCreatetime(nowTime);
		
		List<MlbackPageArea> mlbackPageAreaResList = new ArrayList<MlbackPageArea>();
		String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		
		List<List<PageAreaDetail>> pageArealist = new ArrayList<List<PageAreaDetail>>();
		if(ifMobile.equals("1")){
			//??????
			mlbackPageAreaReq.setPageareaStatus(1);
			mlbackPageAreaResList = mlbackPageAreaService.selectHomepageByStatus(mlbackPageAreaReq);
			
			pageArealist = toReturnList(mlbackPageAreaResList,ifMobile);
		}else{
			//pc
			mlbackPageAreaReq.setPageareaPcstatus(1);
			mlbackPageAreaResList = mlbackPageAreaService.selectHomepageByPcStatus(mlbackPageAreaReq);
			pageArealist = toReturnList(mlbackPageAreaResList,ifMobile);
			
		}
		return Msg.success().add("resMsg", "PageArea???????????????").add("pageArealist", pageArealist);
	}

	private List<List<PageAreaDetail>> toReturnList(List<MlbackPageArea> mlbackPageAreaResList,String ifMobile) {
		
		List<List<PageAreaDetail>> pageAreaDetailAllList = new ArrayList<List<PageAreaDetail>>();
		
		for(MlbackPageArea mlbackPageAreaOne:mlbackPageAreaResList){
			//?????????
			Integer type = mlbackPageAreaOne.getPageareaType();//0??????1?????????
			String idstr = mlbackPageAreaOne.getPageareaTypedetailIdstr();
			
			String idstrArr [] = idstr.split(",");
			
			List<PageAreaDetail> pageAreaDetailFollrList = new ArrayList<PageAreaDetail>();
			if(type==0){
				//type==0-slide
				for(int i=0;i<idstrArr.length;i++){
					//?????????
					PageAreaDetail pageAreaDetailOne = new PageAreaDetail();
					
					String slideIdStr=idstrArr[i];
					Integer slideIdInt = Integer.parseInt(slideIdStr);
					MlbackSlide mlbackSlideReq = new MlbackSlide();
					mlbackSlideReq.setSlideId(slideIdInt);
					MlbackSlide mlbackSlideRes= mlbackSlideService.selectMlbackSlideById(mlbackSlideReq);
						if(mlbackSlideRes!=null){
							Integer ifproORcateORpage = mlbackSlideRes.getSlideIfproorcateorpage();
							pageAreaDetailOne.setPageAreaDetailType(0);//????????????
							if(ifMobile.equals("1")){
								pageAreaDetailOne.setPageAreaDetaiImglUrl(mlbackSlideRes.getSlideWapimgurl());
							}else{
								pageAreaDetailOne.setPageAreaDetaiImglUrl(mlbackSlideRes.getSlidePcimgurl());
							}
							pageAreaDetailOne.setPageAreaDetailIfinto(mlbackSlideRes.getSlideIfinto());
							//?????????0??????-1???-2??????
							if(ifproORcateORpage==0){
								//0pro
								//pageAreaDetailOne.setPageAreaDetaiLinklUrl(mlbackSlideRes.getSlideSeoname()+".html");
								pageAreaDetailOne.setPageAreaDetaiLinklUrl("products/"+mlbackSlideRes.getSlideSeoname());
							}else if(ifproORcateORpage==1){
								//1cate
								//pageAreaDetailOne.setPageAreaDetaiLinklUrl("search/"+mlbackSlideRes.getSlideCateseoname()+".html");
								pageAreaDetailOne.setPageAreaDetaiLinklUrl("collections/"+mlbackSlideRes.getSlideCateseoname());
							}else{
								//2page
								//pageAreaDetailOne.setPageAreaDetaiLinklUrl(mlbackSlideRes.getSlidePageseoname()+".html");
								pageAreaDetailOne.setPageAreaDetaiLinklUrl("products/"+mlbackSlideRes.getSlidePageseoname());
							}
						}
					pageAreaDetailFollrList.add(pageAreaDetailOne);
				}
			}else if(type==1){
				//type==1?????????
				for(int i=0;i<idstrArr.length;i++){
					//?????????
					PageAreaDetail pageAreaDetailOne = new PageAreaDetail();
					
					String actshowproIdStr=idstrArr[i];
					Integer actshowproIdInt = Integer.parseInt(actshowproIdStr);
					MlbackActShowPro mlbackActShowProReq = new MlbackActShowPro();
					mlbackActShowProReq.setActshowproId(actshowproIdInt);
					MlbackActShowPro mlbackActShowProRes= mlbackActShowProService.selectMlbackActShowProById(mlbackActShowProReq);
					if(mlbackActShowProRes!=null){
						Integer ifproORcateORpage = mlbackActShowProRes.getActshowproIfproorcate();
						pageAreaDetailOne.setPageAreaDetailType(1);//?????????????????????
						if(ifMobile.equals("1")){
							pageAreaDetailOne.setPageAreaDetaiImglUrl(mlbackActShowProRes.getActshowproImgwapurl());
						}else{
							pageAreaDetailOne.setPageAreaDetaiImglUrl(mlbackActShowProRes.getActshowproImgpcurl());
						}
						pageAreaDetailOne.setPageAreaDetailIfinto(1);//1??????????????????
						
						if(ifproORcateORpage==0){
							//0-pro
							//pageAreaDetailOne.setPageAreaDetaiLinklUrl(mlbackActShowProRes.getActshowproSeoname()+".html");
							pageAreaDetailOne.setPageAreaDetaiLinklUrl("products/"+mlbackActShowProRes.getActshowproSeoname());
						}else if(ifproORcateORpage==1){
							//1-cate
							//pageAreaDetailOne.setPageAreaDetaiLinklUrl("search/"+mlbackActShowProRes.getActshowproCateseoname()+".html");
							pageAreaDetailOne.setPageAreaDetaiLinklUrl("collections/"+mlbackActShowProRes.getActshowproCateseoname());
						}else{
							//2-page
							//pageAreaDetailOne.setPageAreaDetaiLinklUrl(mlbackActShowProRes.getActshowproPageseoname()+".html");
							pageAreaDetailOne.setPageAreaDetaiLinklUrl("products/"+mlbackActShowProRes.getActshowproPageseoname());
						}
					}
					pageAreaDetailFollrList.add(pageAreaDetailOne);
				}
			}else{
				//type==2??????
				String cateIdStr=idstrArr[0];//??????????????????,??????????????????,
				Integer cateIdInt = Integer.parseInt(cateIdStr);
				MlbackCategory mlbackCategoryReq = new MlbackCategory();
				mlbackCategoryReq.setCategoryId(cateIdInt);
				MlbackCategory mlbackCategoryRes= mlbackCategoryService.selectMlbackCategoryById(mlbackCategoryReq);
				if(mlbackCategoryRes!=null){
					String pidsStr = mlbackCategoryRes.getCategoryProductIds();
					String cateSeo = mlbackCategoryRes.getCategorySeo();
					if((pidsStr==null)||("".equals(pidsStr))){
						continue;
					}else{
						String productidsStrArr [] = pidsStr.split(",");
						String productidStr ="";
						Integer productidInt =0;
						List<MlbackProduct> mlbackProductReqList = new ArrayList<MlbackProduct>();
						//?????????????????????
						MlbackProduct mlbackProductResOne = new MlbackProduct();
						List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
						for(int x=0;x<productidsStrArr.length;x++){
							productidStr = productidsStrArr[x];
							productidInt = Integer.parseInt(productidStr);
							//?????????pid???????????????
							MlbackProduct mlbackProductReq = new MlbackProduct();
							mlbackProductReq.setProductId(productidInt);
							//??????????????????????????????????????????
							mlbackProductReqList =mlbackProductService.selectMlbackProductbyPid(mlbackProductReq);
							if(mlbackProductReqList.size()>0){
								mlbackProductResOne = mlbackProductReqList.get(0);
								mlbackProductResList.add(mlbackProductResOne);
							}
						}
						if(mlbackProductResList.size()>8){
							for(int k=0;k<8;k++){
								MlbackProduct mlbackProductReqOne = mlbackProductResList.get(k);
								PageAreaDetail pageAreaDetailReturn = new PageAreaDetail();
								pageAreaDetailReturn.setMlbackProduct(mlbackProductReqOne);
								pageAreaDetailReturn.setPageAreaDetailIfinto(1);
								pageAreaDetailReturn.setPageAreaDetailType(2);
								//pageAreaDetailReturn.setPageAreaDetaiLinklUrl("search/"+cateSeo+".html");
								pageAreaDetailReturn.setPageAreaDetaiLinklUrl("collections/"+cateSeo);
								pageAreaDetailFollrList.add(pageAreaDetailReturn);
							}
						}else{
							for(MlbackProduct mlbackProductReqOne:mlbackProductResList){
								PageAreaDetail pageAreaDetailReturn = new PageAreaDetail();
								pageAreaDetailReturn.setMlbackProduct(mlbackProductReqOne);
								pageAreaDetailReturn.setPageAreaDetailIfinto(1);
								pageAreaDetailReturn.setPageAreaDetailType(2);
								//pageAreaDetailReturn.setPageAreaDetaiLinklUrl("search/"+cateSeo+".html");
								pageAreaDetailReturn.setPageAreaDetaiLinklUrl("collections/"+cateSeo);
								pageAreaDetailFollrList.add(pageAreaDetailReturn);
							}
						}
					}
				}else{
					//cateId?????????pro-List??????
					pageAreaDetailFollrList.add(null);//?????????????????????
				}
			}
			pageAreaDetailAllList.add(pageAreaDetailFollrList);
		}
		return pageAreaDetailAllList;
	}
	
	/**
	 * 8.0	20201019
	 * ??????????????????PageAreaList??????
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByPageArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByPageArea(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "pageareaName") String pageareaName,
			@RequestParam(value = "pageareaSupercateid", defaultValue = "1") Integer pageareaSupercateid) throws Exception{
		
		//???????????????????????????
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackPageArea mlbackPageAreaReq = new MlbackPageArea();
		mlbackPageAreaReq.setPageareaId(pageareaSupercateid);
		mlbackPageAreaReq.setPageareaName(pageareaName);
		List<MlbackPageArea> mlbackPageAreaResList = mlbackPageAreaService.selectMlbackPageAreaBackSearch(mlbackPageAreaReq);
		
		PageInfo page = new PageInfo(mlbackPageAreaResList, PagNum);
		
		return Msg.success().add("pageInfo", page);
			
	}
}
