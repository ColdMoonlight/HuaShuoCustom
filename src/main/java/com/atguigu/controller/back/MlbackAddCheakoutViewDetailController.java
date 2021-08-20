package com.atguigu.controller.back;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackAddCheakoutViewDetail;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.UrlCount;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAddCheakoutViewDetailService;
import com.atguigu.service.UrlCountService;

@Controller
@RequestMapping("/MlbackAddCheakoutViewDetail")
public class MlbackAddCheakoutViewDetailController {
	
	@Autowired
	MlbackAddCheakoutViewDetailService mlbackAddCheakoutViewDetailService;
	
	@Autowired
	UrlCountService urlCountService;
		
	/**
	 * 1.0	zsh200804
	 * toMlbackActShowPro列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toMlbackAddCheakoutViewDetailPage")
	public String tologin(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/report/mlbackAddCheakoutViewDetailPage";
		}
	}

	/**3.0	zsh200804
	 * 计数MlbackAddCheakoutViewDetailByTime
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getAddCheakoutViewDetailNum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAddCheakoutViewDetailNum(HttpSession session,@RequestBody MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetail) {
		
		String starttime = mlbackAddCheakoutViewDetail.getAddcheakoutviewdetailCreatetime();
		String endtime = mlbackAddCheakoutViewDetail.getAddcheakoutviewdetailMotifytime();
		Integer actnum = mlbackAddCheakoutViewDetail.getAddcheakoutviewdetailActnum();
		MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetailreq = new MlbackAddCheakoutViewDetail();
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailCreatetime(starttime);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailMotifytime(endtime);
		mlbackAddCheakoutViewDetailreq.setAddcheakoutviewdetailActnum(actnum);
		List<MlbackAddCheakoutViewDetail> mlbackAddCheakoutViewDetailList = mlbackAddCheakoutViewDetailService.selectMlbackAddCheakoutViewDetailByTimeAndActnum(mlbackAddCheakoutViewDetailreq);
		Integer toDayNum = mlbackAddCheakoutViewDetailList.size();
		return Msg.success().add("toDayNum", toDayNum);
	}
	
	/**4.0	zsh200804
	 * 分类getAddCheakoutViewUrlCountList列表list数据
	 * @param MlbackAddCheakoutViewDetail
	 * @return
	 */
	@RequestMapping(value="/getAddCheakoutViewUrlCountList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAddCheakoutViewUrlCountList(HttpSession session,@RequestBody MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetail) {
		
		String starttime = mlbackAddCheakoutViewDetail.getAddcheakoutviewdetailCreatetime();
		String endtime = mlbackAddCheakoutViewDetail.getAddcheakoutviewdetailMotifytime();
		UrlCount urlCountReq = new UrlCount();
		urlCountReq.setSearchStartTime(starttime);
		urlCountReq.setSearchEndTime(endtime);
		List<UrlCount> urlCountList = urlCountService.selectCheckoutUrlCountByTime(urlCountReq);
		
		return Msg.success().add("urlCountList", urlCountList);
	}

}
