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
import com.atguigu.bean.MlbackActShowPro;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackCategory;
import com.atguigu.bean.MlbackProduct;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackActShowProService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCategoryService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackActShowPro")
public class MlbackActShowProController {
	
	@Autowired
	MlbackActShowProService mlbackActShowProService;
		
	@Autowired
	MlbackCategoryService mlbackCategoryService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	onuse	20200103	检查
	 * toMlbackActShowPro列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackActShowProPage")
	public String tologin(HttpSession session) throws Exception{
	
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/marketing/mlbackActShowProPage";
		}
	}
	
	/**2.0	onuse	20200103	检查
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackActShowProByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackActShowPro> mlbackActShowProList = mlbackActShowProService.selectMlbackActShowProGetAll();
			PageInfo page = new PageInfo(mlbackActShowProList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**2.0	onuse	20200103	检查
	 * 分类MlbackActShowPro列表分页list数据
	 * @return
	 */
	@RequestMapping(value="/getMlbackActShowProDownlist")
	@ResponseBody
	public Msg getMlbackActShowProDownlist(HttpSession session) {
		List<MlbackActShowPro> mlbackActShowProList = mlbackActShowProService.selectMlbackActShowProGetAll();
		return Msg.success().add("mlbackActShowProList", mlbackActShowProList);
	}
	
	/**3.0	20200703
	 * MlbackActShowPro	initializaActShowPro
	 * @param MlbackActShowPro
	 * @return
	 */
	@RequestMapping(value="/initializaActShowPro",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaActShowPro(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackActShowPro mlbackActShowPro = new MlbackActShowPro();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackActShowPro.setActshowproCreatetime(nowTime);
		mlbackActShowPro.setActshowproStatus(0);//0未上架1上架中
		//无id，insert
		System.out.println("插入前"+mlbackActShowPro.toString());
		mlbackActShowProService.insertSelective(mlbackActShowPro);
		System.out.println("插入后"+mlbackActShowPro.toString());
		return Msg.success().add("resMsg", "Catalog初始化成功").add("mlbackActShowPro", mlbackActShowPro);
	}
	
	
	/**3.0	zsh20200103	检查
	 * MlbackActShowPro	insert
	 * @param MlbackActShowPro
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackActShowPro mlbackActShowPro){
		//接收参数信息
		//获取类名
		Integer actshowproIfproORcate = mlbackActShowPro.getActshowproIfproorcate();
		if(actshowproIfproORcate==0){
			//产品
			Integer proId = mlbackActShowPro.getActshowproProid();
			
			MlbackProduct mlbackProductReq = new MlbackProduct();
			MlbackProduct mlbackProductRes = new MlbackProduct();
			mlbackProductReq.setProductId(proId);
			List<MlbackProduct> mlbackProductResList = mlbackProductService.selectMlbackProductSimple(mlbackProductReq);
			mlbackProductRes = mlbackProductResList.get(0);
			String Pname = mlbackProductRes.getProductName();
			String Pseoname = mlbackProductRes.getProductSeo();
			mlbackActShowPro.setActshowproProname(Pname);//Pseoname
			mlbackActShowPro.setActshowproSeoname(Pseoname);
		}else if(actshowproIfproORcate==1){
			//类
			Integer cId = mlbackActShowPro.getActshowproCateid();
			MlbackCategory mlbackCategoryReq = new MlbackCategory();
			MlbackCategory mlbackCategoryRes = new MlbackCategory();
			
			mlbackCategoryReq.setCategoryId(cId);
			
			List<MlbackCategory> mlbackCategoryResList = mlbackCategoryService.selectMlbackCategory(mlbackCategoryReq);
			mlbackCategoryRes = mlbackCategoryResList.get(0);
			
			String CategoryDesc = mlbackCategoryRes.getCategoryDesc();
			mlbackActShowPro.setActshowproCatename(CategoryDesc);//Cname
			String CateSeoName = mlbackCategoryRes.getCategorySeo();
			mlbackActShowPro.setActshowproCateseoname(CateSeoName);
		}else{
			String pageSeoname = mlbackActShowPro.getActshowproPageseoname();
			mlbackActShowPro.setActshowproPageseoname(pageSeoname);//pageSeoname
		}
		
		//mlbackProductService;
		String nowtime = DateUtil.strTime14s();
		mlbackActShowPro.setActshowproMotifytime(nowtime);

		//有id，update
		int intResult = mlbackActShowProService.updateByPrimaryKeySelective(mlbackActShowPro);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**4.0	onuse	20200103	检查
	 * MlbackActShowPro	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackActShowPro mlbackActShowPro){
		//接收id信息
		Integer actshowproId = mlbackActShowPro.getActshowproId();
		int intResult = mlbackActShowProService.deleteByPrimaryKey(actshowproId);
		System.out.println(intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	onuse	20200103	检查
	 * 查看单条类目的详情细节
	 * @param MlbackActShowPro
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackActShowProDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackActShowProDetail(@RequestBody MlbackActShowPro mlbackActShowPro){
		
		Integer actshowproId = mlbackActShowPro.getActshowproId();
		//接受actshowproId
		MlbackActShowPro mlbackActShowProReq = new MlbackActShowPro();
		mlbackActShowProReq.setActshowproId(actshowproId);
		//查询本条
		MlbackActShowPro mlbackActShowProOne =mlbackActShowProService.selectMlbackActShowProById(mlbackActShowProReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackActShowProOne", mlbackActShowProOne);
	}
	
	/**
	 * 6.0	onuse	20200103	检查
	 * 查看首页活动品区域的顺序
	 * @param MlbackActShowPro
	 * @return 
	 */
	@RequestMapping(value="/getMlbackActShowProListByActnum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackActShowProListByActnum(@RequestBody MlbackActShowPro mlbackActShowPro){
		
		Integer actshowproActnum = mlbackActShowPro.getActshowproActnum();
		
		//接受actshowproId
		MlbackActShowPro mlbackActShowProReq = new MlbackActShowPro();
		mlbackActShowProReq.setActshowproStatus(1);
		mlbackActShowProReq.setActshowproActnum(actshowproActnum);
		//查询本条
		List<MlbackActShowPro> mlbackActShowProList =mlbackActShowProService.selectMlbackActShowProByActnum(mlbackActShowProReq);
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackActShowProList", mlbackActShowProList);
	}

}
