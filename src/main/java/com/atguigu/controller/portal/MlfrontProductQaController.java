package com.atguigu.controller.portal;

import java.util.ArrayList;
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
import com.atguigu.bean.MlbackProduct;
import com.atguigu.bean.MlfrontProductQa;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackProductService;
import com.atguigu.service.MlfrontProductQaService;
import com.atguigu.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping("/MlfrontProductQa")
public class MlfrontProductQaController {
		
	@Autowired
	MlfrontProductQaService mlfrontProductQaService;
	
	@Autowired
	MlbackProductService mlbackProductService;
	
	/**
	 * 1.0	20200703
	 * to后台分类MlfrontProductQa列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toMlfrontProductQaPage")
	public String toMlfrontProductQaPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/product/mlfrontProductQaPage";
		}
	}
	/**
	 * 4.0	useOn	0505
	 * 后台MlfrontProductQa列表分页list数据
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getAllMlfrontProductQa")
	@ResponseBody
	public Msg getAllMlfrontProductQa(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session){
		//查询全部QA
		int PagNum = Const.PAGE_NUM_CATEGORY;
		PageHelper.startPage(pn, PagNum);
		List<MlfrontProductQa> MlfrontProductQaResList = mlfrontProductQaService.selectMlfrontProductQaGetAll();
		PageInfo page = new PageInfo(MlfrontProductQaResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	/**1.0	UseNow	1202
	 * MlfrontProductQa	insert
	 * @param MlfrontProductQa
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlfrontProductQa mlfrontProductQa){
		//接受参数信息
		mlfrontProductQaService.insertSelective(mlfrontProductQa);
		
		List<MlfrontProductQa> mlfrontProductQaResList = mlfrontProductQaService.selectMlfrontProductQaGetAll();
		int qaId = mlfrontProductQaResList.get(0).getProductqaId();
		//产品ids (QA模块绑定产品id)
		String productIds = mlfrontProductQa.getProductqaProductIds();
		if(StringUtil.isNotEmpty(productIds)){
			//更新每个产品，绑定QA模块
			//updateProductQAId(productIds,qaId);
		}
		return Msg.success().add("resMsg", "新增QA模块成功").add("MlfrontProductQa", mlfrontProductQa);
	}
	
	/**2.0	useOn	0505
	 * MlfrontProductQa	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlfrontProductQa mlfrontProductQa){
		//接收id信息
		int productqaId = mlfrontProductQa.getProductqaId();
		mlfrontProductQaService.deleteByPrimaryKey(productqaId);
		return Msg.success().add("resMsg", "delete success");
	}
	
	/**3.0	useOn	0505
	 * MlfrontProductQa	update
	 * @param id MlfrontProductQa
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Msg update(@RequestBody MlfrontProductQa mlfrontProductQa){
		//接受信息
		int qaId = mlfrontProductQa.getProductqaId();
		//产品ids (QA模块绑定产品id)
		String productIds = mlfrontProductQa.getProductqaProductIds();
		if(StringUtil.isNotEmpty(productIds)){
			//更新每个产品，绑定QA模块
			//updateProductQAId(productIds,qaId);
		}
		String nowtime = DateUtil.strTime14s();
		mlfrontProductQa.setProductqaModifyTime(nowtime);
		//更新本条状态
		mlfrontProductQaService.updateByPrimaryKeySelective(mlfrontProductQa);
		return Msg.success().add("resMsg", "更新成功");
	}
	
	/**
	 * 4.0	useOn	0505
	 * 查看单条信息
	 * @param MlbackAreafreight
	 * @return 
	 */
	@RequestMapping(value="/getOneMlfrontProductQaDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneMlfrontProductQaDetail(@RequestParam(value = "productqaId") Integer productqaId){
		
		//接受categoryId
		MlfrontProductQa mlfrontProductQaReq = new MlfrontProductQa();
		mlfrontProductQaReq.setProductqaId(productqaId);
		//查询本条
		List<MlfrontProductQa> mlfrontProductQaResList = mlfrontProductQaService.selectMlfrontProductQaById(mlfrontProductQaReq);
		MlfrontProductQa mlfrontProductQaOne =mlfrontProductQaResList.get(0);
		return Msg.success().add("resMsg", "查看单条mlfrontProductQaOne的详情细节完毕")
					.add("MlfrontProductQaOne", mlfrontProductQaOne);
	}
	
	/**
	 * 
	 * 更新产品所归属QA模块
	 */
	private void updateProductQAId(String productIds, int qaId) {
		if(StringUtil.isNotEmpty(productIds)){
			String[] pIdsArr =  productIds.split(",");
			
			for(String pId : pIdsArr){
				MlbackProduct mlbackProductReq = new MlbackProduct();
				mlbackProductReq.setProductId(Integer.parseInt(pId));
				
			}
		}
	}
	
	
}
