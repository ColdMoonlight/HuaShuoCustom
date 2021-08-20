package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlfrontAddress;

public interface MlfrontAddressMapper {

    int insert(MlfrontAddress record);

    MlfrontAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKey(MlfrontAddress record);
    
    //插入新地区价格模板
    int insertSelective(MlfrontAddress record);
    //删除本条地区价格模板
    int deleteByPrimaryKey(Integer addressId);
    //更新本条地区价格模板
    int updateByPrimaryKeySelective(MlfrontAddress record);
    //查询后台登陆用户00
    List<MlfrontAddress> selectMlfrontAddressByParam(MlfrontAddress example);
//    //查询后台登陆用户00
//    List<MlfrontAddress> selectMlfrontAddressByuId(MlfrontAddress example);
//    //查询后台登陆用户00
//    List<MlfrontAddress> selectMlfrontAddressById(MlfrontAddress example);
//    //查询全部登陆用户00
//	List<MlfrontAddress> selectMlfrontAddressGetAll();
//	
//	MlfrontAddress selectMlfrontAddressByIp(String addressIp);
}