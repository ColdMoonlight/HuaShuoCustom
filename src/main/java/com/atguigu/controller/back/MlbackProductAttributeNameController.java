package com.atguigu.controller.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MlbackProductAttributeName;
import com.atguigu.common.Msg;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.MlbackProductAttributeNameService;
import com.atguigu.service.MlbackProductService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/MlbackProductAttributeName")
public class MlbackProductAttributeNameController {
		
	@Autowired
	MlbackProductAttributeNameService mlbackProductAttributeNameService;
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	MlbackProductService mlbackProductService;

	/**1.0	UseNow	0505
	 * MlbackChannel	insert
	 * @param MlbackChannel
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProductAttributeName mlbackProductAttributeName){
		//接受参数信息
		//获取类名
		Integer productattrnameId = mlbackProductAttributeName.getProductattrnameId();
		String nowtime = DateUtil.strTime14s();
		if(productattrnameId==null){
			mlbackProductAttributeName.setProductattrnameCreatetime(nowtime);
			mlbackProductAttributeName.setProductattrnameStatus(0);
			//无id,insert
			mlbackProductAttributeNameService.insertSelective(mlbackProductAttributeName);
			System.out.println("mlbackProductAttributeName insert success+intResult:"+mlbackProductAttributeName.toString());
			return Msg.success().add("resMsg", "插入成功").add("mlbackProductAttributeName", mlbackProductAttributeName);
		}else{
			//有id,update
			mlbackProductAttributeNameService.updateByPrimaryKeySelective(mlbackProductAttributeName);
			return Msg.success().add("resMsg", "更新成功");
		}		
	}
	
	/**2.0	onuse	200104
	 * MlbackProductAttributeName	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(@RequestBody MlbackProductAttributeName MlbackProductAttributeName){
		//接收id信息
		Integer productimgId = MlbackProductAttributeName.getProductattrnameId();
		//本条存在,执行删除
		mlbackProductAttributeNameService.deleteByPrimaryKey(productimgId);
		return Msg.success().add("resMsg", "delete success");

	}
	
	/**
	 * 3.0	onuse	200104
	 * 查看单条id下的细节详情图
	 * @param MlbackProductAttributeName
	 * @return 
	 */
	@RequestMapping(value="/getMlbackProductAttributeNameListByProductId",method=RequestMethod.POST)
	@ResponseBody
	public Msg getMlbackProductAttributeNameListByProductId(HttpServletResponse rep,HttpServletRequest res,@RequestBody MlbackProductAttributeName mlbackProductAttributeName){
		
		Integer productId = mlbackProductAttributeName.getProductattrnamePid();
		//接受信息
		List<MlbackProductAttributeName> mbackProductAttributeNameResList =mlbackProductAttributeNameService.selectMlbackProductAttributeNameByProductId(productId);
		return Msg.success().add("resMsg", "查看本productId下的所有属性,完毕")
					.add("mbackProductAttributeNameResList", mbackProductAttributeNameResList);
	}
	
}
