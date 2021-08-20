package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackOrderStateEmail;

public interface MlbackOrderStateEmailMapper {

    int insert(MlbackOrderStateEmail record);

    MlbackOrderStateEmail selectByPrimaryKey(Integer orderstateemailId);

    int updateByPrimaryKey(MlbackOrderStateEmail record);
    
    //插入新类目
    int insertSelective(MlbackOrderStateEmail record);
    //删除本条
    int deleteByPrimaryKey(Integer orderstateemailId);
    //更新本条
    int updateByPrimaryKeySelective(MlbackOrderStateEmail record);
    //通过id查询本条
    List<MlbackOrderStateEmail> selectMlbackOrderStateEmailById(MlbackOrderStateEmail example);
    //查询全部
	List<MlbackOrderStateEmail> selectMlbackOrderStateEmailGetAll();
	//通过id查询本条
	List<MlbackOrderStateEmail> selectMlbackOrderStateEmailByName(MlbackOrderStateEmail mlbackAbandonPurchase);
}