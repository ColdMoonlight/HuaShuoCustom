package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlbackCouponDescDetail;
import com.atguigu.dao.MlbackCouponDescDetailMapper;

@Service
public class MlbackCouponDescDetailService {
	
	@Autowired
	MlbackCouponDescDetailMapper mlbackCouponDescDetailMapper;
	
	/**1
	 * @author Shinelon
	 * @param MlbackCouponDescDetail
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(MlbackCouponDescDetail mlbackCouponDescDetail) {
		int intReslut = mlbackCouponDescDetailMapper.insertSelective(mlbackCouponDescDetail);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param MlbackCouponDescDetail
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int footnavId) {
		int  intReslut = mlbackCouponDescDetailMapper.deleteByPrimaryKey(footnavId);
		return intReslut;
	}

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlbackCouponDescDetail mlbackCouponDescDetail) {
		int  intReslut = mlbackCouponDescDetailMapper.updateByPrimaryKeySelective(mlbackCouponDescDetail);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlbackCouponDescDetail> selectMlbackCouponDescDetailById(MlbackCouponDescDetail mlbackCouponDescDetail) {
		List<MlbackCouponDescDetail>  mlbackCouponDescDetailList = mlbackCouponDescDetailMapper.selectMlbackCouponDescDetailById(mlbackCouponDescDetail);
		return mlbackCouponDescDetailList;
	}
	
	/**5
	 * @author Shinelon
	 * @param MlbackProduct
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlbackCouponDescDetail> selectMlbackCouponDescDetailAll() {
		List<MlbackCouponDescDetail>  mlbackCouponDescDetailList = mlbackCouponDescDetailMapper.selectMlbackCouponDescDetailGetAll();
		return mlbackCouponDescDetailList;
	}
	
	/**6
	 * @author Shinelon
	 * @param MlbackCouponDescDetail
	 * @exception 查看全部的地址信息
	 * 
	 * */
	public List<MlbackCouponDescDetail> selectMlbackCouponDescDetailByStatus(MlbackCouponDescDetail mlbackCouponDescDetail) {
		List<MlbackCouponDescDetail>  mlbackCouponDescDetailList = mlbackCouponDescDetailMapper.selectMlbackCouponDescDetailByStatus(mlbackCouponDescDetail);
		return mlbackCouponDescDetailList;
	}
	
}
