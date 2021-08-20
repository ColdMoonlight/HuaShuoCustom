package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.dao.MlfrontOrderMapper;

@Service
public class MlfrontOrderService {
	
	@Autowired
	MlfrontOrderMapper mlfrontOrderMapper;

	/**1
	 * add方法
	 * */
	public int insertSelective(MlfrontOrder mlfrontOrder) {
		int intReslut = mlfrontOrderMapper.insertSelective(mlfrontOrder);
		return intReslut;
	}
	
	/**2
	 * 删除本条信息
	 * */
	public int deleteByPrimaryKey(int orderId) {
		int  intReslut = mlfrontOrderMapper.deleteByPrimaryKey(orderId);
		return intReslut;
	}

	/**3
	 * 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(MlfrontOrder mlfrontOrder) {
		int  intReslut = mlfrontOrderMapper.updateByPrimaryKeySelective(mlfrontOrder);
		return intReslut;
	}

	/**4
	 * 查单条信息byId
	 * */
	public List<MlfrontOrder> selectMlfrontOrderById(MlfrontOrder mlfrontOrder) {
		List<MlfrontOrder>  mlfrontOrderList = mlfrontOrderMapper.selectMlfrontOrderById(mlfrontOrder);
		return mlfrontOrderList;
	}
	/**5
	 * 查登录用户筛选单个不同状态的order信息List
	 * */
	public List<MlfrontOrder> selectOrderListByUidAndStatus(MlfrontOrder mlfrontOrder) {
		List<MlfrontOrder>  mlfrontOrderList = mlfrontOrderMapper.selectOrderListByUidAndStatus(mlfrontOrder);
		return mlfrontOrderList;
	}
	/**6
	 * 查登录用户筛选全部成功后状态的order信息List
	 * */
	public List<MlfrontOrder> selectOrderListByUidAndSuccessStatus(MlfrontOrder mlfrontOrder) {
		List<MlfrontOrder>  mlfrontOrderList = mlfrontOrderMapper.selectOrderListByUidAndSuccessStatus(mlfrontOrder);
		return mlfrontOrderList;
	}
	/**7
	 * 查百条以内信息
	 * */
	public List<MlfrontOrder> selectMlfrontOrderAllHundred() {
		List<MlfrontOrder>  mlfrontOrderList = mlfrontOrderMapper.selectMlfrontOrderAllHundred();
		return mlfrontOrderList;
	}

	public List<MlfrontOrder> selectMlfrontOrderByTrackingNumber(MlfrontOrder mlfrontOrderReq) {
		List<MlfrontOrder>  mlfrontOrderList = mlfrontOrderMapper.selectMlfrontOrderByTrackingNumber(mlfrontOrderReq);
		return mlfrontOrderList;
	}
	
}
