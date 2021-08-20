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
import com.atguigu.bean.MlbackShowArea;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackShowAreaService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackShowArea")
public class MlbackShowAreaController {
		
	@Autowired
	MlbackShowAreaService mlbackShowAreaService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	onuse	200104
	 * to_MlbackShowArea列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackShowAreaPage")
	public String toMlbackShowAreaPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/marketing/mlbackShowAreaPage";
		}
	}
	
	/**2.0	onuse	200104
	 * 分类MlbackShowArea列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackShowAreaListByPage")
	@ResponseBody
	public Msg getMlbackShowAreaListByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackShowArea> mlbackShowAreaList = mlbackShowAreaService.selectMlbackShowAreaAll();
			PageInfo page = new PageInfo(mlbackShowAreaList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	20200703
	 * MlbackSlide	initializaSlide
	 * @param MlbackSlide
	 * @return
	 */
	@RequestMapping(value="/initializaShowArea",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaShowArea(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackShowArea mlbackShowArea = new MlbackShowArea();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackShowArea.setShowareaCreatetime(nowTime);
		mlbackShowArea.setShowareaStatus(0);//0未上架1上架中
		//无id，insert
		System.out.println("插入前"+mlbackShowArea.toString());
		mlbackShowAreaService.insertSelective(mlbackShowArea);
		System.out.println("插入后"+mlbackShowArea.toString());
		return Msg.success().add("resMsg", "Catalog初始化成功").add("mlbackShowArea", mlbackShowArea);
	}
	
	/**3.1	20200708
	 * MlbackShowArea	insert/update
	 * @param MlbackShowArea
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackShowArea mlbackShowArea){
		//接受参数信息
		//System.out.println("mlbackShowArea:"+mlbackShowArea);
		//取出id
		Integer showareaIdId = mlbackShowArea.getShowareaId();
		String nowTime = DateUtil.strTime14s();
		mlbackShowArea.setShowareaMotifytime(nowTime);
		if(showareaIdId==null){
			//无id，insert
			mlbackShowArea.setShowareaCreatetime(nowTime);
			mlbackShowAreaService.insertSelective(mlbackShowArea);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id，update
			mlbackShowAreaService.updateByPrimaryKeySelective(mlbackShowArea);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	20200708
	 * MlbackShowArea	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackShowArea mlbackShowArea){
		//接收id信息
		int showareaIdId = mlbackShowArea.getShowareaId();
		mlbackShowAreaService.deleteByPrimaryKey(showareaIdId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	20200708
	 * 查看单条详情
	 * @param getOneMlbackShowAreaDetail
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackShowAreaDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackShowAreaDetail(@RequestBody MlbackShowArea mlbackShowArea){
		
		Integer showareaId = mlbackShowArea.getShowareaId();
		//接受showareaId
		MlbackShowArea mlbackShowAreaReq = new MlbackShowArea();
		mlbackShowAreaReq.setShowareaId(showareaId);
		//查询本条
		List<MlbackShowArea> mlbackShowAreaResList =mlbackShowAreaService.selectMlbackShowAreaById(mlbackShowAreaReq);
		MlbackShowArea mlbackShowAreaOne = new MlbackShowArea();
		if(mlbackShowAreaResList.size()>0){
			mlbackShowAreaOne =mlbackShowAreaResList.get(0);
		}else{
			mlbackShowAreaOne = null;
		}
		return Msg.success().add("resMsg", "查看单条mlbackShowAreaOne的详情细节完毕")
					.add("mlbackShowAreaOne", mlbackShowAreaOne);
	}
	
	/**
	 * 6.0	20200708
	 * 查看单条详情
	 * @param getOneMlbackShowAreaDetail
	 * @return 
	 */
	@RequestMapping(value="/getMlbackShowAreaOne",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackShowAreaOne(@RequestBody MlbackShowArea mlbackShowArea){
		
		Integer showareaNumth = mlbackShowArea.getShowareaNumth();
		//接受showareaId
		MlbackShowArea mlbackShowAreaReq = new MlbackShowArea();
		mlbackShowAreaReq.setShowareaNumth(showareaNumth);
		mlbackShowAreaReq.setShowareaStatus(1);//使用中
		//查询本条
		List<MlbackShowArea> mlbackShowAreaResList =mlbackShowAreaService.selectMlbackShowAreaByNumthAndStatus(mlbackShowAreaReq);
		MlbackShowArea mlbackShowAreaOne = new MlbackShowArea();
		if(mlbackShowAreaResList.size()>0){
			mlbackShowAreaOne =mlbackShowAreaResList.get(0);
		}else{
			mlbackShowAreaOne = null;
		}
		return Msg.success().add("resMsg", "查看单条mlbackShowAreaOne的详情细节完毕")
					.add("mlbackShowAreaOne", mlbackShowAreaOne);
	}
	
}
