package com.atguigu.controller.back;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.MlbackSearch;
import com.atguigu.bean.UrlCount;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackSearchService;
import com.atguigu.service.UrlCountService;

@Controller
@RequestMapping("/MlbackSearch")
public class MlbackSearchController {
	
	@Autowired
	MlbackSearchService mlbackSearchService;
	
	@Autowired
	UrlCountService urlCountService;
		
	/**
	 * 1.0	zsh201104
	 * toMlbackSearch列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackSearchDetailPage")
	public String toMlbackSearchDetailPage(HttpSession session) throws Exception{
	
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//mlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/report/mlbackSearchDetailPage";
		}
	}
	
	/**2.0	zsh201104
	 * 计数MlbackAddCheakoutViewDetailByTime
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getSearchListByTime",method=RequestMethod.POST)
	@ResponseBody
	public Msg getSearchListByTime(HttpSession session,@RequestBody MlbackSearch mlbackSearch) {
		
		String starttime = mlbackSearch.getSearchCreatetime();
		String endtime = mlbackSearch.getSearchMotifytime();
		MlbackSearch mlbackSearchreq = new MlbackSearch();
		mlbackSearchreq.setSearchCreatetime(starttime);
		mlbackSearchreq.setSearchMotifytime(endtime);
		List<MlbackSearch> mlbackSearchList = mlbackSearchService.selectSearchListByTime(mlbackSearchreq);
		Integer toDayNum = mlbackSearchList.size();
		return Msg.success().add("toDayNum", toDayNum);
	}
	
	/**3.0	zsh201104
	 * 分类MlbackSearch列表list数据
	 * @param	MlbackSearch
	 * @return
	 */
	@RequestMapping(value="/getSearchUrlCountList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getSearchUrlCountList(HttpSession session,@RequestBody MlbackSearch mlbackSearch) {
		
		String starttime = mlbackSearch.getSearchCreatetime();
		String endtime = mlbackSearch.getSearchMotifytime();
		UrlCount urlCountReq = new UrlCount();
		urlCountReq.setSearchStartTime(starttime);
		urlCountReq.setSearchEndTime(endtime);
		
		List<UrlCount> urlCountList = urlCountService.selectSearchUrlCountByTime(urlCountReq);
		
		return Msg.success().add("urlCountList", urlCountList);
	}

}
