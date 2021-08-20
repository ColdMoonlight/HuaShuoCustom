package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlfrontCouponLuckyDraw;
import com.atguigu.dao.MlfrontCouponLuckyDrawMapper;

@Service
public class MlfrontCouponLuckyDrawService {
	
	
	@Autowired
	MlfrontCouponLuckyDrawMapper mlfrontCouponLuckyDrawMapper;
	
	/**
	 * @author 210802
	 * @param MlfrontCouponLuckyDraw
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlfrontCouponLuckyDraw MlfrontCouponLuckyDraw) {
		int intReslut = mlfrontCouponLuckyDrawMapper.insertSelective(MlfrontCouponLuckyDraw);
		return intReslut;
	}

	/**
	 * @author 210802
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int id) {
		int  intReslut = mlfrontCouponLuckyDrawMapper.deleteByPrimaryKey(id);
		return intReslut;
	}
	
	/**
	 * @author 210802
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlfrontCouponLuckyDraw MlfrontCouponLuckyDraw) {
		int  intReslut = mlfrontCouponLuckyDrawMapper.updateByPrimaryKeySelective(MlfrontCouponLuckyDraw);
		return intReslut;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 根据id查询该条信息
	 * 
	 * */
	public MlfrontCouponLuckyDraw selectMlfrontCouponLuckyDrawById(Integer cartId) {
		MlfrontCouponLuckyDraw  mlfrontCouponLuckyDraw = mlfrontCouponLuckyDrawMapper.selectByPrimaryKey(cartId);
		return mlfrontCouponLuckyDraw;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 
	 * 获取当前session 下是否有mlfrontCouponLuckyDraw，并返回
	 * 
	 * */
	public List<MlfrontCouponLuckyDraw> selectMlfrontCouponLuckyDrawBySessionId(MlfrontCouponLuckyDraw mlfrontCouponLuckyDraw) {
		List<MlfrontCouponLuckyDraw>  mlfrontCouponLuckyDrawList = mlfrontCouponLuckyDrawMapper.selectMlfrontCouponLuckyDrawBySessionId(mlfrontCouponLuckyDraw);
		return mlfrontCouponLuckyDrawList;
	}
}
