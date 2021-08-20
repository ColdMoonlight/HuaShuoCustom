package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.CustomerWholesale;

public interface CustomerWholesaleMapper {

    int insert(CustomerWholesale record);

    CustomerWholesale selectByPrimaryKey(Integer wholesaleId);

    int updateByPrimaryKey(CustomerWholesale record);

	//-----------------------------------------------------------/
	int insertSelective(CustomerWholesale record);
	 
	int deleteByPrimaryKey(Integer wholesaleId);
	 
	int updateByPrimaryKeySelective(CustomerWholesale record);

	List<CustomerWholesale> selectCustomerWholesaleGetAll();

	List<CustomerWholesale> selectCustomerWholesaleById(CustomerWholesale customerWholesale);

	List<CustomerWholesale> selectCustomerWholesaleBackSearch(CustomerWholesale customerWholesale);
	 
}