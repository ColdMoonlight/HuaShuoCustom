package com.atguigu.controller.back;

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
import com.atguigu.bean.MlbackSuperCate;
import com.atguigu.common.Const;
//import com.atguigu.bean.MlbackProduct;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackSuperCateService;
//import com.atguigu.service.MlbackProductService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackSuperCate")
public class MlbackSuperCateController {
	
	@Autowired
	MlbackSuperCateService mlbackSuperCateService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	
	/**
	 * 1.0	onuse	20191225	检查
	 * to后台分类MlbackCategory列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackSuperCatePage")
	public String tologin(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/product/mlbackSuperCatePage";
		}
	}
	
	/**2.0	onuse	20191225	检查
	 * 后台MlbackSuperCate列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackSuperCateByPage")
	@ResponseBody
	public Msg getMlbackSuperCateByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = Const.PAGE_NUM_CATEGORY;
		PageHelper.startPage(pn, PagNum);
		List<MlbackSuperCate> mlbackSuperCateList = mlbackSuperCateService.selectMlbackSuperCateGetAll();
		PageInfo page = new PageInfo(mlbackSuperCateList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	onuse	20191225	检查
	 * MlbackSuperCate	insert
	 * @param MlbackSuperCate
	 * @return
	 */
	@RequestMapping(value="/initializaSuperCate",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaCategory(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackSuperCate mlbackSuperCate = new MlbackSuperCate();
		
		String nowTime = DateUtil.strTime14s();
		mlbackSuperCate.setSupercateCreatetime(nowTime);
		System.out.println("插入前"+mlbackSuperCate.toString());
		mlbackSuperCateService.insertSelective(mlbackSuperCate);
		System.out.println("插入后"+mlbackSuperCate.toString());
		return Msg.success().add("resMsg", "插入成功").add("mlbackSuperCate", mlbackSuperCate);
	}
	
	/**3.0	onuse	20191225	检查
	 * MlbackCategory	insert/update
	 * @param MlbackCategory
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackSuperCate mlbackSuperCate){

		String nowTime = DateUtil.strTime14s();
		mlbackSuperCate.setSupercateMotifytime(nowTime);
		mlbackSuperCateService.updateByPrimaryKeySelective(mlbackSuperCate);
		System.out.println("后台操作:SupercateId不为null,走update+intResult:"+mlbackSuperCate.toString());
		return Msg.success().add("resMsg", "更新成功").add("mlbackSuperCate", mlbackSuperCate);
	}
	
	/**4.0	onuse	20191225	检查
	 * MlbackCategory	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackSuperCate mlbackSuperCate){
		//接收id信息
		Integer SupercateId = mlbackSuperCate.getSupercateId();
		mlbackSuperCateService.deleteByPrimaryKey(SupercateId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	onuse	20200103	检查
	 * 查单条MlbackSuperCate详情by-categoryId
	 * @param categoryId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackSuperCateDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackSuperCateDetail(@RequestBody MlbackSuperCate mlbackSuperCate,HttpSession session){
		
		//接受supercateId
		Integer supercateId = mlbackSuperCate.getSupercateId();
		MlbackSuperCate mlbackSuperCateReq = new MlbackSuperCate();
		mlbackSuperCateReq.setSupercateId(supercateId);
		//查询本条
		List<MlbackSuperCate> mlbackSuperCateResList =mlbackSuperCateService.selectMlbackSuperCateById(mlbackSuperCateReq);
		MlbackSuperCate mlbackSuperCateOne =mlbackSuperCateResList.get(0);
		return Msg.success().add("resMsg", "查mlbackSuperCate完毕").add("mlbackSuperCate", mlbackSuperCateOne);
	}
	/**
	 * 6.0	onuse	20200519	检查
	 * 用在category的类目中下拉所需MlbackSuperCateList
	 * @param categoryId
	 * @return 
	 */
	@RequestMapping(value="/getSuperCateDownList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getSuperCateDownList(HttpSession session){
		
		List<MlbackSuperCate> mlbackSuperCateResList =mlbackSuperCateService.selectMlbackSuperCateGetAll();
		return Msg.success().add("resMsg", "在category的类目中下拉所需MlbackSuperCateList").add("mlbackSuperCateResList", mlbackSuperCateResList);
	}	
	
}
