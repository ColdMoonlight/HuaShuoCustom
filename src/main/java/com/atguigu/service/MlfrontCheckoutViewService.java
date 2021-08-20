package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontCheckoutView;
import com.atguigu.dao.MlfrontCheckoutViewMapper;

@Service
public class MlfrontCheckoutViewService {	
	
	@Autowired
	MlfrontCheckoutViewMapper mlfrontCheckoutViewMapper;
	
	/**
	 * @author Shinelon
	 * @param mlfrontCheckoutView
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlfrontCheckoutView mlfrontCheckoutView) {
		int intReslut = mlfrontCheckoutViewMapper.insertSelective(mlfrontCheckoutView);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int addcartviewdetailId) {
		int  intReslut = mlfrontCheckoutViewMapper.deleteByPrimaryKey(addcartviewdetailId);
		return intReslut;
	}
	
	public int updateByPrimaryKeySelective(MlfrontCheckoutView mlfrontCheckoutView) {
		int intReslut = mlfrontCheckoutViewMapper.updateByPrimaryKeySelective(mlfrontCheckoutView);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看信息byTime
	 * */
	public List<MlfrontCheckoutView> selectMlfrontCheckoutViewByPayinfoid(MlfrontCheckoutView mlfrontCheckoutView) {
		List<MlfrontCheckoutView>  mlbackProductViewDetailList = mlfrontCheckoutViewMapper.selectMlfrontCheckoutViewByPayinfoid(mlfrontCheckoutView);
		return mlbackProductViewDetailList;
	}

}
