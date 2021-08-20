package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlfrontOrder;

public interface MlfrontOrderMapper {

    int insert(MlfrontOrder record);

    MlfrontOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKey(MlfrontOrder record);
    
    //插入新地区价格模板
    int insertSelective(MlfrontOrder record);
    //删除本条地区价格模板
    int deleteByPrimaryKey(Integer orderId);
    //更新本条地区价格模板
    int updateByPrimaryKeySelective(MlfrontOrder record);
    //查询后台登陆用户00
    List<MlfrontOrder> selectMlfrontOrderById(MlfrontOrder example);
    
	List<MlfrontOrder> selectOrderListByUidAndStatus(MlfrontOrder mlfrontOrder);
	
	List<MlfrontOrder> selectOrderListByUidAndSuccessStatus(MlfrontOrder mlfrontOrder);

	List<MlfrontOrder> selectMlfrontOrderAllHundred();

	List<MlfrontOrder> selectMlfrontOrderByTrackingNumber(MlfrontOrder mlfrontOrderReq);
}