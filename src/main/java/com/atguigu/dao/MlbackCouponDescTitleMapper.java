package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackCouponDescTitle;

public interface MlbackCouponDescTitleMapper {

    int insert(MlbackCouponDescTitle record);

    MlbackCouponDescTitle selectByPrimaryKey(Integer coupondesctieleId);

    int updateByPrimaryKey(MlbackCouponDescTitle record);
    
    //插入新类目
    int insertSelective(MlbackCouponDescTitle record);
    //删除本条
    int deleteByPrimaryKey(Integer coupondesctieleId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackCouponDescTitle record);
    //通过id查询本条
    MlbackCouponDescTitle selectMlbackCouponDescTitleById(MlbackCouponDescTitle example);
    //查询全部
	List<MlbackCouponDescTitle> selectMlbackCouponDescTitleGetAll();

	List<MlbackCouponDescTitle> selectMlbackCouponDescTitleListByStatus(MlbackCouponDescTitle mlbackCouponDescTitle);
}