package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackCaclPay;

public interface MlbackCaclPayMapper {

    int insert(MlbackCaclPay record);

    MlbackCaclPay selectByPrimaryKey(Integer tbMlbackCaclpayId);

    int updateByPrimaryKey(MlbackCaclPay record);
    
    int insertSelective(MlbackCaclPay record);
    
    int deleteByPrimaryKey(Integer tbMlbackCaclpayId);
    
    int updateByPrimaryKeySelective(MlbackCaclPay record);

	List<MlbackCaclPay> selectMlbackCaclPayByParams(MlbackCaclPay mlbackCaclPay);
}