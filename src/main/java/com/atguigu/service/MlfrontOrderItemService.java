package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.dao.MlfrontOrderItemMapper;

@Service
public class MlfrontOrderItemService {
	
	@Autowired
	MlfrontOrderItemMapper mlfrontOrderItemMapper;

	/**1
	 * @author Shinelon
	 * @param MlfrontOrderItem
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlfrontOrderItem mlfrontOrderItem) {
		int intReslut = mlfrontOrderItemMapper.insertSelective(mlfrontOrderItem);
		return intReslut;
	}
	
	/**2
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int orderitemId) {
		int  intReslut = mlfrontOrderItemMapper.deleteByPrimaryKey(orderitemId);
		return intReslut;
	}

	/**3
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlfrontOrderItem mlfrontOrderItem) {
		int  intReslut = mlfrontOrderItemMapper.updateByPrimaryKeySelective(mlfrontOrderItem);
		return intReslut;
	}
	
	/**4
	 * @author Shinelon
	 * @param MlfrontOrder
	 * @exception 查看全部的地址信息
	 * 
	 * */
//	public List<MlfrontOrderItem> selectMlfrontOrderItemAll() {
//		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemGetAll();
//		return mlfrontOrderItemList;
//	}
//	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontOrderItem> selectMlfrontOrderItemById(MlfrontOrderItem mlfrontOrderItem) {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemById(mlfrontOrderItem);
		return mlfrontOrderItemList;
	}
	
	/**5
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public List<MlfrontOrderItem> selectMlfrontOrderItemByOrderId(MlfrontOrderItem mlfrontOrderItem) {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemByOrderId(mlfrontOrderItem);
		return mlfrontOrderItemList;
	}
//	
//	/**5
//	 * @author Shinelon
//	 * @param 
//	 * @exception 查看全部用户信息
//	 * 
//	 * */
//	public List<MlfrontOrderItem> selectMlfrontOrderItemByNowTime(String nowTime) {
//		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemByNowTime(nowTime);
//		return mlfrontOrderItemList;
//	}
//	
//	/**6
//	 * @author Shinelon
//	 * @param 
//	 * @exception time查询
//	 * 
//	 * */
//	public List<MlfrontOrderItem> selectMlfrontOrderItemByMotifyTime(MlfrontOrderItem mlfrontOrderItem) {
//		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemByMotifyTime(mlfrontOrderItem);
//		return mlfrontOrderItemList;
//	}
//
	
	public List<MlfrontOrderItem> selectMlfrontOrderItemAllHundred() {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemAllHundred();
		return mlfrontOrderItemList;
	}

	public List<MlfrontOrderItem> selectMlfrontOrderItemByParam(MlfrontOrderItem mlfrontOrderItem) {
		List<MlfrontOrderItem>  mlfrontOrderItemList = mlfrontOrderItemMapper.selectMlfrontOrderItemByParam(mlfrontOrderItem);
		return mlfrontOrderItemList;
	}
}
