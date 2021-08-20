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
import com.atguigu.bean.MlbackHtmlEmail;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackHtmlEmailService;
import com.atguigu.service.MlfrontPayInfoService;
import com.atguigu.utils.EmailHtmlUtil;

@Controller
@RequestMapping("/MlbackHtmlEmail")
public class MlbackHtmlEmailController {
		
	@Autowired
	MlbackHtmlEmailService mlbackHtmlEmailService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlfrontPayInfoService mlfrontPayInfoService;
	
	/**
	 * 1.0	zsh20210223
	 * toMlbackHtmlEmail列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackHtmlEmailPage")
	public String toMlbackHtmlEmailPage(HttpSession session) throws Exception{
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
//		if(mlbackAdmin==null){
//			//MlbackAdmin对象为空
//			return "back/mlbackAdminLogin";
//		}else{
			return "back/email/mlbackHtmlEmailPage";
//		}
	}
	
	
	/**2.0	zsh20210223
	 * 分类MlbackHtmlEmail列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackHtmlEmailByPage")
	@ResponseBody
	public Msg getMlbackHtmlEmailByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
		int PagNum = 20;
			PageHelper.startPage(pn, PagNum);
			List<MlbackHtmlEmail> mlbackHtmlEmailList = mlbackHtmlEmailService.selectMlbackHtmlEmailAll();
			PageInfo page = new PageInfo(mlbackHtmlEmailList, PagNum);
			return Msg.success().add("pageInfo", page);
//		}
	}
	
	/**3.0	zsh20210223
	 * MlbackHtmlEmail	initializaHtmlEmail
	 * @param MlbackHtmlEmail
	 * @return
	 */
	@RequestMapping(value="/initializaHtmlEmail",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaHtmlEmail(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackHtmlEmail mlbackHtmlEmail = new MlbackHtmlEmail();
		mlbackHtmlEmail.setHtmlemailStatus(0);//0不生效1生效
		mlbackHtmlEmailService.insertSelective(mlbackHtmlEmail);
		System.out.println("插入后"+mlbackHtmlEmail.toString());
		return Msg.success().add("resMsg", "HtmlEmail初始化成功").add("mlbackHtmlEmail", mlbackHtmlEmail);
	}
	
	/**3.0	zsh20210223
	 * MlbackAreafreight	insert
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackHtmlEmail mlbackHtmlEmail){
		//接受参数信息
		//取出id
		Integer htmlemailId = mlbackHtmlEmail.getHtmlemailId();
		if(htmlemailId==null){
			//无id,insert
			mlbackHtmlEmailService.insertSelective(mlbackHtmlEmail);
			System.out.println("后台操作:HtmlEmailId为null,走add+intResult:"+mlbackHtmlEmail.toString());
			return Msg.success().add("resMsg", "插入成功").add("mlbackHtmlEmail", mlbackHtmlEmail);
		}else{
			//有id,update
			mlbackHtmlEmailService.updateByPrimaryKeySelective(mlbackHtmlEmail);
			System.out.println("后台操作:HtmlEmailId不为null,走update+intResult:"+mlbackHtmlEmail.toString());
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**4.0	zsh20210223
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackHtmlEmail mlbackHtmlEmail){
		//接收id信息
		Integer htmlemailId = mlbackHtmlEmail.getHtmlemailId();
		int intResult = mlbackHtmlEmailService.deleteByPrimaryKey(htmlemailId);
		System.out.println("后台操作:MlbackHtmlEmail delete success:"+intResult);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**
	 * 5.0	zsh20210223
	 * 查看单条类目的详情细节
	 * @param MlbackHtmlEmail
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackHtmlEmailOneAllDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackHtmlEmailOneAllDetail(@RequestBody MlbackHtmlEmail mlbackHtmlEmail){
		
		Integer htmlemailId = mlbackHtmlEmail.getHtmlemailId();
		//接受categoryId
		MlbackHtmlEmail mlbackHtmlEmailReq = new MlbackHtmlEmail();
		mlbackHtmlEmailReq.setHtmlemailId(htmlemailId);
		//查询本条
		MlbackHtmlEmail mlbackHtmlEmailOne =mlbackHtmlEmailService.selectByPrimaryKey(htmlemailId);
		return Msg.success().add("resMsg", "查看单条mlbackHtmlEmailOne的详情细节完毕").add("mlbackHtmlEmailOne", mlbackHtmlEmailOne);
	}
	
	/**
	 * 5.1	zsh20210223
	 * 查看单条类目的详情细节
	 * @param MlbackHtmlEmail
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackHtmlEmailOneAllDetailByName",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackHtmlEmailOneAllDetailByName(@RequestBody MlbackHtmlEmail mlbackHtmlEmailInto){
		
		String htmlemailName = mlbackHtmlEmailInto.getHtmlemailName();
		
		//接受categoryId
		MlbackHtmlEmail MlbackHtmlEmailReq = new MlbackHtmlEmail();
		MlbackHtmlEmailReq.setHtmlemailName(htmlemailName);
		MlbackHtmlEmailReq.setHtmlemailStatus(1);
		//查询本条
		List<MlbackHtmlEmail> mlbackHtmlEmailResList =mlbackHtmlEmailService.selectMlbackHtmlEmailListByName(MlbackHtmlEmailReq);
		MlbackHtmlEmail mlbackHtmlEmailOne = new MlbackHtmlEmail();
		if(mlbackHtmlEmailResList.size()>0){
			mlbackHtmlEmailOne =mlbackHtmlEmailResList.get(0);
		}else{
			mlbackHtmlEmailOne = null;
		}
		return Msg.success().add("resMsg", "查看单条MlbackHtmlEmailOne的详情细节完毕")
					.add("mlbackHtmlEmailOne", mlbackHtmlEmailOne);
	}
	
	@RequestMapping(value="/tosendMlbackHtmlEmailOneContent",method=RequestMethod.POST)
	@ResponseBody
	public Msg tosendMlbackHtmlEmailOneContent(@RequestBody MlbackHtmlEmail mlbackHtmlEmail){
		
		String content = mlbackHtmlEmail.getHtmlemailSeven();
		String userEmail = mlbackHtmlEmail.getHtmlemailSix();
		
		EmailHtmlUtil.sendHtmlUnCheckoutEmil(userEmail,content) ;
		
		
		Integer payInfoId = mlbackHtmlEmail.getHtmlemailId();
		
		MlfrontPayInfo mlfrontPayInfoSMSed = new MlfrontPayInfo();
		mlfrontPayInfoSMSed.setPayinfoId(payInfoId);
		mlfrontPayInfoSMSed.setPayinfoIfEmail(1);
		//2-需要把这一单的payinfo_SMS更新过来
		mlfrontPayInfoService.updateByPrimaryKeySelective(mlfrontPayInfoSMSed);
		
		return Msg.success().add("resMsg", "邮件发送成功");
	}
	
}
