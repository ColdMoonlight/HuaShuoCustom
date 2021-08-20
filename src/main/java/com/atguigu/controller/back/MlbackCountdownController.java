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
import com.atguigu.bean.MlbackCountdown;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackCountdownService;
import com.atguigu.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/MlbackCountdown")
public class MlbackCountdownController {
	
	@Autowired
	MlbackCountdownService mlbackCountdownService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	20200707
	 * toMlbackCountdownPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackCountdownPage")
	public String toMlbackCountdownPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/marketing/mlbackCountdownPage";
		}
	}
	
	/**2.0	20200707
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackCountdownByPage")
	@ResponseBody
	public Msg getMlbackActShowProByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		int PagNum = Const.PAGE_NUM_CATEGORY;
		PageHelper.startPage(pn, PagNum);
		List<MlbackCountdown> MlbackCountdownList = mlbackCountdownService.selectMlbackCountdownGetAll();
		System.out.println("MlbackCountdownList.size:"+MlbackCountdownList.size());
		PageInfo page = new PageInfo(MlbackCountdownList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20200703
	 * MlbackCountdown	initializaSlide
	 * @param MlbackCountdown
	 * @return
	 */
	@RequestMapping(value="/initializaSlide",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaSlide(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackCountdown mlbackCountdown = new MlbackCountdown();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackCountdown.setCountdownCreatetime(nowTime);
		//无id,insert
		System.out.println("插入前"+mlbackCountdown.toString());
		mlbackCountdownService.insertSelective(mlbackCountdown);
		System.out.println("插入后"+mlbackCountdown.toString());
		return Msg.success().add("resMsg", "Catalog初始化成功").add("mlbackCountdown", mlbackCountdown);
	}
	
	/**3.0	20200707
	 * mlbackCountdown	save
	 * @param mlbackCountdown
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackCountdown mlbackCountdown){
		//接受参数信息
		//mlbackProductService;
		String nowtime = DateUtil.strTime14s();
		mlbackCountdown.setCountdownMotifytime(nowtime);
		//有id,update
		mlbackCountdownService.updateByPrimaryKeySelective(mlbackCountdown);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**4.0	20200707
	 * MlbackCountdown	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackCountdown mlbackCountdown){
		//接收SlideId
		Integer countdownId = mlbackCountdown.getCountdownId();
		mlbackCountdownService.deleteByPrimaryKey(countdownId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	20200707
	 * 查看单条Slide的详情细节
	 * @param MlbackCountdown
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackCountdownDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackCountdownDetail(@RequestBody MlbackCountdown mlbackCountdown){
		
		Integer countdownId = mlbackCountdown.getCountdownId();
		//接受actshowproId
		MlbackCountdown mlbackCountdownReq = new MlbackCountdown();
		mlbackCountdownReq.setCountdownId(countdownId);
		//查询本条
		List<MlbackCountdown> mlbackCountdowList =mlbackCountdownService.selectMlbackCountdownById(mlbackCountdownReq);
		MlbackCountdown mlbackCountdownOne = new MlbackCountdown();
		if(mlbackCountdowList.size()>0){
			mlbackCountdownOne =  mlbackCountdowList.get(0);
		}
		return Msg.success().add("resMsg", "查看单条倒计时的详情完毕").add("mlbackCountdownOne", mlbackCountdownOne);
	}

}
