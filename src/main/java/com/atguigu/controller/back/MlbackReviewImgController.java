package com.atguigu.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackReviewImg;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackReviewImgService;

@Controller
@RequestMapping("/MlbackReviewImg")
public class MlbackReviewImgController {
		
	@Autowired
	MlbackReviewImgService mlbackReviewImgService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	onuse	200104
	 * 查看单条id下的细节详情图
	 * @param reviewId
	 * @return 
	 */
	@RequestMapping(value="/getMlbackReviewImgListByReviewId",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackReviewImgListByProductId(@RequestParam(value = "reviewId") Integer reviewId){
		
		//接受信息
		MlbackReviewImg mlbackReviewImgReq = new MlbackReviewImg();
		//传入参数设置
		mlbackReviewImgReq.setReviewId(reviewId);
		List<MlbackReviewImg> mlbackReviewImgResList =mlbackReviewImgService.selectMlbackReviewImgByReviewId(reviewId);
		return Msg.success().add("resMsg", "查看本reviewimgId下的所有小图,完毕")
					.add("mlbackReviewImgResList", mlbackReviewImgResList);
	}
	
	/**2.0	onuse	200104
	 * MlbackReviewImg	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackReviewImg mlbackReviewImg){
		//接收id信息
		Integer reviewimgId = mlbackReviewImg.getReviewimgId();
		mlbackReviewImgService.deleteByPrimaryKey(reviewimgId);
		return Msg.success().add("resMsg", "delete success");
	}
	
}
