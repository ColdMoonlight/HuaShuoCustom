package com.atguigu.controller.back;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackAddCartViewDetail;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.UrlCount;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAddCartViewDetailService;
import com.atguigu.service.UrlCountService;

@Controller
@RequestMapping("/MlbackAddCartViewDetail")
public class MlbackAddCartViewDetailController {
	
	@Autowired
	MlbackAddCartViewDetailService mlbackAddCartViewDetailService;
	
	@Autowired
	UrlCountService urlCountService;
		
	/**
	 * 1.0	zsh200804
	 * toMlbackActShowPro列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlbackAddCartViewDetailPage")
	public String tologin(HttpSession session) throws Exception{
	
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//mlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/report/mlbackAddCartViewDetailPage";
		}
	}
	
	/**3.0	zsh200804
	 * 分类MlbackActShowPro列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getAddCartViewDetailNumByTimeAndActnum",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAddCartViewDetailNum(HttpSession session,@RequestBody MlbackAddCartViewDetail mlbackAddCartViewDetail) {
		
		String starttime = mlbackAddCartViewDetail.getAddcartviewdetailCreatetime();
		String endtime = mlbackAddCartViewDetail.getAddcartviewdetailMotifytime();
		Integer cartviewdetailActnum = mlbackAddCartViewDetail.getAddcartviewdetailActnum();
		MlbackAddCartViewDetail mlbackAddCartViewDetailreq = new MlbackAddCartViewDetail();
		mlbackAddCartViewDetailreq.setAddcartviewdetailCreatetime(starttime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailMotifytime(endtime);
		mlbackAddCartViewDetailreq.setAddcartviewdetailActnum(cartviewdetailActnum);
		List<MlbackAddCartViewDetail> mlbackAddCartViewDetailList = mlbackAddCartViewDetailService.selectMlbackAddCartViewDetailByTimeAndActnum(mlbackAddCartViewDetailreq);
		Integer toDayNum = mlbackAddCartViewDetailList.size();
		return Msg.success().add("toDayNum", toDayNum);
	}
	
	/**4.0	zsh200804
	 * 分类getAddCartViewDetailList列表list数据
	 * @param	MlbackAddCartViewDetail
	 * @return
	 */
	@RequestMapping(value="/getAddCartViewUrlCountList",method=RequestMethod.POST)
	@ResponseBody
	public Msg getAddCartViewUrlCountList(HttpSession session,@RequestBody MlbackAddCartViewDetail mlbackAddCartViewDetail) {
		
		String starttime = mlbackAddCartViewDetail.getAddcartviewdetailCreatetime();
		String endtime = mlbackAddCartViewDetail.getAddcartviewdetailMotifytime();
		UrlCount urlCountReq = new UrlCount();
		urlCountReq.setSearchStartTime(starttime);
		urlCountReq.setSearchEndTime(endtime);
		
		List<UrlCount> urlCountList = urlCountService.selectCartUrlCountByTime(urlCountReq);
		
		return Msg.success().add("urlCountList", urlCountList);
	}

}
