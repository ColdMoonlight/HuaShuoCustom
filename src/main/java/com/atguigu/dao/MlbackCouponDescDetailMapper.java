package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackCouponDescDetail;

public interface MlbackCouponDescDetailMapper {

    int insert(MlbackCouponDescDetail record);

    MlbackCouponDescDetail selectByPrimaryKey(Integer coupondescdetailId);

    int updateByPrimaryKey(MlbackCouponDescDetail record);
    
    //插入新类目00
    int insertSelective(MlbackCouponDescDetail record);
    //删除本条00
    int deleteByPrimaryKey(Integer coupondescdetailId);
    //更新本条00
    int updateByPrimaryKeySelective(MlbackCouponDescDetail record);
    //查询后台登陆用户00
    List<MlbackCouponDescDetail> selectMlbackCouponDescDetailById(MlbackCouponDescDetail record);
    //查询全部登陆用户00
	List<MlbackCouponDescDetail> selectMlbackCouponDescDetailGetAll();

	//简单查询底部导航
	List<MlbackCouponDescDetail> selectMlbackCouponDescDetailByStatus(MlbackCouponDescDetail record);
}