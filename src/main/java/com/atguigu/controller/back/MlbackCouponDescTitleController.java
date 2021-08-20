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
import com.atguigu.bean.MlbackCouponDescDetail;
import com.atguigu.bean.MlbackCouponDescTitle;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCouponDescDetailService;
import com.atguigu.service.MlbackCouponDescTitleService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackCouponDescTitle")
public class MlbackCouponDescTitleController {
	
	@Autowired
	MlbackCouponDescTitleService mlbackCouponDescTitleService;
		
	@Autowired
	MlbackCouponDescDetailService mlbackCouponDescDetailService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	onuse	200104
	 * toMlbackCouponDescTitlePage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackCouponDescTitlePage")
	public String toMlbackCouponDescTitlePage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/operate/mlbackCouponDescTitlePage";
		}
	}
	
	/**2.0	onuse	200104
	 * 分类mlbackCouponDescTitle列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackCouponDescTitleByPage")
	@ResponseBody
	public Msg getMlbackCouponDescTitleByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackCouponDescTitle> mlbackCouponDescTitleList = mlbackCouponDescTitleService.selectMlbackCouponDescTitleGetAll();
			System.out.println("mlbackCouponDescTitleList.size:"+mlbackCouponDescTitleList.size());
			PageInfo page = new PageInfo(mlbackCouponDescTitleList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	20200703
	 * MlbackCouponDescTitle	initializaCouponDescTitle
	 * @param MlbackCouponDescTitle
	 * @return
	 */
	@RequestMapping(value="/initializaCouponDescTitle",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaCouponDescTitle(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackCouponDescTitle mlbackCouponDescTitle = new MlbackCouponDescTitle();
		String nowtime = DateUtil.strTime14s();
		mlbackCouponDescTitle.setCoupondesctieleCreatetime(nowtime);
		mlbackCouponDescTitle.setCoupondesctieleStatus(0);
		//无id,insert
		System.out.println("插入前"+mlbackCouponDescTitle.toString());
		mlbackCouponDescTitleService.insertSelective(mlbackCouponDescTitle);
		System.out.println("插入后"+mlbackCouponDescTitle.toString());
		return Msg.success().add("resMsg", "mlbackCouponDescTitle初始化成功").add("mlbackCouponDescTitle", mlbackCouponDescTitle);
	}
	
	/**3.1	onuse	200104
	 * mlbackCouponDescTitle	save
	 * @param mlbackCouponDescTitle
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCouponDescTitle mlbackCouponDescTitle){
		//接受参数信息
		
		String nowtime = DateUtil.strTime14s();
		mlbackCouponDescTitle.setCoupondesctieleMotifytime(nowtime);

		//有id，update
		mlbackCouponDescTitleService.updateByPrimaryKeySelective(mlbackCouponDescTitle);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**4.0	UseNow	190905
	 * MlbackCouponDescTitle	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackCouponDescTitle mlbackCouponDescTitle){
		//接收id信息
		Integer coupondesctieleId = mlbackCouponDescTitle.getCoupondesctieleId();
		mlbackCouponDescTitleService.deleteByPrimaryKey(coupondesctieleId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	UseNow	190905
	 * 查看单条的详情细节
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCouponDescTitleDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCouponDescTitleDetail(@RequestBody MlbackCouponDescTitle mlbackCouponDescTitle){
		//接受coupondesctieleId
		Integer coupondesctieleId = mlbackCouponDescTitle.getCoupondesctieleId();
		
		MlbackCouponDescTitle mlbackCouponDescTitleReq = new MlbackCouponDescTitle();
		mlbackCouponDescTitleReq.setCoupondesctieleId(coupondesctieleId);
		//查询本条
		MlbackCouponDescTitle mlbackCouponDescTitleOne =mlbackCouponDescTitleService.selectMlbackCouponDescTitleById(mlbackCouponDescTitleReq);
		
		return Msg.success().add("resMsg", "查看单条类目的详情细节完毕")
					.add("mlbackCouponDescTitleOne", mlbackCouponDescTitleOne);
	}
	
	/**
	 * 6.0	UseNow	190905
	 * 查询wap不同位置的图片
	 * @param MlbackSlide
	 * @return 
	 */
	@RequestMapping(value="/getMlbackCouponDescTitleListByStatus",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackCouponDescTitleListByStatus(HttpServletResponse rep,HttpServletRequest res){
		//接受slideArea
		MlbackCouponDescTitle mlbackCouponDescTitleReq = new MlbackCouponDescTitle();
		mlbackCouponDescTitleReq.setCoupondesctieleStatus(1);
		//查询本条
		List<MlbackCouponDescTitle> mlbackCouponDescTitleList =mlbackCouponDescTitleService.selectMlbackCouponDescTitleListByStatus(mlbackCouponDescTitleReq);
		
		MlbackCouponDescDetail mlbackCouponDescDetailReq = new MlbackCouponDescDetail();
		mlbackCouponDescDetailReq.setCoupondescdetailStatus(1);
		List<MlbackCouponDescDetail> mlbackCouponDescDetailList = mlbackCouponDescDetailService.selectMlbackCouponDescDetailByStatus(mlbackCouponDescDetailReq);
		
		return Msg.success().add("resMsg", "查看该位置的轮播完毕")
				.add("mlbackCouponDescTitleList", mlbackCouponDescTitleList)
				.add("mlbackCouponDescDetailList", mlbackCouponDescDetailList);
	}

}
