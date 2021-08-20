package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackAddCartViewDetail;

public interface MlbackAddCartViewDetailMapper {
    
    int insertSelective(MlbackAddCartViewDetail record);
    
    int deleteByPrimaryKey(Integer addcartviewdetailId);

	List<MlbackAddCartViewDetail> selectMlbackAddCartViewDetailByTimeAndActnum(MlbackAddCartViewDetail mlbackAddCartViewDetail);
}