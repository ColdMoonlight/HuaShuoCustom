package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.CustomerWholesale;
import com.atguigu.dao.CustomerWholesaleMapper;

@Service
public class CustomerWholesaleService {
	
	@Autowired
	CustomerWholesaleMapper customerWholesaleMapper;
	
	/**1.0
	 * @author Shinelon
	 * @param CustomerWholesale
	 * @exception add方法用户信息是否存在
	 * */
	public int insertSelective(CustomerWholesale CustomerWholesale) {
		int intReslut = customerWholesaleMapper.insertSelective(CustomerWholesale);
		return intReslut;
	}
	
	/**2.0
	 * @author
	 * @param 
	 * @exception 删除本条信息
	 * */
	public int deleteByPrimaryKey(int CatalogId) {
		int  intReslut = customerWholesaleMapper.deleteByPrimaryKey(CatalogId);
		return intReslut;
	}
	
	/**3.0
	 * @author
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(CustomerWholesale CustomerWholesale) {
		int  intReslut = customerWholesaleMapper.updateByPrimaryKeySelective(CustomerWholesale);
		return intReslut;
	}
	
	/**4.0
	 * @author
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<CustomerWholesale> selectMenuCustomerWholesaleGetAll() {
		List<CustomerWholesale>  CustomerWholesaleList = customerWholesaleMapper.selectCustomerWholesaleGetAll();
		return CustomerWholesaleList;
	}
	
	/**5.0
	 * @author Shinelon
	 * @param CustomerWholesale
	 * @exception 查看用户信息是否存在
	 * */
	public List<CustomerWholesale> selectCustomerWholesaleById(CustomerWholesale CustomerWholesale) {
		List<CustomerWholesale>  CustomerWholesaleList = customerWholesaleMapper.selectCustomerWholesaleById(CustomerWholesale);
		return CustomerWholesaleList;
	}

	public List<CustomerWholesale> selectCustomerWholesaleBackSearch(CustomerWholesale CustomerWholesale) {
		List<CustomerWholesale>  CustomerWholesaleList = customerWholesaleMapper.selectCustomerWholesaleBackSearch(CustomerWholesale);
		return CustomerWholesaleList;
	}

}
