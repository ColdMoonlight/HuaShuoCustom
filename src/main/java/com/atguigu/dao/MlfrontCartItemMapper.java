package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlfrontCartItem;

public interface MlfrontCartItemMapper {

    int insert(MlfrontCartItem record);

    MlfrontCartItem selectByPrimaryKey(Integer cartitemId);

    int updateByPrimaryKey(MlfrontCartItem record);
    
    
    //插入新类目00
    int insertSelective(MlfrontCartItem record);
    //删除本条00
    int deleteByPrimaryKey(Integer cartitemId);
    //更新本条00
    int updateByPrimaryKeySelective(MlfrontCartItem record);
    //查询后台登陆用户00
//    List<MlfrontCartItem> selectMlfrontCartItemByConditionS(MlfrontCartItem record);
    //查询单条后台登陆用户00
    List<MlfrontCartItem> selectMlfrontCartItemOne(MlfrontCartItem record);
//    //查询全部登陆用户00
//	List<MlfrontCartItem> selectMlfrontCartItemGetAll();
	//查询全部登陆用户00
	List<MlfrontCartItem> selectMlfrontCartItemById(MlfrontCartItem record);
	//查询最近的100条信息登陆用户00
	List<MlfrontCartItem> selectMlfrontCartItemGetAllHundred();
}