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
import com.atguigu.bean.MlbackAreafreight;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackAreafreightService;

@Controller
@RequestMapping("/MlbackAreafreight")
public class MlbackAreafreightController {
		
	@Autowired
	MlbackAreafreightService mlbackAreafreightService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	onuse	20191225	检查
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackAreafreightPage")
	public String toMlbackAreafreightPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/operate/mlbackAreafreightPage";
		}
	}
	
	/**2.0	zsh20191225
	 * 分类MlbackAreafreight列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackAreafreightByPage")
	@ResponseBody
	public Msg getMlbackAreafreightWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = 50;
			PageHelper.startPage(pn, PagNum);
			List<MlbackAreafreight> mlbackAreafreightList = mlbackAreafreightService.selectMlbackAreafreightAll();
			PageInfo page = new PageInfo(mlbackAreafreightList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	onuse	20191225	检查
	 * MlbackAreafreight	save
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackAreafreight mlbackAreafreight){
		//接受参数信息
		System.out.println("mlbackAreafreight:"+mlbackAreafreight);
		//取出id
		Integer areafreightId = mlbackAreafreight.getAreafreightId();
		//String nowTime = DateUtil.strTime14s();
		if(areafreightId==null){
			//无id，insert
			mlbackAreafreightService.insertSelective(mlbackAreafreight);
			//System.out.println("后台操作:insert,mlbackAreafreight,success+intResult："+intResult);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlbackAreafreightService.updateByPrimaryKeySelective(mlbackAreafreight);
			//System.out.println("后台操作:update,mlbackAreafreight,success+intResult："+intResult);
			return Msg.success().add("resMsg", "更新成功");
			
		}		
	}
	
	/**4.0	onuse	20191225	check
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackAreafreight mlbackAreafreight){
		//接收id信息
		int areafreightIdInt = mlbackAreafreight.getAreafreightId();
		mlbackAreafreightService.deleteByPrimaryKey(areafreightIdInt);
		return Msg.success().add("resMsg", "delete success");
	}
	
	
	/**
	 * 5.0	onuse	20191225	check
	 * 查看单条MlbackAreafreight详情
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackAreafreightDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackAreafreightDetail(@RequestParam(value = "areafreightId") Integer areafreightId){
		
		//接受categoryId
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		mlbackAreafreightReq.setAreafreightId(areafreightId);
		//查询本条
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
		if(mlbackAreafreightResList.size()>0){
			mlbackAreafreightOne =mlbackAreafreightResList.get(0);
		}else{
			mlbackAreafreightOne = null;
		}
		return Msg.success().add("resMsg", "查看单条mlbackAreafreight的详情细节完毕")
					.add("mlbackAreafreightOne", mlbackAreafreightOne);
	}
	
	/**
	 * 6.0	onuse	20191225	check
	 * 通过英文简写,查看单条MlbackAreafreight的详情
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackAreafreightDetailByAreafreightEng",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackAreafreightDetailByAreafreightEng(@RequestParam(value = "areafreightCountryEnglish") String areafreightCountryEnglish){
		
		//接受categoryId
		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
		//mlbackAreafreightReq.setAreafreightCountryEnglish(areafreightCountryEnglish);
		mlbackAreafreightReq.setAreafreightCountryCode(areafreightCountryEnglish);
		//查询本条
		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
		if(mlbackAreafreightResList.size()>0){
			mlbackAreafreightOne =mlbackAreafreightResList.get(0);
		}else{
			mlbackAreafreightOne = null;
		}
		return Msg.success().add("resMsg", "查看单条mlbackAreafreight的详情细节完毕")
					.add("mlbackAreafreightOne", mlbackAreafreightOne);
	}
	
}
