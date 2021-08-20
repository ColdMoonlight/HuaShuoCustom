package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlPaypalShipAddress;
import com.atguigu.dao.MlPaypalShipAddressMapper;


@Service
public class MlPaypalShipAddressService {
	
	
	@Autowired
	MlPaypalShipAddressMapper mlPaypalShipAddresstMapper;
	
	/**
	 * @author Shinelon
	 * @param MlPaypalShipAddress
	 * @exception add方法用户信息是否存在
	 * 
	 * */
	public int insertSelective(MlPaypalShipAddress mlPaypalShipAddress) {
		int intReslut = mlPaypalShipAddresstMapper.insertSelective(mlPaypalShipAddress);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 删除本条信息
	 * 
	 * */
	public int deleteByPrimaryKey(int shippingaddressId) {
		int  intReslut = mlPaypalShipAddresstMapper.deleteByPrimaryKey(shippingaddressId);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新本条信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlPaypalShipAddress mlPaypalShipAddress) {
		int  intReslut = mlPaypalShipAddresstMapper.updateByPrimaryKeySelective(mlPaypalShipAddress);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param MlPaypalShipAddress
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlPaypalShipAddress> selectMlPaypalShipAddressByPayinfoid(MlPaypalShipAddress mlPaypalShipAddress) {
		List<MlPaypalShipAddress>  mlPaypalShipAddressRes = mlPaypalShipAddresstMapper.selectMlPaypalShipAddressByPayinfoid(mlPaypalShipAddress);
		return mlPaypalShipAddressRes;
	}
	/**
	 * @author Shinelon
	 * @param MlPaypalShipAddress
	 * @exception 查看用户信息是否存在
	 * */
	public List<MlPaypalShipAddress> selectMlPaypalShipAddressByEmail(MlPaypalShipAddress mlPaypalShipAddressifOldReq) {
		List<MlPaypalShipAddress>  mlPaypalShipAddressRes = mlPaypalShipAddresstMapper.selectMlPaypalShipAddressByEmail(mlPaypalShipAddressifOldReq);
		return mlPaypalShipAddressRes;
	}

}
