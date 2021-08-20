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
import com.atguigu.bean.CustomerWholesale;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atguigu.service.CustomerWholesaleService;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/CustomerWholesale")
public class CustomerWholesaleController {
	
	@Autowired
	CustomerWholesaleService customerWholesaleService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	/**
	 * 1.0	20200703
	 * to后台分类MlbackCatalog列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toCustomerWholesalePage")
	public String toCustomerWholesalePage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//MlbackAdmin对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/order/customerWholesalePage";
		}
	}
	
	/**2.0	20200703
	 * 后台MlbackCatalog列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getCustomerWholesaleByPage")
	@ResponseBody
	public Msg getCustomerWholesaleByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = Const.PAGE_NUM_CATEGORY;
		PageHelper.startPage(pn, PagNum);
		List<CustomerWholesale> customerWholesaleList = customerWholesaleService.selectMenuCustomerWholesaleGetAll();
		PageInfo page = new PageInfo(customerWholesaleList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**3.0	20200703
	 * customerWholesale	update
	 * @param customerWholesale
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody CustomerWholesale customerWholesale){
		//接受参数信息
		System.out.println("customerWholesale:"+customerWholesale);
		String wholesaleMessage = customerWholesale.getWholesaleCustomerMessage();
		System.out.println("wholesaleMessage:"+wholesaleMessage);
		String afterMessage = wholesaleMessage.replaceAll("[^\\u0000-\\uFFFF]", "");
		//取出id
		Integer wholesaleId = customerWholesale.getWholesaleId();
		String nowTime = DateUtil.strTime14s();
		customerWholesale.setWholesaleMotifytime(nowTime);
		customerWholesale.setWholesaleCustomerMessage(afterMessage);
		if(wholesaleId==null){
			//无id,insert
			customerWholesale.setWholesaleCreatetime(nowTime);
			customerWholesaleService.insertSelective(customerWholesale);
			System.out.println("customerWholesale--insertSuccess--:"+customerWholesale);
			return Msg.success().add("resMsg", "插入成功");
		}else{
			//有id,update
			customerWholesaleService.updateByPrimaryKeySelective(customerWholesale);
			return Msg.success().add("resMsg", "更新成功");
			
		}
	}
	
	/**4.0	20200703
	 * CustomerWholesale	delete
	 * @param CustomerWholesale-wholesaleId
	 * @return 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody CustomerWholesale customerWholesale){
		//接收id信息
		Integer wholesaleId = customerWholesale.getWholesaleId();
		customerWholesaleService.deleteByPrimaryKey(wholesaleId);
		return Msg.success().add("resMsg", "Catalog delete  success");
	}
	
	/**
	 * 6.0	20200703
	 * 查单条CustomerWholesale详情
	 * @param CustomerWholesale-wholesaleId
	 * @return 
	 */
	@RequestMapping(value="/getOneCustomerWholesaleDetail",method=RequestMethod.POST)
	@ResponseBody
	public Msg getOneCustomerWholesaleDetail(@RequestBody CustomerWholesale customerWholesale){
		
		//接受wholesaleId
		Integer wholesaleId = customerWholesale.getWholesaleId();
		CustomerWholesale customerWholesaleReq = new CustomerWholesale();
		customerWholesaleReq.setWholesaleId(wholesaleId);
		//查询本条
		List<CustomerWholesale> customerWholesaleResList =customerWholesaleService.selectCustomerWholesaleById(customerWholesaleReq);
		CustomerWholesale customerWholesaleOne =customerWholesaleResList.get(0);
		return Msg.success().add("resMsg", "查CatalogOne完毕").add("customerWholesaleOne", customerWholesaleOne);
	}
	
	/**
	 * 7.0	20200608
	 * 后端获取backSearchByWholesale产品list
	 * @return 
	 * */
	@RequestMapping(value="/backSearchByWholesale",method=RequestMethod.POST)
	@ResponseBody
	public Msg backSearchByProduct(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "wholesaleCustomerName") String wholesaleCustomerName,
			@RequestParam(value = "wholesaleCustomerStatus", defaultValue = "1") String wholesaleCustomerStatus) throws Exception{
		
		//接收传递进来的参数
		int PagNum = 30;
		PageHelper.startPage(pn, PagNum);
		
		CustomerWholesale customerWholesaleReq = new CustomerWholesale();
		customerWholesaleReq.setWholesaleCustomerStatus(wholesaleCustomerStatus);
		customerWholesaleReq.setWholesaleCustomerName(wholesaleCustomerName);
		List<CustomerWholesale> customerWholesaleResList = customerWholesaleService.selectCustomerWholesaleBackSearch(customerWholesaleReq);
		PageInfo page = new PageInfo(customerWholesaleResList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
}
