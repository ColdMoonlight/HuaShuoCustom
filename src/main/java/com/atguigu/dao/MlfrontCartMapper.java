package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.MlfrontCart;

public interface MlfrontCartMapper {
	
    int insert(MlfrontCart record);

    MlfrontCart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKey(MlfrontCart record);
    
    //插入新类目00
    int insertSelective(MlfrontCart record);
    //删除本条00
    int deleteByPrimaryKey(Integer cartId);
    //更新本条00
    int updateByPrimaryKeySelective(MlfrontCart record);
	//通过时间查询全部登陆用户00
	List<MlfrontCart> selectMlfrontCartByDate(MlfrontCart mlfrontCart);
	//查询时间端查询购物车
	List<MlfrontCart> selectMlfrontCartGetAllHundred();
	//查询ip查询购物车
	List<MlfrontCart> selectMlfrontCartByIp(MlfrontCart record);
	//查询UidAndStatus查询购物车
	List<MlfrontCart> selectMlfrontCartByUidAndStatus(MlfrontCart record);
	//查询CartId查询购物车
	MlfrontCart selectMlfrontCartByCartId(Integer cartId);
}