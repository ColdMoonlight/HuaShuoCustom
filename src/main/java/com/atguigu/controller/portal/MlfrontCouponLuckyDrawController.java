package com.atguigu.controller.portal;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlfrontCouponLuckyDraw;
import com.atguigu.common.Msg;
import com.atguigu.service.MlfrontCouponLuckyDrawService;

@Controller
@RequestMapping("/MlfrontCouponLuckyDraw")
public class MlfrontCouponLuckyDrawController {
		
	@Autowired
	MlfrontCouponLuckyDrawService mlfrontCouponLuckyDrawService;
	
	/**
	 * 210802
	 * MlfrontCouponLuckyDraw	insert
	 * @param MlfrontCouponLuckyDraw
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw){
		//接受参数信息
		mlfrontCouponLuckyDrawService.insertSelective(mlfrontCouponLuckyDraw);
		
		return Msg.success().add("resMsg", "新增mlfrontCouponLuckyDraw成功").add("mlfrontCouponLuckyDraw", mlfrontCouponLuckyDraw);
	}
	
	/**
	 * 210802
	 * MlfrontCouponLuckyDraw	delete
	 * @param id
	 */
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg delete(@RequestBody MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw){
//		//接收id信息
//		int id = mlfrontCouponLuckyDraw.getCouponluckydrawId();
//		mlfrontCouponLuckyDrawService.deleteByPrimaryKey(id);
//		return Msg.success().add("resMsg", "delete success");
//	}
	
	/**
	 * 210802
	 * mlfrontCouponLuckyDraw	update
	 * @param  mlfrontCouponLuckyDraw
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Msg update(@RequestBody MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw){
		//更新本条详情
		mlfrontCouponLuckyDrawService.updateByPrimaryKeySelective(mlfrontCouponLuckyDraw);
		return Msg.success().add("resMsg", "Update Successful").add("mlfrontCouponLuckyDraw", mlfrontCouponLuckyDraw);
	}
	
	/**
	 * 210802
	 * 查看单条mlfrontCouponLuckyDraw的详情细节
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontCouponLuckyDrawDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontCouponLuckyDrawDetailById(@RequestBody MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw){
		//接受信息 通过id获取单条详情
		Integer id = mlfrontCouponLuckyDraw.getCouponluckydrawId();
		MlfrontCouponLuckyDraw mlfrontCouponLuckyDrawReturn =mlfrontCouponLuckyDrawService.selectMlfrontCouponLuckyDrawById(id);
		return Msg.success().add("resMsg", "查看单条MlfrontCouponLuckyDraw详情完毕").add("mlfrontCouponLuckyDrawReturn", mlfrontCouponLuckyDrawReturn);
	}	
	
	/**
	 * 210802
	 * 获取当前session 下是否有mlfrontCouponLuckyDraw，并返回
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/selectMlfrontCouponLuckyDrawBySessionId",method=RequestMethod.GET)
	@ResponseBody
	public Msg selectMlfrontCouponLuckyDrawBySessionId(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		//接受信息 通过sessionId获取记录，如果有，则返回该记录，没有，返回空对象
		MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw = new MlfrontCouponLuckyDraw();
		String sessionId = session.getId();
		mlfrontCouponLuckyDraw.setCouponluckydrawSessionid(sessionId);
		List<MlfrontCouponLuckyDraw> mlfrontCouponLuckyDrawList =mlfrontCouponLuckyDrawService.selectMlfrontCouponLuckyDrawBySessionId(mlfrontCouponLuckyDraw);
		MlfrontCouponLuckyDraw mlfrontCouponLuckyDrawReturn = null;
		if(mlfrontCouponLuckyDrawList.size()>0){
			mlfrontCouponLuckyDrawReturn = mlfrontCouponLuckyDrawList.get(0);
		}
		return Msg.success().add("resMsg", "查看当前session下MlfrontCouponLuckyDraw详情完毕").add("mlfrontCouponLuckyDrawReturn", mlfrontCouponLuckyDrawReturn);
	}
	

}
