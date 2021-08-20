package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackCoupon;
import com.atguigu.dao.MlbackCouponMapper;

@Service
public class MlbackCouponService {
	
	@Autowired
	MlbackCouponMapper mlbackCouponMapper;
	
	/**
	 * @author Shinelon
	 * @param MlbackCoupon
	 * @exception add方法mlbackCoupon
	 * 
	 * */
	public int insertSelective(MlbackCoupon mlbackCoupon) {
		int intReslut = mlbackCouponMapper.insertSelective(mlbackCoupon);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param MlbackCoupon
	 * @exception 删除本条mlbackCoupon
	 * */
	public int deleteByPrimaryKey(int couponId) {
		int  intReslut = mlbackCouponMapper.deleteByPrimaryKey(couponId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条mlbackCoupon
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackCoupon mlbackCoupon) {
		int  intReslut = mlbackCouponMapper.updateByPrimaryKeySelective(mlbackCoupon);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception 查看mlbackCoupon是否存在
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCoupon(MlbackCoupon mlbackCoupon) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponByConditionS(mlbackCoupon);
		return mlbackCouponList;
	}
	/**
	 * @author Shinelon
	 * @param MlfrontUser
	 * @exception 查看mlbackCoupon是否存在
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCouponBYCode(MlbackCoupon mlbackCoupon) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponBYCode(mlbackCoupon);
		return mlbackCouponList;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部mlbackCoupon信息
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCouponGetAll() {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponGetAll();
		return mlbackCouponList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查询生效中得抽奖优惠券
	 * */
	public List<MlbackCoupon> selectMlbackCouponByLuckDrawType(MlbackCoupon mlbackCoupon) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponByLuckDrawType(mlbackCoupon);
		return mlbackCouponList;
	}

	public List<MlbackCoupon> selectMlbackCouponBackSearch(MlbackCoupon mlbackCouponReq) {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponBackSearch(mlbackCouponReq);
		return mlbackCouponList;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部mlbackCoupon信息,并按状态排序
	 * 
	 * */
	public List<MlbackCoupon> selectMlbackCouponOrderByStatus() {
		List<MlbackCoupon>  mlbackCouponList = mlbackCouponMapper.selectMlbackCouponOrderByStatus();
		return mlbackCouponList;
	}
	
}
