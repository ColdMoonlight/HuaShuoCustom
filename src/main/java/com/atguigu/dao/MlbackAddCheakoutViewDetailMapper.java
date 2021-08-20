package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackAddCheakoutViewDetail;

public interface MlbackAddCheakoutViewDetailMapper {

    int insert(MlbackAddCheakoutViewDetail record);

    MlbackAddCheakoutViewDetail selectByPrimaryKey(Integer addcheakoutviewdetailId);

    int updateByPrimaryKeySelective(MlbackAddCheakoutViewDetail record);

    int updateByPrimaryKey(MlbackAddCheakoutViewDetail record);
    
    
    
    
    int insertSelective(MlbackAddCheakoutViewDetail record);
    
    int deleteByPrimaryKey(Integer addcheakoutviewdetailId);

	List<MlbackAddCheakoutViewDetail> selectMlbackAddCheakoutViewDetailByTimeAndActnum(
			MlbackAddCheakoutViewDetail mlbackAddCheakoutViewDetail);
}