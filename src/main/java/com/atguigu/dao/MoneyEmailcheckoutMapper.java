package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MoneyEmailcheckout;

public interface MoneyEmailcheckoutMapper {
    int deleteByPrimaryKey(Integer emailcheckoutId);

    int insert(MoneyEmailcheckout record);

    int insertSelective(MoneyEmailcheckout record);

    MoneyEmailcheckout selectByPrimaryKey(Integer emailcheckoutId);

    int updateByPrimaryKeySelective(MoneyEmailcheckout record);

    int updateByPrimaryKey(MoneyEmailcheckout record);
    
	List<MoneyEmailcheckout> selectMoneyEmailcheckoutByPayId(MoneyEmailcheckout moneyEmailcheckout);
	
	List<MoneyEmailcheckout> selectMoneyEmailcheckoutByPayOId(MoneyEmailcheckout moneyEmailcheckout);

	List<MoneyEmailcheckout> selectMoneyEmailcheckoutByPayOnameAndOid(MoneyEmailcheckout moneyEmailcheckout);
}