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
import com.atguigu.bean.MlbackEmailRichText;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackEmailRichTextService;
import com.atguigu.service.MlfrontPayInfoService;

@Controller
@RequestMapping("/MlbackEmailRichText")
public class MlbackEmailRichTextController {
		
	@Autowired
	MlbackEmailRichTextService mlbackEmailRichTextService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	/**
	 * 1.0	zsh20210323
	 * toMlbackEmailRichText列表页面
	 * @param jsp
	 * @return
	 * */
	@RequestMapping("/toMlbackEmailRichTextPage")
	public String toMlbackEmailRichTextPage(HttpSession session) throws Exception{
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/email/mlbackEmailRichTextPage";
		}
	}
	
	
	/**2.0	zsh20210223
	 * 分类MlbackEmailRichText列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackEmailRichTextByPage")
	@ResponseBody
	public Msg getMlbackEmailRichTextByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackEmailRichText> mlbackEmailRichTextList = mlbackEmailRichTextService.selectMlbackEmailRichTextAll();
			PageInfo page = new PageInfo(mlbackEmailRichTextList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	zsh20210223
	 * MlbackEmailRichText	initializaHtmlEmail
	 * @param MlbackEmailRichText
	 * @return
	 */
	@RequestMapping(value="/initializaEmailRichText",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaEmailRichText(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackEmailRichText mlbackEmailRichText = new MlbackEmailRichText();
		mlbackEmailRichText.setEmailrichtextTitle("titel");
		mlbackEmailRichTextService.insertSelective(mlbackEmailRichText);
		System.out.println("插入后"+mlbackEmailRichText.toString());
		return Msg.success().add("resMsg", "HtmlEmail初始化成功").add("MlbackEmailRichText", mlbackEmailRichText);
	}
	
	/**3.0	zsh20210223
	 * MlbackAreafreight	insert
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackEmailRichText mlbackEmailRichText){
		//接受参数信息
		//取出id
		Integer emailrichtextId = mlbackEmailRichText.getEmailrichtextId();
		if(emailrichtextId==null){
			//无id,insert
			mlbackEmailRichTextService.insertSelective(mlbackEmailRichText);
			System.out.println("后台操作:HtmlEmailId为null,走add+intResult:"+mlbackEmailRichText.toString());
			return Msg.success().add("resMsg", "插入成功").add("MlbackEmailRichText", mlbackEmailRichText);
		}else{
			//有id,update
			mlbackEmailRichTextService.updateByPrimaryKeySelective(mlbackEmailRichText);
			System.out.println("后台操作:HtmlEmailId不为null,走update+intResult:"+mlbackEmailRichText.toString());
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	zsh20210223
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackEmailRichText mlbackEmailRichText){
		//接收id信息
		Integer emailrichtextId = mlbackEmailRichText.getEmailrichtextId();
		int intResult = mlbackEmailRichTextService.deleteByPrimaryKey(emailrichtextId);
		System.out.println("后台操作:MlbackEmailRichText delete success:"+intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	zsh20210223
	 * 查看单条类目的详情细节
	 * @param MlbackEmailRichText
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackEmailRichTextOneAllDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackEmailRichTextOneAllDetail(@RequestBody MlbackEmailRichText mlbackEmailRichText){
		
		Integer emailrichtextId = mlbackEmailRichText.getEmailrichtextId();
		//接受categoryId
		MlbackEmailRichText mlbackEmailRichTextReq = new MlbackEmailRichText();
		mlbackEmailRichTextReq.setEmailrichtextId(emailrichtextId);
		//查询本条
		MlbackEmailRichText MlbackEmailRichTextOne =mlbackEmailRichTextService.selectByPrimaryKey(emailrichtextId);
		return Msg.success().add("resMsg", "查看单条MlbackEmailRichTextOne的详情细节完毕").add("MlbackEmailRichTextOne", MlbackEmailRichTextOne);
	}
	
	/**
	 * 5.1	zsh20210223
	 * 查看单条类目的详情细节
	 * @param MlbackEmailRichText
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackEmailRichTextOneAllDetailByName",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackEmailRichTextOneAllDetailByName(@RequestBody MlbackEmailRichText mlbackEmailRichTextInto){
		
		String emailrichtextSeoname = mlbackEmailRichTextInto.getEmailrichtextSeoname();
		
		//接受categoryId
		MlbackEmailRichText mlbackEmailRichTextReq = new MlbackEmailRichText();
		mlbackEmailRichTextReq.setEmailrichtextSeoname(emailrichtextSeoname);
		//查询本条
		List<MlbackEmailRichText> MlbackEmailRichTextResList =mlbackEmailRichTextService.selectMlbackEmailRichTextListByName(mlbackEmailRichTextReq);
		MlbackEmailRichText mlbackEmailRichTextOne = new MlbackEmailRichText();
		if(MlbackEmailRichTextResList.size()>0){
			mlbackEmailRichTextOne =MlbackEmailRichTextResList.get(0);
		}else{
			mlbackEmailRichTextOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackEmailRichTextOne的详情细节完毕")
					.add("MlbackEmailRichTextOne", mlbackEmailRichTextOne);
	}
	
}
