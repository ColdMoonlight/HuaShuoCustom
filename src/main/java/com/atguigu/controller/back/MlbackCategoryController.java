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
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.IfMobileUtils;

@Controller
@RequestMapping("/MlbackCategory")
public class MlbackCategoryController {
	
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	20200608
	 * to后台分类MlbackCategory列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackCategoryPage")
	public String tologin(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/product/mlbackCategoryPage";
		}
	}
	
	/**2.0	20200608
	 * 后台MlbackCategory列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackCategoryByPage")
	@ResponseBody
	public Msg getGroupDisplayWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		List<MlbackCategory> mlbackCategoryList = mlbackCategoryService.selectMlbackCategoryGetAll();
		PageInfo page = new PageInfo(mlbackCategoryList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20200608
	 * MlbackCategory	initializaCategory
	 * @param MlbackCategory
	 * @return
	 */
	@RequestMapping(value="/initializaCategory",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaCategory(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackCategory mlbackCategory = new MlbackCategory();
		//接受参数信息
		String categoryParentName="---none---";
		String categoryDesc="";
		categoryParentName ="---none---";
		//判断归属是否为none
		Integer	categoryParentId = -1;
		mlbackCategory.setCategoryParentId(categoryParentId);
		mlbackCategory.setCategoryDesc(categoryDesc);
		mlbackCategory.setCategoryParentName(categoryParentName);
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackCategory.setCategoryCreatetime(nowTime);
		mlbackCategory.setCategoryStatus(0);//0未上架1上架中
		//无id,insert
		System.out.println("插入前"+mlbackCategory.toString());
		mlbackCategoryService.insertSelective(mlbackCategory);
		System.out.println("插入后"+mlbackCategory.toString());
		return Msg.success().add("resMsg", "Category初始化成功").add("mlbackCategory", mlbackCategory);
	}
	
	/**4.0	20200608
	 * MlbackCategory	update
	 * @param MlbackCategory
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCategory mlbackCategory){
		//接受参数信息
		//获取类名
		String categoryName = mlbackCategory.getCategoryName();
		//获取归属类名
		Integer categoryParentId = mlbackCategory.getCategoryParentId();
		MlbackCategory MlbackCategoryParentNameReq = new MlbackCategory();
		MlbackCategoryParentNameReq.setCategoryId(categoryParentId);
		List<MlbackCategory> MlbackCategoryList = mlbackCategoryService.selectMlbackCategory(MlbackCategoryParentNameReq);
		String categoryParentName="";
		String categoryDesc="";
		if(MlbackCategoryList.size()>0){
			MlbackCategory MlbackCategoryParentNameRes = MlbackCategoryList.get(0);
			categoryParentName = MlbackCategoryParentNameRes.getCategoryName();
			categoryDesc = MlbackCategoryParentNameRes.getCategoryDesc();
		}else{
			categoryParentName ="---none---";
		}
		//判断归属是否为none
		if(categoryParentName.equals("---none---")){
			mlbackCategory.setCategoryDesc(categoryName);
		}else{
			mlbackCategory.setCategoryDesc(categoryDesc+">"+categoryName);
		}
		String nowTime = DateUtil.strTime14s();
		mlbackCategory.setCategoryMotifytime(nowTime);
		mlbackCategory.setCategoryParentName(categoryParentName);
		mlbackCategoryService.updateByPrimaryKeySelective(mlbackCategory);
		return Msg.success().add("resMsg", "category保存成功");
	}
	
	/**5.0	20200608
	 * MlbackCategory	delete
	 * @param MlbackCategory-categoryId
	 * @return 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackCategory mlbackCategory){
		//接收id信息
		int categoryIdInt = mlbackCategory.getCategoryId();
		mlbackCategoryService.deleteByPrimaryKey(categoryIdInt);
		return Msg.success().add("resMsg", "category delete  success");
	}
	
	/**
	 * 6.0	20200608
	 * 查单条MlbackCategory详情
	 * @param MlbackCategory-categoryId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCategoryDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCategoryDetail(@RequestBody MlbackCategory mlbackCategory){
		
		//接受categoryId
		Integer categoryId = mlbackCategory.getCategoryId();
		MlbackCategory mlbackCategoryReq = new MlbackCategory();
		mlbackCategoryReq.setCategoryId(categoryId);
		//查询本条
		List<MlbackCategory> mlbackCategoryResList =mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
		MlbackCategory mlbackCategoryOne =mlbackCategoryResList.get(0);
		return Msg.success().add("resMsg", "查categoryOne完毕").add("mlbackCategoryOne", mlbackCategoryOne);
	}
	
	/**
	 * 7.0	20200608
	 * 获取全部类目,以便于下拉选择
	 * @param 无参
	 * @return 
	 */
	@RequestMapping(value="/getMlbackCategoryDropdownSelect",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackCategoryDropdownSelect(HttpServletResponse rep,HttpServletRequest res){
		
		//查询全部的category信息,便于下拉选择
		List<MlbackCategory> mlbackCategorydownList = mlbackCategoryService.selectMlbackCategoryGetAllByParentId();
		//System.out.println("操作说明:管理员查-categorydownList菜单");
		
		List<MlbackCategory> mlbackCategorydownEr =new ArrayList<MlbackCategory>();
		for(MlbackCategory mlbackCategoryOne :mlbackCategorydownList){
			Integer categoryParentId = mlbackCategoryOne.getCategoryParentId();
			if(categoryParentId>0){
				mlbackCategorydownEr.add(mlbackCategoryOne);
			}
		}
		return Msg.success().add("resMsg", "success").add("mlbackCategorydownList", mlbackCategorydownList).add("mlbackCategorydownEr", mlbackCategorydownEr);
	}
	
	/**
	 * 8.0	20200608
	 * 后端获取类下产品list详情页面wap/pc
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/backSearchBycategory",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchBycategory(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "categoryName") String categoryName,
			@RequestParam(value = "categorySuperCateId", defaultValue = "1") Integer categorySuperCateId) throws Exception{
		
		//接收传递进来的参数
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackCategory mlbackCategoryReq = new MlbackCategory();
		mlbackCategoryReq.setCategorySuperCateId(categorySuperCateId);
		mlbackCategoryReq.setCategoryName(categoryName);
		List<MlbackCategory> mlbackCategoryResList = mlbackCategoryService.selectMlbackCategoryBackSearch(mlbackCategoryReq);
		
		PageInfo page = new PageInfo(mlbackCategoryResList, PagNum);
		
		return Msg.success().add("pageInfo", page);
			
	}
	
	/**
	 * 9.0	20200608
	 * 前台获取类下产品list页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toprolistBycategorySeoPage",method=RequestMethod.GET)
	public String toprolistBycategorySeoPage(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "categorySeo") String categorySeo) throws Exception{
		//接收传递进来的参数
		String categorySeoReq = categorySeo;
		
		//放回响应域中
		res.setAttribute("categorySeo", categorySeoReq);
		//放回session域中
		session.setAttribute("categorySeo", categorySeoReq);
		
		MlbackCategory mlbackCategoryReq = new MlbackCategory();
		
		mlbackCategoryReq.setCategorySeo(categorySeoReq);
		
		List<MlbackCategory> mlbackCategoryResList = mlbackCategoryService.selectMlbackCategoryBySeo(mlbackCategoryReq);
		
		if(mlbackCategoryResList.size()>0){
			
			MlbackCategory mlbackCategoryRes = mlbackCategoryResList.get(0);
			Integer cateStatus = mlbackCategoryRes.getCategoryStatus();
			if(cateStatus==1){
				String categoryMetaTitle = mlbackCategoryRes.getCategoryMetatitle();
				String categoryMetaKeyWords = mlbackCategoryRes.getCategoryMetakeywords();
				String categoryMetaDesc = mlbackCategoryRes.getCategoryMetadesc();
				session.setAttribute("categoryMetaTitle", categoryMetaTitle);
				session.setAttribute("categoryMetaKeyWords", categoryMetaKeyWords);
				session.setAttribute("categoryMetaDesc", categoryMetaDesc);
			}else{
				return "redirect:/";
			}
		}else{
			return "redirect:/";
		}
		//返回视图
		return "portal/prolistBycategorySeo";
	}
	
	/**
	  * 10.0	20200608
	  * 通过产品名查看单条产品的详情
	  * @param mlbackCategory-bycategorySeo
	  * @return 
	  */
	 @RequestMapping(value="/searchBycategorySeo",method=RequestMethod.POST)
	 @ResponseBody
	 public Msg searchBycategorySeo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlbackCategory mlbackCategory){
		 
		 //初始化返回下拉所需
		 //查询all-category信息,便于下拉选择
//		 List<MlbackCategory> mlbackCategorydownList = mlbackCategoryService.selectMlbackCategoryGetAllByParentId();
		 List<MlbackCategory> mlbackCategorydownList = mlbackCategoryService.selectMlbackCategoryGetAllByParentIdFront();
		 
		 //接受信息
		 String categorySeoReq = mlbackCategory.getCategorySeo();
		 MlbackCategory mlbackCategoryReq = new MlbackCategory();
		 mlbackCategoryReq.setCategorySeo(categorySeoReq);
		 List<MlbackCategory> mlbackCategoryList = mlbackCategoryService.selectMlbackCategoryBySeo(mlbackCategoryReq);
		 
		 if(!(mlbackCategoryList.size()>0)){
			 return Msg.success().add("resMsg", "通过categorySeo未查到该category").add("mlbackProductResList", null).add("mlbackCategorydownList", mlbackCategorydownList);
		 }
		 
		 MlbackCategory mlbackCategoryres = mlbackCategoryList.get(0);
	 
		 String CategoryProductIdsStr = mlbackCategoryres.getCategoryProductIds();
		 
		 if(CategoryProductIdsStr==null){
			 return Msg.success().add("resMsg", "该类下无prolist").add("mlbackProductResList", null).add("mlbackCategorydownList", mlbackCategorydownList);
		 }
		 if("".equals(CategoryProductIdsStr)){
			 return Msg.success().add("resMsg", "该类下无prolist").add("mlbackProductResList", null).add("mlbackCategorydownList", mlbackCategorydownList);
		 }
		
		 String productidsStrArr [] =CategoryProductIdsStr.split(",");
		 String productidStr ="";
		 Integer productidInt =0;
		 List<MlbackProduct> mlbackProductReqList = new ArrayList<MlbackProduct>();
		 List<MlbackProduct> mlbackProductResList = new ArrayList<MlbackProduct>();
		 MlbackProduct mlbackProductResOne = new MlbackProduct();
		 for(int i=0;i<productidsStrArr.length;i++){
			 productidStr = productidsStrArr[i];
			 productidInt = Integer.parseInt(productidStr);
			 //查询白pid的产品详情
			 MlbackProduct mlbackProductReq = new MlbackProduct();
			 mlbackProductReq.setProductId(productidInt);
			 //查回来的直接就是仅上架的产品
			 mlbackProductReqList =mlbackProductService.selectMlbackProductbyCategorySeo(mlbackProductReq);
			 if(mlbackProductReqList.size()>0){
				 mlbackProductResOne = mlbackProductReqList.get(0);
				 mlbackProductResList.add(mlbackProductResOne);
			 }
		 }
		 
		 String cateImg = "";
		 Integer cateImgStatus = 0;
		 String ifMobile = IfMobileUtils.isMobileOrPc(rep, res);
		 if(ifMobile.equals("1")){
			  cateImg = mlbackCategoryres.getCategoryImgurl();
			  cateImgStatus=mlbackCategoryres.getCategoryImgstatus();
		 }else{
			  cateImg = mlbackCategoryres.getCategoryImgpcurl();
			  cateImgStatus = mlbackCategoryres.getCategoryImgpcstatus();
		 }
		 return Msg.success().add("resMsg", "searchBycategorySeo完毕").add("cateImg", cateImg).add("cateImgStatus", cateImgStatus)
				 .add("mlbackProductResList", mlbackProductResList).add("mlbackCategorydownList", mlbackCategorydownList);
	 }

}
