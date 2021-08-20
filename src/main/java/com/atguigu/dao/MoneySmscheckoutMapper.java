package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MoneySmscheckout;

public interface MoneySmscheckoutMapper {
    int deleteByPrimaryKey(Integer smscheckoutId);

    int insert(MoneySmscheckout record);

    int insertSelective(MoneySmscheckout record);

    MoneySmscheckout selectByPrimaryKey(Integer smscheckoutId);

    int updateByPrimaryKeySelective(MoneySmscheckout record);

    int updateByPrimaryKey(MoneySmscheckout record);

	List<MoneySmscheckout> selectMoneySmscheckoutByPayId(MoneySmscheckout moneySmscheckout);
	
	List<MoneySmscheckout> selectMoneySmscheckoutByPayOId(MoneySmscheckout moneySmscheckout);

	List<MoneySmscheckout> selectMoneySmscheckoutByPayOnameAndOid(MoneySmscheckout moneySmscheckout);
	
}