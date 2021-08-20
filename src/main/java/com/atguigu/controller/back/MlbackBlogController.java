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
import org.springframework.web.servlet.ModelAndView;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackBlog;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackBlogService;
import com.atguigu.service.MlbackSearchService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackBlog")
public class MlbackBlogController {
	
	@Autowired
	MlbackBlogService mlbackBlogService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackSearchService mlbackSearchService;
	
	/**
	 * 1.0	20210327
	 * to后台分类MlbackBlog列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackBlogPage")
	public String tologin(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/marketing/mlbackBlogPage";
		}
	}
	
	/**
	 * 1.0	20210327
	 * to后台分类MlbackBlog列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackBlogList")
	public String toMlbackBlogList(HttpSession session) throws Exception{
		
		return "portal/subject/blogList";
	}
	
	/**2.0	20210327
	 * 后台MlbackBlog列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getMlbackBlogByPage")
	@ResponseBody
	public Msg getMlbackBlogByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		List<MlbackBlog> mlbackBlogList = mlbackBlogService.selectMlbackBlogGetAll();
		PageInfo page = new PageInfo(mlbackBlogList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20210327
	 * MlbackBlog	initializaBlog
	 * @param MlbackBlog
	 * @return
	 */
	@RequestMapping(value="/initializaBlog",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaBlog(HttpServletResponse rep,HttpServletRequest res){
		
		MlbackBlog mlbackBlog = new MlbackBlog();
		//取出id
		String nowTime = DateUtil.strTime14s();
		mlbackBlog.setBlogCreatetime(nowTime);
		mlbackBlog.setBlogMotifytime(nowTime);
		mlbackBlog.setBlogStatus(0);
		//System.out.println("插入前"+MlbackBlog.toString());
		mlbackBlogService.insertSelective(mlbackBlog);
		//System.out.println("插入后"+MlbackBlog.toString());
		return Msg.success().add("resMsg", "Product初始化成功").add("MlbackBlog", mlbackBlog);
	}
	
	/**
	 * 4.0	20210327
	 * 查看单个产品的详情
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getOneMlbackBlogDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlbackBlogDetail(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackBlog mlbackBlog){
		//接受信息
		Integer blogIdInt = mlbackBlog.getBlogId();
		
		MlbackBlog mlbackBlogReq = new MlbackBlog();
		mlbackBlogReq.setBlogId(blogIdInt);
		List<MlbackBlog> mlbackBlogResList =mlbackBlogService.selectMlbackBlogByParam(mlbackBlogReq);
		MlbackBlog mlbackBlogOne = new MlbackBlog();
		if(mlbackBlogResList.size()>0){
			//如果用这个id查到,就拿出来.
			mlbackBlogOne = mlbackBlogResList.get(0);
		}else{
			//如果用这个id没查到,就取出当前所有产品最新上的那款.
			mlbackBlogResList = mlbackBlogService.selectMlbackBlogGetAll();
			mlbackBlogOne = mlbackBlogResList.get(0);
		}
		//System.out.println("操作说明：查询-MlbackBlogOne:"+MlbackBlogOne);
		return Msg.success().add("resMsg", "查看单个产品详情完毕").add("MlbackBlogOne", mlbackBlogOne);
	}
	
	/**5.0	20210327
	 * MlbackBlog	update
	 * @param MlbackBlog
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackBlog mlbackBlog){
		//接受参数信息
		String nowTime = DateUtil.strTime14s();
		mlbackBlog.setBlogMotifytime(nowTime);
		
		mlbackBlogService.updateByPrimaryKeySelective(mlbackBlog);
		return Msg.success().add("resMsg", "Blog保存成功");
	}
	
	/**6.0	20210327
	 * MlbackBlog	delete
	 * @param MlbackBlog-BlogId
	 * @return 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackBlog mlbackBlog){
		//接收id信息
		int blogIdInt = mlbackBlog.getBlogId();
		mlbackBlogService.deleteByPrimaryKey(blogIdInt);
		return Msg.success().add("resMsg", "Blog delete  success");
	}
	
	/**
	 * 7.0	20210327
	 * 查看单个产品的详情
	 * @param productId
	 * @return 
	 */
	@RequestMapping(value="/getMlbackBlogSimpleList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackBlogSimpleList(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackBlog mlbackBlog){
		//接受信息
		MlbackBlog mlbackBlogReq = new MlbackBlog();
		mlbackBlogReq.setBlogStatus(1);
		List<MlbackBlog> mlbackBlogResList =mlbackBlogService.selectMlbackBlogSimpleByParam(mlbackBlogReq);
		//System.out.println("操作说明：查询-MlbackBlogOne:"+MlbackBlogOne);
		return Msg.success().add("resMsg", "查看list完毕").add("mlbackBlogResList", mlbackBlogResList);
	}
	
	/**
	 * 7.0	20210327
	 * 后端获取backSearchByBlog产品list
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByBlog",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByBlog(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "blogName") String blogName,
			@RequestParam(value = "blogSuperCateid", defaultValue = "1") Integer blogSuperCateid) throws Exception{
		
		//接收传递进来的参数
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		MlbackBlog mlbackBlogReq = new MlbackBlog();
		mlbackBlogReq.setBlogName(blogName);
		List<MlbackBlog> MlbackBlogResList = mlbackBlogService.selectMlbackBlogBack(mlbackBlogReq);
		PageInfo page = new PageInfo(MlbackBlogResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 8.0	onuse	200103
	 * 前台详情页面wap/pc的BlogDetail
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping(value="/toBlogDetailPageByhtml",method=RequestMethod.GET)
	 public ModelAndView toBlogDetailPageByhtml(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestParam(value = "blogSeoname") String blogSeoname) throws Exception{
	
		//准备封装参数
		MlbackBlog mlbackBlogrepBySeo = new MlbackBlog();
		mlbackBlogrepBySeo.setBlogSeoname(blogSeoname);
		String nowTime = DateUtil.strTime14s();
		System.out.println("nowTime:"+nowTime+"客户点击blogSeoname链接进入: "+blogSeoname);
	
		ModelAndView modelAndView = new ModelAndView();
		
		List<MlbackBlog> MlbackBlogResList = mlbackBlogService.selectMlbackBlogByParam(mlbackBlogrepBySeo);
	  
		if(!(MlbackBlogResList.size()>0)){
			modelAndView.setViewName("redirect:/");
			return modelAndView;
		}else{
			MlbackBlog mlbackBlogRes = MlbackBlogResList.get(0);
			
			Integer blogStatus = mlbackBlogRes.getBlogStatus();
			if(blogStatus==1){
				
				Integer blogIdReq = mlbackBlogRes.getBlogId();
				
				MlbackBlog mlbackBlogrep = new MlbackBlog();
				mlbackBlogrep.setBlogId(blogIdReq);
				//接受信息
				List<MlbackBlog> mbackBlogResList =mlbackBlogService.selectMlbackBlogByParam(mlbackBlogrep);
				modelAndView.addObject("mbackBlogResList", mbackBlogResList);
				//放回响应域中
				res.setAttribute("blogId", blogIdReq);
				//放回session域中
				session.setAttribute("blogDetailId", blogIdReq);
				session.setAttribute("blogMetaTitle", mlbackBlogRes.getBlogMetaTitle());
				session.setAttribute("blogMetaKeywords", mlbackBlogRes.getBlogMetaKeyword());
				session.setAttribute("blogMeteDesc", mlbackBlogRes.getBlogMetaDesc());
				//返回视图
				modelAndView.setViewName("portal/blog/blogDetails");
				return modelAndView;
			}else{
				System.out.println("通过链接点进来的blogSeoname:"+blogSeoname+",但是此产品已下架,跳回首页");
				modelAndView.setViewName("redirect:/");
				return modelAndView;
			}
		}
	 }
	 
}
